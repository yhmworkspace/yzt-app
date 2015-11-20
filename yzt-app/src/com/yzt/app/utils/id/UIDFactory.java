package com.yzt.app.utils.id;

import java.net.InetAddress;
import java.security.MessageDigest;
import java.util.Random;


/**
 * UUID工厂抽象类, UUID是默认的String类型.
 *
 * <p> 例子:
 * <pre>
 * private static UIDFactory uuid = null;
 *
 * try {
 *      uuid = UIDFactory.getInstance("UUID");
 * } catch (UIDNotSupportException unsex) {};
 *
 * String id = uuid.getNextUID();
 * </pre>
 *
 */
public abstract class UIDFactory
{
    //~ Static fields/initializers /////////////////////////////////////////////
    /** Global Unified Identifier */
    public static final String UID_GUID = "GUID";

    /** United Unified Identifier */
    public static final String UID_UUID = "UUID";

    /** Current Epoch millis SEED */
    protected static final long EPOCH = System.currentTimeMillis();

    /** JVM Hashcode */
    protected static final long JVMHASH = Math.abs((new Object()).hashCode());

    /** Epoch has millisecond */
    protected static final long MACHINEID = getMachineID();

    /** Random by seed */
    protected static final Random RANDOM = new Random(EPOCH);

    /** MD5 Instance */
    private static MessageDigest md5;

    /* Initialize MD5 factory  */
    static
    {
        try
        {
            md5 = MessageDigest.getInstance("MD5");
        }
        catch (java.security.NoSuchAlgorithmException ex)
        {
            System.out.println("->" + ex);
        }
    }

    //~ 实例属性 ////////////////////////////////////////////////////////
    /** MD5 标志 */
    private boolean isMd5 = false;

    //~ 方法 ////////////////////////////////////////////////////////////////
    /**
     * 获得默认的UIDFactory.
     *
     *@return   UIDFactory UID manager object
     */
    public static UIDFactory getDefault()
    {
        return UUID.getInstance();
    }

    /**
     * 获得指定的UIDFactory.
     *
     *@param uidfactory                         Description of the Parameter
     *@return                                   UIDFactory
     *@exception UIDNotSupportException         Description of the Exception
     *@throws java.lang.ClassNotFoundException
     */
    public static UIDFactory getInstance(String uidfactory)
        throws UIDNotSupportException
    {
        if (uidfactory.equalsIgnoreCase(UID_UUID))
        {
            return UUID.getInstance();
        }
        if (uidfactory.equalsIgnoreCase(UID_GUID))
        {
            return GUID.getInstance();
        }
        throw new UIDNotSupportException(uidfactory + " Not Found!");
    }

    /**
     * 获得下一个UID.
     *
     *@return   String Storagable UID
     */
    public abstract String getNextUID();

    /**
     * 获得当前UID.
     *
     *@return   String Storagable UID
     */
    public abstract String getUID();

    /**
     * 设置MD5输出
     *
     *@param flag  MD5 switch
     */
    public void setMD5(boolean flag)
    {
        isMd5 = flag;
    }

    /**
     * 检查MD5开关状态.
     *
     *@return   true为开.
     */
    public boolean isMD5()
    {
        return isMd5;
    }

    /**
     * 设置当前UID.
     *
     *@param uid            Object uid
     *@exception Exception  Description of the Exception
     */
    public abstract void setUID(String uid)
        throws Exception;

    /**
     * 获得可打印ID String.
     *
     *@return   String
     */
    public abstract String toPrintableString();

    /**
     * 转换bytes 到 MD5 bytes.
     *
     *@param bytes  Description of the Parameter
     *@return byte[]
     */
    protected static byte[] toMD5(byte[] bytes)
    {
        return md5.digest(bytes);
    }

    /**
     * 获得机器IDGUID class的属性
     *
     *@return   The machineID value
     */
    private static long getMachineID()
    {
        long i = 0;
        try
        {
            InetAddress inetaddress = InetAddress.getLocalHost();
            byte[] abyte0 = inetaddress.getAddress();
            i = toInt(abyte0);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return i;
    }

    /**
     * 转换bytes to 到int utils.
     *
     *@param abyte0  Object bytes array
     *@return        Result int
     */
    private static int toInt(byte[] abyte0)
    {
        return((abyte0[0] << 24) & 0xff000000) | ((abyte0[1] << 16) & 0xff0000) | ((abyte0[2] << 8) & 0xff00) | (abyte0[3] & 0xff);
    }

    /**
     * 获得定长的UUID
     * @param len int
     * @return String
     */
    public abstract String getNextUID(int len);
}

