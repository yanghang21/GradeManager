package cn.stu.utils;

import java.util.ArrayList;
import java.util.List;

import cn.stu.domain.Credit;
import cn.stu.domain.Grade;
import cn.stu.domain.GradeTmp;

public class CreditUtils {
	public static List<GradeTmp> getList(List<Credit> list){
		List<GradeTmp> tempList = new ArrayList<GradeTmp>();
		float _credit ;

		int[] cre = new int[31];
		for(int i=0;i<=30;i++){//初始化
			cre[i]=0;
		}
		for(Credit credit :list){//统计学分绩分布
			_credit=credit.getCredit();
			for(int i=1;i<=14;i++){
				if(_credit>=(i-1)*10&&_credit<i*10)
					cre[i]++;
			}
			if(_credit>=140&&_credit<=150)
				cre[15]++;
			
		}
		for(int i=1;i<=15;i++){
			GradeTmp gt = new GradeTmp();
			String seg = (i-1)*10 +"-"+ i*10;
			gt.setSegment(seg);
			gt.setNumber(cre[i]);
			tempList.add(gt);
		}
		return tempList;
	}

}
