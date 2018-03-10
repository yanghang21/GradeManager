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

	// =============== ModelDriven的支持 ==================

	protected T model;

	public BaseAction() {
		try {
			// 通过反射获取model的真实类型
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			// 通过反射创建model的实例
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}

	// =============== Service实例的声明 ==================

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
	 * 获取当前登录的用户
	 * 
	 * @return
	 */

	protected User getCurrentUser() {
		return (User) ActionContext.getContext().getSession().get("user");
	}

}
