package cn.entity;
/**
 * 记录单聊消息类
 * @author asus1
 *
 */
public class OneMsg {
	private String msgId;
	private String userIdSend;
	private String userIdTarget;
	private String body;
	private long createTime;
	
	public OneMsg() {
		super();
	}
	public OneMsg(String msgId, String userIdSend, String userIdTarget, String body, long createTime) {
		super();
		this.msgId = msgId;
		this.userIdSend = userIdSend;
		this.userIdTarget = userIdTarget;
		this.body = body;
		this.createTime = createTime;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getUserIdSend() {
		return userIdSend;
	}
	public void setUserIdSend(String userIdSend) {
		this.userIdSend = userIdSend;
	}
	public String getUserIdTarget() {
		return userIdTarget;
	}
	public void setUserIdTarget(String userIdTarget) {
		this.userIdTarget = userIdTarget;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "OneMsg [msgId=" + msgId + ", userIdSend=" + userIdSend + ", userIdTarget=" + userIdTarget + ", body="
				+ body + ", createTime=" + createTime + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((msgId == null) ? 0 : msgId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OneMsg other = (OneMsg) obj;
		if (msgId == null) {
			if (other.msgId != null)
				return false;
		} else if (!msgId.equals(other.msgId))
			return false;
		return true;
	}

	
}
