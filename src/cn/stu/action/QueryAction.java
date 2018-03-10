package cn.stu.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.stu.base.BaseAction;
import cn.stu.domain.Credit;
import cn.stu.domain.Grade;
import cn.stu.domain.Query;
import cn.stu.domain.StudentInfo;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class QueryAction extends BaseAction<Query> {
	private String ids;
	
	
	public String byPerson() {
		
		creditService.rankSumAvgCre();
		long sno = model.getSno();
		String course = model.getCourse();
		List<Grade> grades = null;
		Credit credit = null;
		if (sno == 0) {
			addFieldError("sno", "学号不能为空");
			return "personGrade";
		}
		StudentInfo stu = studentService.findBySno(sno);
		if (stu==null) {
			addFieldError("sno", "查询的学生不存在！");
			return "personGrade";
		}
		int count = studentService.countStudent(stu.getBj());
		credit = creditService.findBySno(sno);
		grades = queryService.findBySnoAndCourse(sno, course);
		if (grades.size()==0) {
			grades = queryService.findBySno(sno);
			System.out.println("query---------"+grades.size());
			if(grades.size()!=0){
				
				ActionContext.getContext().put("grades", grades);
				ActionContext.getContext().put("credit", credit);
				ActionContext.getContext().getSession().put("count", count);
			}
			return "personGrade";
				
		}
		
		ActionContext.getContext().put("grades", grades);
		ActionContext.getContext().put("credit", credit);
		ActionContext.getContext().getSession().put("count", count);
		return "personGrade";

	}

	// 班级成绩查询
	public String byClass() {
		String bj = model.getBj();
		String course = model.getCourse();
		ActionContext.getContext().getSession().put("queryCourse", bj);
		
		List<Grade> grades = queryService.findByClass(bj,
				course);
		if(grades.size()!=0){
		ActionContext.getContext().put("grades", grades);
		return SUCCESS;
		}
		grades = queryService.findByCourse(course);
		ActionContext.getContext().put("grades", grades);
		return SUCCESS;
	}

	/*
	 * 排名
	 */
	public String byGradeDesc() {

		String course = model.getCourse();
		String bj = model.getBj();
		System.out.println(course+"---"+bj);
		// String course = new
		// String(model.getCourse().getBytes("iso-8859-1"),"utf-8");
		// String bj = new String(model.getBj().getBytes("iso-8859-1"),"utf-8");
		List<Grade> grades = queryService.listByGrade(bj, course);
		if(grades.size()!=0){
		ActionContext.getContext().put("grades", grades);
		return SUCCESS;
		}
		grades = queryService.listByGrades(course);
		ActionContext.getContext().put("grades", grades);

		return SUCCESS;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
