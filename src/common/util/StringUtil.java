package common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	public static boolean checkFail(String target, int minLength, int maxLength, String pattern){
		if(target == null || target.length() < minLength || target.length() > maxLength){
			return true;
		}
		if(pattern == null){
			return false;
		}
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(target);
		return !m.matches();
	}
	
	public static boolean isEmpty(Object str) {
		return (str == null || "".equals(str));
	}

}
