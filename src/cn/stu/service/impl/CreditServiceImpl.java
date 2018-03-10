package cn.stu.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.enterprise.inject.Model;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
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
public class CreditServiceImpl extends BaseDaoImpl<Credit> implements
		cn.stu.service.CreditService {

	@Override
	public List<Credit> order(String bj, String condition) {
		// TODO Auto-generated method stub
		List<Credit> credits = null;
		credits = (List<Credit>) getSession().createQuery(//
				"FROM Credit u where u.bj=? ORDER BY " + condition + " DESC")//
				.setParameter(0, bj).list();
		if (credits.size() == 0) {
			credits = (List<Credit>) getSession().createQuery(//
					"FROM Credit  ORDER BY " + condition + " DESC")//
					.list();
		}
		return credits;
	}

	@Override
	public Map<Long, List<Grade>> credits(String bj) {
		// TODO Auto-generated method stub
		// Linked遍历时有序,hash无序
		Map<Long, List<Grade>> map = new LinkedHashMap<>();
		List<Grade> grades = null;
		List<StudentInfo> stus = (List<StudentInfo>) getSession().createQuery(//
				"FROM StudentInfo u where u.bj=? ORDER BY SNO ASC")//
				.setParameter(0, bj).list();
		if (stus.size() == 0) {
			stus = (List<StudentInfo>) getSession().createQuery(//
					"FROM StudentInfo  ORDER BY SNO ASC")//
					.list();
		}
		for (StudentInfo stu : stus) {
			grades = (List<Grade>) getSession().createQuery(//
					"FROM Grade u WHERE u.student.sno=? ORDER BY COURSE DESC")//
					.setParameter(0, stu.getSno())//
					.list();
			map.put(stu.getSno(), grades);
		}
		return map;
	}

	@Override
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

	@Override
	public Credit findBySno(long sno) {
		// TODO Auto-generated method stub
		Credit credit = null;
		credit = (Credit) getSession().createQuery(//
				"FROM Credit u WHERE u.sno=?")//
				.setParameter(0, sno)//
				.uniqueResult();
		if (credit == null) {
			credit = new Credit(-1, -1, -1, -1, -1, -1);
			credit.setSno(sno);
			getSession().save(credit);
		}
		return credit;
	}

	@Override
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

	@Override
	public List<Credit> getBjCredits(String bj) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// Linked遍历时有序,hash无序
		List<Credit> grades = null;
		grades = (List<Credit>) getSession().createQuery(//
				"FROM Credit u where u.bj=? ORDER BY SNO ASC")//
				.setParameter(0, bj).list();
		if (grades.size() == 0) {
			grades = (List<Credit>) getSession().createQuery(//
					"FROM Credit  ORDER BY SNO ASC")//
					.list();
		}
		
		return grades;
	}

}
