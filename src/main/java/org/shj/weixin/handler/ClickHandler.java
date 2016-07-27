package org.shj.weixin.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import net.sf.json.JSONObject;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.shj.weixin.Constants;
import org.shj.weixin.msg.TextMsg;
import org.shj.weixin.util.HttpUtil;
import org.shj.weixin.util.PropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 处理所有的 event为 CLICK的菜单点击事件, 如果有多个此类菜单, 可根据EventKey的值（菜单的key值）来确定具体是哪个菜单
 * @author Shen Huang Jian
 *
 */
public class ClickHandler extends Handler{
	private Logger log = LoggerFactory.getLogger(ClickHandler.class);

	@Override
	public String handlerRequest(JSONObject jsonObj) {
		log.info("CLICK event 菜单.。。。。");
		String fromUser = jsonObj.getString("FromUserName");
		String key = jsonObj.getString("EventKey");
		
		if("m3s1".equals(key)){
			return null;
		}
		
		//本测试帐号中，此菜单返回文本类型的消息
		TextMsg text = new TextMsg();
		setCommonValuesInMsg(jsonObj, text);
		
		if("m3s2".equals(key)){
			//引导用户的URL, 可以设置不同的state的值，在Controller那里可以根据这个值来导向不同的页面
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
			
			String redirecturl = PropertyUtil.getStringProperty("appUrlPrefix") + "reqeustAuth.do";
			try {
				text.setContent(url.replace("REDIRECT_URI", URLEncoder.encode(redirecturl, "UTF-8"))
									.replace("APPID", PropertyUtil.getAppId()));
			} catch (UnsupportedEncodingException e) {
				log.error("",e);
			}
			
		}else if("m3s5".equals(key)){
			
			//生成的二维码必须先提交到微信服务器，才能发送给用户，微信又要求5秒内返回，先回复""，再用客服接口回复图片
			Process2DCode th = new Process2DCode(jsonObj.getString("FromUserName"));
			th.start();
			
			return "";
		} else{
			text.setContent(fromUser + "点击" + key);
		}
		
		return text.toXml();
	}
	
	private class Process2DCode extends Thread{
		private String userId;
		
		Process2DCode(String userId){
			this.userId = userId;
		}
		
		public void run(){
			String ticket = HttpUtil.getTicketForTemp2DCode(3600, 88888);
			try {
				String encode = URLEncoder.encode(ticket, "UTF-8");
				
				CloseableHttpClient httpclient = null;
				httpclient = HttpClients.createDefault();
				
				HttpGet get = new HttpGet(Constants.GET_2D_CODE.replace("TICKET", encode));
				CloseableHttpResponse response = httpclient.execute(get);
				InputStream is = response.getEntity().getContent();
				log.info("1111111111111");
				
				URL url = Thread.currentThread().getContextClassLoader().getResource("/");
				log.info(url.getPath());
				log.info(url.toURI().getPath());
				
				String filePath = url.getPath() + "temp" + File.separator + "2d.jpg";
				log.info("temp filePath: " + filePath);
				
				File f = new File(filePath);
				if(!f.exists()){
					FileOutputStream os = new FileOutputStream(f);
					byte[] b = new byte[1024];
					int read = 0;
					while((read = is.read(b)) != -1){
						os.write(b, 0, read);
					}
					os.flush();
					os.close();
				}
				String mediaId = HttpUtil.postFileToWeiXinServer("image", f);
				log.info("mediaId: " + mediaId);
				String data = "{\"touser\":\"%s\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"%s\" }}";
				HttpUtil.postToWeiXinServer(Constants.POST_RESPONSE_MSG, String.format(data, userId, mediaId));
				
			} catch (Exception e) {
				log.error("",e);
			}
		}
	}
}
