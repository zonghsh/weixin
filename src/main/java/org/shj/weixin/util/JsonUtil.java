package org.shj.weixin.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtil {

	/**
     * 默认json格式化方式
     */
    public static final SerializerFeature[] DEFAULT_FORMAT = {
    		SerializerFeature.WriteDateUseDateFormat, 
    		SerializerFeature.WriteEnumUsingToString,
            SerializerFeature.WriteNonStringKeyAsString, 
            SerializerFeature.QuoteFieldNames, 
            SerializerFeature.SkipTransientField,
            SerializerFeature.SortField};
    
    public static final SerializerFeature[] PRETTY_FORMAT = {
		SerializerFeature.WriteDateUseDateFormat, 
		SerializerFeature.WriteEnumUsingToString,
        SerializerFeature.WriteNonStringKeyAsString, 
        SerializerFeature.QuoteFieldNames, 
        SerializerFeature.SkipTransientField,
        SerializerFeature.SortField, 
        SerializerFeature.PrettyFormat};
    
    public static String getStringFromJsonStr(String jsonStr, String key){
    	return JSON.parseObject(jsonStr).getString(key);
    }
    
    public static String prettyFormatJson(String jsonString) {
        return JSON.toJSONString(getJSONFromString(jsonString), true);
    }
    
    public static JSONObject getJSONFromString(final String jsonString) {
        if (StringUtil.isEmpty(jsonString)) {
            return new JSONObject();
        }
        return JSON.parseObject(jsonString);
    }
    
    public static String toJSONString(Object object){
    	return toJSONString(object, false);
    }
    
    public static String toJSONString(Object object, boolean prettyFormat){
    	return JSON.toJSONString(object, prettyFormat? PRETTY_FORMAT :DEFAULT_FORMAT);
    }
}
