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

	
	//3. 开启事务
	Transaction transaction = session.beginTransaction();
	
	//4. 执行用户权限初始化操作
//	java.util.Map<String, String> map = new HashMap<String, String>();
//	map.put("按班查询d", "javascript:queryByClass();");
//	map.put("精确查询r", "javascript:queryByPerson();");
//	Privilege pri = new Privilege();
//	pri.setName("成绩查询f ");
//	pri.setPrivilegeMap(map);
//	
//	User student = new User();
//	student.setLoginName("user1");
//	student.setPassword("21232f297a57a5a743894a0e4a801fc3");
//	student.setRole("学生");
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
    	                //map.keySet()返回的是所有key的值
    	                String str = map.get(in);//得到每个key多对用value的值
    	                System.out.println(in + "     " + str);
    	            }
               
    }
	System.out.println(u1.getPrivileges());
	
	
	//5. 提交事务 
	transaction.commit();
	
	//6. 关闭 Session
	session.close();
}

}
