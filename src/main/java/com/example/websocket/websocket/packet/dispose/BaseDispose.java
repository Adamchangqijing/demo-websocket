package com.example.websocket.websocket.packet.dispose;

import com.alibaba.fastjson.JSON;
import com.example.websocket.utils.LoggerUtils;
import com.example.websocket.websocket.enums.TextFormat;
import com.example.websocket.websocket.exception.WSocketException;
import com.example.websocket.websocket.packet.base.AppendBody;
import com.example.websocket.websocket.packet.base.BasePacket;
import com.example.websocket.websocket.packet.base.HeaderPacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;

public abstract class BaseDispose {

    Logger logger = LoggerUtils.getLogger(BaseDispose.class);

    /**
     * 代理处理
     * @param ctx
     * @param basePacket
     */
    public void poxyAppDispose(ChannelHandlerContext ctx, BasePacket basePacket){
        //协议验证
        if (simulatorValidation(ctx, basePacket)) {
            detailedDispose(ctx, basePacket);
        } else {
            throw new WSocketException("协议验证失败");
        }
    }

    /**
     * 详细处理过程
     * @param ctx
     * @param basePacket
     * @return
     */
    abstract void detailedDispose(ChannelHandlerContext ctx,BasePacket basePacket) ;

    protected abstract void doDetailedDispose(ChannelHandlerContext ctx, BasePacket basePacket);

    /**
     * 协议验证
     * @param ctx
     * @param basePacket
     * @return
     */
    public boolean simulatorValidation(ChannelHandlerContext ctx,BasePacket basePacket){
        AppendBody appendBody = basePacket.getAppendBody();
        HeaderPacket header = basePacket.getHeader();
        logger.info("simulator:协议内容" + JSON.toJSONString(basePacket,true));
//        if (appendBody == null) return false;
//        if (!StringUtil.isBank(appendBody.getSysNo())) return false;
        //验证协议版本
//    if (headerPacket.getMsgId == 0) return false
//        if (!StringUtil.isBank(header.getMsgSn())) return false;
        return true;
    }


    /**
     * 发送消息
     */
    public boolean send(Channel channel , Object msg, TextFormat textFormat) {
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
        if (msgs != null){
            return  channel.writeAndFlush(new TextWebSocketFrame(msgs.toString())).isSuccess();
        }
        return false;
    }

}
