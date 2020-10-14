package com.example.websocket.websocket.model;

import java.io.Serializable;

/**
 * 返回值
 * @author zhouyongbo
 */
public class ResultInfo implements Serializable {


    private String message;
    /**
     *  成功 100 失败101
     */
    private Integer code;
    /**
     * 返回数据
     */
    private Object data;

    public ResultInfo() {
    }

    public ResultInfo(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ResultInfo(String message, Integer code, Object data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public static ResultInfo success(Object data){
        return new ResultInfo(null,100,data);
    }


    public static ResultInfo error(String meassage){
        return new ResultInfo(meassage,101,null);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
