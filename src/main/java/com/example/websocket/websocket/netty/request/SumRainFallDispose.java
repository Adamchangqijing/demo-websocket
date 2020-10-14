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
 * 可能需要加一个心的业务编码
 */
@Slf4j
public class SumRainFallDispose extends PacketDispose {

    @Override
    public void doDetailedDispose(ChannelHandlerContext ctx, BasePacket basePacket) {
        String token = basePacket.getAppendBody().getToken();
        log.info("token:" + token);

//        Map<String, DyRainVO> dyRainVOMap = GeoRainCache.dyRainSumVOMap;
        ResultInfo body = ResultInfo.success("ffff");
        BasePacket handler = ReponsePacket.handler(basePacket,
                BusinessNumber.USER_LOGIN_RES_ANOTHER.getNumber(), body, TextFormat.JSON);
        boolean send = send(ctx.channel(), StringEncode.stringEncode(handler), TextFormat.JSON);
        if (!send)log.error("登录信息发送失败");

        //TODO 发送其他业务消息
    }



}
