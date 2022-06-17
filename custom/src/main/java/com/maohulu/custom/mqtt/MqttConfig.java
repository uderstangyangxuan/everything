package com.maohulu.custom.mqtt;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * 开启Spring Integration的注解扫描 -- @IntegrationComponentScan，
 *
 * @author huliu
 * @date 15:53 2022/6/9
 */
@Configuration
@IntegrationComponentScan
public class MqttConfig {
    @Autowired
    private MqttProperties mqttProperties;


    /**
     * 连接器
     */
    @Bean
    public MqttConnectOptions getMqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        // 设置是否清空session，false表示服务器会保留客户端的连接记录，true表示每次连接到服务器都以新的身份连接
        mqttConnectOptions.setCleanSession(true);
        // 设置超时时间，默认30秒
        mqttConnectOptions.setConnectionTimeout(mqttProperties.getConnectionTimeOut());
        mqttConnectOptions.setKeepAliveInterval(mqttProperties.getKeepAlive());
        mqttConnectOptions.setAutomaticReconnect(true);
        // 设置连接的用户名
        mqttConnectOptions.setUserName(mqttProperties.getUsername());
        // 设置连接的密码
        mqttConnectOptions.setPassword(mqttProperties.getPassword().toCharArray());
        //服务器地址
        mqttConnectOptions.setServerURIs(new String[]{mqttProperties.getUrl()});
        mqttConnectOptions.setKeepAliveInterval(2);
        return mqttConnectOptions;
    }

    /**
     * 配置MQTT客户端工厂类DefaultMqttPahoClientFactory
     */
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions());
        return factory;
    }

    /*-----------------     消息生产者的配置       ---------------------*/

    /**
     * 配置Outbound出站，出站通道适配器
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        // 初始化出站通道适配器，使用的是Eclipse Paho MQTT客户端库
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(mqttProperties.getProducerClientId(), mqttClientFactory());
        // 配置异步发送
        messageHandler.setAsync(true);
        messageHandler.setDefaultRetained(false);
        messageHandler.setAsyncEvents(false);
        // 设置默认的服务质量
        messageHandler.setDefaultQos(2);
        return messageHandler;
    }

    /**
     * 配置Outbound出站，发布者发送的消息通道
     */
    @Bean("mqttOutboundChannel")
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    /*-----------------     消息消费者的配置       ---------------------*/

    /**
     * 配置Inbound入站，消费者基本连接配置
     */
    @Bean
    public MessageProducerSupport mqttInbound() {
        // 初始化入站通道适配器，使用的是Eclipse Paho MQTT客户端库
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(mqttProperties.getConsumerClientId(), mqttClientFactory(), "test");
        // 配置默认Paho消息转换器(qos=0, retain=false, charset=UTF-8)
        adapter.setConverter(new DefaultPahoMessageConverter());
        // 设置服务质量
        // 0 最多一次，数据可能丢失;
        // 1 至少一次，数据可能重复;
        // 2 只有一次，有且只有一次;最耗性能
        adapter.setQos(2);
        // 设置订阅通道
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    /**
     * 配置Inbound入站，消费者订阅的消息通道
     */
    @Bean()
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return message -> System.out.println(message.getPayload());
    }

}
