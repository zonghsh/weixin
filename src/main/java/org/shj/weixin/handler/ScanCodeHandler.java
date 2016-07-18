package org.shj.weixin.handler;

import net.sf.json.JSONObject;

import org.shj.weixin.enums.RespMsgType;
import org.shj.weixin.msg.TextMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 处理所有的 event为 scancode_waitmsg和scancode_push的菜单点击事件, 如果有多个此类菜单, 可根据EventKey的值（菜单的key值）来确定具体是哪个菜单
 * @author Shen Huang Jian
 *
 */
public class ScanCodeHandler extends Handler{
	private Logger log = LoggerFactory.getLogger(ScanCodeHandler.class);

	@Override
	public String handlerRequest(JSONObject jsonObj) {
		//扫码推事件的事件推送
		log.info("处理Scan.。。。。");
		String scaninfo = jsonObj.getString("ScanCodeInfo");
		
		String scanResult = JSON.parseObject(scaninfo).getString("ScanResult");
		
		TextMsg text = new TextMsg();
		this.setCommonValuesInMsg(jsonObj, text);
		text.setContent("扫描结果：" + scanResult);
		text.setMsgType(RespMsgType.text.name());
		
		return text.toXml();
	}
}
