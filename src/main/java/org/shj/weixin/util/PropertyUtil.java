package org.shj.weixin.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyUtil {
	
	private static Logger log =  LoggerFactory.getLogger(PropertyUtil.class);

	private static Properties props;
	
	static{
		props = new Properties();
		InputStream is = null;
		
		try{
			is = Thread.currentThread().getContextClassLoader().getResource("weixin.properties").openStream();
			props.load(is);
			
		}catch(IOException ioe){
			log.error("weixin.properties doesn't be found.");
		}
		
	}
	
	public static String getStringProperty(String key){
		return props.getProperty(key);
	}
	
	public static String getAppId(){
		return props.getProperty("appID");
	}
	
	public static String getAppsecret(){
		return props.getProperty("appsecret");
	}
	
	public static String getToken(){
		return props.getProperty("Token");
	}
}