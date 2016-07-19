package org.shj.weixin.entity;

/**
 * App Server调用微信服务器API时，微信服务器返回的消息
 * 
 * @author Shen Huang Jian
 *
 */
public class ResponseMsg {
	
	public ResponseMsg(){}

	private String errcode;
	
	private String errmsg;

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
	
}
