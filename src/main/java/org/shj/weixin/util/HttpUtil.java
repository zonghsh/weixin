package org.shj.weixin.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.shj.weixin.Constants;
import org.shj.weixin.entity.AccessToken;
import org.shj.weixin.entity.AccessTokenHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class HttpUtil {
	private static Logger log = LoggerFactory.getLogger(HttpUtil.class);

	/**
	 * The WeiXin server will return {"access_token":"ACCESS_TOKEN","expires_in":7200}
	 * @return
	 */
	public static String getAccessToken(){
		CloseableHttpClient httpclient = null;
		try{
			httpclient = HttpClients.createDefault();
			
			String getTokenUrl = Constants.GET_TOKEN.replace("APPID", PropertyUtil.getAppId())
									   .replace("APPSECRET", PropertyUtil.getAppsecret());
			
			HttpGet httpGet = new HttpGet(getTokenUrl);
			CloseableHttpResponse response = httpclient.execute(httpGet);
			AccessToken token = convertResponse(response, AccessToken.class);
			
			return token.getAccessToken();
			
		}catch(Exception e){
        	log.error("There is error when get access_token.", e);
        }finally{
        	if(httpclient != null){
        		try {
					httpclient.close();
				} catch (IOException e) {
					//do nothing
				}
        	}
        	
        }
		return null;
	}
	
	public static <T> T postToWeiXinServer(String jsonStr, String url, Class<T> clazz){
		String accessToken = AccessTokenHolder.instance.getAccessToken();
		String newUrl = url.replace("ACCESS_TOKEN", accessToken);
		
		log.info("Request URL: " + newUrl);
		log.info("Request body: ");
		log.info(jsonStr);
		
		CloseableHttpClient httpclient = null;
		try{
			httpclient = HttpClients.createDefault();
			HttpPost post = new HttpPost(newUrl);
			
			StringEntity jsonEntity = new StringEntity(jsonStr, ContentType.APPLICATION_JSON);
			post.setEntity(jsonEntity);
						
			CloseableHttpResponse response = httpclient.execute(post);

			return convertResponse(response, clazz);
			
		}catch(Exception e){
        	log.error("There is error when get access_token.", e);
        	
        }finally{
        	if(httpclient != null){
        		try {
					httpclient.close();
				} catch (IOException e) {
					//do nothing
				}
        	}
        	
        }
		return null;
	}
	
	private static <T> T convertResponse(HttpResponse response, Class<T> clazz) throws IOException{
		HttpEntity entity = response.getEntity();
		InputStream is = entity.getContent();
		byte[] bytes = new byte[(int)entity.getContentLength()];
		is.read(bytes);
		
		String resStr = new String(bytes, "UTF-8");
		log.info("Response: ");
		log.info(resStr);
		EntityUtils.consume(entity);
		
		//TODO: Weixin Server return error
		
		return JSON.parseObject(resStr, clazz);
	}
}
