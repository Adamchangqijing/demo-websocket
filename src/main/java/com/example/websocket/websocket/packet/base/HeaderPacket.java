package com.example.websocket.websocket.packet.base;

public class HeaderPacket {

    private String msgSn;

    private Integer msgId;

    private String msgVn = "1.0";

    public String getMsgSn() {
        return msgSn;
    }

    public void setMsgSn(String msgSn) {
        this.msgSn = msgSn;
    }

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public String getMsgVn() {
        return msgVn;
    }

    public void setMsgVn(String msgVn) {
        this.msgVn = msgVn;
    }
}
