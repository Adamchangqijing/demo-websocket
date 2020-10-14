package com.example.websocket.websocket.packet.dispose;

import com.example.websocket.websocket.packet.base.BasePacket;
import io.netty.channel.ChannelHandlerContext;

public abstract class TokenDispose extends BaseDispose {

    @Override
    void detailedDispose(ChannelHandlerContext ctx, BasePacket basePacket) {

        return ;
    }
}
