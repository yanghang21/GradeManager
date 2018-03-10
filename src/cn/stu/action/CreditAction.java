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
//			List<Grade> grades = map.get(in);//�õ�ÿ��key�����value��ֵ
//			int sum=0; 
//			int count=0; 
//			int csum=0; 
//			int cres=0; 
//			for(Grade gra : grades)
//			 {	
//				cre.setName(gra.getStudent().getName());
//			 	cre.setBj(gra.getStudent().getBj());
//				switch (gra.getCourse()) {
//				case "����":
//				cre.setYw(gra.getGrade());
//				sum+=gra.getGrade();
//				count++;
//				csum+=(gra.getGrade()*cmap.get("����"));
//				cres+=cmap.get("����");
//				break;
//				case "��ѧ":cre.setSx(gra.getGrade());
//				sum+=gra.getGrade();
//				count++;
//				csum+=(gra.getGrade()*cmap.get("��ѧ"));
//				cres+=cmap.get("��ѧ");
//				break;
//				case "Ӣ��":cre.setYy(gra.getGrade());
//				sum+=gra.getGrade();
//				count++;
//				csum+=(gra.getGrade()*cmap.get("Ӣ��"));
//				cres+=cmap.get("Ӣ��");
//				break;
//				case "����":cre.setWl(gra.getGrade());
//				sum+=gra.getGrade();
//				count++;
//				csum+=(gra.getGrade()*cmap.get("����"));
//				cres+=cmap.get("����");
//				break;
//				case "��ѧ":cre.setHx(gra.getGrade());
//				sum+=gra.getGrade();
//				count++;
//				csum+=(gra.getGrade()*cmap.get("��ѧ"));
//				cres+=cmap.get("��ѧ");
//				break;
//				case "����":cre.setSw(gra.getGrade());
//				sum+=gra.getGrade();
//				count++;
//				csum+=(gra.getGrade()*cmap.get("����"));
//				cres+=cmap.get("����");
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
		//List<Credit> gradeList = creditService.order(model.getBj(), model.getCondition());// ѧ��LIst
		List<Credit> gradeList = new ArrayList<Credit>();// ѧ��LIst
		System.out.println(ids);
		String[] idArr = ids.split(",");
		Long tempt;
		for (String id : idArr) {
			tempt = Long.parseLong(id);
			Credit cre = creditService.getById(tempt);
			gradeList.add(cre);

		}
		
		String name = gradeList.get(0).getName();
		String[] tableHeader = { "ѧ��", "����", "�༶", "����","Ӣ��","����",
				"����","��ѧ","��ѧ","�ܷ�","ƽ����","ѧ�ּ�"};

		short cellNumber = (short) tableHeader.length;// �������
		HSSFWorkbook workbook = new HSSFWorkbook(); // ����һ��excel
		HSSFCell cell = null; // Excel����
		HSSFRow row = null; // Excel����
		HSSFCellStyle style = workbook.createCellStyle(); // ���ñ�ͷ������
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCellStyle style1 = workbook.createCellStyle(); // ������������
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font = workbook.createFont(); // ��������
		HSSFSheet sheet = workbook.createSheet("sheet1"); // ����һ��sheet
		HSSFHeader header = sheet.getHeader();// ����sheet��ͷ
		HSSFCellStyle snoStyle = workbook.createCellStyle();
		HSSFDataFormat format = workbook.createDataFormat();
		snoStyle.setDataFormat(format.getFormat("0"));
		
		try {
			if (gradeList.size() < 1) {
				header.setCenter("��������");
			} else {
				header.setCenter("ѧ�ּ���");
				row = sheet.createRow(0);
				row.setHeight((short) 400);
				for (int k = 0; k < cellNumber; k++) {
					cell = row.createCell(k);// ������0�е�k��
					cell.setCellValue(tableHeader[k]);// ���õ�0�е�k�е�ֵ
					sheet.setColumnWidth(k, 4000);// �����еĿ��
					font.setColor(HSSFFont.COLOR_NORMAL); // ���õ�Ԫ���������ɫ.
					font.setFontHeight((short) 350); // ���õ�Ԫ����߶�
					style1.setFont(font);// ����������
					cell.setCellStyle(style1);
				}

				for (int i = 0; i < gradeList.size(); i++) {
					Credit grade1 = gradeList.get(i);// ��ȡstudent����
					row = sheet.createRow((short) (i + 1));// ������i+1��
					row.setHeight((short) 400);// �����и�

					cell = row.createCell(0);// ������i+1�е�0��
					cell.setCellValue(grade1.getSno());// ���õ�i+1�е�0�е�ֵ
					cell.setCellStyle(snoStyle);// ���÷��

					if (grade1.getName() != null) {
						cell = row.createCell(1); // ������i+1�е�1��

						cell.setCellValue(grade1.getName());// ���õ�i+1�е�1�е�ֵ

						cell.setCellStyle(style); // ���÷��
					}
					// ��������ĺ�����Ļ�����ͬ���Ͳ���ע����
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

		HttpServletResponse response = null;// ����һ��HttpServletResponse����
		OutputStream out = null;// ����һ�����������
		try {

			response = ServletActionContext.getResponse();// ��ʼ��HttpServletResponse����
			out = response.getOutputStream();//
			String headerStr = "ѧ�ּ���";

			headerStr = new String(headerStr.getBytes("gb2312"), "ISO8859-1");// headerStringΪ����ʱת��
			response.setHeader("Content-disposition", "attachment; filename="
					+ headerStr + ".xls");// filename�����ص�xls���������������Ӣ��
			response.setContentType("application/msexcel;charset=UTF-8");// ��������
			response.setHeader("Pragma", "No-cache");// ����ͷ
			response.setHeader("Cache-Control", "no-cache");// ����ͷ
			response.setDateHeader("Expires", 0);// ��������ͷ
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
