package com.example.websocket.websocket.enums;

public enum  BusinessNumber {
    USER_LOGIN(0X10001,10,"用户登录"),
    USER_LOGIN_ANOTHER(0X10011,10,"第二种用户登陆"),
    USER_LOGIN_RES(0X10002,10,"用户登录响应"),
    USER_LOGIN_RES_ANOTHER(0X10012,10,"第二种用户登录响应"),
    ALL_PUSH(0X10004,10,"群推功能"),
    ALL_PUSH_ANOTHER(0X10014,10,"第二种群推送"),
    ACTIVE_RECEIVE(0X10005,10,"主动接收消息"),
    ACTIVE_PUSH(0X10006,10,"主动推送响应"),
    USER_LOGIN_OUT_MSG(0X10003,10,"用户退出消息");
    private int number;
    private int level;
    private String remake;

    BusinessNumber(int number, int level, String remake) {
        this.number = number;
        this.level = level;
        this.remake = remake;
    }

    public static BusinessNumber getBusinessNumberByNumber(Integer number){
        BusinessNumber[] levels = BusinessNumber.values();
        for (BusinessNumber businessNumber:levels){
            Integer k = businessNumber.getNumber();
            if (k.equals(number)){
                return businessNumber;
            }

        }
        return null;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }
}
