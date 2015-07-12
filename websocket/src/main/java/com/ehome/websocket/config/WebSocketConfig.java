package com.ehome.websocket.config;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
import java.util.Set;

/**
 * WebSocket
 *
 * @author: 郝晓雷
 * @date: 2015-07-12 15:40
 * @desc: websocket配置
 */
public class WebSocketConfig implements ServerApplicationConfig {

    /**
     * 注解方式来获取websocket服务
     * @param set
     */
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> set) {
        System.out.println("正在扫描websocket服务..." + set.size());
        return set;
    }

    /**
     * 接口方式来获取websocket服务，(作用和上面的注解方式一样)
     * @param set
     */
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> set) {
        return null;
    }
}
