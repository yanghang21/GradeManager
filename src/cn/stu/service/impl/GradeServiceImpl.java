package cn.stu.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.enterprise.inject.Model;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;

import cn.stu.base.BaseDaoImpl;
import cn.stu.domain.Course;
import cn.stu.domain.Credit;
import cn.stu.domain.Grade;
import cn.stu.domain.Query;
import cn.stu.domain.StudentInfo;

@Service
@Transactional
public class GradeServiceImpl extends BaseDaoImpl<Query> implements
		cn.stu.service.GradeService {

	@Override
	public String addGrade(Query query) {
		// TODO Auto-generated method stub
		StudentInfo stu = (StudentInfo) getSession().createQuery(//
				"FROM StudentInfo u WHERE u.sno=?")//
				.setParameter(0, query.getSno())//
				.uniqueResult();
		if(stu==null){//!isCourse(query.getCourse())
			return "学生不存在，添加失败";
		}
		//
		Grade gra = (Grade) getSession().createQuery(//
				"FROM Grade u WHERE u.student.sno=? AND u.course=?")//
				.setParameter(0, query.getSno())//
				.setParameter(1, query.getCourse()).uniqueResult();
		if (gra != null) {
			gra.setGrade(query.getGrade());
			getSession().update(gra);//
			return "成绩更新成功";
		}
		
		Grade grade = new Grade();
		grade.setCourse(query.getCourse());
		grade.setGrade(query.getGrade());
		grade.setStudent(stu);

		stu.getGrades().add(grade);
		getSession().save(grade);
		getSession().save(stu);
		updateRank(grade);
		return "添加成功";
	}

	@Override
	public String addManyGrade(Query query) {
		// TODO Auto-generated method stub
		String error = "";
		String manyGrade = query.getManyGrade().trim();
		String[] str = manyGrade.split("[\\r\\n]+");// 匹配换行
		int tempGra = -1;
		for (String grade1 : str) {
			String[] ele = grade1.split("\\s+");
			if (!isNumeric(ele[2])) {
				error += ele[0].trim() + "的" + "[" + ele[1] + "]成绩格式有误!"
						+ "\\r\\n";
				continue;
			}
			tempGra = Integer.parseInt(ele[2]);

			try {
				StudentInfo stu = (StudentInfo) getSession().createQuery(//
						"FROM StudentInfo u WHERE u.sno=?")//
						.setParameter(0, Long.parseLong(ele[0].trim()))//
						.uniqueResult();
			
				if (stu == null || tempGra == -1 || ele[1] == null
						|| tempGra > 150 || tempGra < 0) {//||!isCourse(ele[1])
					error += ele[0].trim() + "的" + "[" + ele[1] + "]添加失败!"
							+ "\\r\\n";

					System.out.println(ele[0] + "--" + ele[1] + "--" + ele[2]+"============");
					continue;
				}
				Grade gra = (Grade) getSession().createQuery(//
						"FROM Grade u WHERE u.student.sno=? AND u.course=?")//
						.setParameter(0, Long.parseLong(ele[0].trim()))//
						.setParameter(1, ele[1]).uniqueResult();
				if (gra != null && tempGra >= 0 && tempGra <= 150) {
					gra.setGrade(tempGra);
					getSession().update(gra);//
					updateRank(gra);
					error += ele[0].trim() + "的" + "[" + ele[1] + "]更新成功!"
							+ "\\r\\n";
					continue;
				}

				Grade grade = new Grade();
				grade.setCourse(ele[1]);
				grade.setGrade(Integer.parseInt(ele[2]));
				grade.setStudent(stu);
				
				stu.getGrades().add(grade);
				getSession().save(grade);
				
				getSession().save(stu);
				updateRank(grade);
				error += ele[0].trim() + "的" + "[" + ele[1] + "]添加成功!"
						+ "\\r\\n";

			} catch (RuntimeException e) {
				// TODO: handle exception
				error += ele[0] + "的" + ele[1] + "添加失败!" + "\\r\\n";
				System.out.println(e);

			}

		}
		return error;

	}

	public void delete(Query query) {
		Grade gra = (Grade) getSession().createQuery(//
				"FROM Grade u WHERE u.id=?")//
				.setParameter(0, query.getId())//
				.uniqueResult();
		getSession().delete(gra);
		
		String bj = gra.getStudent().getBj();
		String course = gra.getCourse();
//		boolean flag = false;
//		List<String> classes = getClasses();
//		for(String cls :classes){
//			if(cls.equals(course))
//				flag=true;
//		}
		//非系统课程不更新排名
		if(!isCourse(course))
			return;
		Credit credit =  (Credit) getSession().createQuery(//
				"FROM Credit u WHERE u.sno=?")//
				.setParameter(0, gra.getStudent().getSno())//
				.uniqueResult();
		String nCourse="";
		switch (course) {
		case "语文":
			nCourse="yw";
			credit.setYw(-1);
			credit.setYwR(0);
			break;
		case "数学":
			nCourse="sx";
			credit.setSx(-1);
			credit.setSxR(0);
			break;
		case "英语":
			nCourse="yy";
			credit.setYy(-1);
			credit.setYyR(0);
			break;
		case "物理":
			nCourse="wl";
			credit.setWl(-1);
			credit.setWlR(0);
			break;
		case "化学":
			nCourse="hx";
			credit.setHx(-1);
			credit.setHxR(0);
			break;
		case "生物":
			nCourse="sw";
			credit.setSw(-1);
			credit.setSwR(0);
			break;

		default:
			break;
		}//这里删除不太一样
		
		getSession().update(credit);
		upCourseRank(nCourse, bj);
		switch (course) {
		case "语文":
			credit.setYwR(0);
			break;
		case "数学":
			credit.setSxR(0);
			break;
		case "英语":
			credit.setYyR(0);
			break;
		case "物理":
			credit.setWlR(0);
			break;
		case "化学":
			credit.setHxR(0);
			break;
		case "生物":
			credit.setSwR(0);
			break;

		default:
			break;
		}//这里删除不太一样，需要将sum,avg,credit也重新计算出来		
		getSession().update(credit);
		updateSumAvgCre(gra);
		rankSumAvgCre();
		
		

		query.setBj(bj);
		query.setCourse(course);
	}

	@Override
	public void modify(Query query) {
		// TODO Auto-generated method stub
		Grade grade = (Grade) getSession().createQuery(//
				"FROM Grade u WHERE u.student.sno=? AND u.course=?")//
				.setParameter(0, query.getSno())//
				.setParameter(1, query.getCourse())//
				.uniqueResult();

		grade.setGrade(query.getGrade());
		getSession().update(grade);
		updateRank(grade);

	}

	// 判断数字
	public boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 根据成绩增删改时更新该成绩的credit表的班级排名
	 * 
	 * @param grade
	 */
	public void updateRank(Grade grade) {
		if(!isCourse(grade.getCourse()))
			return;
		// TODO Auto-generated method stub
		Credit credit = null;
		credit = (Credit) getSession().createQuery(//
				"FROM Credit u WHERE u.sno=?")//
				.setParameter(0, grade.getStudent().getSno())//
				.uniqueResult();
		if (credit == null) {
			credit = new Credit(-1, -1, -1, -1, -1, -1);
			credit.setSno(grade.getStudent().getSno());
			getSession().save(credit);
		}
		int gra = grade.getGrade();
		String course = grade.getCourse().trim();
		String bj = grade.getStudent().getBj().trim();
		switch (course) {
		case "语文":
			credit.setYw(gra);
			getSession().update(credit);
			updateSumAvgCre(grade);
			upCourseRank("yw", bj);
			break;
		case "数学":
			credit.setSx(gra);
			getSession().update(credit);
			updateSumAvgCre(grade);
			upCourseRank("sx", bj);
			break;
		case "英语":
			credit.setYy(gra);
			getSession().update(credit);
			updateSumAvgCre(grade);
			upCourseRank("yy", bj);
			break;
		case "物理":
			credit.setWl(gra);
			getSession().update(credit);
			updateSumAvgCre(grade);
			upCourseRank("wl", bj);
			break;
		case "化学":
			credit.setHx(gra);
			getSession().update(credit);
			updateSumAvgCre(grade);
			upCourseRank("hx", bj);
			break;
		case "生物":
			credit.setSw(gra);
			getSession().update(credit);
			updateSumAvgCre(grade);
			upCourseRank("sw", bj);
			break;

		default:
			break;
		}
		rankSumAvgCre();
		

	}
/**
 * 根据课程班级排名
 * @param course
 * @param bj
 */
	public void upCourseRank(String course, String bj) {
	
		List<Credit> credits = (List<Credit>) getSession().createQuery(//
				"FROM Credit WHERE bj=" + bj + " ORDER BY " + course + " DESC")//
				.list();
		for (int i = 1; i <= credits.size(); i++) {
			switch (course) {
			case "yw":
				credits.get(i - 1).setYwR(i);
				break;
			case "sx":
				credits.get(i - 1).setSxR(i);
				break;
			case "yy":
				credits.get(i - 1).setYyR(i);
				break;
			case "wl":
				credits.get(i - 1).setWlR(i);
				break;
			case "hx":
				credits.get(i - 1).setHxR(i);
				break;
			case "sw":
				credits.get(i - 1).setSwR(i);
				break;

			default:
				break;
			}
			getSession().update(credits.get(i - 1));

		}
	}
/**
 * 成绩的增删改需要更新credit中的sum,avg,credit
 * @param grade
 */
	public void updateSumAvgCre(Grade grade) {
		List<Grade> grades = (List<Grade>) getSession().createQuery(//
				"FROM Grade u WHERE u.student.sno=?")//
				.setParameter(0, grade.getStudent().getSno())//
				.list();
		Credit cre = (Credit) getSession().createQuery(//
				"FROM Credit u WHERE u.sno=?")//
				.setParameter(0, grade.getStudent().getSno())//
				.uniqueResult();
		Map<String, Integer> cmap = getCredits();
		int sum = 0;
		int count = 0;
		int csum = 0;
		int cres = 0;
		for (Grade gra : grades) {
			cre.setName(gra.getStudent().getName());
			cre.setBj(gra.getStudent().getBj());
			switch (gra.getCourse()) {
			case "语文":
				sum += gra.getGrade();
				count++;
				csum += (gra.getGrade() * cmap.get("语文"));
				cres += cmap.get("语文");
				break;
			case "数学":
				sum += gra.getGrade();
				count++;
				csum += (gra.getGrade() * cmap.get("数学"));
				cres += cmap.get("数学");
				break;
			case "英语":
				sum += gra.getGrade();
				count++;
				csum += (gra.getGrade() * cmap.get("英语"));
				cres += cmap.get("英语");
				break;
			case "物理":
				sum += gra.getGrade();
				count++;
				csum += (gra.getGrade() * cmap.get("物理"));
				cres += cmap.get("物理");
				break;
			case "化学":
				sum += gra.getGrade();
				count++;
				csum += (gra.getGrade() * cmap.get("化学"));
				cres += cmap.get("化学");
				break;
			case "生物":
				sum += gra.getGrade();
				count++;
				csum += (gra.getGrade() * cmap.get("生物"));
				cres += cmap.get("生物");
				break;

			default:
				break;
			}

		}
		if (count != 0){
			cre.set_avg(sum / count);
		    cre.set_sum(sum);
		}
		if(count==0){
			cre.set_avg(0);
			cre.set_sum(0);
			cre.setCredit(0);
		}
		    

		if (cres != 0)
			cre.setCredit(new BigDecimal(csum / (float) cres).setScale(3,
					BigDecimal.ROUND_HALF_UP).floatValue());
		if(cres==0){
			cre.set_avg(0);
			cre.set_sum(0);
			cre.setCredit(0);
		}
		getSession().update(cre);
	}

	public Map<String, Integer> getCredits() {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<>();
		List<Course> courses = null;
		courses = (List<Course>) getSession().createQuery(//
				"FROM  Course u")//
				.list();
		for (Course cor : courses) {
			map.put(cor.getCname(), cor.getCredit());
		}

		return map;
	}
	public void rankSumAvgCre() {
		// TODO Auto-generated method stub
		List<Credit> credits = null;
		credits = (List<Credit>) getSession().createQuery(//
				"FROM Credit  ORDER BY _sum  DESC")//
				.list();
		for (int i = 1; i <= credits.size(); i++) {
			credits.get(i - 1).set_sumR(i);
			getSession().update(credits.get(i - 1));
		}
		credits = (List<Credit>) getSession().createQuery(//
				"FROM Credit  ORDER BY _avg  DESC")//
				.list();
		for (int i = 1; i <= credits.size(); i++) {
			credits.get(i - 1).set_avgR(i);
			getSession().update(credits.get(i - 1));
		}
		credits = (List<Credit>) getSession().createQuery(//
				"FROM Credit  ORDER BY credit  DESC")//
				.list();
		for (int i = 1; i <= credits.size(); i++) {
			credits.get(i - 1).setCreditR(i);
			getSession().update(credits.get(i - 1));
		}

	}
	/**
	 * 判断课程是否是系统中存在的课程
	 * @param course
	 * @return
	 */
	public boolean isCourse(String course){
		List<Course> courses = (List<Course>) getSession().createQuery(//
				"FROM  Course u")//
				.list();
		for(Course cor : courses){
			if(course.trim().equals(cor.getCname().trim())){
				return true;
			}
		}
		return false;
	}

	@Override
	public List<String> getClasses() {
		// TODO Auto-generated method stub
		String hql = "select distinct(u.bj) FROM StudentInfo u ";
		List<String> classes = (List<String>)getSession().createQuery(//
				hql)//
				.list();
		return classes;
	}



}
