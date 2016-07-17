package org.shj.weixin.handler;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ViewHandler extends Handler{

	private Logger log = LoggerFactory.getLogger(ViewHandler.class);
	
	@Override
	public String handlerRequest(JSONObject jsonObj) {
		String eventKey = jsonObj.getString("EventKey");
		log.info("跳转到：" + eventKey);
		return null;
	}

}
