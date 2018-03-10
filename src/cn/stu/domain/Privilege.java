package cn.stu.domain;


import java.util.Map;
import java.util.Set;

public class Privilege {
	private long id;
	private String name;
	private Map<String, String> privilegeMap;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, String> getPrivilegeMap() {
		return privilegeMap;
	}
	public void setPrivilegeMap(Map<String, String> privilegeMap) {
		this.privilegeMap = privilegeMap;
	}

	
	
}
