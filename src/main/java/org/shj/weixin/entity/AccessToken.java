package org.shj.weixin.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class AccessToken {
	
	public AccessToken(){}

	@JSONField(name = "access_token")
	private String accessToken;
	
	@JSONField(name = "expires_in")
	private int expriesIn;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpriesIn() {
		return expriesIn;
	}

	public void setExpriesIn(int expriesIn) {
		this.expriesIn = expriesIn;
	}
	
	
}
