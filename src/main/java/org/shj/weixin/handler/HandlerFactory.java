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
	 * @param jsonObj 微信服务器推送过来的xml格式的字符串转成的JSON Object
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
					//以上三种菜单，都会发送图片给app server，此种情况微信服务器会发送两次消息推送给app server
					//第一次是MsgType为event，handler为PicHandler ，
					//第二次MsgType为image, handler为ImgHandler
					//如果以上两个handler都返回消息给用户，则以ImgHandler为准，PicHandler将被忽略。测试几次情况均如此
					handler = new PicHandler();
					break;
				case location_select :  
					//这个handler应该和上面的PicHandler类似
					handler = new MenuLocationHandler();
					break;
				case subscribe :  
					handler = new SubscribeHandler();
					break;
				case unsubscribe :  
					handler = new SubscribeHandler();
					break;
				case TEMPLATESENDJOBFINISH:
					handler = new TemplateSendJobFinishHandler();
					break;
				case LOCATION: 
					//用户同意上报地理位置后，每次进入公众号会话时，都会在进入时上报地理位置，
					//或在进入会话后每5秒上报一次地理位置，公众号可以在公众平台网站中修改以上设置。
					//上报地理位置时，微信会将上报地理位置事件推送到开发者填写的URL
					handler = new TraceLocationHandler();
					break;
				default:  
					//TODO: 还有其它的？
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
