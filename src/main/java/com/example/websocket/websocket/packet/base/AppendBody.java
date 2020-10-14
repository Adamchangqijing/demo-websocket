package com.example.websocket.websocket.packet.base;

public class AppendBody {

    private String sysNo;

    private String token;

    private String format;
    /**
     * 响应报文编号
     */
    private String resMsgSn;


    public String getResMsgSn() {
        return resMsgSn;
    }

    public void setResMsgSn(String resMsgSn) {
        this.resMsgSn = resMsgSn;
    }

    public String getSysNo() {
        return sysNo;
    }

    public void setSysNo(String sysNo) {
        this.sysNo = sysNo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
