package com.example.websocket.websocket.netty.filter;


import com.alibaba.fastjson.JSON;
import com.example.websocket.utils.MD5Utils;

/**
 * 字符串协议编码  app
 * @author zhouyongbo
 */
public class StringEncode {

    public static String stringEncode(Object data){
        String s = JSON.toJSONString(data);
        String $$ = (s+ MD5Utils.encrypt16(s.length()+"")).replaceAll("\\#", "\\$\\$");
        return "#"+$$;
    }
}
