package org.shj.weixin.entity;

import java.util.concurrent.atomic.AtomicBoolean;

import org.shj.weixin.util.HttpUtil;
import org.shj.weixin.util.PropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccessTokenHolder {
	private Logger log = LoggerFactory.getLogger(AccessTokenHolder.class);

	public static AccessTokenHolder instance = new AccessTokenHolder();
	
	private AtomicBoolean inited = new AtomicBoolean();
	
	private AccessTokenHolder(){
	}
	
	private String accessToken;
	
	public String getAccessToken(){
		if(inited.get()){
			//微信服务器会保留过期的access_token一段时间，以避免新的access_token还没取到
			return accessToken;
		}
		
		while(!inited.get()){
			try{
				Thread.sleep(100);
			}catch(InterruptedException e){
				//do nothing
			}
		}
		
		return accessToken;
	}
	
	public void refreshToken(){
		int tryTimes = PropertyUtil.getIntProperty("tryTimes");
		String val = null;
		
		for(int i = 0 ; i < tryTimes; i++){
			val = HttpUtil.getAccessToken();
			if(val != null){
				break;
			}
		}
		if(val == null){
			log.error("Can't get access_token from server.");
			throw new RuntimeException("微信服务器没有正确返回access_token.");
		}
		accessToken = val;
		inited.getAndSet(true);
	}
}
