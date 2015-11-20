package com.yzt.app.utils.id;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * 本程序用于生成GUID
 */
public class GUIDGener {
	 //~ 静态实例属性 /////////////////////////////////////////////
    private static UIDFactory uuid;

    static
    {
        try
        {
            uuid = UIDFactory.getInstance("GUID");
        }
        catch (UIDNotSupportException unsex)
        {
        }
    }

    //~ 构造器 ///////////////////////////////////////////////////////////
    /** 默认构造器 */
    public GUIDGener()
    {
    }

    //~ 方法 ////////////////////////////////////////////////////////////////
    /**
     * 获得GUID
     * @return String
     */
    public static String getGUID()
    {
        return uuid.getNextUID();
    }

    /**
     * 测试程序
     * @param args String[]
     */
    public static void main(String[] args)
    {
        try
        {
            BufferedWriter fw = new BufferedWriter(new FileWriter("F:\\id.txt"));
            for (int i = 0; i <= 100; i++)
            {
                fw.write(uuid.getNextUID());
                fw.write(System.getProperty("line.separator"));
            }
            fw.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
