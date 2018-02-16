package cn.socket;
/**
 * 聊天服务器类，处理浏览器端发送消息请求
 * 每一个用户请求都会实例化一个chatSocket
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;

import cn.dao.OneMsgDao;
import cn.dao.UserDao;
import cn.entity.ContentVo;
import cn.entity.Message;
import cn.entity.OneMsg;

@ServerEndpoint("/chat")
public class chatSocket {
	//由于该类不是spring管理，需要手动创建bean对象
	private ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");
	private String pwd;//当前用户的密码
	private String username;//当前聊天人的用户名
	private Session session;//当前聊天人的通信连接
	private static Gson gson=new Gson();
	//根据用户名找到对应的session
	private static Map<String,Session>map=new HashMap<String,Session>();
	private static List<Session> sessions = new ArrayList<Session>();//存储每个人的通信会话
	private static List<String> names = new ArrayList<String>();//存储每个用户的名字
	//打开通信连接
	@OnOpen
	public  void open(Session session) throws IOException {
		// 该session不是servlet中的session
		this.session=session;
		// 获取用户名密码字符串
		String param = session.getQueryString();
		// System.out.println(param);
		// 将用户名和密码拆分开
		String[] nameAndPwd = param.split("\\?");
		this.username = nameAndPwd[0].split("=")[1];
//		System.out.println(username);
		pwd = nameAndPwd[1].split("=")[1];
//		System.out.println(username + "," + pwd);
		//将某个用户的通讯会话和用户名添加到集合
		sessions.add(session);
		names.add(this.username);
		map.put(this.username,session);
		String msg = this.username + "进入聊天室<br/>";
		Message message = new Message();
		message.setAlert(msg);
		message.setNames(names);
		//将msg广播给所有在线用户
		broadcast(sessions,gson.toJson(message));
	}
	//用户退出时，清空该用户的数据
	@OnClose
	public void close(Session session) throws IOException{
		sessions.remove(session);
		names.remove(username);
		String msg = username + "退出聊天室<br/>";
		Message message = new Message();
		message.setAlert(msg);
		message.setNames(names);
		broadcast(sessions,gson.toJson(message));
	}
	//接收浏览器端消息(JSON字符串)
	@OnMessage
	public void message(Session session,String json){
		
		//创建聊天室中的消息对象
		Message message=new Message();
		//将json字符串转换成对象
		ContentVo vo=gson.fromJson(json, ContentVo.class);
		message.setUsername(this.username);
		
		if(vo.getType()==1){
			message.setContent(username, vo.getContent());
			//广播消息给所有人
			broadcast(sessions,gson.toJson(message));
		}else{
			//单聊
			message.setContent("<font color=red>"+username+"</font>", 
					"<font color=red>"+"私聊:"+"</font>"+vo.getContent());
			String name=vo.getName();//发送目标的名字
			Session sessionTarget=map.get(name);//根据目标名字找到目标的通信会话(通信管道)
			try {
				//将消息记录存到数据库
				//创建消息对象
				OneMsg oneMsg=new OneMsg();
				//将消息内容添加到消息对象中
				oneMsg.setBody(vo.getContent());
				//UUID设置消息对象的ID
				oneMsg.setMsgId(UUID.randomUUID().toString());
				//获取userDao bean对象
				UserDao userDao=ctx.getBean("userDao",UserDao.class);
				//获取发送者ID
				String userIdSend=userDao.findUserByName(username).getUserId();
				//获取接收者ID
				String userIdTarget=userDao.findUserByName(name).getUserId();
				oneMsg.setUserIdSend(userIdSend);
				oneMsg.setUserIdTarget(userIdTarget);
				oneMsg.setCreateTime(System.currentTimeMillis());
				OneMsgDao oneMsgDao=ctx.getBean("oneMsgDao",OneMsgDao.class);
				//保存消息到数据库
				oneMsgDao.saveOneMsg(oneMsg);
				//发送单聊消息
				session.getBasicRemote().sendText(gson.toJson(message));
				sessionTarget.getBasicRemote().sendText(gson.toJson(message));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	//群聊时将每个用户的消息循环推送到前台(广播消息)
	public void broadcast(List<Session>sessions,String msg) {
		for(Iterator<Session>it=sessions.iterator();it.hasNext();){
			try {
				it.next().getBasicRemote().sendText(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
