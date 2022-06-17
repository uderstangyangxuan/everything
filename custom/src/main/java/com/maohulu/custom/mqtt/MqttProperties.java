package com.maohulu.custom.mqtt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author huliu
 * @since 2022/4/14 10:43
 */
@Data
@Component
@ConfigurationProperties(prefix = "mqtt")
public class MqttProperties {
    private static final long serialVersionUID = -1425980007744001158L;

    private String url;

    private String username;

    private String password;

    private int keepAlive;

    private int connectionTimeOut;

    private String producerClientId;

    private String producerQos;

    private String consumerClientId;

    private String consumerQos;

    private String consumerTopic;

    private int completionTimeout;

    private String defaultTopic;
}

