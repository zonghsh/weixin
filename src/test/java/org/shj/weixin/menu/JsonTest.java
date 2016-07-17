package org.shj.weixin.menu;

import java.util.ArrayList;
import java.util.List;

import org.shj.weixin.enums.MenuType;

import com.alibaba.fastjson.JSON;

public class JsonTest {
	
	public static void main(String[] args){
		Menu menu = initMenu();
		String jsonStr = JSON.toJSONString(menu);
		System.out.println(jsonStr);
	}

	private static Menu initMenu(){
		Menu menu = new Menu();
		List<MenuBtn> button = new ArrayList<MenuBtn>();
		
		//begin menu1
		MenuBtn btn = new MenuBtn();
		btn.setName("菜单一");
		
		MenuBtn sub = new MenuBtn();
		List<MenuBtn> subBtns = new ArrayList<MenuBtn>();
		
		sub.setName("单击事件");
		sub.setKey("m1s1");
		sub.setType(MenuType.click);
		subBtns.add(sub);
		
		sub = new MenuBtn();
		sub.setName("跳转URL");
		sub.setKey("m1s2");
		sub.setType(MenuType.view);
		subBtns.add(sub);
		
		sub = new MenuBtn();
		sub.setName("扫码推事件");
		sub.setKey("m1s3");
		sub.setType(MenuType.scancode_push);
		subBtns.add(sub);
		
		sub = new MenuBtn();
		sub.setName("扫码提示框");
		sub.setKey("m1s4");
		sub.setType(MenuType.scancode_waitmsg);
		subBtns.add(sub);
		
		sub = new MenuBtn();
		sub.setName("拍照发图");
		sub.setKey("m1s5");
		sub.setType(MenuType.pic_sysphoto);
		subBtns.add(sub);
		
		btn.setSubButton(subBtns);
		button.add(btn);
		//end menu1
		
		//begin menu2
		btn = new MenuBtn();
		btn.setName("菜单二");
		
		subBtns = new ArrayList<MenuBtn>();
		
		sub = new MenuBtn();
		sub.setName("拍照或相册");
		sub.setKey("m2s1");
		sub.setType(MenuType.pic_photo_or_album);
		subBtns.add(sub);
		
		sub = new MenuBtn();
		sub.setName("跳转URL");
		sub.setKey("m2s2");
		sub.setType(MenuType.pic_weixin);
		subBtns.add(sub);
		
		sub = new MenuBtn();
		sub.setName("扫码推事件");
		sub.setKey("m2s3");
		sub.setType(MenuType.location_select);
		subBtns.add(sub);
		
		sub = new MenuBtn();
		sub.setName("扫码提示框");
		sub.setKey("m2s4");
		sub.setType(MenuType.media_id);
		subBtns.add(sub);
		
		sub = new MenuBtn();
		sub.setName("拍照发图");
		sub.setKey("m2s5");
		sub.setType(MenuType.view_limited);
		subBtns.add(sub);
		
		btn.setSubButton(subBtns);
		button.add(btn);
		//end menu2
		
		btn = new MenuBtn();
		btn.setName("Hello");
		sub.setKey("m3");
		btn.setType(MenuType.click);
		
		button.add(btn);
		
		menu.setButton(button);
		
		return menu;
	}
}
