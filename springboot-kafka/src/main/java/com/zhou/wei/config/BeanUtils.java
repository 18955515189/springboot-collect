package com.zhou.wei.config;

import java.io.*;

/**
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @description 对象转字节数组工具
 * @since 2020/4/4 0004 21:51
 **/
public class BeanUtils {

    /**
     * 对象转字节数组
     * @param obj
     * @return
     */
    public static byte[] ObjectToBytes(Object obj){
        byte[] bytes = null;
        ByteArrayOutputStream bo = null;
        ObjectOutputStream oo = null;
        try {
            bo = new ByteArrayOutputStream();
            oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            bytes = bo.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(bo!=null){
                    bo.close();
                }
                if(oo!=null){
                    oo.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }


    /**
     * 字节数组转对象
     * @param bytes
     * @return
     */
    public static Object BytesToObject(byte[] bytes){
        Object obj = null;
        ByteArrayInputStream bi = null;
        ObjectInputStream oi = null;
        try {
            bi =new ByteArrayInputStream(bytes);
            oi =new ObjectInputStream(bi);
            obj = oi.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(bi!=null){
                    bi.close();
                }
                if(oi!=null){
                    oi.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return obj;
    }


}
