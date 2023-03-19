package com.youhuo.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <p>
 * gateway 网关
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-03-16
 */
@EnableFeignClients
@SpringBootApplication
public class YouHuoGatewayApp {
    public static void main(String[] args) {
        // 启动 Spring Boot 应用
        SpringApplication.run(YouHuoGatewayApp.class, args);
    }

}
