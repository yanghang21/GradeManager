package cn.stu.service;


import java.util.List;

import cn.stu.base.BaseDao;
import cn.stu.domain.Course;
import cn.stu.domain.Grade;
import cn.stu.domain.Query;


public interface CourseService extends BaseDao<Course>{
void addCourse(Course course);
Course findByCname(String cname);
String getCourse(String number);
}
