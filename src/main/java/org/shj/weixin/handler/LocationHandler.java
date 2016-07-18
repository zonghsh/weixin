package org.shj.weixin.handler;

import net.sf.json.JSONObject;

import org.shj.weixin.enums.RespMsgType;
import org.shj.weixin.msg.TextMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocationHandler extends Handler{
	private Logger log = LoggerFactory.getLogger(LocationHandler.class);

	@Override
	public String handlerRequest(JSONObject jsonObj) {
		log.info("Handle location msg.");
		
		double x = jsonObj.getDouble("Location_X");
		double y = jsonObj.getDouble("Location_Y");
		String address = jsonObj.getString("Label");
		
		TextMsg text = new TextMsg();
		setCommonValuesInMsg(jsonObj, text);
		text.setMsgType(RespMsgType.text.name());
		text.setContent("你选择的坐标：X:" + x + "; Y: " + y + "\n位置: " + address);
		
		return text.toXml();
	}
}
