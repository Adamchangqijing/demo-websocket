package com.example.websocket.config.socket;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 *
 * 服务配置文件
 */
@Component
@Configuration
@ConfigurationProperties(prefix = "socket")
public class SocketServerConfig {

    Server server;

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }
}
