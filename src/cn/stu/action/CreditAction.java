package cn.stu.action;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.stu.base.BaseAction;
import cn.stu.domain.Credit;
import cn.stu.domain.Grade;
import cn.stu.domain.Query;
@Controller
@Scope("prototype")
public class CreditAction extends BaseAction<Credit>{
	private String condition;
	private String ids;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String credits(){
//		Map<Long,List<Grade>> map= creditService.credits(model.getBj());
//		Map<String, Integer> cmap = creditService.getCredits();
//		List<Credit> creList = new ArrayList<Credit>();
//		for (Long in : map.keySet()) {
//			Credit cre = creditService.findBySno(in);          
//			List<Grade> grades = map.get(in);//得到每个key多对用value的值
//			int sum=0; 
//			int count=0; 
//			int csum=0; 
//			int cres=0; 
//			for(Grade gra : grades)
//			 {	
//				cre.setName(gra.getStudent().getName());
//			 	cre.setBj(gra.getStudent().getBj());
//				switch (gra.getCourse()) {
//				case "语文":
//				cre.setYw(gra.getGrade());
//				sum+=gra.getGrade();
//				count++;
//				csum+=(gra.getGrade()*cmap.get("语文"));
//				cres+=cmap.get("语文");
//				break;
//				case "数学":cre.setSx(gra.getGrade());
//				sum+=gra.getGrade();
//				count++;
//				csum+=(gra.getGrade()*cmap.get("数学"));
//				cres+=cmap.get("数学");
//				break;
//				case "英语":cre.setYy(gra.getGrade());
//				sum+=gra.getGrade();
//				count++;
//				csum+=(gra.getGrade()*cmap.get("英语"));
//				cres+=cmap.get("英语");
//				break;
//				case "物理":cre.setWl(gra.getGrade());
//				sum+=gra.getGrade();
//				count++;
//				csum+=(gra.getGrade()*cmap.get("物理"));
//				cres+=cmap.get("物理");
//				break;
//				case "化学":cre.setHx(gra.getGrade());
//				sum+=gra.getGrade();
//				count++;
//				csum+=(gra.getGrade()*cmap.get("化学"));
//				cres+=cmap.get("化学");
//				break;
//				case "生物":cre.setSw(gra.getGrade());
//				sum+=gra.getGrade();
//				count++;
//				csum+=(gra.getGrade()*cmap.get("生物"));
//				cres+=cmap.get("生物");
//				break;
//
//				default:
//					break;
//				}
//				 
//			 }
//			if(count!=0)
//			cre.set_avg(sum/count);
//			cre.set_sum(sum);
//			
//			if(cres!=0)
//			cre.setCredit( new BigDecimal(csum/(float)cres).setScale(3, BigDecimal.ROUND_HALF_UP).floatValue()  );
//			creList.add(cre);
//			creditService.update(cre);
//		}
		List<Credit> creList = creditService.getBjCredits(model.getBj());
		ActionContext.getContext().put("grades", creList);         
		return "showCredit";
	}
	public String byCreditDesc(){
		List<Credit> grades = creditService.order(model.getBj(), model.getCondition());
		ActionContext.getContext().put("grades", grades);
		return "showCredit";
	}
	
	public String export() throws Exception {
		//List<Credit> gradeList = creditService.order(model.getBj(), model.getCondition());// 学生LIst
		List<Credit> gradeList = new ArrayList<Credit>();// 学生LIst
		System.out.println(ids);
		String[] idArr = ids.split(",");
		Long tempt;
		for (String id : idArr) {
			tempt = Long.parseLong(id);
			Credit cre = creditService.getById(tempt);
			gradeList.add(cre);

		}
		
		String name = gradeList.get(0).getName();
		String[] tableHeader = { "学号", "姓名", "班级", "语文","英语","生物",
				"物理","数学","化学","总分","平均分","学分绩"};

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
				header.setCenter("学分绩表");
				row = sheet.createRow(0);
				row.setHeight((short) 400);
				for (int k = 0; k < cellNumber; k++) {
					cell = row.createCell(k);// 创建第0行第k列
					cell.setCellValue(tableHeader[k]);// 设置第0行第k列的值
					sheet.setColumnWidth(k, 4000);// 设置列的宽度
					font.setColor(HSSFFont.COLOR_NORMAL); // 设置单元格字体的颜色.
					font.setFontHeight((short) 350); // 设置单元字体高度
					style1.setFont(font);// 设置字体风格
					cell.setCellStyle(style1);
				}

				for (int i = 0; i < gradeList.size(); i++) {
					Credit grade1 = gradeList.get(i);// 获取student对象
					row = sheet.createRow((short) (i + 1));// 创建第i+1行
					row.setHeight((short) 400);// 设置行高

					cell = row.createCell(0);// 创建第i+1行第0列
					cell.setCellValue(grade1.getSno());// 设置第i+1行第0列的值
					cell.setCellStyle(snoStyle);// 设置风格

					if (grade1.getName() != null) {
						cell = row.createCell(1); // 创建第i+1行第1列

						cell.setCellValue(grade1.getName());// 设置第i+1行第1列的值

						cell.setCellStyle(style); // 设置风格
					}
					// 由于下面的和上面的基本相同，就不加注释了
					if (grade1.getBj() != null) {
						cell = row.createCell(2);
						cell.setCellValue(grade1.getBj());
						cell.setCellStyle(style);
					}
					
						cell = row.createCell(3);
						cell.setCellValue(grade1.getYw());
						cell.setCellStyle(style);
					
						cell = row.createCell(4);
						cell.setCellValue(grade1.getYy());
						cell.setCellStyle(style);
						cell = row.createCell(5);
						cell.setCellValue(grade1.getSw());
						cell.setCellStyle(style);
						cell = row.createCell(6);
						cell.setCellValue(grade1.getWl());
						cell.setCellStyle(style);
						cell = row.createCell(7);
						cell.setCellValue(grade1.getSx());
						cell.setCellStyle(style);
						cell = row.createCell(8);
						cell.setCellValue(grade1.getHx());
						cell.setCellStyle(style);
						cell = row.createCell(9);
						cell.setCellValue(grade1.get_sum());
						cell.setCellStyle(style);
						cell = row.createCell(10);
						cell.setCellValue(grade1.get_avg());
						cell.setCellStyle(style);
						cell = row.createCell(11);
						cell.setCellValue(grade1.getCredit());
						cell.setCellStyle(style);
					

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
			String headerStr = "学分绩表";

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
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
}
