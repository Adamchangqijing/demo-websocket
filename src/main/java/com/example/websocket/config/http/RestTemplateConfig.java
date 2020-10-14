package com.example.websocket.config.http;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @描述 rest风格调用配置
 */
@Component
@Configurable
public class RestTemplateConfig {

    @Bean
    @Primary
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
