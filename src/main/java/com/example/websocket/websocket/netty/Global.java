package com.example.websocket.websocket.netty;


import com.alibaba.fastjson.JSON;
import com.example.websocket.websocket.enums.TextFormat;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class Global {

    private static Map<String,SessionChannel> channelMap = new LinkedHashMap<String, SessionChannel>();


    public static void addChannel(Channel channel){
        SessionChannel sessionChannel = getByChannel(channel);
        if (sessionChannel == null) sessionChannel = new SessionChannel(channel);
        else{
            sessionChannel.setIsEnable(true);
            sessionChannel.setChannel(channel);
        }
        channelMap.put(sessionChannel.getIp(),sessionChannel);
    }


    public static List<SessionChannel> allEnable(){
        List<SessionChannel> channels = new ArrayList<>();
        for (SessionChannel sessionChannel : channelMap.values()) {
            if (sessionChannel.isEnable())channels.add(sessionChannel);
        }
        return channels;
    }

    public static SessionChannel getByIp(String ip){
        return channelMap.get(ip);
    }

    public static SessionChannel getByChannel(Channel channel){
        for (SessionChannel sessionChannel : channelMap.values()) {
            if (sessionChannel.getChannel().equals(channel))return sessionChannel;
        }
        return null;
    }

    public static void removeChannel(Channel channel){
        SessionChannel byChannel = getByChannel(channel);
        if (byChannel!=null)byChannel.setIsEnable(false);
    }

    /**
     * 群发 定时器给一个的时候群发
     * @param msg
     * @param textFormat
     */
    public static void sendMass(Object msg, TextFormat textFormat){
        Object msgs = null;
        if (TextFormat.TEXT == textFormat){
            if (msg instanceof String){
                msgs = msg;
            }else {
                msgs = msg.toString();
            }
        }else{
            if (msg instanceof String){
                msgs = msg;
            }else{
                msgs = JSON.toJSONString(msgs);
            }
        }
        for (SessionChannel sessionChannel : allEnable()) {
            sessionChannel.getChannel().writeAndFlush(new TextWebSocketFrame(msgs.toString())).isSuccess();
        }

    }

    static {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info(" 链接总数量为 : "+ channelMap.size());
                    log.info(" 有效链接总数量为 : "+ allEnable().size());
                }
            }
        }).start();
    }
}
