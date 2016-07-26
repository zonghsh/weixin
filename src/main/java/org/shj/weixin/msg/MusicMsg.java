package org.shj.weixin.msg;

import org.shj.weixin.enums.RespMsgType;

public class MusicMsg extends BaseMsg{
	
	public MusicMsg(){
		this.setMsgType(RespMsgType.music.name());
	}
	
	/** 回复的Music的 mediaId，其值为Music文件上传到微信服务器后返回的值 */
	private String mediaId;
	
	/** 音乐标题 */
	private String title;
	
	/** 音乐描述 */
	private String description;
	
	/** 音乐链接 */
	private String musicUrl;
	
	/** 高质量音乐链接，WIFI环境优先使用该链接播放音乐 */
	private String hqMusicUrl;
	
	/** 缩略图的媒体id，通过素材管理接口上传多媒体文件，得到的id */
	private String thumbMediaId;
	
	protected String buildSelfPart(){
		return "<Music><Title><![CDATA[" + title +"]]></Title><Description><![CDATA["
				+ description +"]]></Description><MusicUrl><![CDATA[" 
				+ musicUrl +"]]></MusicUrl><HQMusicUrl><![CDATA[" + hqMusicUrl 
				+"]]></HQMusicUrl><ThumbMediaId><![CDATA[" 
				+ thumbMediaId + "]]></ThumbMediaId></Music>"; //直接用+号连接了，懒得改了
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

	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public String getHqMusicUrl() {
		return hqMusicUrl;
	}

	public void setHqMusicUrl(String hqMusicUrl) {
		this.hqMusicUrl = hqMusicUrl;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	
	

}
