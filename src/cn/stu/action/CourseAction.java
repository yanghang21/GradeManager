package cn.stu.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.management.relation.Role;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.annotations.Source;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.stu.base.BaseAction;
import cn.stu.domain.Course;
import cn.stu.domain.Grade;
import cn.stu.domain.Query;
import cn.stu.domain.Teacher;
import cn.stu.domain.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class CourseAction extends BaseAction<Course> {
public CourseAction() {
	// TODO Auto-generated constructor stub
}

	public String addTeacher(){
		
		//courseService.addCourse(model);
		List<Teacher> teacherList = teacherService.findByCname(model.getCname());
		ActionContext.getContext().put("teacherList", teacherList);
		return "beforeAdd";
	}
	//关联教师
	public String conTeacher(){
		Course course = courseService.findByCname(model.getCname());
		Teacher teacher = teacherService.getById(model.getId());
		course.getTeachers().add(teacher);
		return listAll();
	}
	//移除教师
	public String removeTeacher(){
		Course course = courseService.findByCname(model.getCname());
		Teacher teacher = teacherService.getById(model.getId());
		Set<Teacher> teachers = course.getTeachers();
		if(teachers.contains(teacher)){
			teachers.remove(teacher);
		};
		return listAll();
	}
	public String add(){
	
	courseService.addCourse(model);;
		return "addSuccess";
	}

	public String delete(){
		courseService.delete(model.getId());
		return listAll();
	}
	public String listAll(){
		List<Course> courseList= courseService.findAll();
		ActionContext.getContext().put("courseList", courseList);
		
		return "list";
	}
	public String getCourse(){
		User user = (User) ActionContext.getContext().getSession().get("user");
		String course = courseService.getCourse(user.getNumber());
		ActionContext.getContext().getSession().put("teaCourse",course);
		return "queryByClass";
	}
	

   }
	
	
	
	
	
	
	

	
	
	


