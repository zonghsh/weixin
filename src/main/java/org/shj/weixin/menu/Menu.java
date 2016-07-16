package org.shj.weixin.menu;

import java.util.List;


public class Menu {
	
	public Menu(){}

	/**
     * 一级菜单列表，最多3个
     */
    private List<MenuBtn> button;

	public List<MenuBtn> getButton() {
		return button;
	}

	public void setButton(List<MenuBtn> button) {
		this.button = button;
	}
    
    
}
