package org.shj.weixin.msg;

import org.shj.weixin.enums.RespMsgType;

/**
 * <xml>
	<ToUserName><![CDATA[toUser]]></ToUserName>
	<FromUserName><![CDATA[fromUser]]></FromUserName>
	<CreateTime>12345678</CreateTime>
	<MsgType><![CDATA[image]]></MsgType>
	<Image>
	<MediaId><![CDATA[media_id]]></MediaId>
	</Image>
	</xml>
 * 
 * @author Shen Huang Jian
 *
 */
public class ImgMsg extends BaseMsg{
	
	public ImgMsg(){
		this.setMsgType(RespMsgType.image.name());
	}
	
	/** 回复的图片的 mediaId，其值为图片上传到微信服务器后返回的值 */
	private String mediaId;
	
	protected String buildSelfPart(){
		return "<Image><MediaId><![CDATA[" + mediaId + "]]></MediaId></Image>";
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

}
