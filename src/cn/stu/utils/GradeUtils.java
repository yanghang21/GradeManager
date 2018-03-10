package cn.stu.utils;

import java.util.ArrayList;
import java.util.List;

import cn.stu.domain.Grade;
import cn.stu.domain.GradeTmp;

public class GradeUtils {
	public static List<GradeTmp> getList(List<Grade> list){
		List<GradeTmp> tempList = new ArrayList<GradeTmp>();
		int grade ;
		int _gra20=0 ;
		int _gra40=0 ;
		int _gra60=0 ;
		int _gra80=0 ;
		int _gra100=0 ;
		int _gra120=0 ;
		int _gra140=0 ;
		int _gra150=0 ;
		for(Grade gra :list){
			grade=gra.getGrade();
			if(grade>=0&&grade<20){
				_gra20++;
			}
			if(grade>=20&&grade<40){
				_gra40++;
			}
			if(grade>=40&&grade<60){
				_gra60++;
			}
			if(grade>=60&&grade<80){
				_gra80++;
			}
			if(grade>=80&&grade<100){
				_gra100++;
			}
			if(grade>=100&&grade<120){
				_gra120++;
			}
			if(grade>=120&&grade<140){
				_gra140++;
			}
			if(grade>=140&&grade<=150){
				_gra150++;
			}
		}
		for(int i=0;i<8;i++){
			GradeTmp gt = new GradeTmp();
			switch (i) {
			case 0:
				gt.setNumber(_gra20);
				gt.setSegment("0-20");
				break;
			case 1:
				gt.setNumber(_gra40);
				gt.setSegment("20-40");
				break;
			case 2:
				gt.setNumber(_gra60);
				gt.setSegment("40-60");
				break;
			case 3:
				gt.setNumber(_gra80);
				gt.setSegment("60-80");
				break;
			case 4:
				gt.setNumber(_gra100);
				gt.setSegment("80-100");
				break;
			case 5:
				gt.setNumber(_gra120);
				gt.setSegment("100-120");
				break;
			case 6:
				gt.setNumber(_gra140);
				gt.setSegment("120-140");
				break;
			case 7:
				gt.setNumber(_gra150);
				gt.setSegment("140-150");
				break;

			default:
				break;
			}
			tempList.add(gt);
		}
		return tempList;
	}

}
