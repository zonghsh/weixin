package org.shj.weixin.msg;

/**
 * Sample Text message:
 *  <xml>
	<ToUserName><![CDATA[toUser]]></ToUserName>
	<FromUserName><![CDATA[fromUser]]></FromUserName>
	<CreateTime>12345678</CreateTime>
	<MsgType><![CDATA[text]]></MsgType>
	<Content><![CDATA[你好]]></Content>
	</xml>
 * @author Shen Huang Jian
 *
 */
public class TextMsg extends BaseMsg{
	
	/** 回复的消息内容 */
	private String content;
	
	protected String buildSelfPart(){
		return "<Content><![CDATA[" + content + "]]></Content>";
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}	
}
