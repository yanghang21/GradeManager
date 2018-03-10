package cn.stu.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.management.relation.Role;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.annotations.Source;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.stu.base.BaseAction;
import cn.stu.domain.Credit;
import cn.stu.domain.Grade;
import cn.stu.domain.Query;
import cn.stu.domain.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

@Controller
@Scope("prototype")
public class GradeAction extends BaseAction<Query> {
	private String gradeInfo;
	private String ids;
	public String getClasses(){
		List<String> classes = gradeService.getClasses();
		ActionContext.getContext().put("classes", classes);
		return "class";
	}
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	// 修改
	public String beforeModify() {
		Grade grade = (Grade) queryService.findById(model.getId());
	
		ActionContext.getContext().put("grade", grade);
		return "modify";
	}

	public String modify() {
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		String flag = (String) session.getAttribute("flag");
		gradeService.modify(model);
		String bj = (String) ActionContext.getContext().getSession().get("queryCourse");
		if(flag=="all"){
			List<Grade> grades;
			if(!bj.equals("")){
		       grades = queryService.findByClass(model.getBj(),
				model.getCourse());
			}else{
				 grades = queryService.findByCourse(model.getCourse());
			}
		ActionContext.getContext().put("grades", grades);		
		return "tolist";
		}
		else {
			List<Grade> grades = queryService.findBySno(model.getSno());
			Credit credit = creditService.findBySno(model.getSno());
			ActionContext.getContext().put("grades", grades);	
			ActionContext.getContext().put("credit", credit);	
			return "toperson";
		}
	}

	/** 成绩录入 */

	public String add() {

	String error = gradeService.addGrade(model);
		ActionContext.getContext().getSession().put("error", error);
		return "addSuccess";
	}

	public String delete() {
		String bj = (String) ActionContext.getContext().getSession().get("queryCourse");
		HttpSession session = ServletActionContext.getRequest().getSession();
		String flag = (String) session.getAttribute("flag");
		gradeService.delete(model);	
	
		
		if(flag=="all"){
			List<Grade> grades;
			if(!bj.equals("")){
			 grades = queryService.findByClass(model.getBj(),
					model.getCourse());}else{
						grades=queryService.findByCourse(model.getCourse());
					}
			ActionContext.getContext().put("grades", grades);		
			return "tolist";
			}
			else {
				List<Grade> grades = queryService.findBySno(model.getSno());
				Credit credit = creditService.findBySno(model.getSno());
				ActionContext.getContext().put("credit", credit);
				ActionContext.getContext().put("grades", grades);
				
				return "toperson";
			}
	}

	public String addMany() {
		String error = gradeService.addManyGrade(model);
		if (error == "")
			error = "添加成功！";

		ActionContext.getContext().getSession().put("error", error);

		// gradeService.addGrade(model);
		return "addSuccess";
	}


	/*
	 * 
	 * 导出到excel
	 */

	public String export() throws Exception {
		List<Grade> gradeList = new ArrayList<Grade>();// 学生LIst
		String[] idArr = ids.split(",");
		Long tempt;
		String name;
		for (String id : idArr) {
			tempt = Long.parseLong(id);
			Grade gra = queryService.findById(tempt);
			gradeList.add(gra);

		}
		name = gradeList.get(0).getStudent().getName();
		String[] tableHeader = { "学号", "姓名", "班级", "科目", "分数" };

		short cellNumber = (short) tableHeader.length;// 表的列数
		HSSFWorkbook workbook = new HSSFWorkbook(); // 创建一个excel
		HSSFCell cell = null; // Excel的列
		HSSFRow row = null; // Excel的行
		HSSFCellStyle style = workbook.createCellStyle(); // 设置表头的类型
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCellStyle style1 = workbook.createCellStyle(); // 设置数据类型
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font = workbook.createFont(); // 设置字体
		HSSFSheet sheet = workbook.createSheet("sheet1"); // 创建一个sheet
		HSSFHeader header = sheet.getHeader();// 设置sheet的头
		HSSFCellStyle snoStyle = workbook.createCellStyle();
		HSSFDataFormat format = workbook.createDataFormat();
		snoStyle.setDataFormat(format.getFormat("0"));
		try {
			if (gradeList.size() < 1) {
				header.setCenter("查无资料");
			} else {
				header.setCenter("成绩表");
				row = sheet.createRow(0);
				row.setHeight((short) 400);
				for (int k = 0; k < cellNumber; k++) {
					cell = row.createCell(k);// 创建第0行第k列
					cell.setCellValue(tableHeader[k]);// 设置第0行第k列的值
					sheet.setColumnWidth(k, 8000);// 设置列的宽度
					font.setColor(HSSFFont.COLOR_NORMAL); // 设置单元格字体的颜色.
					font.setFontHeight((short) 350); // 设置单元字体高度
					style1.setFont(font);// 设置字体风格
					cell.setCellStyle(style1);
				}

				for (int i = 0; i < gradeList.size(); i++) {
					Grade grade1 = gradeList.get(i);// 获取student对象
					row = sheet.createRow((short) (i + 1));// 创建第i+1行
					row.setHeight((short) 400);// 设置行高

					cell = row.createCell(0);// 创建第i+1行第0列
					cell.setCellValue(grade1.getStudent().getSno());// 设置第i+1行第0列的值
					cell.setCellStyle(snoStyle);// 设置风格

					if (grade1.getStudent().getName() != null) {
						cell = row.createCell(1); // 创建第i+1行第1列

						cell.setCellValue(grade1.getStudent().getName());// 设置第i+1行第1列的值

						cell.setCellStyle(style); // 设置风格
					}
					// 由于下面的和上面的基本相同，就不加注释了
					if (grade1.getStudent().getBj() != null) {
						cell = row.createCell(2);
						cell.setCellValue(grade1.getStudent().getBj());
						cell.setCellStyle(style);
					}
					if (grade1.getCourse() != null) {
						cell = row.createCell(3);
						cell.setCellValue(grade1.getCourse());
						cell.setCellStyle(style);
					}
					if (grade1.getGrade() != 0) {
						cell = row.createCell(4);
						cell.setCellValue(grade1.getGrade());
						cell.setCellStyle(style);
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpServletResponse response = null;// 创建一个HttpServletResponse对象
		OutputStream out = null;// 创建一个输出流对象
		try {

			response = ServletActionContext.getResponse();// 初始化HttpServletResponse对象
			out = response.getOutputStream();//
			String headerStr = "成绩单";

			headerStr = new String(headerStr.getBytes("gb2312"), "ISO8859-1");// headerString为中文时转码
			response.setHeader("Content-disposition", "attachment; filename="
					+ headerStr + ".xls");// filename是下载的xls的名，建议最好用英文
			response.setContentType("application/msexcel;charset=UTF-8");// 设置类型
			response.setHeader("Pragma", "No-cache");// 设置头
			response.setHeader("Cache-Control", "no-cache");// 设置头
			response.setDateHeader("Expires", 0);// 设置日期头
			workbook.write(out);
			out.flush();
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {

				if (out != null) {
					out.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return null;
	}

	public String getGradeInfo() {
		return gradeInfo;
	}

	public void setGradeInfo(String gradeInfo) {
		this.gradeInfo = gradeInfo;
	}

}
