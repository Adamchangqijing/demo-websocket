package com.example.websocket.base;

import lombok.Data;

import java.io.Serializable;


/**
 * 返回信息封装类
 *
 * @param <T>
 */
@Data
public class ResultVO<T> implements Serializable {
    private static final long serialVersionUID = 9025467631196322473L;
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private T data;


}
