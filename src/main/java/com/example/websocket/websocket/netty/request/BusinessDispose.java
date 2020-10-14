package com.example.websocket.websocket.netty.request;


import com.example.websocket.websocket.enums.BusinessNumber;
import com.example.websocket.websocket.enums.TextFormat;
import com.example.websocket.websocket.model.ResultInfo;
import com.example.websocket.websocket.netty.filter.StringEncode;
import com.example.websocket.websocket.netty.reponse.ReponsePacket;
import com.example.websocket.websocket.packet.base.BasePacket;
import com.example.websocket.websocket.packet.dispose.PacketDispose;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 可能需要新增一个类似的群发的类
 */
@Slf4j
public class BusinessDispose extends PacketDispose {

    @Override
    public void doDetailedDispose(ChannelHandlerContext ctx, BasePacket basePacket) {
        String token = basePacket.getAppendBody().getToken();
//        log.info("token:" + token);
        log.info("ssss:{}",basePacket.getBody());

        ResultInfo body = ResultInfo.success("我收到你的消息啦,消息内容是：" + basePacket.getBody());
        BasePacket handler = ReponsePacket.handler(basePacket,
                BusinessNumber.ACTIVE_PUSH.getNumber(), body, TextFormat.JSON);
        boolean send = send(ctx.channel(), StringEncode.stringEncode(handler), TextFormat.JSON);

    }



}
