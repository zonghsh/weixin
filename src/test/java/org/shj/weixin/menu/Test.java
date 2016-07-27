package org.shj.weixin.menu;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.shj.weixin.Constants;
import org.shj.weixin.entity.AccessTokenHolder;
import org.shj.weixin.util.HttpUtil;

import com.alibaba.fastjson.JSONObject;

public class Test {

	public static void main(String[] args) {
		AccessTokenHolder.instance
				.setAccessToken("hQ7jxz_8buEroEW_CRQPTPl_Tn3wtw4hlKv8wfJsVDvN-Fs6DsvrlDvtKwxN20vSCyVqwV5D1jDFUUB7Ew7aq0y99NEhXMvxEVJY9gLPj6vYCNhCgP5W6LC8p8O7QKHSYUQiABAFBX");

		
		/*
		 * try { String encode = URLEncoder.encode(
		 * "gQEB8ToAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL3FqcXlCNi1rS0g1UjR0NXdJQlI4AAIEs3WXVwMEEA4AAA=="
		 * , "UTF-8");
		 * 
		 * CloseableHttpClient httpclient = null; httpclient =
		 * HttpClients.createDefault();
		 * 
		 * HttpGet get = new HttpGet(Constants.GET_2D_CODE.replace("TICKET",
		 * encode)); CloseableHttpResponse response = httpclient.execute(get);
		 * InputStream is = response.getEntity().getContent();
		 * 
		 * File f = new File("F:\\download\\2d.jpg"); if(!f.exists()){
		 * FileOutputStream os = new FileOutputStream(f); byte[] b = new
		 * byte[1024]; int read = 0; while((read = is.read(b)) != -1){
		 * os.write(b, 0, read); }
		 * 
		 * os.flush(); os.close(); }
		 * 
		 * AccessTokenHolder.instance.setAccessToken(
		 * "hQ7jxz_8buEroEW_CRQPTPl_Tn3wtw4hlKv8wfJsVDvN-Fs6DsvrlDvtKwxN20vSCyVqwV5D1jDFUUB7Ew7aq0y99NEhXMvxEVJY9gLPj6vYCNhCgP5W6LC8p8O7QKHSYUQiABAFBX"
		 * ); String mediaId = HttpUtil.postFileToWeiXinServer("image", f);
		 * System.out.println(mediaId);
		 * 
		 * //String data =
		 * "{\"touser\":\"%s\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"%s\" }}"
		 * ; //HttpUtil.postToWeiXinServer(Constants.POST_RESPONSE_MSG,
		 * String.format(data, userId, mediaId));
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */
	}

	
}
