package org.shj.weixin.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.activation.MimetypesFileTypeMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.shj.weixin.Constants;
import org.shj.weixin.entity.AccessToken;
import org.shj.weixin.entity.AccessTokenHolder;
import org.shj.weixin.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

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
			
			HttpGet get = new HttpGet(getTokenUrl);
			CloseableHttpResponse response = httpclient.execute(get);
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
	
	public static String getJsapiTicket(){
		try{
			String getTokenUrl = Constants.GET_JSAPI_TICKET.replace("ACCESS_TOKEN", AccessTokenHolder.instance.getAccessToken());
			JSONObject obj = getToWeiXinServer(getTokenUrl);
			return obj.getString("ticket");
			
		}catch(Exception e){
        	log.error("There is error when get access_token.", e);
        }
		return null;
	}
	
	/**
	 * 根据OPENID获取用户信息
	 * 
	 * @param openId 微信用户的OpenId
	 * @return
	 */
	public static UserInfo getUserInfoByOpenId(String openId){
		String url = Constants.GET_USR_INFO.replace("OPENID", openId);
		return HttpUtil.getToWeiXinServer(url, UserInfo.class);
	}
	
	/**
	 * 
	 * @param expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
	 * @param sceneId 场景值ID，临时二维码时为32位非0整型
	 * @return
	 */
	public static String getTicketForTemp2DCode(int expireSeconds, int sceneId){
		return getTicketFor2DCode(expireSeconds, "QR_SCENE", sceneId, null);
	}
	
	public static String getTicketFor2DCode(int sceneId){
		return getTicketFor2DCode(0, "QR_LIMIT_SCENE", sceneId, null);
	}
	
	public static String getTicketForString2DCode(String sceneIdStr){
		return getTicketFor2DCode(0, "QR_LIMIT_STR_SCENE", 0, sceneIdStr);
	}
	
	/**
	 * 
	 * @param expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
	 * @param actionName 二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值
	 * @param sceneId 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
	 * @return
	 */
	private static String getTicketFor2DCode(int expireSeconds, String actionName,  int sceneId, String sceneIdStr){
		String postData = null;
		if("QR_SCENE".equals(actionName)){
			String data = "{\"expire_seconds\": %d, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";
			postData = String.format(data, expireSeconds, sceneId);
			
		}else if("QR_LIMIT_SCENE".equals(actionName)){
			String data = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";
			postData = String.format(data, sceneId);
			
		}else if("QR_LIMIT_STR_SCENE".equals(actionName)){
			String data = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": \"%s\"}}}";
			postData = String.format(data, sceneIdStr);
		}
		
		JSONObject json = postToWeiXinServer(Constants.POST_2D_CODE_TICKET, postData);
		return json.getString("ticket");
		
	}
	
	/**
	 * 
	 * @param fileType 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	 * @param file
	 * @return media_id
	 */
	public static String postFileToWeiXinServer(String fileType, File file){
		String urlStr = Constants.POST_FILE.replace("ACCESS_TOKEN", AccessTokenHolder.instance.getAccessToken())
				.replace("TYPE", fileType);
		String res = "";
		HttpURLConnection conn = null;
		String BOUNDARY = "---------------------------"	+ System.currentTimeMillis(); // boundary就是request头和上传文件内容的分隔符
		try {

			URL url = new URL(urlStr);

			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(20000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");
			conn.setRequestProperty("Content-Type",	"multipart/form-data; boundary=" + BOUNDARY);

			OutputStream out = new DataOutputStream(conn.getOutputStream());

			// file
			String filename = file.getName();
			String contentType = new MimetypesFileTypeMap()
					.getContentType(file);
			if (filename.endsWith(".png")) {
				contentType = "image/png";
			} else if (filename.endsWith(".jpg")) {
				contentType = "image/jpeg";
			} else if (filename.endsWith(".mp4")) {
				contentType = "video/mpeg4";
			}
			if (contentType == null || contentType.equals("")) {
				contentType = "application/octet-stream";
			}

			StringBuffer strBuf = new StringBuffer();
			strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
			strBuf.append("Content-Disposition: form-data; name=\"file\"; filename=\""
					+ filename + "\"\r\n");
			strBuf.append("Content-Type:" + contentType + "\r\n\r\n");

			out.write(strBuf.toString().getBytes("utf-8"));

			DataInputStream in = new DataInputStream(new FileInputStream(file));
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			in.close();

			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");
			out.write(endData);
			out.flush();
			out.close();

			// 读取返回数据
			strBuf = new StringBuffer();

			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				strBuf.append(line).append("\n");
			}
			res = strBuf.toString();
			reader.close();
			reader = null;
		} catch (Exception e) {
			System.out.println("发送POST请求出错。" + urlStr);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		log.info("Response: " + res);
		return JsonUtil.getJSONFromString(res).getString("media_id");
	}
	
	/**
	 * 
	 * @param fileType 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	 * @param file
	 * @return media_id
	 */
	public static String postFileToWeiXinServer(String fileType, InputStream is){
		String newUrl = Constants.POST_FILE.replace("ACCESS_TOKEN", AccessTokenHolder.instance.getAccessToken())
								.replace("TYPE", fileType);
		
		log.info("Request URL: " + newUrl);
		
		CloseableHttpClient httpclient = null;
		try{
			httpclient = HttpClients.createDefault();
			CloseableHttpResponse response = null;
			
			HttpPost post = new HttpPost(newUrl);
			InputStreamEntity isEntity = new InputStreamEntity(is);
			post.setEntity(isEntity);
			response = httpclient.execute(post);
			
			String responsStr = getResponseInfo(response);//{"type":"TYPE","media_id":"MEDIA_ID","created_at":123456789}
			
			return JsonUtil.getJSONFromString(responsStr).getString("media_id");
			

		}catch(Exception e){
        	log.error("There is error when get access_token.", e);
		}
		return null;
	}
	
	public static JSONObject postToWeiXinServer(String url, String jsonStr){
		return requestToWeiXinServer(url, JSONObject.class, true, jsonStr);
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	public static JSONObject getToWeiXinServer(String url){
		return requestToWeiXinServer(url, JSONObject.class, false, null);
	}
	
	/**
	 * 向微信服务器发送Get请求
	 * @param url
	 * @param clazz 微信服务器返回的Json信息转化成的class
	 * @return
	 */
	public static <T> T getToWeiXinServer(String url, Class<T> clazz){
		return requestToWeiXinServer(url, clazz, false, null);
	}
	
	/**
	 * 向微信服务器发送Post请求
	 * @param url
	 * @param clazz 微信服务器返回的Json信息转化成的class
	 * @return
	 */
	public static <T> T postToWeiXinServer(String jsonStr, String url, Class<T> clazz){
		return requestToWeiXinServer(url, clazz, true, jsonStr);
	}
	
	private static <T> T requestToWeiXinServer(String url, Class<T> clazz, boolean isPost, String jsonStr){
		String newUrl = url;
		
		if(url.contains("ACCESS_TOKEN")){
			newUrl = url.replace("ACCESS_TOKEN", AccessTokenHolder.instance.getAccessToken());
		}
		
		log.info("Request URL: " + newUrl);
		if(isPost){
			log.info("Request body: " + jsonStr);
		}
		
		CloseableHttpClient httpclient = null;
		try{
			httpclient = HttpClients.createDefault();
			CloseableHttpResponse response = null;
			
			if(isPost){
				HttpPost post = new HttpPost(newUrl);
				StringEntity jsonEntity = new StringEntity(jsonStr, ContentType.APPLICATION_JSON);
				post.setEntity(jsonEntity);
				response = httpclient.execute(post);
				
			}else{
				HttpGet get = new HttpGet(newUrl);
				response = httpclient.execute(get);
			}
			
			String responsStr = getResponseInfo(response);
			
			if("com.alibaba.fastjson.JSONObject".equals(clazz.getName())){
				return (T)JsonUtil.getJSONFromString(responsStr);
			}else{
				return JSON.parseObject(responsStr, clazz);
			}

		}catch(Exception e){
			if(httpclient != null){
				try {
					httpclient.close();
				} catch (IOException e1) {
					//do nothing
				}
			}
        	log.error("There is error when get access_token.", e);
		}
		return null;
	}
	
	private static String getResponseInfo(HttpResponse response) throws IOException{
		HttpEntity entity = response.getEntity();
		InputStream is = entity.getContent();
		byte[] bytes = new byte[(int)entity.getContentLength()];
		is.read(bytes);
		
		String resStr = new String(bytes, "UTF-8");
		log.info("Response: ");
		log.info(resStr);
		EntityUtils.consume(entity);
		
		//TODO: Weixin Server return error
		
		return resStr;
	}
	
	private static <T> T convertResponse(HttpResponse response, Class<T> clazz) throws IOException{
		return JSON.parseObject(getResponseInfo(response), clazz);
	}
}
