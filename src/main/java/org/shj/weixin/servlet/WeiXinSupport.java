package org.shj.weixin.servlet;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.shj.weixin.util.PropertyUtil;
import org.shj.weixin.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeiXinSupport {
	
	private static Logger log =  LoggerFactory.getLogger(WeiXinSupport.class);
	
	private String token;
	
	private String appID;
	
	private String appsecret;
	
	public WeiXinSupport(){
		token = PropertyUtil.getToken();
		appID = PropertyUtil.getAppId();
		appsecret = PropertyUtil.getAppsecret();
	}

	/**
	 * 加密/校验流程如下：
	 * 1. 将token、timestamp、nonce三个参数进行字典序排序
	 * 2. 将三个参数字符串拼接成一个字符串进行sha1加密
	 * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
	 * 详见微信开发者文档
	 * @param request
	 * @return 接入成功或失败
	 */
	public boolean isValidServerConfig(HttpServletRequest request){
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		
		if (StringUtil.isEmpty(signature)) {
			return false;
		}
		if (StringUtil.isEmpty(timestamp)) {
			return false;
		}
		if (StringUtil.isEmpty(nonce)) {
			return false;
		}
		if (StringUtil.isEmpty(echostr)) {
			return false;
		}
		
		String[] ArrTmp = { getToken(), timestamp, nonce };
		Arrays.sort(ArrTmp);
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ArrTmp.length; i++) {
			sb.append(ArrTmp[i]);
		}
		
		String pwd = StringUtil.encrypt(sb.toString());

		log.info("signature:" + signature + "; timestamp:" + timestamp + "; nonce:"
				+ nonce + "; pwd:" + pwd + "; echostr:" + echostr);

		if (pwd.trim().equals(signature.trim())) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getToken(){
		return token;
	}

	public String getAppID() {
		return appID;
	}

	public String getAppsecret() {
		return appsecret;
	}
	
	
}
