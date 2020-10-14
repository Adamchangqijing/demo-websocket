package com.example.websocket.websocket.enums;

public enum LookStateEnum {

    unread(0,"未读"),
    read(1,"已读");

    private int key;
    private String remark;

    LookStateEnum(int key, String remark) {
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
