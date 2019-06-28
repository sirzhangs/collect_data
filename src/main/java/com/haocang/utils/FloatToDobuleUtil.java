package com.haocang.utils;

public class FloatToDobuleUtil {

    public static Double parseDouble(Float f){
        if(f == null){
            return null;
        }else{
            Double d = Double.parseDouble(String.valueOf(f));
            return d;
        }
    }
}
