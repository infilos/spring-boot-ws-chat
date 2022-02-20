package com.infilos.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfigure implements WebSocketMessageBrokerConfigurer {

    /**
     * 启用消息端点，路径为 /websocket
     * 
     * STOMP 为简单文本导向的消息传递协议，用于定义数据交换的格式和规则
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket").withSockJS();
    }

    /**
     * 配置消息代理，用于将消息从一个客户端路由到另一个客户端
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 以“/app”开头的消息应该路由到消息处理方法
        registry.enableSimpleBroker("/topic");
        
        // 以“/topic”开头的消息应该路由到消息代理,向订阅该主题的所有连接客户端广播消息
        registry.setApplicationDestinationPrefixes("/app");
    }
}
