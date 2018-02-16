package cn.service;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.dao.UserDao;
import cn.entity.User;
import cn.util.Salt;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;
	// 登录检查
	public User login(String name, String password) throws NameException, PasswordException {
		if(name==null||name.isEmpty()){
			throw new NameException("用户名不能为空");
		}
		if(password==null||password.isEmpty()){
			throw new PasswordException("密码不能为空");
		}
		User user=userDao.findUserByName(name);
		if(user==null){
			throw new NameException("用户名错误");
		}
		//将接收到的密码password加密与数据库中的密码比较
		if(user.getPassword().equals(Salt.md5Salt(password))){
			//每次登录设置令牌token:踢人下线
			String token=UUID.randomUUID().toString();
			user.setToken(token);
			userDao.updateUser(user);
			return user;
		}
		throw new PasswordException("密码错误");
	}

	// 注册检查
	public User regist(String name, String nick, String password, String confirm)
			throws NameException, PasswordException {
		if(name==null||name.isEmpty()){
			throw new NameException("用户名不能为空");
		}
		if(password==null||password.isEmpty()){
			throw new PasswordException("密码不能为空");
		}
		if(!password.equals(confirm)){
			throw new PasswordException("密码不一致");
		}
		if(nick==null||nick.isEmpty()){
			nick=name;
		}
		User user=userDao.findUserByName(name);
		if(user!=null){
			throw new NameException("该用户名已注册");
		}
		String userId=UUID.randomUUID().toString();
		user=new User(userId,name,Salt.md5Salt(password),nick,"");
		userDao.saveUser(user);
		return user;
	}
	//检查当前浏览器中的令牌与数据库中的是否一致
	public boolean checkToken(String userId, String token) {
		return token.equals(userDao.findUserById(userId).getToken());
	}

}
