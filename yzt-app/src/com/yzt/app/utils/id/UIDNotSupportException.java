package com.yzt.app.utils.id;

public class UIDNotSupportException extends ClassNotFoundException{
	 private static final long serialVersionUID = 4643285680438929579L;

	//~ 构造器 ///////////////////////////////////////////////////////////
    /**
     * 带Msg的默认构造器
     *
     *@param s  Description of the Parameter
     */
	  public UIDNotSupportException(String s)
	  {
	    super(s, null);
	  }

}
