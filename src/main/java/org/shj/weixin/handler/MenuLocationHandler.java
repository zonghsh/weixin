package org.shj.weixin.handler;

import net.sf.json.JSONObject;

import org.shj.weixin.enums.RespMsgType;
import org.shj.weixin.msg.TextMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 用户通过菜单按钮发送的位置信息，如果回复给用户信息，会被LocationHandler覆盖掉
 * 
 * @author Shen Huang Jian
 *
 */
public class MenuLocationHandler extends Handler{

	private Logger log = LoggerFactory.getLogger(MenuLocationHandler.class);
	
	@Override
	public String handlerRequest(JSONObject jsonObj) {
		//扫码推事件的事件推送
		log.info("处理location.。。。。");
		String locationinfo = jsonObj.getString("SendLocationInfo");
		
		com.alibaba.fastjson.JSONObject locInfoObj = JSON.parseObject(locationinfo);
		double x = locInfoObj.getDoubleValue("Location_X");
		double y = locInfoObj.getDoubleValue("Location_Y");
		String address = locInfoObj.getString("Label");
		
		TextMsg text = new TextMsg();
		this.setCommonValuesInMsg(jsonObj, text);
		text.setContent("你选择的坐标：X:" + x + "; Y: " + y + "\n位置: " + address);
		text.setMsgType(RespMsgType.text.name());
		
		return text.toXml();
	}

}
