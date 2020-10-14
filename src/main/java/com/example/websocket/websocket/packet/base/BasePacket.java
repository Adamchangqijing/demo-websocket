package com.example.websocket.websocket.packet.base;

/**
 * 基础包
 */
public class BasePacket {
    /**
     * 头部
     */
    private HeaderPacket header;
    /**
     * 追加内容
     */
    private AppendBody appendBody;

    /**
     * 报文正文
     */
    private String body;


    public HeaderPacket getHeader() {
        return header;
    }

    public void setHeader(HeaderPacket header) {
        this.header = header;
    }

    public AppendBody getAppendBody() {
        return appendBody;
    }

    public void setAppendBody(AppendBody appendBody) {
        this.appendBody = appendBody;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
