package com.yzt.app.utils.web;

public class ResultBase {
	public static final String RESULT_SUCC = "0";
	public static final String RESULT_FAIL = "99";
	
	protected String result="2";
	protected String message="";
	protected Object obj;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		if(null==this.message || this.message.length()==0){
			if(this.result == RESULT_SUCC) this.message = "加载成功";
			else this.message = "加载失败";
		}
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	public String toString(){
		return obj.toString()+result+message;
	}
}
