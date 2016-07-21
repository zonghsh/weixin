package org.shj.weixin.handler;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用户同意上报地理位置后，每次进入公众号会话时，都会在进入时上报地理位置，
 * 或在进入会话后每5秒上报一次地理位置，公众号可以在公众平台网站中修改以上设置。
 * 上报地理位置时，微信会将上报地理位置事件推送到开发者填写的URL
 * 
 * @author Shen Huang Jian
 *
 */
public class TraceLocationHandler extends Handler{
	private Logger log = LoggerFactory.getLogger(TraceLocationHandler.class);

	@Override
	public String handlerRequest(JSONObject jsonObj) {
		log.info("Handle TraceLocation.");		
		return null;
	}
}
