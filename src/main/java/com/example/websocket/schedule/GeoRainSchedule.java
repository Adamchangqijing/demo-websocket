package com.example.websocket.schedule;

import com.alibaba.fastjson.JSON;
import com.example.websocket.websocket.enums.BusinessNumber;
import com.example.websocket.websocket.enums.TextFormat;
import com.example.websocket.websocket.model.ResultInfo;
import com.example.websocket.websocket.netty.Global;
import com.example.websocket.websocket.netty.filter.StringEncode;
import com.example.websocket.websocket.netty.reponse.ReponsePacket;
import com.example.websocket.websocket.packet.base.BasePacket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GeoRainSchedule {



    @Scheduled(cron = "0 0/1 * * * ?")
    public void caclGeoFromCimiss() {
        ResultInfo body = ResultInfo.success("这是一条大家都能收到的消息");
        log.info("ssss:{}",body);
        BasePacket handler = ReponsePacket.handler(null, BusinessNumber.ALL_PUSH.getNumber(), JSON.toJSONString(body));
        //群发
        Global.sendMass(StringEncode.stringEncode(handler), TextFormat.JSON);
    }




}
