package cn.stu.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import cn.stu.domain.Privilege;
import cn.stu.domain.User;

public class UserPrivilege {
	//辅导员权限初始化
public static void setAdminPrivilege(User user){
	java.util.Map<String, String> map1 = new HashMap<String, String>();
	map1.put("按班查询", "javascript:queryByClass();");
	map1.put("精确查询", "javascript:queryByPerson();");
	Privilege pri1 = new Privilege();
	pri1.setName("成绩查询 ");
	pri1.setPrivilegeMap(map1);
	
	java.util.Map<String, String> map2 = new HashMap<String, String>();
	map2.put("成绩录入", "inputGrade.jsp");
	map2.put("学生录入", "inputStudent.jsp");
	Privilege pri2 = new Privilege();
	pri2.setName("信息录入 ");
	pri2.setPrivilegeMap(map2);

	java.util.Map<String, String> map3 = new HashMap<String, String>();
	map3.put("用户列表", "user_listAll.action");
	map3.put("学生列表", "student_listAll.action");
	Privilege pri3 = new Privilege();
	pri3.setName("用户管理 ");
	pri3.setPrivilegeMap(map3);
	
	java.util.Map<String, String> map4 = new HashMap<String, String>();
	map4.put("课程列表", "course_listAll.action");
	map4.put("课程添加", "inputCourse.jsp");
	Privilege pri4 = new Privilege();
	pri4.setName("课程管理 ");
	pri4.setPrivilegeMap(map4);
	
	java.util.Map<String, String> map5 = new HashMap<String, String>();
	map5.put("学分绩", "javascript:queryCredits();");
	map5.put("班级对比", "grade_getClasses.action");
	Privilege pri5 = new Privilege();
	pri5.setName("成绩分析 ");
	pri5.setPrivilegeMap(map5);
	
	
	Set<Privilege> privileges = new HashSet<Privilege>();
	privileges.add(pri1);
	privileges.add(pri2);
	privileges.add(pri3);
	privileges.add(pri4);
	privileges.add(pri5);
	user.setPrivileges(privileges);
	
	
}
//教师权限初始化
public static void setTeacherPrivilege(User user) {
	// TODO Auto-generated method stub
	java.util.Map<String, String> map1 = new HashMap<String, String>();
	map1.put("任课查询", "course_getCourse.action");
	Privilege pri1 = new Privilege();
	pri1.setName("成绩查询 ");
	pri1.setPrivilegeMap(map1);
	Set<Privilege> privileges = new HashSet<Privilege>();
	privileges.add(pri1);
	user.setPrivileges(privileges);
	
}
//学生权限初始化
public static void setStudentPrivilege(User user) {
	// TODO Auto-generated method stub
	java.util.Map<String, String> map1 = new HashMap<String, String>();
	map1.put("精确查询", "javascript:queryByPerson();");
	Privilege pri1 = new Privilege();
	pri1.setName("成绩查询 ");
	pri1.setPrivilegeMap(map1);
	
	java.util.Map<String, String> map2 = new HashMap<String, String>();
	map2.put("选定课程", "student_beforeSelectCourse.action");
	Privilege pri2 = new Privilege();
	pri2.setName("选课系统 ");
	pri2.setPrivilegeMap(map2);
	
	Set<Privilege> privileges = new HashSet<Privilege>();
	privileges.add(pri1);
	privileges.add(pri2);
	user.setPrivileges(privileges);
	
}
}
