package org.shj.weixin.handler;

import net.sf.json.JSONObject;

import org.shj.weixin.enums.RespMsgType;
import org.shj.weixin.msg.TextMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 处理所有的 event为 pic_sysphoto, pic_photo_or_album 和 pic_weixin的菜单点击事件, 
 * 如果有多个此类菜单, 可根据EventKey的值（菜单的key值）来确定具体是哪个菜单
 * @author Shen Huang Jian
 *
 */
public class PicHandler extends Handler{
	private Logger log = LoggerFactory.getLogger(PicHandler.class);

	@Override
	public String handlerRequest(JSONObject jsonObj) {
		//扫码推事件的事件推送
		log.info("处理图片.。。。。");
		String picinfo = jsonObj.getString("SendPicsInfo");
		
		com.alibaba.fastjson.JSONObject picInfoObj = JSON.parseObject(picinfo);
		int count = picInfoObj.getIntValue("Count");
		log.info("用户发送了" + count + "张图片");
		
		TextMsg text = new TextMsg();
		this.setCommonValuesInMsg(jsonObj, text);
		text.setContent("你发送了" + count + "张图片");
		text.setMsgType(RespMsgType.text.name());
		
		return text.toXml();
	}
}
