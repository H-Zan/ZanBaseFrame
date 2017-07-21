package com.huzan.zanbaseframe.base.util.normal;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ZAN on 2017/7/19.
 */

public class CheckUtil {
	
	public static boolean isEmpty(String s){
		return s == null || "".equals(s);
	}
	
	public static boolean isEmpty(List list) {
		return !(list != null && list.size() > 0);
	}
	
	public static boolean isEmpty(Map map) {
		return !(map != null && map.size() > 0);
	}
	
	public static boolean isMobileNumber(String mobiles) {
		
		Pattern p = Pattern.compile("^1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\\d{8}$");
		
		Matcher m = p.matcher(mobiles);
		
		return m.matches();
		
	}
}
