package cn.stu.service;


import java.util.List;

import cn.stu.base.BaseDao;
import cn.stu.domain.Credit;
import cn.stu.domain.Grade;
import cn.stu.domain.Query;


public interface GradeService extends BaseDao<Query>{
String addGrade(Query query);
String addManyGrade(Query query);
void delete(Query query);
void modify(Query query);
List<String> getClasses(); 




}
