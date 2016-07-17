package org.shj.weixin.handler;

import net.sf.json.JSONObject;

import org.shj.weixin.enums.EventType;
import org.shj.weixin.msg.BaseMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对于菜单的handler，因为微信中菜单数量有限制，为了方便开发，对每一个菜单都创建了相应的handler类，具体规则：
 * 一级菜单，因为最多有3个，所以创建了 M1Handler, M2Handler, M3Handler
 * 二级菜单，命名为 M1S1Handler, M1S2Handler..., M2S1Handler, M2S2Handler
 * 如果开发者只需要2个一级菜单，则只需要实现 M1Handler, M2Handler即可.
 * 同理，对于二级菜单，也只需要实现其相应的Handler中的方法。
 * 设置菜单的key值时，一级菜单为：M1， M2， M3; 二级菜单为：M1S1, M1S2....
 *    
 * @author Shen Huang Jian
 *
 */
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
