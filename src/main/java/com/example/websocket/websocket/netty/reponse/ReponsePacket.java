package com.example.websocket.websocket.netty.reponse;

import com.alibaba.fastjson.JSON;
import com.example.websocket.websocket.enums.TextFormat;
import com.example.websocket.websocket.packet.base.AppendBody;
import com.example.websocket.websocket.packet.base.BasePacket;
import com.example.websocket.websocket.packet.base.HeaderPacket;

import java.util.UUID;

/**
 * 响应信息处理
 */
public class ReponsePacket {

    public static BasePacket handler(BasePacket basePacket, int msgId, Object body, TextFormat format){
        BasePacket basePacketRes = new BasePacket();
        HeaderPacket headerPacketRes = new HeaderPacket();
        headerPacketRes.setMsgId(msgId);
        headerPacketRes.setMsgSn(UUID.randomUUID().toString());
        basePacketRes.setHeader(headerPacketRes);
        AppendBody appendBody = new AppendBody();
        appendBody.setFormat(format.getKey());
        appendBody.setResMsgSn(basePacket.getHeader().getMsgSn());
        appendBody.setSysNo(basePacket.getAppendBody().getSysNo());
        appendBody.setToken(basePacket.getAppendBody().getToken());

        if (body instanceof String){
            basePacketRes.setBody(body.toString());
        }else {
            basePacketRes.setBody(JSON.toJSONString(body));
        }

        basePacketRes.setAppendBody(appendBody);
        return basePacketRes;
    }

    public static BasePacket handler(String msgSn,String body,int msgId,String token,String systemNo){
        HeaderPacket headerPacketRes = new HeaderPacket();
        headerPacketRes.setMsgId(msgId);
        headerPacketRes.setMsgSn(msgSn);

        AppendBody appendBody = new AppendBody();
        appendBody.setFormat(TextFormat.JSON.getKey());
        appendBody.setSysNo(systemNo);
        appendBody.setToken(token);

        BasePacket basePacketRes = new BasePacket();
        basePacketRes.setHeader(headerPacketRes);
        basePacketRes.setAppendBody(appendBody);
        basePacketRes.setBody(body);
        return basePacketRes;

    }


    public static BasePacket handler(String msgSn,int msgId,String body){
        HeaderPacket headerPacketRes = new HeaderPacket();
        headerPacketRes.setMsgId(msgId);
        if (msgSn == null)msgSn = UUID.randomUUID().toString().replaceAll("-","");
        headerPacketRes.setMsgSn(msgSn);

        AppendBody appendBody = new AppendBody();
        appendBody.setFormat(TextFormat.JSON.getKey());
        appendBody.setSysNo("TYPE");
        appendBody.setToken("ff");
        BasePacket basePacketRes = new BasePacket();
        basePacketRes.setHeader(headerPacketRes);
        basePacketRes.setAppendBody(appendBody);
        basePacketRes.setBody(body);
        return basePacketRes;

    }

}
