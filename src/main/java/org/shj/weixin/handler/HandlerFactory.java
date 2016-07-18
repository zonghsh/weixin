package org.shj.weixin.handler;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.shj.weixin.enums.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HandlerFactory {
	
	private Logger log = LoggerFactory.getLogger(HandlerFactory.class);
	
	public static HandlerFactory factory = new HandlerFactory();
	
	private Map<String, Handler> map = new HashMap<String, Handler>();
	
	private HandlerFactory(){}

	/**
	 * 
	 * @param eventKey 事件KEY值，与自定义菜单接口中KEY值对应
	 * @return
	 */
	public Handler createHandler(JSONObject jsonObj){
				
		String msgType = jsonObj.getString("MsgType");
		Handler handler = null;
		
		if("event".equals(msgType)){
			String event = jsonObj.getString("Event");
			handler = map.get(event);
			
			if(handler != null){
				return handler;
			}
			
			EventType et = EventType.valueOf(event);
			
			switch(et){
				case CLICK : 
					handler = new ClickHandler();
					break;
				case VIEW : 
					handler = new ViewHandler();
					break;
				case scancode_push :  
				case scancode_waitmsg :  
					handler = new ScanCodeHandler();
					break;
				case pic_sysphoto :  
				case pic_photo_or_album :  
				case pic_weixin :  
					handler = new PicHandler();
					break;
				case location_select :  
					handler = new ClickHandler();
					break;
				case subscribe :  
					handler = new SubscribeHandler();
					break;
				case unsubscribe :  
					handler = new SubscribeHandler();
					break;
				default:  
					handler = new ClickHandler();
					break;
			}
			
			map.put(event, handler);
			return handler;
			
		}
		
		handler = map.get(msgType);
		if(handler != null){
			return handler;
		}
		
		switch(msgType){
			case "text":
				handler = new TextHandler();
				break;
			case "image":
				handler = new ImgHandler();
				break;
			case "voice":
				handler = new VoiceHandler();
				break;
			case "video":
			case "shortvideo":
				handler = new VideoHandler();
				break;
			case "location":
				handler = new LocationHandler();
				break;
			case "link":
				handler = new LinkHandler();
				break;
		}
		map.put(msgType, handler);
		
		return handler;
		
	}
}
