package org.shj.weixin;

public interface Constants {
	
	String GET_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	String GET_JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

	String CREATE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	String GET_WEIXIN_IP = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN";
	
	String GET_USR_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	//此链接为未关注用户访问公众号某页面时，公众号如果需要获取用户信息，则先会出现一个授权页面，如果用户允许，微信服务器会转到公众号的那个URL，并附上code的值，
	//公众号需要根据code的值，用此URL去获取一个临时的access_token，用那个access_token再去获取用户的信息
	String GET_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	
	//获取第二步的refresh_token后，请求以下链接获取access_token： 
	String REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	
	String GET_TEMP_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	//获取二维码ticket
	String POST_2D_CODE_TICKET = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
	
	//用ticket换取二维码，对TICKET进行URLEncode
	String GET_2D_CODE = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
	
	//新增临时素材
	String POST_FILE = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	
	//客服回复消息
	String POST_RESPONSE_MSG = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
}
