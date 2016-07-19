package org.shj.weixin.msg;

public class VoiceMsg extends BaseMsg{
	
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
