package cn.stu.domain;

public class Grade {
	private long id;
	private StudentInfo student;
	private String course;//¿Î³Ì
	private int grade;
	private int rank;
	
	
	
	public StudentInfo getStudent() {
		return student;
	}
	public void setStudent(StudentInfo student) {
		this.student = student;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	
}
