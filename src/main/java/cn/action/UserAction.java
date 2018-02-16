package cn.action;



import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.entity.User;
import cn.service.UserService;

@Controller
@Scope("prototype")
public class UserAction implements SessionAware,ServletResponseAware{
	private String username;
	private String password;
	private String nick;
	private String confirm;
	private Map<String,Object>session;
	@Resource
	private UserService userService;
	private HttpServletResponse response;
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String name) {
		this.username = name;
	}
	
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public HttpServletResponse getServletResponse() {
		return response;
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	//登录检查
	public String login(){
//		System.out.println("login...");
		try {
			User user=userService.login(username, password);
			Cookie token=new Cookie("token",user.getToken());
			Cookie userId=new Cookie("userId",user.getUserId());
			token.setPath("/");
			userId.setPath("/");
			response.addCookie(token);
			response.addCookie(userId);
			session.put("username", username);
			session.put("password", password);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			session.put("error", e.getMessage()+"请重新登录");
			return "error";
		}
		
	}
	public String regist(){
		System.out.println("regist...");
		try {
			userService.regist(username, nick, password, confirm);
			session.put("success", "注册成功，请登录");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			session.put("msg", e.getMessage()+"请重新输入");
			return "error";
		}
		
		
	}
	
}
