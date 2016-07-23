package org.shj.weixin.handler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import net.sf.json.JSONObject;

import org.shj.weixin.enums.RespMsgType;
import org.shj.weixin.msg.TextMsg;
import org.shj.weixin.util.PropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 处理所有的 event为 CLICK的菜单点击事件, 如果有多个此类菜单, 可根据EventKey的值（菜单的key值）来确定具体是哪个菜单
 * @author Shen Huang Jian
 *
 */
public class ClickHandler extends Handler{
	private Logger log = LoggerFactory.getLogger(ClickHandler.class);

	@Override
	public String handlerRequest(JSONObject jsonObj) {
		log.info("CLICK event 菜单.。。。。");
		String fromUser = jsonObj.getString("FromUserName");
		String key = jsonObj.getString("EventKey");
		
		if("m3s1".equals(key)){
			return null;
		}
		
		//本测试帐号中，此菜单返回文本类型的消息
		TextMsg text = new TextMsg();
		
		setCommonValuesInMsg(jsonObj, text);
		text.setMsgType(RespMsgType.text.name());
		
		if("m3s2".equals(key)){
			//引导用户的URL, 可以设置不同的state的值，在Controller那里可以根据这个值来导向不同的页面
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
			
			String redirecturl = PropertyUtil.getStringProperty("appUrlPrefix") + "reqeustAuth.do";
			try {
				text.setContent(url.replace("REDIRECT_URI", URLEncoder.encode(redirecturl, "UTF-8"))
									.replace("APPID", PropertyUtil.getAppId()));
			} catch (UnsupportedEncodingException e) {
				
				e.printStackTrace();
			}
			
		}else{
			text.setContent(fromUser + "点击" + key);
		}
		
		return text.toXml();
	}
}
