package cn.stu.service;


import java.util.List;
import java.util.Map;

import cn.stu.base.BaseDao;
import cn.stu.domain.Credit;
import cn.stu.domain.Grade;
import cn.stu.domain.Query;


public interface CreditService extends BaseDao<Credit>{
	List<Credit> order(String bj,String condition);
	 Map<Long, List<Grade>> credits(String bj);
	 Map<String, Integer> getCredits();
	 Credit findBySno(long sno);
	 void rankSumAvgCre();
	 List<Credit> getBjCredits(String bj);
	 
	
}
