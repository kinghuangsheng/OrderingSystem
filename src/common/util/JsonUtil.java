package common.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
	
	
	public static String toJsonString(Object o){
		return JSONObject.toJSONString(o);
	}
	
	public static <T> List<T> parseArray(String text, Class<T> clazz){
		return JSONArray.parseArray(text, clazz);
	}
	public static <T> T parseObject(String text, Class<T> clazz){
		return JSON.parseObject(text, clazz);
	}

}
