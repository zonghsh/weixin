package org.shj.weixin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.shj.weixin.Constants;
import org.shj.weixin.entity.TempUserInfo;
import org.shj.weixin.entity.UnsubscribeAccessToken;
import org.shj.weixin.util.HttpUtil;
import org.shj.weixin.util.PropertyUtil;
import org.shj.weixin.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {
	
	private Logger log = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired  
	private  HttpServletRequest request; 

	@RequestMapping(value="reqeustAuth")
    public ModelAndView login(){
		log.info("code: " + request.getParameter("code"));
		
		//指定要返回的页面为auth.jsp  
        Map<String, Object> model = new HashMap<String, Object>();
		String code = request.getParameter("code");
		log.info("code: " + code);
		if(StringUtil.isEmpty(code)){
			log.info("用户禁止授权。");
			model.put("username", "unknow user");
			
		}else{
			log.info("用户允许授权。");
			String url = Constants.GET_ACCESS_TOKEN.replace("APPID", PropertyUtil.getAppId())
					.replace("SECRET", PropertyUtil.getAppsecret()).replace("CODE", code);
			UnsubscribeAccessToken token = HttpUtil.getToWeiXinServer(url, UnsubscribeAccessToken.class);
			
			log.info("scope: " + token.getScope());
			//如果网页授权作用域为snsapi_userinfo，则此时开发者可以通过access_token和openid拉取用户信息了。
			if("snsapi_userinfo".equals(token.getScope())){
				url = Constants.GET_TEMP_USER_INFO.replace("ACCESS_TOKEN", token.getAccessToken())
								.replace("OPENID", token.getOpenId());
				TempUserInfo user = HttpUtil.getToWeiXinServer(url, TempUserInfo.class);
				model.put("username", user.getNickname());
				
			}else{
				model.put("username", "scope not snsapi_userinfo");
			}
		}
        
        ModelAndView mav = new ModelAndView("auth", model);  
        return mav;  
        
    }
}
