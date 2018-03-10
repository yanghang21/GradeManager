package cn.stu.service;


import java.util.List;
import java.util.Map;

import cn.stu.base.BaseDao;
import cn.stu.domain.Credit;
import cn.stu.domain.Grade;
import cn.stu.domain.Query;
import cn.stu.domain.StudentInfo;


public interface QueryService extends BaseDao<Query>{
 public List<Grade> findByClass(String bj,String course);
 public List<Grade> findByCourse(String course);
 List<Grade> listByGrade(String bj,String course);
 List<Grade> listByGrades(String course);
 List<Grade> findBySnoAndCourse(Long sno,String course);
 List<Grade> findBySno(Long sno);
 List<Grade> findByIds(Long[] ids);
 Grade findById(Long id);
 float calculatePass(String bj,String course);
 String calculateAvgHigh(String bj,String course);

}
