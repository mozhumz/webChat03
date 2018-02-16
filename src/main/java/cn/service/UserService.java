package cn.service;

import cn.entity.User;
import cn.service.NameException;
import cn.service.PasswordException;

public interface UserService {
	//登录功能
	User login(String name,String password)throws NameException,PasswordException;
	//注册功能
	User regist(String name,String nick,String password,String confirm)
	throws NameException,PasswordException;
	//检查令牌
	boolean checkToken(String userId, String token);
}
