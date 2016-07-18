package org.shj.weixin.handler;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 处理所有的 event为VIEW的菜单点击事件, 如果有多个此类菜单, 可根据EventKey的值（URL）来确定具体是哪个菜单
 * 
 * @author Shen Huang Jian
 *
 */
public class ViewHandler extends Handler{

	private Logger log = LoggerFactory.getLogger(ViewHandler.class);
	
	@Override
	public String handlerRequest(JSONObject jsonObj) {
		String eventKey = jsonObj.getString("EventKey");
		log.info("跳转到：" + eventKey);
		return null;
	}

}
