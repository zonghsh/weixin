package org.shj.weixin.handler;

import net.sf.json.JSONObject;

import org.shj.weixin.enums.RespMsgType;
import org.shj.weixin.msg.TextMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VoiceHandler extends Handler{
	private Logger log = LoggerFactory.getLogger(VoiceHandler.class);

	@Override
	public String handlerRequest(JSONObject jsonObj) {
		log.info("Handle voice msg.");
//		String format = jsonObj.getString("Format");//声音文件格式
		
		//开通语音识别后，用户每次发送语音给公众号时，微信会在推送的语音消息XML数据包中，
		//增加一个Recongnition字段（注：由于客户端缓存，开发者开启或者关闭语音识别功能，
		//对新关注者立刻生效，对已关注用户需要24小时生效。开发者可以重新关注此帐号进行测试）
		String recog = jsonObj.getString("Recognition");
		
		TextMsg text = new TextMsg();
		setCommonValuesInMsg(jsonObj, text);
		text.setMsgType(RespMsgType.text.name());
		text.setContent("你说的是不是： " + recog);
		
		return text.toXml();
	}
}