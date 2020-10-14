package com.example.websocket.utils;

import java.math.BigDecimal;

public class NumUtil {


    public static double save2Point(double d){
        BigDecimal b = new BigDecimal(d);
        d = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return d;
    }

    public static double save4Point(double d){
        BigDecimal b = new BigDecimal(d);
        d = b.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
        return d;
    }

    public static double save1Point(double d){
        BigDecimal b = new BigDecimal(d);
        d = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        return d;
    }
}
