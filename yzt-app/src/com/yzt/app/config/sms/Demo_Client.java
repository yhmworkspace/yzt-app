package com.yzt.app.config.sms;

import java.io.UnsupportedEncodingException;
import java.net.*;

public class Demo_Client {
	
	public static String sentContentTo(String mobile,String smscontent)throws UnsupportedEncodingException{
		String sn="SDK-BBX-010-23257";
		String pwd="55d2-d#d";
		/*
		String ct = "你的验证码："+smscontent+",请尽快完成操作[柚子糖科技]";
		Client client=new Client(sn,pwd);
	
		//短信发送	
	    String   content   =   java.net.URLEncoder.encode(ct,  "utf-8");  
		String result_mt = client.mdsmssend(mobile, content, "", "", "", "");
		*/
		String result_mt = (int)((Math.random()*9)*100000) +"";
		return result_mt;
	}


	public static void main(String[] args) throws UnsupportedEncodingException
	{

		String sn="SDK-BBX-010-23257";
		String pwd="55d2-d#d";
		Client client=new Client(sn,pwd);

		//短信发送	
        String   content   =   java.net.URLEncoder.encode("接口测试[您报备的签名:123456,09]",  "utf-8");  
		String result_mt = client.mdsmssend("18622137225", content, "", "", "", "");		
	
		System.out.print(result_mt);
		//个性短信发送
//		String result_gxmt = client.mdgxsend("138*****000", "测试", "", "", "", "");
//		System.out.print(result_gxmt);

	}
}
