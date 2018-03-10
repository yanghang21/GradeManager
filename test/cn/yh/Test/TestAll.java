package cn.yh.Test;

import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.Map;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.stu.domain.Privilege;
import cn.stu.domain.User;





public class TestAll {

private ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

@Test
public void testBean() throws Exception {
	SessionFactory sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
	System.out.println(sessionFactory);
	Session session = sessionFactory.openSession();

	
	//3. ��������
	Transaction transaction = session.beginTransaction();
	
	//4. ִ���û�Ȩ�޳�ʼ������
//	java.util.Map<String, String> map = new HashMap<String, String>();
//	map.put("�����ѯd", "javascript:queryByClass();");
//	map.put("��ȷ��ѯr", "javascript:queryByPerson();");
//	Privilege pri = new Privilege();
//	pri.setName("�ɼ���ѯf ");
//	pri.setPrivilegeMap(map);
//	
//	User student = new User();
//	student.setLoginName("user1");
//	student.setPassword("21232f297a57a5a743894a0e4a801fc3");
//	student.setRole("ѧ��");
//	Set<Privilege> privileges = new HashSet<Privilege>();
//	privileges.add(pri);
//	student.setPrivileges(privileges);
//	
//	
//	
//	
//	session.save(pri);
//	session.save(student);
	User u1 = (User) session.get(User.class, 1l);
	
	java.util.Set<Privilege> m = u1.getPrivileges();
	Iterator<Privilege> it = m.iterator();
	while (it.hasNext()) {
       java.util.Map<String, String> map =  (java.util.Map<String, String>) it.next().getPrivilegeMap();
       for (String in : map.keySet()) {
    	                //map.keySet()���ص�������key��ֵ
    	                String str = map.get(in);//�õ�ÿ��key�����value��ֵ
    	                System.out.println(in + "     " + str);
    	            }
               
    }
	System.out.println(u1.getPrivileges());
	
	
	//5. �ύ���� 
	transaction.commit();
	
	//6. �ر� Session
	session.close();
}

}
