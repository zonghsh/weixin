package org.shj.weixin.handler;

import net.sf.json.JSONObject;

import org.shj.weixin.enums.RespMsgType;
import org.shj.weixin.msg.TextMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用户给公众号发送文本消息时的处理器
 * @author Shen Huang Jian
 *
 */
public class TextHandler extends Handler{
	private Logger log = LoggerFactory.getLogger(TextHandler.class);

	@Override
	public String handlerRequest(JSONObject jsonObj) {
		log.info("Handle text msg.");
		String content = jsonObj.getString("Content");
		TextMsg text = new TextMsg();
		
		setCommonValuesInMsg(jsonObj, text);
		text.setMsgType(RespMsgType.text.name());
		text.setContent("你输入了：" + content);
		
		return text.toXml();
	}
}
