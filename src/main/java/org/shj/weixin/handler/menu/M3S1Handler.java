package org.shj.weixin.handler.menu;

import net.sf.json.JSONObject;

import org.shj.weixin.handler.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class M3S1Handler extends Handler{
	private Logger log = LoggerFactory.getLogger(M3S1Handler.class);

	@Override
	public String handlerRequest(JSONObject jsonObj) {
		log.info("处理菜单3中子菜单1.。。。。");
		
		return null;
	}
}
