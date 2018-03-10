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
	dataset.addValue(500, "JAVA图书", "J2SE类");
	dataset.addValue(100, "JAVA图书", "J2ME类");
	dataset.addValue(900, "JAVA图书", "J2EE类");
	return dataset;
}
public static JFreeChart createChart(){
	JFreeChart chart = ChartFactory.createBarChart3D(
			"JAVA图书销量统计", "java图书", "销量（本）", 
			createDataSet(), PlotOrientation.HORIZONTAL
			, false, false, false);
	return chart;
}
}
