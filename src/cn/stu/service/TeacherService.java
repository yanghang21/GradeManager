package cn.stu.service;

import java.util.List;

import cn.stu.base.BaseDao;
import cn.stu.domain.StudentInfo;
import cn.stu.domain.Teacher;
import cn.stu.domain.User;



public interface TeacherService extends BaseDao<Teacher> {

    List<Teacher>  findByCname(String cname);
}
