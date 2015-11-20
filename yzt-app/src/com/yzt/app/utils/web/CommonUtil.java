package com.yzt.app.utils.web;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;


public class CommonUtil {
	/** * @function != null  */
	public static Boolean notEmpty(Object objct){
		if(objct != null ) return true;
		return false;
	}
	/** * @function != null && length > 0 */
	public static Boolean notEmpty(String objct){
		if(objct != null && objct.trim().length() > 0) return true;
		return false;
	}
	/** * @function != null && size > 0 */
	public static Boolean notEmpty(List<?> list){
		if(list != null && list.size() > 0) return true;
		return false;
	}
	/** * @function != null && size > 0 */
	public static Boolean notEmpty(Set<?> set){
		if(set != null && set.size() > 0) return true;
		return false;
	}
	/** * @function != null */
	public static Boolean notEmpty(Map<?,?> map){
		if(map != null && !map.isEmpty()) return true;
		return false;
	}
	
	/** * @function != null */
	public static Boolean notEmpty(Map<?,?> map,Object key){
		if(map != null && map.get(key) != null) return true;
		return false;
	}
	
	/** * @function 判断是否  > 0 */
	public static Boolean greaterThanZero(Long l){
		if(notEmpty(l) && l > 0) return true;
		return false;
	}
	/** * @function 判断是否  > 0 */
	public static Boolean greaterThanZero(Integer l){
		if(l == null) return false;
		return greaterThanZero(Long.valueOf(l));
	}
	/*** @function 判断是否  < 0  */
	public static Boolean lessThanZero(Long l){
		if(notEmpty(l) && l < 0) return true;
		return false;
	}
	/*** @function 判断是否  < 0  */
	public static Boolean lessThanZero(Integer l){
		if(l == null) return false;
		return lessThanZero(Long.valueOf(l));
	}
	
	public static List<Long> parseStringToLong(String string,String splitString){
		List<Long> list = new ArrayList<Long>();
		String[] s = string.split(splitString);
		for(String o:s){
			if(CommonUtil.notEmpty(o)){
				list.add(Long.valueOf(o));
			}
		}
		return list;
	}
	
	public static List<Integer> parseStringToInteger(String string,String splitString){
		List<Integer> list = new ArrayList<Integer>();
		String[] s = string.split(splitString);
		for(String o:s){
			if(CommonUtil.notEmpty(o)){
				list.add(Integer.valueOf(o));
			}
		}
		return list;
	}
	
	public static String toString(Object o){
		if(o != null) return o.toString();
		return "";
	}
	
	/**
	 * 生成随机字符（包含数字和字母）
	 * @param pwd_len 字符长度
	 * @author liuxue
	 * @date 2013-7-29 上午11:10:27
	 */
	public static String randomString(int pwd_len){
		//35是因为数组是从0开始的，24个字母（不包含字母l，字母o）+10个数字  
		final int  maxNum = 34;
		int i;  //生成的随机数
		int count = 0; //生成的密码的长度
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
		     'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
		    'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };  
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while(count < pwd_len){
		//生成随机数，取绝对值，防止生成负数，
		    i = Math.abs(r.nextInt(maxNum));  //生成的数最大为34-1
		    if (i >= 0 && i < str.length) {
		    	pwd.append(str[i]);
		    	count ++;
		   }
		}
	    return pwd.toString();
	}
	
	/**
	 * 生成随机字符（包含数字和字母）
	 * @param pwd_len 字符长度
	 * @author liuxue
	 * @date 2013-7-29 上午11:10:27
	 */
	public static String randomNumber(int pwd_len){
		final int  maxNum = 11;
		int i; 
		int count = 0; 
		char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };  
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while(count < pwd_len){
		//生成随机数，取绝对值，防止生成负数，
		    i = Math.abs(r.nextInt(maxNum)); 
		    if (i >= 0 && i < str.length) {
		    	pwd.append(str[i]);
		    	count ++;
		   }
		}
	    return pwd.toString();
	}
	
	
	/**
	 * 按给定格式获取当前时间字符串
	 * 
	 * @param format
	 * @return
	 */
	public static String getDateTimeStr(String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateString = formatter.format(new Date());
		return dateString;
	}

	/**
	 * 按给定格式给定时间字符串
	 * 
	 * @param format
	 * @param inDate
	 * @return
	 */
	public static String getDateTimeStr(String format, Date inDate) {
		if (inDate == null)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateString = formatter.format(inDate);
		return dateString;
	}
	
}
