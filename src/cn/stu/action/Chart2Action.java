package cn.stu.action;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.stu.base.BaseAction;
import cn.stu.domain.Course;
import cn.stu.domain.Credit;
import cn.stu.domain.Grade;
import cn.stu.domain.GradeTmp;
import cn.stu.domain.Query;
import cn.stu.domain.StudentInfo;
import cn.stu.domain.User;
import cn.stu.utils.CreditUtils;
import cn.stu.utils.GradeUtils;
import cn.stu.utils.MySpiderWebPlot;

@Controller
@Scope("prototype")
public class Chart2Action extends BaseAction<Query> {
	private String src;
	private String ids;
	private String snos;


	public String getSnos() {
		return snos;
	}
	public void setSnos(String snos) {
		this.snos = snos;
	}
	public String bar() throws Exception {
		// 添加数据
		List<Grade> lists = new ArrayList<Grade>();
		String[] tempIds = ids.split(",");
		for(String id : tempIds){
			Grade grade = queryService.findById(Long.parseLong(id));
			lists.add(grade);
		}
		List<GradeTmp> gts = GradeUtils.getList(lists);
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (GradeTmp gt : gts) {
			dataset.addValue(gt.getNumber(), "人数", gt.getSegment());
		}

		// 创建一个柱状图
		JFreeChart chart = ChartFactory.createBarChart3D(model.getBj()+"班"+model.getCourse()+"成绩统计图", "分数段",
				"人数", dataset, PlotOrientation.VERTICAL, true, false, false);
		chart.setTitle(new TextTitle(lists.get(0).getCourse()+"成绩条形图", new Font("黑体", Font.ITALIC, 22)));
		LegendTitle legend = chart.getLegend(0);
		legend.setItemFont(new Font("宋体", Font.BOLD, 14));
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		CategoryAxis categoryAxis = plot.getDomainAxis();
		categoryAxis.setLabelFont(new Font("宋体", Font.BOLD, 22));
		categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		categoryAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 18));
		NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
		numberAxis.setLabelFont(new Font("宋体", Font.BOLD, 22));

		 HttpServletResponse response=ServletActionContext.getResponse();
		 response.setContentType("image/");
		 ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1, chart,
		 520, 500, null);
		 response.getOutputStream().close();
//		HttpServletRequest request = ServletActionContext.getRequest();
//		String filename = ServletUtilities.saveChartAsJPEG(chart, 750, 300,
//				request.getSession());
//
//		src = request.getContextPath() + "/DisplayChart?filename=" + filename;
		return null;
	}
	public String spider() throws Exception {
		// 添加数据
		List<Grade> gradeList = new ArrayList<Grade>();// 学生LIst
		String[] idArr = ids.split(",");
		Long tempt;
		String name;
		for (String id : idArr) {
			tempt = Long.parseLong(id);
			Grade gra = queryService.findById(tempt);
			gradeList.add(gra);

		}
		DefaultCategoryDataset dataset =new DefaultCategoryDataset();
		for(Grade gra :gradeList){
			dataset.addValue(gra.getGrade(),"分数", gra.getCourse());;
		}
		MySpiderWebPlot spiderWebPlot = new MySpiderWebPlot(dataset);
		
		JFreeChart jFreeChart = new JFreeChart("个人单科成绩分布",TextTitle.DEFAULT_FONT,spiderWebPlot,false);
		LegendTitle legendTitle = new LegendTitle(spiderWebPlot);
		legendTitle.setPosition(RectangleEdge.BOTTOM);
		jFreeChart.addSubtitle(legendTitle);
		
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("image/");
		ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1, jFreeChart,
				520, 500, null);
		response.getOutputStream().close();
//		HttpServletRequest request = ServletActionContext.getRequest();
//		String filename = ServletUtilities.saveChartAsJPEG(chart, 750, 300,
//				request.getSession());
//
//		src = request.getContextPath() + "/DisplayChart?filename=" + filename;
		return null;
	}
	public String creditLine() throws Exception {
		// 添加数据
		StudentInfo stu = studentService.findBySno(Long.parseLong(snos));
		String bj = stu.getBj();
		List<Course> courses = courseService.findAll();	
		List<Grade> grades = queryService.findBySno(Long.parseLong(snos));
		DefaultCategoryDataset mDataset =new DefaultCategoryDataset();
		String[] temp;
		for(Grade gra:grades){
			String course = gra.getCourse();
			temp = queryService.calculateAvgHigh(bj, course).split(",");
			mDataset.addValue(Integer.parseInt(temp[0]), "班级最高分", course);
			mDataset.addValue(Integer.parseInt(temp[1]), "班级平均分", course);
			mDataset.addValue(gra.getGrade(), "个人成绩", course);
		}
		 StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		    mChartTheme.setLargeFont(new Font("黑体", Font.BOLD, 20));
		    mChartTheme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 15));
		    mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));
		    ChartFactory.setChartTheme(mChartTheme);	
		    JFreeChart mChart = ChartFactory.createLineChart(
		            "个人成绩折线图",//图名字
		            "科目",//横坐标
		            "分数",//纵坐标
		            mDataset,//数据集
		            PlotOrientation.VERTICAL,
		            true, // 显示图例
		            true, // 采用标准生成器 
		            false);// 是否生成超链接
		    CategoryPlot mPlot = (CategoryPlot)mChart.getPlot();
		    LineAndShapeRenderer lasp = (LineAndShapeRenderer) mPlot.getRenderer();
		    lasp.setBaseShapesVisible(true);//设置拐点可见
		    lasp.setSeriesStroke(0, new BasicStroke(3F));//折线加粗
		    mPlot.setBackgroundPaint(Color.white);
		    mPlot.setRangeGridlinePaint(Color.BLUE);//背景底部横虚线
		    mPlot.setOutlinePaint(Color.RED);//边界线
		    
		
	HttpServletResponse response=ServletActionContext.getResponse();
	response.setContentType("image/");
	ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1, mChart,
			450, 400, null);
	response.getOutputStream().close();
	return null;
	}
