package com.example.websocket.init;


import com.example.websocket.config.socket.SocketServerConfig;
import com.example.websocket.websocket.netty.NettyServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RunnerInit implements CommandLineRunner {

    @Autowired
    SocketServerConfig socketServerConfig;

    @Override
    public void run(String... args) throws Exception {
    //初始化webSocket
        new NettyServer(socketServerConfig.getServer().getPort()).start();
        log.info("==============webSocket 启动===============");
    }
}
