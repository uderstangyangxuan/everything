package com.maohulu.custom.mqtt;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * 对外提供推送消息的接口
 * 1. 使用@MessagingGateway注解，配置MQTTMessageGateway消息推送接口
 * 2. 使用defaultRequestChannel值，调用时将向其发送消息的默认通道
 * 3. 配置灵活的topic主题
 *
 * @author huliu
 * @date 15:54 2022/6/9
 */
@Component
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {
    void send(String payload);

    void sendToMqtt(byte[] data,@Header(MqttHeaders.TOPIC) String topic);

    void sendToTopic(String payload, @Header(MqttHeaders.TOPIC) String topic);

    void sendToTopic(String payload, @Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos);
}