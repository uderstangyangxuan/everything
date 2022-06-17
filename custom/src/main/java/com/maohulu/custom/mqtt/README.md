### Spring Integration 大致交互逻辑
#### 对于发布者：

消息通过消息网关发送出去，由 MessageChannel 的实例 DirectChannel 处理发送的细节。
DirectChannel 收到消息后，内部通过 MessageHandler 的实例 MqttPahoMessageHandler 发送到指定的 Topic。

#### 对于订阅者：

通过注入 MessageProducerSupport 的实例 MqttPahoMessageDrivenChannelAdapter，实现订阅 Topic 和绑定消息消费的 MessageChannel。
同样由 MessageChannel 的实例 DirectChannel 处理消费细节。Channel 消息后会发送给我们自定义的 MqttInboundMessageHandler 实例进行消费。

可以看到整个处理的流程和前面将的基本一致。Spring Integration 就是抽象出了这么一套消息通信的机制，具体的通信细节由它集成的中间件来决定，这里是 MQTT Eclipse Paho Java Client。

