package org.shj.weixin.handler.menu;

import net.sf.json.JSONObject;

import org.shj.weixin.handler.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class M1S2Handler extends Handler{
	private Logger log = LoggerFactory.getLogger(M1S2Handler.class);

	@Override
	public String handlerRequest(JSONObject jsonObj) {
		log.info("处理菜单1中子菜单2.。。。。");
		
		return null;
	}
}
