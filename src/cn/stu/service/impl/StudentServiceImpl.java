package cn.stu.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.jca.cci.core.support.CciDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;










import com.opensymphony.xwork2.ActionContext;

import cn.stu.base.BaseDao;
import cn.stu.base.BaseDaoImpl;
import cn.stu.domain.Course;
import cn.stu.domain.StudentInfo;
import cn.stu.domain.User;



@Service
@Transactional
public class StudentServiceImpl extends BaseDaoImpl<StudentInfo> implements cn.stu.service.StudentService {

	
	@Override
	public void addStudent(StudentInfo stu) {
		// TODO Auto-generated method stub
		getSession().save(stu);
	}

	@Override
	public StudentInfo findBySno(Long sno) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				StudentInfo stu = (StudentInfo) getSession().createQuery(//
						"FROM StudentInfo u WHERE u.sno=?")//
						.setParameter(0, sno)//
						.uniqueResult();
		return stu;
	}

	@Override
	public int countStudent(String bj) {
		// TODO Auto-generated method stub
		
		return getSession().createQuery(//
				"FROM Credit u WHERE u.bj=?")//
				.setParameter(0, bj)//
				.list().size();
	}
	@Override
	public void selectCourse(StudentInfo stu,Course course) {
		// TODO Auto-generated method stub
		
		stu.getCourses().add(course);
		course.getStudents().add(stu);
		getSession().update(stu);
		getSession().update(course);
	}

	@Override
	public void removeCourse(StudentInfo stu, Course cor) {
		// TODO Auto-generated method stub
		stu.getCourses().remove(cor);
		cor.getStudents().remove(stu);
		getSession().update(stu);
		getSession().update(cor);
		
	}


}
