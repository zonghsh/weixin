package org.shj.weixin.listener;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.shj.weixin.Constants;
import org.shj.weixin.entity.AccessTokenHolder;
import org.shj.weixin.entity.ResponseMsg;
import org.shj.weixin.enums.MenuType;
import org.shj.weixin.menu.Menu;
import org.shj.weixin.menu.MenuBtn;
import org.shj.weixin.util.HttpUtil;
import org.shj.weixin.util.JsonUtil;
import org.shj.weixin.util.PropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccessTokenListener implements ServletContextListener{
	
	private Logger log = LoggerFactory.getLogger(AccessTokenListener.class);
	
	private ScheduledExecutorService service;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		log.info("Destroy AccessTokenListener.");
		service.shutdown();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		//开启一个线程去定期更新access_token
		service = Executors.newScheduledThreadPool(1);
		log.info("Start refresh access_token task.");
		service.scheduleAtFixedRate(new Runnable(){
			public void run(){
				AccessTokenHolder.instance.refreshToken();
			}
		}, 1, 7150, TimeUnit.SECONDS);
		
		boolean menuInitialized = PropertyUtil.getBooleanProperty("menuInitialized");
		if(!menuInitialized){ //TODO: this value always be false
			log.info("begin init menu...");
			Menu menu = initMenu();
			String jsonStr = JsonUtil.toJSONString(menu);
			//log.info(jsonStr);
			HttpUtil.postToWeiXinServer(jsonStr, Constants.CREATE_MENU, ResponseMsg.class);
		}
	}

	private Menu initMenu(){
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
		sub.setUrl(PropertyUtil.getStringProperty("appUrlPrefix") + "/html/viewClickReturnPage.html");
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
		sub.setName("微信相册");
		sub.setKey("m2s2");
		sub.setType(MenuType.pic_weixin);
		subBtns.add(sub);
		
		sub = new MenuBtn();
		sub.setName("地理位置");
		sub.setKey("m2s3");
		sub.setType(MenuType.location_select);
		subBtns.add(sub);
		
		/* 以下两个button，需要填写MediaId
		 * 微信客户端将打开开发者在按钮中填写的永久素材id对应的图文消息URL，永久素材类型只支持图文消息。
		 * 请注意：永久素材id必须是在“素材管理/新增永久素材”接口上传后获得的合法id。
		sub = new MenuBtn();
		sub.setName("下发消息");
		sub.setKey("m2s4");
		sub.setType(MenuType.media_id);
		sub.setMediaId("mediaId1");
		subBtns.add(sub);
		
		sub = new MenuBtn();
		sub.setName("跳转图文消息");
		sub.setKey("m2s5");
		sub.setType(MenuType.view_limited);
		sub.setMediaId("mediaId2");
		subBtns.add(sub);
		*/
		
		btn.setSubButton(subBtns);
		button.add(btn);
		//end menu2
		
		btn = new MenuBtn();
		btn.setName("Hello");
		
		subBtns = new ArrayList<MenuBtn>();
		btn.setSubButton(subBtns);
		
		sub = new MenuBtn();
		sub.setName("客服消息");
		sub.setKey("m3s1");
		sub.setType(MenuType.click);
		subBtns.add(sub);
		
		sub = new MenuBtn();
		sub.setName("生成回调URL");
		sub.setKey("m3s2");
		sub.setType(MenuType.click);
		subBtns.add(sub);
		
		button.add(btn);
		
		menu.setButton(button);
		
		return menu;
	}
}
