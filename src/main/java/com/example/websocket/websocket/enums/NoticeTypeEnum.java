package com.example.websocket.websocket.enums;

public enum NoticeTypeEnum {
    user(1,1,"指定用户"),
    allUser(2,1,"所有用户"),
    allOnlineUser(3,1,"所有在线用户");

    private int key;
    private int targetType; //1：用户，2：系统
    private String remark;


    NoticeTypeEnum(int key, int targetType, String remark) {
        this.key = key;
        this.targetType = targetType;
        this.remark = remark;
    }

    public static NoticeTypeEnum getNoticiTypeEnumByKey(Integer key){
        NoticeTypeEnum[] noticeTypeEnums = NoticeTypeEnum.values();
        for (NoticeTypeEnum noticeTypeEnum : noticeTypeEnums){
            Integer k = noticeTypeEnum.getKey();
            if (k.equals(key)){
                return noticeTypeEnum;
            }
        }
        return null;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getTargetType() {
        return targetType;
    }

    public void setTargetType(int targetType) {
        this.targetType = targetType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
