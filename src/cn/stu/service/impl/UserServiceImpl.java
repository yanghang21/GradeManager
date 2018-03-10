package cn.stu.service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;















import com.opensymphony.xwork2.ActionContext;

import cn.stu.base.BaseDao;
import cn.stu.base.BaseDaoImpl;
import cn.stu.domain.StudentInfo;
import cn.stu.domain.Teacher;
import cn.stu.domain.User;
import cn.stu.utils.UserPrivilege;
import freemarker.core.ReturnInstruction.Return;



@Service
@Transactional
public class UserServiceImpl extends BaseDaoImpl<User> implements cn.stu.service.UserService {

	public User findByLoginNameAndPassword(String loginName, String password) {
		// ʹ�������MD5ժҪ���жԱ�
		String md5Digest = DigestUtils.md5Hex(password);
		return (User) getSession().createQuery(//
				"FROM User u WHERE u.loginName=? AND u.password=?")//
				.setParameter(0, loginName)//
				.setParameter(1, md5Digest)//
				.uniqueResult();
	}
	
	public void addUser(User user){
		String role = user.getRole().trim();
		
		String password = user.getPassword();
		String md5Digest = DigestUtils.md5Hex(password);
		user.setPassword(md5Digest);
		switch (role) {
		case "����Ա":
			UserPrivilege.setAdminPrivilege(user);
			break;
		case "��ʦ":
			UserPrivilege.setTeacherPrivilege(user);
			break;
		case "ѧ��":
			UserPrivilege.setStudentPrivilege(user);
			break;
		
		default:
			break;
		}
		getSession().save(user);
	}
	
	
	public void logOut(){
		ActionContext.getContext().getSession().remove("user");
		
	}

	@Override
	public void check(String username) {
		HttpServletResponse response=  ServletActionContext.getResponse();  
		response.setContentType("text/html;charset=UTF-8");  
		if(username==""){
			try {
				response.getWriter().println("�û�����Ϊ��!");
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(username.length()<3){
			try {
				response.getWriter().println("�û����Ȳ���С��3!");
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		User user = (User) getSession().createQuery(//
				"FROM User u WHERE u.loginName=?")//
				.setParameter(0, username)//
				.uniqueResult();
	    if(user != null){  
	        //����  
	        try {
				response.getWriter().println("�û��Ѿ�����!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	    }else{  
	        try {
				response.getWriter().println("�û�������ʹ�á�");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	    }  
	}

	@Override
	public void delete(String loginName) {
		// TODO Auto-generated method stub
		User user = (User) getSession().createQuery(//
				"FROM User u WHERE u.loginName=?")//
				.setParameter(0, loginName)//
				.uniqueResult();
		getSession().delete(user);
		
	}

	@Override
	public void checkNum(String role, String num) {
		// TODO Auto-generated method stub
		HttpServletResponse response=  ServletActionContext.getResponse();  
		response.setContentType("text/html;charset=UTF-8");  
		if(role.equals("ѧ��")){
			List<StudentInfo> stu =getSession().createQuery(//
					"FROM StudentInfo u WHERE u.sno=?")//
					.setParameter(0,Long.parseLong(num))//
					.list();
			try {
			if(stu.size()==0){
					response.getWriter().println("false");
			}else{response.getWriter().println("true");}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			if(role.equals("��ʦ")){
				List<Teacher> tea =getSession().createQuery(//
						"FROM Teacher u WHERE u.tno=?")//
						.setParameter(0,num)//
						.list();
				try {
				if(tea.size()==0){
						response.getWriter().println("false");
			}else{response.getWriter().println("true");}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}	
}	


