package cn.stu.service;

import cn.stu.base.BaseDao;
import cn.stu.domain.Course;
import cn.stu.domain.StudentInfo;
import cn.stu.domain.User;



public interface StudentService extends BaseDao<StudentInfo> {

	/**
	 * 根据登录名与密码查询用户
	 * 
	 * @param loginName
	 * @param password
	 *            明文密码
	 * @return
	 */
	
    void addStudent(StudentInfo stu);
    StudentInfo findBySno(Long sno);
    int countStudent(String bj);
    void selectCourse(StudentInfo stu,Course course);
	void removeCourse(StudentInfo stu, Course cor);
}
