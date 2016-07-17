package org.shj.weixin.handler;

import net.sf.json.JSONObject;

import org.shj.weixin.Constants;
import org.shj.weixin.entity.UserInfo;
import org.shj.weixin.enums.EventType;
import org.shj.weixin.enums.MsgType;
import org.shj.weixin.msg.TextMsg;
import org.shj.weixin.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscribeHandler extends Handler{

	private Logger log = LoggerFactory.getLogger(SubscribeHandler.class);
	
	@Override
	public String handlerRequest(JSONObject jsonObj) {
		String event = jsonObj.getString("Event");
		
		if(EventType.subscribe.name().equals(event)){
			log.info("有新用户订阅");
			
			String url = Constants.GET_USR_INFO.replace("OPENID", jsonObj.getString("FromUserName"));
			
			UserInfo userInfo = HttpUtil.getToWeiXinServer(url, UserInfo.class);
			TextMsg msg = new TextMsg();
			
			this.setCommonValuesInMsg(jsonObj, msg);
			msg.setContent("欢迎" + userInfo.getNickname() + "订阅。。。");
			msg.setMsgType(MsgType.text.name());
			
			return msg.toXml();
		}else{
			log.info("用户取消订阅");
			return null;
		}		
	}

}
