package org.shj.weixin.http;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.shj.weixin.Constants;
import org.shj.weixin.util.JsonUtil;
import org.shj.weixin.util.PropertyUtil;

import com.alibaba.fastjson.JSON;

public class HttpUtil {

	/**
	 * 
	 * @return
	 */
	public static String getAccessToken(){
		try{
			CloseableHttpClient httpclient = HttpClients.createDefault();
			
			String getTokenUrl = Constants.GET_TOKEN.replace("APPID", PropertyUtil.getAppId())
									   .replace("APPSECRET", PropertyUtil.getAppsecret());
			HttpGet httpGet = new HttpGet(getTokenUrl);
			CloseableHttpResponse response = httpclient.execute(httpGet);
			
			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			byte[] bytes = new byte[(int)entity.getContentLength()];
			is.read(bytes);
			
			//{"access_token":"ACCESS_TOKEN","expires_in":7200}
			String resStr = new String(bytes, "UTF-8");
			
			System.out.println(resStr);
			
			EntityUtils.consume(entity);
			
			return JsonUtil.getStringFromJsonStr(resStr, "access_token");
			
		}catch(Exception e){
        	e.printStackTrace();
        }
		return "";
	}
}
