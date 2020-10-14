package com.example.websocket.websocket.enums;

public enum TextFormat {
    TEXT("text"),
    JSON("json");

    private String key;

    TextFormat(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
