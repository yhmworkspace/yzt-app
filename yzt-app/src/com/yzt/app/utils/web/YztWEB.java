package com.yzt.app.utils.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class YztWEB {
	
	/**
	 * 与页面交互（jznrinfo.xml里面）
	 * 读取一个文件，保存在一个String中
	 * @param triggerURL
	 * @return
	 */
	public static String HttpRetrieve(String triggerURL) {
		if(triggerURL == null){
			return null;
		}
		StringBuffer document = new StringBuffer();
		try {
			URL url = new URL(triggerURL);
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				document.append(line);
			}
			reader.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return document.toString();
	}

}
