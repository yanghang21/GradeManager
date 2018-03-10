package cn.stu.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import cn.stu.domain.Privilege;
import cn.stu.domain.User;

public class UserPrivilege {
	//����ԱȨ�޳�ʼ��
public static void setAdminPrivilege(User user){
	java.util.Map<String, String> map1 = new HashMap<String, String>();
	map1.put("�����ѯ", "javascript:queryByClass();");
	map1.put("��ȷ��ѯ", "javascript:queryByPerson();");
	Privilege pri1 = new Privilege();
	pri1.setName("�ɼ���ѯ ");
	pri1.setPrivilegeMap(map1);
	
	java.util.Map<String, String> map2 = new HashMap<String, String>();
	map2.put("�ɼ�¼��", "inputGrade.jsp");
	map2.put("ѧ��¼��", "inputStudent.jsp");
	Privilege pri2 = new Privilege();
	pri2.setName("��Ϣ¼�� ");
	pri2.setPrivilegeMap(map2);

	java.util.Map<String, String> map3 = new HashMap<String, String>();
	map3.put("�û��б�", "user_listAll.action");
	map3.put("ѧ���б�", "student_listAll.action");
	Privilege pri3 = new Privilege();
	pri3.setName("�û����� ");
	pri3.setPrivilegeMap(map3);
	
	java.util.Map<String, String> map4 = new HashMap<String, String>();
	map4.put("�γ��б�", "course_listAll.action");
	map4.put("�γ����", "inputCourse.jsp");
	Privilege pri4 = new Privilege();
	pri4.setName("�γ̹��� ");
	pri4.setPrivilegeMap(map4);
	
	java.util.Map<String, String> map5 = new HashMap<String, String>();
	map5.put("ѧ�ּ�", "javascript:queryCredits();");
	map5.put("�༶�Ա�", "grade_getClasses.action");
	Privilege pri5 = new Privilege();
	pri5.setName("�ɼ����� ");
	pri5.setPrivilegeMap(map5);
	
	
	Set<Privilege> privileges = new HashSet<Privilege>();
	privileges.add(pri1);
	privileges.add(pri2);
	privileges.add(pri3);
	privileges.add(pri4);
	privileges.add(pri5);
	user.setPrivileges(privileges);
	
	
}
//��ʦȨ�޳�ʼ��
public static void setTeacherPrivilege(User user) {
	// TODO Auto-generated method stub
	java.util.Map<String, String> map1 = new HashMap<String, String>();
	map1.put("�οβ�ѯ", "course_getCourse.action");
	Privilege pri1 = new Privilege();
	pri1.setName("�ɼ���ѯ ");
	pri1.setPrivilegeMap(map1);
	Set<Privilege> privileges = new HashSet<Privilege>();
	privileges.add(pri1);
	user.setPrivileges(privileges);
	
}
//ѧ��Ȩ�޳�ʼ��
public static void setStudentPrivilege(User user) {
	// TODO Auto-generated method stub
	java.util.Map<String, String> map1 = new HashMap<String, String>();
	map1.put("��ȷ��ѯ", "javascript:queryByPerson();");
	Privilege pri1 = new Privilege();
	pri1.setName("�ɼ���ѯ ");
	pri1.setPrivilegeMap(map1);
	
	java.util.Map<String, String> map2 = new HashMap<String, String>();
	map2.put("ѡ���γ�", "student_beforeSelectCourse.action");
	Privilege pri2 = new Privilege();
	pri2.setName("ѡ��ϵͳ ");
	pri2.setPrivilegeMap(map2);
	
	Set<Privilege> privileges = new HashSet<Privilege>();
	privileges.add(pri1);
	privileges.add(pri2);
	user.setPrivileges(privileges);
	
}
}
