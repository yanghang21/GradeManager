package cn.stu.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;









import cn.stu.base.BaseDao;
import cn.stu.base.BaseDaoImpl;
import cn.stu.domain.StudentInfo;
import cn.stu.domain.Teacher;
import cn.stu.domain.User;



@Service
@Transactional
public class TeacherServiceImpl extends BaseDaoImpl<Teacher> implements cn.stu.service.TeacherService {

	@Override
	public List<Teacher> findByCname(String cname) {
		// TODO Auto-generated method stub
		
		List<Teacher> teachers  = (List<Teacher>) getSession().createQuery(//
				"FROM Teacher u WHERE u.course=?")//
				.setParameter(0, cname)//
				.list();
		return teachers;
	}

	
	

}
