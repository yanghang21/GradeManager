package cn.stu.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import cn.stu.domain.User;
import cn.stu.service.CourseService;
import cn.stu.service.CreditService;
import cn.stu.service.GradeService;
import cn.stu.service.QueryService;
import cn.stu.service.StudentService;
import cn.stu.service.TeacherService;
import cn.stu.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public abstract class BaseAction<T> extends ActionSupport implements
		ModelDriven<T> {

	// =============== ModelDriven��֧�� ==================

	protected T model;

	public BaseAction() {
		try {
			// ͨ�������ȡmodel����ʵ����
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			// ͨ�����䴴��model��ʵ��
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}

	// =============== Serviceʵ�������� ==================

	@Resource
	protected UserService userService;
	@Resource
	protected QueryService queryService;
	@Resource
	protected StudentService studentService;
	@Resource
	protected GradeService gradeService;
	@Resource
	protected CourseService courseService;
	@Resource
	protected TeacherService teacherService;
	@Resource
	protected CreditService creditService;

	/**
	 * ��ȡ��ǰ��¼���û�
	 * 
	 * @return
	 */

	protected User getCurrentUser() {
		return (User) ActionContext.getContext().getSession().get("user");
	}

}
