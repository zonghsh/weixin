package org.shj.weixin.handler;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TemplateSendJobFinishHandler extends Handler{
	private Logger log = LoggerFactory.getLogger(TemplateSendJobFinishHandler.class);

	@Override
	public String handlerRequest(JSONObject jsonObj) {
		log.info("Handle TemplateSendJobFinish.");
		return null;
	}
}
