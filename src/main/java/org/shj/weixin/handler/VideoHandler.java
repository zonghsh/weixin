package org.shj.weixin.handler;

import net.sf.json.JSONObject;

import org.shj.weixin.enums.RespMsgType;
import org.shj.weixin.msg.TextMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VideoHandler extends Handler{
	private Logger log = LoggerFactory.getLogger(VideoHandler.class);

	@Override
	public String handlerRequest(JSONObject jsonObj) {
		log.info("Handle vedio msg.");
		
		return null;
	}
}
