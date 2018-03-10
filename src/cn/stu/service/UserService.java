package cn.stu.service;

import java.util.List;

import cn.stu.base.BaseDao;
import cn.stu.domain.User;



public interface UserService extends BaseDao<User> {

	/**
	 * ���ݵ�¼���������ѯ�û�
	 * 
	 * @param loginName
	 * @param password
	 *            ��������
	 * @return
	 */
	User findByLoginNameAndPassword(String loginName, String password);
    void addUser(User user);
    void logOut();
    void check(String username);
    void checkNum(String role,String num);
    void delete(String loginName);
}
