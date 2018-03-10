package cn.stu.action;

import java.util.HashSet;
import java.util.List;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.annotations.Source;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.stu.base.BaseAction;
import cn.stu.domain.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
private String username;
private String _role;
private String num;
private String code;
	public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
	/** 登录 */
	public String login() throws Exception {
		/*
		 * 
		 *首先检验验证码
		 */
		if(!code.equalsIgnoreCase((String)ActionContext.getContext().getSession().get("text"))){
			addFieldError("login", "△验证码错误！");
			return "loginFailed";
		}
		User user = userService.findByLoginNameAndPassword(model.getLoginName(), model.getPassword());
		if (user == null) {
			addFieldError("login", "△用户名或密码不正确！");
			return "loginFailed";
		} else {
			// 登录用户
			ActionContext.getContext().getSession().put("user", user);
			return "loginSuccess";
		}
		
	}
	/**注册 */
	public String register(){
		return "register";
	}
	
	public String add(){
		userService.addUser(model);
		ActionContext.getContext().getSession().put("register", "注册成功");
		return null;
	}
	/**
	 * 注销
	 */
	public String logOut(){
		userService.logOut();
		return "logOut";
	}
	/**
	 * 注册查重

	 */
	public void check(){
		
		 userService.check(username);
	}
	/**
	 * 
	 * 用户列表
	 */
	/**
	 * 校验学号教师编号是否合法
	 * @return
	 */
	public void checkNum(){
		userService.checkNum(_role, num);
		 
	}
	public String listAll(){
		List<User> userList = userService.findAll();
		ActionContext.getContext().put("userList", userList);
		return "list";

	}
	/*
	 * 删除用户
	 */
	public String delete(){
		userService.delete(username);
		
		return listAll();
	}
	/*
	 * 初始化密码
	 */
	public String initialPassword(){
		User user = userService.getById(model.getId());
		user.setPassword("e10adc3949ba59abbe56e057f20f883e");
		userService.update(user);
		return listAll();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String get_role() {
		return _role;
	}
	public void set_role(String _role) {
		this._role = _role;
	}
	

	

	
	

}
