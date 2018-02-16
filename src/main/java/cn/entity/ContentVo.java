package cn.entity;
/**
 * 该类将浏览器端发送过来的JSON字符串转换成对象
 * @author asus1
 *
 */
public class ContentVo {
	private String name;//聊天目标的名字
	private String content;//聊天内容
	private int type;//1广播 2单聊
	public ContentVo() {
		super();
	}
	public ContentVo(String name, String content, int type) {
		super();
		this.name = name;
		this.content = content;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	
}
