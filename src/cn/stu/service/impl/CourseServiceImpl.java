package cn.stu.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;

import cn.stu.base.BaseDaoImpl;
import cn.stu.domain.Course;
import cn.stu.domain.Grade;
import cn.stu.domain.Query;
import cn.stu.domain.StudentInfo;
import cn.stu.domain.Teacher;

@Service
@Transactional
public class CourseServiceImpl extends BaseDaoImpl<Course> implements cn.stu.service.CourseService{

	@Override
	public void addCourse(Course course) {
		// TODO Auto-generated method stub
		String cname = course.getCname();
		Course cor = (Course) getSession().createQuery(//
				"FROM Course u WHERE u.cname=?")//
				.setParameter(0, cname)//
				.uniqueResult();
		if(cor!=null){
			cor.setCredit(course.getCredit());
			getSession().update(cor);
			System.out.println("update");
		}else{
			if(cname==null)
				return;
		getSession().save(course);
		}
		
	}



	@Override
	public Course findByCname(String cname) {
		// TODO Auto-generated method stub
		Course cor = (Course) getSession().createQuery(//
				"FROM Course u WHERE u.cname=?")//
				.setParameter(0, cname)//
				.uniqueResult();
		return cor;
	}



	@Override
	public String getCourse(String number) {
		// TODO Auto-generated method stub
		Teacher tea = (Teacher) getSession().createQuery(//
				"FROM Teacher u WHERE u.tno=?")//
				.setParameter(0, number)//
				.uniqueResult();
		
		return tea.getCourse();
	}

	
	

	


}
