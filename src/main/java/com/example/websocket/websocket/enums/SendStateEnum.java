package com.example.websocket.websocket.enums;

public enum SendStateEnum {

    error(0,"失败"),
    success(1,"成功"),
    offline(2,"离线");

    private int key;
    private String remark;

    SendStateEnum(int key, String remark) {
        this.key = key;
        this.remark = remark;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
