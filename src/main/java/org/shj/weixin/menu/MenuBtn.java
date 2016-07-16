package org.shj.weixin.menu;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class MenuBtn {
	
	public MenuBtn(){}

	@JSONField(name = "type")
	private MenuType type;
	
	/**
     * 菜单上显示的文字
     */
    private String name;

    /**
     * 菜单key，当MenuType值为CLICK时用于区别菜单
     */
    private String key;

    /**
     * 菜单跳转的URL，当MenuType值为VIEW时用
     */
    private String url;
    
    @JSONField(name = "media_id")
    private String mediaId;
    
    /**
     * 二级菜单列表，每个一级菜单下最多5个
     */
    @JSONField(name = "sub_button")
    private List<MenuBtn> subButton;


	public MenuType getType() {
		return type;
	}

	public void setType(MenuType type) {
		this.type = type;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<MenuBtn> getSubButton() {
		return subButton;
	}

	public void setSubButton(List<MenuBtn> subButton) {
		this.subButton = subButton;
	}
    
    
}
