package org.shj.weixin.handler;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

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
		String eventKey = jsonObj.getString("EventKey");
		String key = eventKey.toUpperCase();
		Handler handler = map.get(key);
		String className = "org.shj.weixin.handler." + key + "Handler";
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
