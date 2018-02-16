package cn.dao;

import java.util.List;

import cn.entity.User;

public interface UserDao {
	void saveUser(User user);//新增用户
	void deleteUser(User user);//删除用户
	void updateUser(User user);//更新用户信息

	User findUserByName(String name);
	User findUserById(String id);
	List<User> findAllUsers();
}
