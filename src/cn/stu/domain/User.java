package cn.stu.domain;

import java.util.Set;




public class User {
	private long id;
	private String number;//��ʦ�н�ʦ��ţ�ѧ����ѧ�ţ�����Աû��
	private String loginName; // ��¼��
	private String password; // ����
	private String role;//�û���ɫ
	private Set<Privilege> privileges;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Set<Privilege> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	

}
