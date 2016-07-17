package org.shj.weixin.msg;

public abstract class BaseMsg {

	/** 接收方帐号（收到的OpenID） */
	protected String toUserName;
	
	/** 开发者微信号 */
	protected String fromUserName;
	
	/** 消息创建时间 （整型） */
	protected long createTime;
	
	/** app 返回给微信服务器的消息类型 */
	protected String msgType;
	
	public String toXml(){
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>")
		  .append("<ToUserName><![CDATA[").append(toUserName).append("]]></ToUserName>")
		  .append("<FromUserName><![CDATA[").append(fromUserName).append("]]></FromUserName>")
		  .append("<CreateTime>").append(createTime).append("</CreateTime>")
		  .append("<MsgType><![CDATA[").append(msgType).append("]]></MsgType>")
		  .append(buildSelfPart())
		  .append("</xml>");
		
		return sb.toString();
	}
	
	protected abstract String buildSelfPart();

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	
	
}
