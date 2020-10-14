package com.example.websocket.websocket.enums;
/*
 *系统类型
 */
public enum SystemTypeEnum {
    TYPE("TYPE");

    private String systemName;

    SystemTypeEnum(String systemName) {
        this.systemName = systemName;
    }
    public static SystemTypeEnum getSystemTypeEnumBySystemName(String systemName){
        SystemTypeEnum[] SystemTypeEnums = SystemTypeEnum.values();
        for (SystemTypeEnum systemTypeEnum:SystemTypeEnums){
            String  k = systemTypeEnum.getSystemName();
            if (k.equals(systemName)){
                return systemTypeEnum;
            }
        }
        return null;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }
}
