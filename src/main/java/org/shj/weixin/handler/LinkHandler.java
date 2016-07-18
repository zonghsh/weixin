package org.shj.weixin.handler;

import net.sf.json.JSONObject;

import org.shj.weixin.enums.RespMsgType;
import org.shj.weixin.msg.TextMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkHandler extends Handler{
	private Logger log = LoggerFactory.getLogger(LinkHandler.class);

	@Override
	public String handlerRequest(JSONObject jsonObj) {
		log.info("Handle link msg.");
		String title = jsonObj.getString("Title");
		String desc = jsonObj.getString("Description");
		String url = jsonObj.getString("Url");
		
		TextMsg text = new TextMsg();
		setCommonValuesInMsg(jsonObj, text);
		text.setMsgType(RespMsgType.text.name());
		text.setContent("标题：" + title + "\n 内容： " + desc + "\n链接： " + url);
		
		return text.toXml();
	}
}
