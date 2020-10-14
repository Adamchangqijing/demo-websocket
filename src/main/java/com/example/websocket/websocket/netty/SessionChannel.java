package com.example.websocket.websocket.netty;

import io.netty.channel.Channel;

import java.net.InetSocketAddress;
import java.util.Date;

public class SessionChannel {

    private String ip;

    private Date createDate;

    private Date updateDate;

    private Channel channel;

    private boolean isEnable = true;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public SessionChannel(Channel channel) {
        this.channel = channel;
        this.createDate = new Date();
        InetSocketAddress socketAddress = (InetSocketAddress) channel.remoteAddress();
        String ip =  socketAddress.getAddress()+":"+socketAddress.getPort();

    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public void setIsEnable(boolean isEnable) {
        this.isEnable = isEnable;
        this.updateDate = new Date();
    }
}
