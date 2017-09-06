package com.canary.lock.util;

/**
 * Created by xiyongchun on 2017/8/13.
 */
public class Strings {

    public static boolean isEmpty(String string){
        if(string==null || string.trim().equals("")){
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String string){
        return !isEmpty(string);
    }

}
