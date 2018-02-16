//package cn.action;
//
//import javax.annotation.Resource;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import cn.service.UserService;
//
//@Component
//public class ChatInterceptor implements HandlerInterceptor{
//	@Resource
//	private UserService userService;
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		//获取cookie userId,token
//		Cookie[] cookies=request.getCookies();
//		String userId=null;
//		String token=null;
//		if(cookies!=null){
//			for(Cookie c:cookies){
//				if("userId".equals(c.getName())){
//					userId=c.getValue();
//				}
//				if("token".equals(c.getName())){
//					token=c.getValue();
//				}
//			}
//		}
//		String json="{\"state\":1,\"data\":null,\"message\":\"请重新登录\"}";
//		if(token==null||userId==null){
//			response.setContentType("text/html;charset=utf-8");
//			response.getWriter().println(json);
//			return false;
//		}
//		//将userId和token交给业务层比较
//		//如果通过则return TRUE，继续访问，否则返回false，利用response返回一个包含错误消息的JSON对象
//		if(userService.checkToken(userId, token)){
//			return true;
//		}else{
//			response.setContentType("text/html;charset=utf-8");
//			response.getWriter().println(json);
//			return false;
//		}
//		
//	
//	}
//
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//
//	}
//
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//
//	}
//}
