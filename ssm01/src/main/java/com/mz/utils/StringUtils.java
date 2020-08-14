package com.mz.utils;

import java.util.Date;

public class StringUtils {
    public static String generateSN(String before,String after){
        return before+new Date().getTime()+after;
    }
}
