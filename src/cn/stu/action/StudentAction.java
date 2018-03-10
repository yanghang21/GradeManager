package cn.stu.action;

import java.util.HashSet;
import java.util.List;

import javax.management.relation.Role;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.annotations.Source;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.stu.base.BaseAction;
import cn.stu.domain.Course;
import cn.stu.domain.StudentInfo;
import cn.stu.domain.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class StudentAction extends BaseAction<StudentInfo> {

	public String beforeModify(){
	
		StudentInfo stu = studentService.getById(model.getId());
		ActionContext.getContext().put("student", stu);
		return "modify";
	}
	public String modify(){
		
		StudentInfo stu=studentService.findBySno(model.getSno());
		stu.setAge(model.getAge());
		stu.setBj(model.getBj());
		stu.setDept(model.getDept());
		stu.setName(model.getName());
		stu.setPhoneNum(model.getPhoneNum());
		stu.setSex(model.getSex());
		studentService.update(stu);
		List<StudentInfo> studentList = studentService.findAll();
		ActionContext.getContext().put("studentList", studentList);
		return "list";
	}
	
	public String add(){
		StudentInfo stu = studentService.findBySno(model.getSno());
		if(stu!=null){
			stu.setAge(model.getAge());
			stu.setBj(model.getBj());
			stu.setDept(model.getDept());
			stu.setName(model.getName());
			stu.setPhoneNum(model.getPhoneNum());
			stu.setSex(model.getSex());
			studentService.update(stu);
			return "addSuccess";
		}
		studentService.addStudent(model);
		return "addSuccess";
	}
	
	public String listAll(){
		List<StudentInfo> studentList = studentService.findAll();
		ActionContext.getContext().put("studentList", studentList);
		return "list";
	}
	
	public String delete(){
		studentService.delete(model.getId());
		return listAll();
	}
	public String selectCourse(){
		System.out.println(model.getId()+"-----s");
		Course cor = courseService.getById(model.getId());
		User user = (User) ActionContext.getContext().getSession().get("user");
		System.out.println(user.getNumber());
		StudentInfo stu = studentService.findBySno(Long.parseLong(user.getNumber()));
		System.out.println(stu.getPhoneNum());
		studentService.selectCourse(stu, cor);
		return beforeSelectCourse();
	}
	public String removeCourse(){
		System.out.println(model.getId()+"-----s");
		Course cor = courseService.getById(model.getId());
		User user = (User) ActionContext.getContext().getSession().get("user");
		System.out.println(user.getNumber());
		StudentInfo stu = studentService.findBySno(Long.parseLong(user.getNumber()));
		System.out.println(stu.getPhoneNum());
		studentService.removeCourse(stu, cor);
		return beforeSelectCourse();
	}
	public String beforeSelectCourse(){
		List<Course> courseList= courseService.findAll();
		ActionContext.getContext().put("courseList", courseList);
		return "courselist";
	}
	

	
	

}
