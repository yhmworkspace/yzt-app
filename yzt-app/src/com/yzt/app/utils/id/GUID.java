package com.yzt.app.utils.id;

public final class GUID extends UUID{
	 private long m_count = 0L;

	  private GUID()
	  {
	    next();
	  }

	  protected void next()
	  {
	    this.m_hiTag = (EPOCH + JVMHASH * 4294967296L ^ MACHINEID);
	    this.m_loTag = (EPOCH * 2147483647L + ++this.m_count);
	    this.m_uuid = toString(toByteArray());
	  }
}
