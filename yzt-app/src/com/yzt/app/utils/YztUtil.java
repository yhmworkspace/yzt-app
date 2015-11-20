package com.yzt.app.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;

public class YztUtil {
	// 创建文件
	public static File createNewFile(String filepath, String filename) {
		if (filepath.startsWith("file:")) {
			filepath = StringUtils.substringAfter(filepath, ":");
		}
		File localFiel = new File(filepath);
		if (!localFiel.exists()) {
			localFiel.mkdirs();
		}
		return new File(localFiel, filename);
	}

	// 清空文件夹
	public static void clearDir(String path) {
		File dir = new File(path);
		if (dir.exists() && dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				if (file.exists()) {
					file.delete();
				}
			}
		}
	}

	// 删除文件
	public static void deleteFile(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}
	//写入文件
	public static void writeFile(InputStream inputStream, String filepath,
			String filename) throws IllegalStateException, IOException {
		//文件输出流
		FileOutputStream file = new FileOutputStream(createNewFile(filepath,
				filename));
		IOUtils.copy(inputStream, file);
		inputStream.close();
		file.close();
	}

	public static InputStream getInputStreamFromFilepath(String path)
			throws MalformedURLException, IOException {
		String prefix = StringUtils.substringBefore(path, ":");
		if (StringUtils.equals(prefix, "file")) {
			return new FileSystemResource(StringUtils.substringAfter(path, ":"))
					.getInputStream();
		} else if (StringUtils.equals(prefix, "http")) {
			return new UrlResource(path).getInputStream();
		}
		return null;
	}
	
	public static boolean isNumeric(String str){
		for (int i = 0; i < str.length(); i++){
		   if (!Character.isDigit(str.charAt(i))){
			   return false;
		   }
		}
	  return true;
	}
	/*
	 * 移动：13 456789, 15 01789, 18 78
	 * 联通：13 012,15 256， 18 56
	 * 电信: 133,153, 18 09
	 */
	public static boolean isMobileNO(String mobile){
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobile);
		return m.matches();
	}
	
	public static final String[] zodiacArr = { "猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊" };    
    
    public static final String[] constellationArr = { "水瓶座", "双鱼座", "牡羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座",    
            "天蝎座", "射手座", "魔羯座" };    
       
    public static final int[] constellationEdgeDay = { 20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22 };    
       
    /**   
     * 根据日期获取生肖   
     * @return   
     */   
    public static String date2Zodica(Date date) { 
    	Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return zodiacArr[cal.get(Calendar.YEAR) % 12];    
    }    
       
    /**   
     * 根据日期获取星座   
     * @param date   
     * @return   
     */   
    public static String date2Constellation(Date date) {   
    	if (date == null) {
            return "";
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);    
        int day = cal.get(Calendar.DAY_OF_MONTH);    
        if (day < constellationEdgeDay[month]) {    
            month = month - 1;    
        }    
        if (month >= 0) {    
            return constellationArr[month];    
        }    
        //default to return 魔羯    
        return constellationArr[11];    
    }   
    
    private static final double EARTH_RADIUS = 6378137;
    private static double rad(double d)
    {
       return d * Math.PI / 180.0;
    }
    /*
     * java通过经纬度计算两个点的之间的距离的算法
     */
    public static double GetDistance(double lng1, double lat1, double lng2, double lat2)
    {
       double radLat1 = rad(lat1);
       double radLat2 = rad(lat2);
       double a = radLat1 - radLat2;
       double b = rad(lng1) - rad(lng2);
       double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) + 
        Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
       s = s * EARTH_RADIUS;
       s = Math.round(s * 10000) / 10000;
       return s;
    }

}
