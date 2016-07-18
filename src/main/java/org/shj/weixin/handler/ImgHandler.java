package org.shj.weixin.handler;

import net.sf.json.JSONObject;

import org.shj.weixin.enums.RespMsgType;
import org.shj.weixin.msg.TextMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImgHandler extends Handler{
	private Logger log = LoggerFactory.getLogger(ImgHandler.class);

	@Override
	public String handlerRequest(JSONObject jsonObj) {
		log.info("Handle image msg.");
		String picUrl = jsonObj.getString("PicUrl");
		
		TextMsg text = new TextMsg();
		setCommonValuesInMsg(jsonObj, text);
		text.setMsgType(RespMsgType.text.name());
		text.setContent("图片URL：" + picUrl);
		
		return text.toXml();
	}
}
