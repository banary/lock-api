package com.canary.lock.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Integer工具类
 *
 * @author Eden
 * @date 2017/8/13 下午6:51
 */
public class Integers {

    private static Logger logger = LoggerFactory.getLogger(Integers.class);

    public static int valueOf(String value, int defaultValue){
        try {
            return Integer.valueOf(value);
        }catch (Exception e){
            logger.error("The value transfer to number error!value:{}", value);
            return defaultValue;
        }
    }

    public static int valueOf(String value){
        return valueOf(value, 0);
    }
}
