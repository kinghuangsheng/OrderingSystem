package common.util;

import com.alibaba.fastjson.JSONObject;

public class Object2JsonUtil {
	
	
	public static String toJsonString(Object o){
		return JSONObject.toJSONString(o);
	}

}