//	public String creditSpider() throws Exception {
//		// 添加数据
//		Credit credit = creditService.findBySno(Long.parseLong(snos));
//		DefaultCategoryDataset dataset =new DefaultCategoryDataset();
//		
//		dataset.addValue(credit.getYw(),"分数", "语文");
//		dataset.addValue(credit.getSx(),"分数", "数学");
//		dataset.addValue(credit.getYy(),"分数", "英语");
//		dataset.addValue(credit.getWl(),"分数", "物理");
//		dataset.addValue(credit.getHx(),"分数", "化学");
//		dataset.addValue(credit.getSw(),"分数", "生物");
//		dataset.addValue(credit.get_avg(),"分数", "平均分");
//		dataset.addValue(credit.getCredit(),"分数", "学分绩");
//		
//		MySpiderWebPlot spiderWebPlot = new MySpiderWebPlot(dataset);
//		JFreeChart jFreeChart = new JFreeChart("个人单科成绩分布",TextTitle.DEFAULT_FONT,spiderWebPlot,false);
//		LegendTitle legendTitle = new LegendTitle(spiderWebPlot);
//		legendTitle.setPosition(RectangleEdge.BOTTOM);
//		jFreeChart.addSubtitle(legendTitle);
//		
//		HttpServletResponse response=ServletActionContext.getResponse();
//		response.setContentType("image/");
//		ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1, jFreeChart,
//				450, 400, null);
//		response.getOutputStream().close();
//		return null;
//	}
	/**
	 * 各个班级的及格率图表
	 * @return
	 * @throws Exception
	 */
	public String spiderForClass() throws Exception {
		// 添加数据
	
	float pass=0;
	String bj=model.getBj();;
	DefaultCategoryDataset dataset =new DefaultCategoryDataset();
	List<Course> courses = courseService.findAll();	
	for(Course course:courses){
		pass = queryService.calculatePass(bj, course.getCname());
		System.out.println(pass+"--"+bj+course.getCname());
		dataset.addValue(pass,bj+"班各科及格率", course.getCname());
	}
	
	MySpiderWebPlot spiderWebPlot = new MySpiderWebPlot(dataset);
	JFreeChart jFreeChart = new JFreeChart(bj+"班各科及格率",TextTitle.DEFAULT_FONT,spiderWebPlot,false);
	LegendTitle legendTitle = new LegendTitle(spiderWebPlot);
	legendTitle.setPosition(RectangleEdge.BOTTOM);
	jFreeChart.addSubtitle(legendTitle);
	
	HttpServletResponse response=ServletActionContext.getResponse();
	response.setContentType("image/");
	ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1, jFreeChart,
			350,300, null);
	response.getOutputStream().close();
	return null;
	}
	/**
	 * 班级及格率显示同一图表上
	 * @return
	 * @throws Exception
	 */
	public String spiderForAllClass() throws Exception {
		// 添加数据
		List<String> classes = gradeService.getClasses();
		String bj;
		float pass=0;
		DefaultCategoryDataset dataset =new DefaultCategoryDataset();
		List<Course> courses = courseService.findAll();
		for(String obj :classes){
			bj=obj;
		for(Course course:courses){
			pass = queryService.calculatePass(bj, course.getCname());
			dataset.addValue(pass,bj+"班及格率", course.getCname());
		}
		}
		MySpiderWebPlot spiderWebPlot = new MySpiderWebPlot(dataset);
		JFreeChart jFreeChart = new JFreeChart("全年级各班各科及格率对比",TextTitle.DEFAULT_FONT,spiderWebPlot,false);
		LegendTitle legendTitle = new LegendTitle(spiderWebPlot);
		legendTitle.setPosition(RectangleEdge.BOTTOM);
		jFreeChart.addSubtitle(legendTitle);
		
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("image/");
		ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1, jFreeChart,
				350,300, null);
		response.getOutputStream().close();
		return null;
	}
	public String barForAllClass() throws Exception {
		// 添加数据
		List<String> classes = gradeService.getClasses();
		String bj;
		DefaultCategoryDataset dataset =new DefaultCategoryDataset();
		for(String obj :classes){
			bj=obj;
			List<Credit> credits = creditService.getBjCredits(bj);
			List<GradeTmp> gts = CreditUtils.getList(credits);
			for(GradeTmp gt : gts){
			dataset.addValue(gt.getNumber(), bj+"班学分绩", gt.getSegment());
			}
		
		}
//		List<Grade> lists = new ArrayList<Grade>();
//		String[] tempIds = ids.split(",");
//		for(String id : tempIds){
//			Grade grade = queryService.findById(Long.parseLong(id));
//			lists.add(grade);
//		}
//		List<GradeTmp> gts = GradeUtils.getList(lists);
//		DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
//		for (GradeTmp gt : gts) {
//			dataset1.addValue(gt.getNumber(), "人数", gt.getSegment());
//		}

		// 创建一个柱状图
		JFreeChart chart = ChartFactory.createBarChart3D(model.getBj()+"班"+model.getCourse()+"成绩统计图", "分数段",
				"人数", dataset, PlotOrientation.VERTICAL, true, false, false);
		chart.setTitle(new TextTitle("全年级学分绩对比图", new Font("黑体", Font.ITALIC, 22)));
		LegendTitle legend = chart.getLegend(0);
		legend.setItemFont(new Font("宋体", Font.BOLD, 14));
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		CategoryAxis categoryAxis = plot.getDomainAxis();
		categoryAxis.setLabelFont(new Font("宋体", Font.BOLD, 22));
		categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		categoryAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 18));
		NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
		numberAxis.setLabelFont(new Font("宋体", Font.BOLD, 22));

		 HttpServletResponse response=ServletActionContext.getResponse();
		 response.setContentType("image/");
		 ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1, chart,
		 1155, 460, null);
		 response.getOutputStream().close();
