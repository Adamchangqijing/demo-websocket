package com.example.websocket.websocket.packet.dispose;


import com.example.websocket.websocket.packet.base.BasePacket;
import io.netty.channel.ChannelHandlerContext;

public abstract class PacketDispose extends BaseDispose {
    @Override
    public void detailedDispose(ChannelHandlerContext ctx, BasePacket basePacket) {
        doDetailedDispose(ctx,basePacket);
        return ;
    }

}
