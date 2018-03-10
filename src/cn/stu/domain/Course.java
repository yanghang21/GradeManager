package cn.stu.domain;

import java.util.Set;

public class Course {
	private long id;
	private String cname;
	private int credit;//ังทึ
	private Set<StudentInfo> students;
	private Set<Teacher> teachers;
	
	
	
	public Set<StudentInfo> getStudents() {
		return students;
	}
	public void setStudents(Set<StudentInfo> students) {
		this.students = students;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public Set<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}
	
	
	
	
} 
