package cn.stu.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.stu.base.BaseDaoImpl;
import cn.stu.domain.Course;
import cn.stu.domain.Credit;
import cn.stu.domain.Grade;
import cn.stu.domain.Query;
import cn.stu.domain.StudentInfo;
import freemarker.core.ReturnInstruction.Return;


@Service
@Transactional
public class QueryServiceImpl extends BaseDaoImpl<Query> implements cn.stu.service.QueryService{
	@SuppressWarnings("unchecked")
	public List<Grade> findByClass(String bj, String course) {
		List<Grade> grades = null;
		try {
			grades =  (List<Grade>) getSession().createQuery(//
					"FROM Grade u WHERE u.student.bj=? AND u.course=? ORDER BY u.student.sno ASC")//
					.setParameter(0, bj)//
					.setParameter(1, course)//
					.list();
			
		} catch (Exception e) {
			// TODO: handle exception
		   throw new RuntimeException("ÐÅÏ¢ÌîÐ´´íÎó!!!");
		}
			
		return grades;
	}
	
	public List<Grade> listByGrade(String bj,String course){
		List<Grade> grades = null;
		try {
			grades =  (List<Grade>) getSession().createQuery(//
					"FROM Grade u WHERE u.student.bj=? AND u.course=? ORDER BY GRADE DESC")//
					.setParameter(0, bj)//
					.setParameter(1, course)//
					.list();
			
		} catch (Exception e) {
			// TODO: handle exception
		   throw new RuntimeException("ÐÅÏ¢ÌîÐ´´íÎó!!!");
		}
			
		return grades;
	}
	public List<Grade> findBySno(Long sno){
		List<Grade> grades = null;
		try {
			grades =  (List<Grade>) getSession().createQuery(//
					"FROM Grade u WHERE u.student.sno=?")//
					.setParameter(0, sno)//
					.list();
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("ÐÅÏ¢ÌîÐ´´íÎó!!!");
		}
		
		return grades;
	}
	public Grade findById(Long id){
		Grade grade = null;
		try {
			grade =  (Grade) getSession().createQuery(//
					"FROM Grade u WHERE u.id=?")//
					.setParameter(0, id)//
					.uniqueResult();
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("ÐÅÏ¢ÌîÐ´´íÎó!!!");
		}
		
		return grade;
	}
	
	public List<Grade> findBySnoAndCourse(Long sno,String course){
		List<Grade> grades = null;
		try {
			grades =  (List<Grade>) getSession().createQuery(//
					"FROM Grade u WHERE u.student.sno=? AND u.course=?")//
					.setParameter(0, sno)//
					.setParameter(1, course)//
					.list();
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("ÐÅÏ¢ÌîÐ´´íÎó!!!");
		}
		
		return grades;
	}

	@Override
	public List<Grade> findByIds(Long[] ids) {
		// TODO Auto-generated method stub
		return (List<Grade>)getSession().createQuery(//
				"FROM Grade WHERE id IN (:ids)")//
				.setParameterList("ids", ids)//
				.list();

	}

	@Override
	public List<Grade> findByCourse(String course) {
		List<Grade> grades = null;
		try {
			grades =  (List<Grade>) getSession().createQuery(//
					"FROM Grade u WHERE  u.course=? ORDER BY u.student.sno ASC")//
					.setParameter(0, course)//
					.list();
			
		} catch (Exception e) {
			// TODO: handle exception
		   throw new RuntimeException("ÐÅÏ¢ÌîÐ´´íÎó!!!");
		}
			
		return grades;
	}

	@Override
	public List<Grade> listByGrades(String course) {
		List<Grade> grades = null;
		try {
			grades =  (List<Grade>) getSession().createQuery(//
					"FROM Grade u WHERE u.course=? ORDER BY GRADE DESC")//
					.setParameter(0, course)//
					.list();
			
		} catch (Exception e) {
			// TODO: handle exception
		   throw new RuntimeException("ÐÅÏ¢ÌîÐ´´íÎó!!!");
		}
			
		return grades;
	}

	@Override
	public float calculatePass(String bj, String course) {
		// TODO Auto-generated method stub
		List<Grade> grades = findByClass(bj, course);
		int count=0;
		int passers=0;
		for(Grade gra:grades)
		{
			count++;
			if(gra.getGrade()>60){
				passers++;
			}
			
		}
		if(count>0){
			return passers/(float)count;
		}
		return 0;
	}

	@Override
	public String calculateAvgHigh(String bj, String course) {
		// TODO Auto-generated method stub
		List<Grade> grades = listByGrade(bj, course);
		int high = grades.get(0).getGrade();
		int sum = 0;
		int count = 0;
		for(Grade gra:grades){
			sum+=gra.getGrade();
			count++;
		}
		if(count>0){
		int avg = sum/count;
		return String.valueOf(high)+","+avg;
		}
		return null;
	}
	


}
