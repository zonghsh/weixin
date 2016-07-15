package org.shj.weixin.menu;

import java.io.InputStream;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.shj.weixin.Constants;

public class CreateMenu {

	private final static int CONNECTIONTIMEOUT = 10000;// http链接超时
	private final static int REQUESTTIMEOUT = 20000;// http数据请求超时

	private String url = null;
	private Map<String, Object> paras = null;// post的StringBody
	
	public CreateMenu(){}

	public CreateMenu(String url, Map<String, Object> paras) {
		this.url = url;
		this.paras = paras;
	}
	
	public void run() {  
        try{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String getTokenUrl = Constants.GET_TOKEN.replace("APPID", "wx4f613ff9b64ccf37")
								   .replace("APPSECRET", "73e0c526b14c0f2ba1e15beb29eac4e7");
		HttpGet httpGet = new HttpGet(getTokenUrl);
		CloseableHttpResponse response = httpclient.execute(httpGet);
		
		HttpEntity entity = response.getEntity();
		InputStream is = entity.getContent();
		byte[] bytes = new byte[(int)entity.getContentLength()];
		is.read(bytes);
		String resStr = new String(bytes, "UTF-8");
		
		System.out.println(resStr);
		
		EntityUtils.consume(entity);
		
		
		//HttpPost httpPost = new HttpPost(Constants.CREATE_MENU);
        
        }catch(Exception e){
        	e.printStackTrace();
        }
	}

	public static void main(String[] args) {
		CreateMenu cm = new CreateMenu();
		cm.run();
	}
}
