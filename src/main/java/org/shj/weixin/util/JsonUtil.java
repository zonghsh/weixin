package org.shj.weixin.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtil {

	/**
     * 默认json格式化方式
     */
    public static final SerializerFeature[] DEFAULT_FORMAT = {SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteEnumUsingToString,
            SerializerFeature.WriteNonStringKeyAsString, SerializerFeature.QuoteFieldNames, SerializerFeature.SkipTransientField,
            SerializerFeature.SortField, SerializerFeature.PrettyFormat};
    
    public static String getStringFromJsonStr(String jsonStr, String key){
    	return JSON.parseObject(jsonStr).getString(key);
    }
}
