package org.shj.weixin.msg;

import org.shj.weixin.enums.RespMsgType;

public class VoiceMsg extends BaseMsg{
	
	public VoiceMsg(){
		this.setMsgType(RespMsgType.voice.name());
	}
	
	/** 回复的声音的 mediaId，其值为声音文件上传到微信服务器后返回的值 */
	private String mediaId;
	
	protected String buildSelfPart(){
		return "<Voice><MediaId><![CDATA[" + mediaId + "]]></MediaId></Voice>";
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

}
