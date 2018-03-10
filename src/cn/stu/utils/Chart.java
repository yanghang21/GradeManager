package cn.stu.utils;

import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class Chart {
public static CategoryDataset createDataSet(){
	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	dataset.addValue(500, "JAVAͼ��", "J2SE��");
	dataset.addValue(100, "JAVAͼ��", "J2ME��");
	dataset.addValue(900, "JAVAͼ��", "J2EE��");
	return dataset;
}
public static JFreeChart createChart(){
	JFreeChart chart = ChartFactory.createBarChart3D(
			"JAVAͼ������ͳ��", "javaͼ��", "����������", 
			createDataSet(), PlotOrientation.HORIZONTAL
			, false, false, false);
	return chart;
}
}
