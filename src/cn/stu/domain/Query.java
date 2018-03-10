package cn.stu.domain;

public class Query {
private long id;
private String bj;
private String course;
private long sno;
private int grade;
private String manyGrade;
private String order;





public String getManyGrade() {
	return manyGrade;
}
public void setManyGrade(String manyGrade) {
	this.manyGrade = manyGrade;
}
public long getSno() {
	return sno;
}
public void setSno(long sno) {
	this.sno = sno;
}
public int getGrade() {
	return grade;
}
public void setGrade(int grade) {
	this.grade = grade;
}
public String getBj() {
	return bj;
}
public void setBj(String bj) {
	this.bj = bj;
}
public String getCourse() {
	return course;
}
public void setCourse(String course) {
	this.course = course;
}

public String getOrder() {
	return order;
}
public void setOrder(String order) {
	this.order = order;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}


}
