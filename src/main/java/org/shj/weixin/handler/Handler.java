package org.shj.weixin.handler;

import net.sf.json.JSONObject;

import org.shj.weixin.enums.EventType;
import org.shj.weixin.msg.BaseMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Handler {
	
	private Logger log = LoggerFactory.getLogger(Handler.class);
	
	public boolean needResponseMsg(JSONObject jsonObj){
		String event = jsonObj.getString("Event");
		EventType et = EventType.valueOf(event);
		
		switch(et){
			case CLICK : return true;
			case VIEW : return false;
			case scancode_push : return true;
			case scancode_waitmsg : return true;
			case pic_sysphoto : return true;
			case pic_photo_or_album : return true;
			case pic_weixin : return true;
			case location_select : return true;
			default: return false;
		}
		
	}
	
	/**
	 * If the app need to response message after user click the menu,
	 * this method should return the response message. Else return null.
	 */
	public abstract String handlerRequest(JSONObject jsonObj);
	
	public void setCommonValuesInMsg(JSONObject jsonObj, BaseMsg msg){
		String fromUser = jsonObj.getString("FromUserName");
		String ToUserName = jsonObj.getString("ToUserName");
		log.info("fromUser: " + fromUser );
		log.info("ToUserName: " + ToUserName );
		
		msg.setFromUserName(ToUserName);
		msg.setToUserName(fromUser);
		msg.setCreateTime(System.currentTimeMillis() / 1000);
	}
	
	
	
}
