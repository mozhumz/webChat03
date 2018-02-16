package test;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.dao.UserDao;
import cn.entity.User;
import cn.service.UserService;
import cn.util.Salt;

public class TestCase {
	ApplicationContext ctx;

	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext("spring-context.xml");
	}

	@Test
	public void testFindByName() {
		UserDao dao = ctx.getBean("userDao", UserDao.class);
		User user = dao.findUserByName("tom");
		System.out.println(user);
	}

	@Test
	public void testSalt() {
		System.out.println(Salt.md5Salt("123"));
	}

	@Test
	public void testLogin() {
		UserService service = ctx.getBean("userService", UserService.class);
		User user = service.login("tom", "123");
		System.out.println(user);
	}

	@Test
	public void testRegist() {
		UserService service = ctx.getBean("userService", UserService.class);
		User user = service.regist("joe", null, "123", "123");
		System.out.println(user);
	}

	@Test
	public void testUpdateUser() {
		UserService service = ctx.getBean("userService", UserService.class);
		User user = service.regist("mary", null, "123", "123");
		System.out.println(user);
		String token = UUID.randomUUID().toString();
		user.setToken(token);
		UserDao dao = ctx.getBean("userDao", UserDao.class);
		dao.updateUser(user);
		System.out.println(user);
	}
	@Test
	public void testEmpty(){
		String str="";
		System.out.println(str.isEmpty());
	}
}
