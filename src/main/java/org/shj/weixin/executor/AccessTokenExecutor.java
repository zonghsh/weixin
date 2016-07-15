package org.shj.weixin.executor;

import org.shj.weixin.http.HttpUtil;

public class AccessTokenExecutor implements Runnable{

	private String accessToken;
	
	public String getAccessToken(){
		return accessToken;
	}

	@Override
	public void run() {
		accessToken = HttpUtil.getAccessToken();
		
	}
}
