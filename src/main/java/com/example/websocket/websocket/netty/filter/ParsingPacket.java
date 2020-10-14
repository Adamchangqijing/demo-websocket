package com.example.websocket.websocket.netty.filter;

import com.alibaba.fastjson.JSON;
import com.example.websocket.websocket.exception.WSocketException;
import com.example.websocket.websocket.netty.request.BusinessDispose;
import com.example.websocket.websocket.netty.request.LoginDispose;
import com.example.websocket.websocket.netty.request.SumRainFallDispose;
import com.example.websocket.websocket.packet.base.BasePacket;
import com.example.websocket.websocket.packet.base.HeaderPacket;
import com.example.websocket.websocket.packet.dispose.BaseDispose;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


/**
 * 这里也要改定义多个的情况
 * 解析字符串
 * @author zhouyongbo
 */
public class ParsingPacket {
    private static final Logger LOGGER = LoggerFactory.getLogger(ParsingPacket.class);

    public final static Map<Integer, BaseDispose> PACKET_MAP = new HashMap<Integer, BaseDispose>();

    //定义消息体发送到哪一个方法
    static {
        PACKET_MAP.put(0x10001,new LoginDispose());
        PACKET_MAP.put(0x10005,new BusinessDispose());
        PACKET_MAP.put(0x10011,new SumRainFallDispose());
//        PACKET_MAP.put(0x10015,new BusinessDispose());
    }

    /**
     * 进行数据解析
     */
    public static void parsing(ChannelHandlerContext ctx,String decodStr){
        BasePacket basePacket = null;
        try{
            basePacket = JSON.parseObject(decodStr, BasePacket.class);
            if (basePacket == null){
                LOGGER.error("ParsingPacket：字符串json格式错误:"+decodStr);
                throw new WSocketException("ParsingPacket：字符串json格式错误:"+decodStr);
            }
        }catch (Exception e ){
            e.printStackTrace();
            throw new WSocketException("ParsingPacket： 字符串json格式异常:"+decodStr);
        }
        HeaderPacket headerPacket = basePacket.getHeader();
        BaseDispose dispose = PACKET_MAP.get(headerPacket.getMsgId());
        if(dispose==null){
            LOGGER.warn("ParsingPacket： 无此协议:"+decodStr);
            throw new WSocketException("ParsingPacket： 无此协议:"+decodStr);
        }
        dispose.poxyAppDispose(ctx,basePacket);
        return;
    }
}
