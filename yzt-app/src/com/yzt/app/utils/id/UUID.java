package com.yzt.app.utils.id;

public class UUID extends UIDFactory{
	 //~ 静态属性和初始值 /////////////////////////////////////////////
    /** bits长度 */
    protected static final int BITS8 = 8;

    /** byte长度 */
    protected static final int BYTELEN = 16;

    /** 高位码 */
    protected static final int HIMASK = 240;

    /** 低位8bits码 */
    protected static final int LO8BITMASK = 255;

    /** 低位码 */
    protected static final int LOMASK = 15;

    /** Short上位限制 */
    protected static final long MAX_INT = 32767;

    /** 整型上位限制 */
    protected static final long MAX_LONG = 2147483647;

    //~ 实例属性 ////////////////////////////////////////////////////////
    /** UUID 缓存 */
    protected String m_uuid = null;

    /** Epoch has millisecond */
    /** 高位标志 */
    protected long m_hiTag;

    /** 低位标志 */
    protected long m_loTag;

    //~ 构造器 ///////////////////////////////////////////////////////////
    /**
     * 按照用户数据覆盖的构造器.
     *
     *@param highTag  High order tag
     *@param loTag    Low order tag
     */
    protected UUID(long highTag, long loTag)
    {
        m_hiTag = highTag;
        m_loTag = loTag;
        m_uuid = toString(this.toByteArray());
    }

    /** 默认构造器. */
    protected UUID()
    {
        next();
        m_uuid = toString(this.toByteArray());
    }

    //~ 方法 ////////////////////////////////////////////////////////////////
    /**
     * 获得下一个新的UID.
     *
     *@return   java.lang.String
     */
    public String getNextUID()
    {
        next();
        return m_uuid;
    }

    /**
     * 获得下一个指定长度新的UID.
     *
     *@return   java.lang.String
     */
    public String getNextUID(int len)
    {
        next();
        return m_uuid;
    }

    /**
     * 设置当前UID.
     *
     *@param uidStr         The new uID value
     *@exception Exception  Bad string format
     */
    public void setUID(String uidStr)
        throws Exception
    {
        long loTag = 0L;
        long hiTag = 0L;
        int len = uidStr.length();
        if (32 != len)
        {
            throw new Exception("bad string format");
        }
        int i = 0;
        int idx = 0;
        for (; i < 2; i++)
        {
            loTag = 0L;
            for (int j = 0; j < (len / 2); j++)
            {
                String s = uidStr.substring(idx++, idx);
                int val = Integer.parseInt(s, 16);
                loTag <<= 4;
                loTag |= val;
            }
            if (i == 0)
            {
                hiTag = loTag;
            }
        }
        m_hiTag = hiTag;
        m_loTag = loTag;
        m_uuid = toString(this.toByteArray());
    }

    /**
     * 返回当前UID.
     *
     *@return   java.lang.String
     */
    public String getUID()
    {
        return m_uuid;
    }

    /**
     * 比较UUID相同.
     *
     *@param obj  Object UUID
     *@return     Ture if equal
     */
    public boolean equals(Object obj)
    {
        try
        {
            UUID uuid = (UUID) obj;
            return  (uuid.m_hiTag == m_hiTag) && (uuid.m_loTag == m_loTag);
        }
        catch (ClassCastException cce)
        {
            return false;
        }
    }

    /**
     * 获得可打印字串.
     *
     *@return   java.lang.String
     */
    public String toPrintableString()
    {
        byte[] bytes = toByteArray();
        if (16 != bytes.length)
        {
            return "** Bad UUID Format/Value **";
        }
        StringBuffer buf = new StringBuffer();
        int i;
        for (i = 0; i < 4; i++)
        {
            buf.append(Integer.toHexString(hiNibble(bytes[i])));
            buf.append(Integer.toHexString(loNibble(bytes[i])));
        }
        while (i < 10)
        {
            buf.append('-');
            int j = 0;
            while (j < 2)
            {
                buf.append(Integer.toHexString(hiNibble(bytes[i])));
                buf.append(Integer.toHexString(loNibble(bytes[i++])));
                j++;
            }
        }
        buf.append('-');
        for (; i < 16; i++)
        {
            buf.append(Integer.toHexString(hiNibble(bytes[i])));
            buf.append(Integer.toHexString(loNibble(bytes[i])));
        }
        return buf.toString();
    }

    /**
     * 返回UUID字串.
     *
     *@return   UID String
     */
    public String toString()
    {
        return m_uuid;
    }

    /**
     * 获得新的实例.
     *
     *@return   UUID
     */
    protected static UIDFactory getInstance()
    {
        return new UUID();
    }

    /**
     * Overpass a bytes array generator UID String.
     *
     *@param bytes  Object bytes array
     *@return       UID String
     */
    protected static String toString(byte[] bytes)
    {
        if (16 != bytes.length)
        {
            return "** Bad UUID Format/Value **";
        }
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < 16; i++)
        {
            buf.append(Integer.toHexString(hiNibble(bytes[i])));
            buf.append(Integer.toHexString(loNibble(bytes[i])));
        }
        return buf.toString();
    }

    /** 生成并返回UUID和缓存字串. */
    protected void next()
    {
        m_hiTag = (System.currentTimeMillis() + (JVMHASH * 4294967296L)) ^ MACHINEID;
        m_loTag = EPOCH + Math.abs(RANDOM.nextLong());
        m_uuid = toString(this.toByteArray());
    }

    /**
     * Overpass high order tag & low order tag
     * convert to array bytes.
     *
     *@return   Array bytes
     */
    protected byte[] toByteArray()
    {
        byte[] bytes = new byte[16];
        int idx = 15;
        long val = m_loTag;
        for (int i = 0; i < 8; i++)
        {
            bytes[idx--] = (byte) (int) (val & (long) 255);
            val >>= 8;
        }
        val = m_hiTag;
        for (int i = 0; i < 8; i++)
        {
            bytes[idx--] = (byte) (int) (val & (long) 255);
            val >>= 8;
        }
        if (!this.isMD5())
        {
            return bytes;
        }
        else
        {
            return toMD5(bytes);
        }
    }

    /**
     * 获得高位byte.
     *
     *@param b  Object byte
     *@return   Result byte
     */
    private static final byte hiNibble(byte b)
    {
        return (byte) ((b >> 4) & 0xf);
    }

    /**
     * 获得低位byte.
     *
     *@param b  Object byte
     *@return   Result byte
     */
    private static final byte loNibble(byte b)
    {
        return (byte) (b & 0xf);
    }

}
