package com.yzt.app.utils.web;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;


public class Base64 {
	 private static final char[] legalChars = "ghijk67stuJKLM89rvwxyzAlmnOUV+/abcdfePQRSTopqCDEFG012345BHINWXYZ".toCharArray();//字典内顺序任意更改，只要内容保证不缺就行
	    
	    
	    public static String encode(String data) throws UnsupportedEncodingException{
	    	return encode(data.getBytes("utf-8"));
	    }
	    public static String decodeToString(String s) throws UnsupportedEncodingException{
	    	return new String(decode(s), "utf-8");
	    }
	    
	    /**
	     * data[]进行编码
	     * @param data
	     * @return
	     */
	    public static String encode(byte[] data) {
	        int start = 0;
	        int len = data.length;
	        StringBuffer buf = new StringBuffer(data.length * 3 / 2);
	        int end = len - 3;
	        int i = start;
	        int n = 0;
	        while (i <= end) {
	            int d = ((((int) data[i]) & 0x0ff) << 16)
	                    | ((((int) data[i + 1]) & 0x0ff) << 8)
	                    | (((int) data[i + 2]) & 0x0ff);
	            buf.append(legalChars[(d >> 18) & 63]);
	            buf.append(legalChars[(d >> 12) & 63]);
	            buf.append(legalChars[(d >> 6) & 63]);
	            buf.append(legalChars[d & 63]);
	            i += 3;
	            if (n++ >= 14) {
	                n = 0;
	                buf.append(" ");
	            }
	        }
	        if (i == start + len - 2) {
	            int d = ((((int) data[i]) & 0x0ff) << 16)
	                    | ((((int) data[i + 1]) & 255) << 8);
	            buf.append(legalChars[(d >> 18) & 63]);
	            buf.append(legalChars[(d >> 12) & 63]);
	            buf.append(legalChars[(d >> 6) & 63]);
	            buf.append("=");
	        } else if (i == start + len - 1) {
	            int d = (((int) data[i]) & 0x0ff) << 16;
	            buf.append(legalChars[(d >> 18) & 63]);
	            buf.append(legalChars[(d >> 12) & 63]);
	            buf.append("==");
	        }
	        return buf.toString();
	    }

	    public static byte[] decode(String s) {
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();
	        byte[] decodedBytes;
	        try {
	            decode(s, bos);
	            decodedBytes = bos.toByteArray();
	        } catch (IOException e) {
	        	e.printStackTrace();
	            throw new RuntimeException(e.getMessage());
	        }finally{
	        	if(bos != null){
	        		try {
	        			bos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	        	}
	        }
	        return decodedBytes;
	    }

	    private static void decode(String s, OutputStream os) throws IOException {
	    	HashMap<Character,Integer> hashDecode=new HashMap<Character,Integer>();
	    	if(hashDecode.size()==0){
			    for(int i =0;i<64;i++){
			    	char ch = legalChars[i];
			    	hashDecode.put(ch, i);
			    }
		    }
	    	int i = 0;
	        int len = s.length();
	        while (true) {
	            while (i < len && s.charAt(i) <= ' ')
	                i++;
	            if (i == len)
	                break;
	            int tri = (decode(s.charAt(i),hashDecode) << 18)
	                    + (decode(s.charAt(i + 1),hashDecode) << 12)
	                    + (decode(s.charAt(i + 2),hashDecode) << 6)
	                    + (decode(s.charAt(i + 3),hashDecode));

	            os.write((tri >> 16) & 255);
	            if (s.charAt(i + 2) == '=')
	                break;
	            os.write((tri >> 8) & 255);
	            if (s.charAt(i + 3) == '=')
	                break;
	            os.write(tri & 255);
	            i += 4;
	        }
	    }
	    
	    private static int decode(char c,HashMap<Character,Integer> hashDecode) {
		    if(hashDecode.containsKey(c)){
		    	return hashDecode.get(c);
		    }else if(c =='=')
		    	return 0;
		    else
	             throw new RuntimeException("unexpected code: " + c);
	    }

}
