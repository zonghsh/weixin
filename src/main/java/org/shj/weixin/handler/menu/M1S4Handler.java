package org.shj.weixin.handler.menu;

import net.sf.json.JSONObject;

import org.shj.weixin.enums.MsgType;
import org.shj.weixin.handler.Handler;
import org.shj.weixin.msg.TextMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class M1S4Handler extends Handler{
	private Logger log = LoggerFactory.getLogger(M1S4Handler.class);

	@Override
	public String handlerRequest(JSONObject jsonObj) {
		//扫码推事件且弹出“消息接收中”提示框的事件推送
		log.info("处理菜单1中子菜单4.。。。。");
		
		String scaninfo = jsonObj.getString("ScanCodeInfo");
		
		String scanResult = JSON.parseObject(scaninfo).getString("ScanResult");
		
		TextMsg text = new TextMsg();
		this.setCommonValuesInMsg(jsonObj, text);
		text.setContent("扫描结果：" + scanResult);
		text.setMsgType(MsgType.text.name());
		
		return text.toXml();
	}
}
