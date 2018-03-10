package cn.stu.service;

import cn.stu.base.BaseDao;
import cn.stu.domain.Course;
import cn.stu.domain.StudentInfo;
import cn.stu.domain.User;



public interface StudentService extends BaseDao<StudentInfo> {

	/**
	 * ���ݵ�¼���������ѯ�û�
	 * 
	 * @param loginName
	 * @param password
	 *            ��������
	 * @return
	 */
	
    void addStudent(StudentInfo stu);
    StudentInfo findBySno(Long sno);
    int countStudent(String bj);
    void selectCourse(StudentInfo stu,Course course);
	void removeCourse(StudentInfo stu, Course cor);
}
