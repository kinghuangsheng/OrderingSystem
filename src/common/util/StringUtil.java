package common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	public static boolean checkFail(String target, int minLength, int maxLength, String pattern){
		if(target == null || target.length() < minLength || target.length() > maxLength){
			return true;
		}
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(target);
		return !m.matches();
	}

}
