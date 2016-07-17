package org.shj.weixin.handler.menu;

import net.sf.json.JSONObject;

import org.shj.weixin.enums.MsgType;
import org.shj.weixin.handler.Handler;
import org.shj.weixin.msg.TextMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class M1S1Handler extends Handler{
	private Logger log = LoggerFactory.getLogger(M1S1Handler.class);

	@Override
	public String handlerRequest(JSONObject jsonObj) {
		log.info("处理菜单1中子菜单1.。。。。");
		String fromUser = jsonObj.getString("FromUserName");
		
		//本测试帐号中，此菜单返回文本类型的消息
		TextMsg text = new TextMsg();
		
		setCommonValuesInMsg(jsonObj, text);
		text.setMsgType(MsgType.text.name());
		text.setContent(fromUser + "点击测试菜单m1");
		
		return text.toXml();
	}
}
