package cn.entity;
/**
 * 聊天室中的消息类,
 * 将聊天内容ContentVo.content以JSON字符串的方式转发送给浏览器端
 */

import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

public class Message {
	private String alert; // 某某人进入了聊天室/退出聊天室

	private List<String> names; // 储存所有聊天人姓名

	private String content; // 发消息的内容

	private String username;// 发消息人的用户名

	
	public static Gson gson = new Gson();
	public Message() {
		super();
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setContent(String name, String content) {
		this.content = name + " " + new Date().toLocaleString() + "<br/>" + content;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	



	
}
