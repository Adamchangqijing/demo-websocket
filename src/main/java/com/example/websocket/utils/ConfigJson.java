package com.example.websocket.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * jar包读取资源
 * Created by Administrator on 2019/5/15.
 */
public class ConfigJson {

    public static InputStream getInputStream(String path){
        InputStream inputStream = new ClassPathResource("").getClassLoader().getResourceAsStream(path);
        return inputStream;
    }

    public static StringBuffer getStringBuffer(String path, String encode) throws IOException {
        StringBuffer sb = new StringBuffer();

        InputStream inputStream = null;
        BufferedReader br =null;
        try{
            inputStream = getInputStream(path);
            br = new BufferedReader(new InputStreamReader(inputStream, encode));
            String temp = "";
            temp = br.readLine();
            while (temp != null) {
                sb.append(temp + "\r\n");
                temp = br.readLine();
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(br != null){
                br.close();
            }
            if(inputStream != null){
                inputStream.close();
            }
        }
        return sb;
    }
}