//		HttpServletRequest request = ServletActionContext.getRequest();
//		String filename = ServletUtilities.saveChartAsJPEG(chart, 750, 300,
//				request.getSession());
//
//		src = request.getContextPath() + "/DisplayChart?filename=" + filename;
		return null;
	}
	/**
	 * 为班级生成折线图
	 * @return
	 * @throws Exception
	 */
	public String lineForAllClass() throws Exception {
		// 添加数据
		List<String> classes = gradeService.getClasses();
		String bj;
		DefaultCategoryDataset dataset =new DefaultCategoryDataset();
		for(String obj :classes){
			bj=obj;
			List<Credit> credits = creditService.getBjCredits(bj);
			List<GradeTmp> gts = CreditUtils.getList(credits);
			for(GradeTmp gt : gts){
			dataset.addValue(gt.getNumber(), bj+"班学分绩", gt.getSegment());
			}
		
		}
		 StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		    mChartTheme.setLargeFont(new Font("黑体", Font.BOLD, 20));
		    mChartTheme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 15));
		    mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));
		    ChartFactory.setChartTheme(mChartTheme);	
		    JFreeChart mChart = ChartFactory.createLineChart(
		            "全年级学分绩折线图",//图名字
		            "分数段",//横坐标
		            "人数",//纵坐标
		            dataset,//数据集
		            PlotOrientation.VERTICAL,
		            true, // 显示图例
		            true, // 采用标准生成器 
		            false);// 是否生成超链接
		    CategoryPlot mPlot = (CategoryPlot)mChart.getPlot();
		    LineAndShapeRenderer lasp = (LineAndShapeRenderer) mPlot.getRenderer();
		    lasp.setBaseShapesVisible(true);//设置拐点可见
		    lasp.setSeriesStroke(0, new BasicStroke(3F));//折线加粗
		    mPlot.setBackgroundPaint(Color.white);
		    mPlot.setRangeGridlinePaint(Color.BLUE);//背景底部横虚线
		    mPlot.setOutlinePaint(Color.RED);//边界线
		   
		    CategoryAxis domainAxis = mPlot.getDomainAxis();   
	        domainAxis.setLowerMargin(0.01);// 左边距 边框距离
	        domainAxis.setUpperMargin(0.06);// 右边距 边框距离,防止最后边的一个数据靠近了坐标轴。
	        domainAxis.setMaximumCategoryLabelLines(10);
	        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);// 横轴 lable 的位置 横轴上的 Lable 45度倾斜 DOWN_45
		
	HttpServletResponse response=ServletActionContext.getResponse();
	response.setContentType("image/");
	ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1, mChart,
			1155, 460, null);
	response.getOutputStream().close();
	return null;
	}
	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
}
