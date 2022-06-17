package com.maohulu.custom.controller;

import com.maohulu.custom.mqtt.MqttGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huliu
 * @since 2022/4/14 10:43
 */
@RestController
public class TestMqttController {
    @Autowired
    private MqttGateway mqttGateway;

    @GetMapping("/sendTest")
    public String sendMqttTest(@RequestParam("msg") String msg) {
        String test = "test";
        mqttGateway.sendToTopic(msg, test);
        return "OK";
    }

}
