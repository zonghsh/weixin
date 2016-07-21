package org.shj.weixin.servlet;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.shj.weixin.util.PropertyUtil;
import org.shj.weixin.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

public class WeiXinSupport {
	
	private static Logger log =  LoggerFactory.getLogger(WeiXinSupport.class);
	
	private String token;
	private String appID;
	private String appsecret;
	private String encodingAESKey;
	private WXBizMsgCrypt pc;
	
	private ThreadLocal<Boolean> needEncrypt = new ThreadLocal<Boolean>();
	
	public WeiXinSupport(){
		token = PropertyUtil.getToken();
		appID = PropertyUtil.getAppId();
		appsecret = PropertyUtil.getAppsecret();
		encodingAESKey = PropertyUtil.getEncodingAESKey();
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
	
	public String decryptRequest(String requestStr, HttpServletRequest request) throws AesException{
		//在安全模式或兼容模式下，url上会新增两个参数encrypt_type和msg_signature。encrypt_type表示加密类型，
		//msg_signature:表示对消息体的签名。 url上无encrypt_type参数或者其值为raw时表示为不加密；encrypt_type为aes时，
		//表示aes加密（暂时只有raw和aes两种值)。公众帐号开发者根据此参数来判断微信公众平台发送的消息是否加密。
		String encryptType = request.getParameter("encrypt_type");
		
		if("aes".equals(encryptType)){
			needEncrypt.set(true);
			if(pc == null){
				pc = new WXBizMsgCrypt(getToken(), getEncodingAESKey(), getAppID());
			}
			
			String msgSignature = request.getParameter("msg_signature");
			String timestamp = request.getParameter("timestamp");
			String nonce = request.getParameter("nonce");
			
			requestStr = pc.decryptMsg(msgSignature, timestamp, nonce, requestStr);
			log.info("解密后：" + requestStr);
		}
		
		return requestStr;
	}
	
	public String encryptMsg(String replyMsg) throws AesException {
		Boolean flag = needEncrypt.get();//测试时，加密消息只能通过调试接口发送，如果测试用户直接发送消息，就不会加密。此值为null
		if(flag != null && flag){
			String timeStamp = Long.toString(System.currentTimeMillis());
			String nonce = pc.getRandomStr();
			
			return pc.encryptMsg(replyMsg, timeStamp, nonce);
		}
		return replyMsg;
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

	public String getEncodingAESKey() {
		return encodingAESKey;
	}

	public void setEncodingAESKey(String encodingAESKey) {
		this.encodingAESKey = encodingAESKey;
	}
	
	
}
