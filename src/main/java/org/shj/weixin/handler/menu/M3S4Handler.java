package org.shj.weixin.handler.menu;

import net.sf.json.JSONObject;

import org.shj.weixin.handler.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class M3S4Handler extends Handler{
	private Logger log = LoggerFactory.getLogger(M3S4Handler.class);

	@Override
	public String handlerRequest(JSONObject jsonObj) {
		log.info("处理菜单3中子菜单4.。。。。");
		
		return null;
	}
}
