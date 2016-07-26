package org.shj.weixin.msg;

import org.shj.weixin.enums.RespMsgType;

public class VideoMsg extends BaseMsg{
	
	public VideoMsg(){
		this.setMsgType(RespMsgType.video.name());
	}
	
	/** 回复的video的 mediaId，其值为video文件上传到微信服务器后返回的值 */
	private String mediaId;
	
	private String title;
	private String description;
	
	protected String buildSelfPart(){
		return "<Video><MediaId><![CDATA[" + mediaId + "]]></MediaId><Title><![CDATA["
				+ title +"]]></Title></Video><Description><![CDATA[" + description + "]]></Description>";
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}