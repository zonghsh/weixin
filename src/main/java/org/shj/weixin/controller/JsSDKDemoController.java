package org.shj.weixin.controller;

import java.util.Map;

import org.shj.weixin.entity.AccessTokenHolder;
import org.shj.weixin.util.PropertyUtil;
import org.shj.weixin.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JsSDKDemoController {

	private Logger log = LoggerFactory.getLogger(JsSDKDemoController.class);
	
	@RequestMapping(value="showJssdkDemo")
    public ModelAndView prepareSignature(){
		log.info("go to jssdkDemo.jsp");
		String url = PropertyUtil.getStringProperty("appUrlPrefix") + "showJssdkDemo.do";
		Map<String, String> model = StringUtil.sign(AccessTokenHolder.instance.getJsapiTicket(), url);
		ModelAndView mav = new ModelAndView("demo/jssdkDemo", model);
		log.info("url: " + url);
		return mav;
	}
}
