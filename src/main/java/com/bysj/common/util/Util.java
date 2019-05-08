package com.bysj.common.util;

public final class Util {

    public static String getStringValue(Object obj){
        if(obj == null){
            return "";
        }else{
            return String.valueOf(obj);
        }
    }

}
