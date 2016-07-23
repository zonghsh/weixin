package org.shj.weixin.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class UnsubscribeAccessToken {
	
	public UnsubscribeAccessToken(){}

	@JSONField(name = "access_token")
	private String accessToken;
	
	@JSONField(name = "expires_in")
	private int expriesIn;
	
	@JSONField(name = "refresh_token")
	private String refreshToken;
	
	@JSONField(name = "openid")
	private String openId;
	
	private String scope;

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

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	
	
}
