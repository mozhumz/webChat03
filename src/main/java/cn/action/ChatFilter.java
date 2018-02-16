package cn.action;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.service.UserService;

public class ChatFilter implements Filter{
	private WebApplicationContext ctx;
	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) res;
		//getCookies�������Ի�ȡ�ͻ��˴��͵�����cookie����
				Cookie[]cookies=request.getCookies();
				System.out.println(cookies);
				String token=null;
				String userId=null;
				for(Cookie c:cookies){
//					System.out.println(c.getName()+":"+c.getValue());
			
					if("token".equals(c.getName())){
						token=c.getValue();
					}
					if("userId".equals(c.getName())){
						userId=c.getValue();
					}
				}
				//��û��"token"���key�����ض��򵽵�¼ҳ
				if(token==null){
					response.sendRedirect("login.jsp");
					return;
				}
				UserService userService=ctx.getBean("userService",UserService.class);
				//��������cookie�е�token�����ݿ��еĲ�һ�£����ض��򵽵�¼ҳ
				if(userService.checkToken(userId,token)){
					//ִ�к���������
					chain.doFilter(req, res);
				}else{
					response.sendRedirect("login.jsp");
				}
	}

	public void init(FilterConfig cfg) throws ServletException {
		//��ȡ��ǰwebӦ���е�spring����
		ctx=WebApplicationContextUtils.getWebApplicationContext(cfg.getServletContext());
	}

}
