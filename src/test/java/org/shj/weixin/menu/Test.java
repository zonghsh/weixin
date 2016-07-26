package org.shj.weixin.menu;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.shj.weixin.Constants;

public class Test {

	public static void main(String[] args){
		try {
			String encode = URLEncoder.encode("gQEB8ToAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL3FqcXlCNi1rS0g1UjR0NXdJQlI4AAIEs3WXVwMEEA4AAA==", "UTF-8");
			
			CloseableHttpClient httpclient = null;
			httpclient = HttpClients.createDefault();
			
			HttpGet get = new HttpGet(Constants.GET_2D_CODE.replace("TICKET", encode));
			CloseableHttpResponse response = httpclient.execute(get);
			InputStream is = response.getEntity().getContent();
			
			FileOutputStream os = new FileOutputStream("F:\\download\\2d.jpg");
			byte[] b = new byte[1024];
			int read = 0;
			while((read = is.read(b)) != -1){
				os.write(b, 0, read);
			}
			
			os.flush();
			os.close();
			//String mediaId = HttpUtil.postFileToWeiXinServer("image", is);
			
			//String data = "{\"touser\":\"%s\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"%s\" }}";
			//HttpUtil.postToWeiXinServer(Constants.POST_RESPONSE_MSG, String.format(data, userId, mediaId));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
