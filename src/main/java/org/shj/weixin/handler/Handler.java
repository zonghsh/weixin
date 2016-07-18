package org.shj.weixin.handler;

import net.sf.json.JSONObject;

import org.shj.weixin.msg.BaseMsg;

/**
 * 每种 EventType都对应一个Handler    
 * @author Shen Huang Jian
 *
 */
public abstract class Handler {
	
	/**
	 * If the app need to response message after user click the menu,
	 * this method should return the response message. Else return null.
	 */
	public abstract String handlerRequest(JSONObject jsonObj);
	
	public void setCommonValuesInMsg(JSONObject jsonObj, BaseMsg msg){
		String fromUser = jsonObj.getString("FromUserName");
		String ToUserName = jsonObj.getString("ToUserName");
		
		msg.setFromUserName(ToUserName);
		msg.setToUserName(fromUser);
		msg.setCreateTime(System.currentTimeMillis() / 1000);
	}
		
}
