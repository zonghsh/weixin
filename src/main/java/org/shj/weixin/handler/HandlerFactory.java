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
		String event = jsonObj.getString("Event");
		Handler handler = null;
		String key = null;
		
		//对于 VIEW 类型的菜单, eventKey值不是设置菜单的key值，而是跳转的URL
		if(EventType.VIEW.name().equals(event)){
			key = EventType.VIEW.name();
			handler = map.get(key);
			if(handler == null){
				handler = new ViewHandler();
				map.put(key, handler);
			}
			return handler;
			
		}else if(EventType.subscribe.name().equals(event) || EventType.unsubscribe.name().equals(event)){
			key = EventType.subscribe.name();
			handler = map.get(key);
			if(handler == null){
				handler = new SubscribeHandler();
				map.put(key, handler);
			}
			return handler;
			
		}
		
		String eventKey = jsonObj.getString("EventKey");
		key = eventKey.toUpperCase();
		handler = map.get(key);
		String className = "org.shj.weixin.handler.menu." + key + "Handler";
		try{
			if(handler == null){
				Class<?> c = Class.forName(className);
				handler = (Handler) c.newInstance();
				map.put(key, handler);
			}
			
		}catch(Exception ie){
			log.error("Cann't create instance of " + className);
		}
		return handler;
	}
}
