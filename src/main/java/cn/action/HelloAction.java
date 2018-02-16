package cn.action;
/**
 * 测试struts能否正常处理请求
 */
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class HelloAction {

	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public String execute(){
		
		message="HI......";
		System.out.println(
				"Hello Struts2!!!");
		return "success";
	}
}
