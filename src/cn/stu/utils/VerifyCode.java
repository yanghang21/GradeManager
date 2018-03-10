package cn.stu.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.http.client.BufferingClientHttpRequestFactory;

public class VerifyCode
{
private int width=70;//设置图片缓冲区的宽
private int height=35;//设置图片缓冲区的高
private  Random r = new Random();//生成随机数字
private String[] fontNames={"宋 体 华 文 棺 体 黑 体 微 软 雅 黑 " };
private Color bgColor = new Color(255,255,255);//创建图片的白色背景
private String codes = "23456789qwepoiuyrtjklhgfdsazxcvmbn";//可供选择的随机文字
private String text ;//图片上的文本
public BufferedImage getImage(){
	BufferedImage image = createImage();
	Graphics g = image.getGraphics();
	StringBuilder sb = new StringBuilder();
	for (int i =0 ;i<4;i++){
		String str = randomChar()+"";//生成随机字符
		sb.append(str);
		g.setFont(randomFont());
		g.setColor(randomColor());
		g.drawString(str,i*width/4, height-5);
	}
	this.text = sb.toString();
	drawLine(image);//干扰直线
	return image;
}

public String getText() {
	return this.text;
}
/*
 * 保存图片到指定输出流
 */
public static void output(BufferedImage image,OutputStream out) throws IOException {
	ImageIO.write(image, "JPEG", out);
}
private void drawLine(BufferedImage image) {
	// TODO Auto-generated method stub
	Graphics g = image.getGraphics();
	for(int i=0;i<3;i++){
		int x1 = r.nextInt(width);
		int y1 = r.nextInt(height);
		int x2 = r.nextInt(width);
		int y2 = r.nextInt(height);
		g.setColor(randomColor());
		g.drawLine(x1, y1, x2, y2);
	}
	
}
private Color randomColor() {
	// TODO Auto-generated method stub
	int red = r.nextInt(150);
	int green = r.nextInt(150);
	int blue = r.nextInt(150);
	return new Color(red, green, blue);
}
private Font randomFont() {
	// TODO Auto-generated method stub
	int index = r.nextInt(fontNames.length);
	int style = r.nextInt(4);
	int size = r.nextInt(4)+24;
	
	return new Font(fontNames[index], style, size);
}
private char randomChar() {
	// TODO Auto-generated method stub
	int index = r.nextInt(codes.length());
	char y = codes.charAt(index);
	return y;
}
private BufferedImage createImage() {
	// TODO Auto-generated method stub
	BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	Graphics  g = image.getGraphics();
	g.setColor(bgColor);
	g.fillRect(0, 0, width, height);
	return image;
}
}