package com.youhuo.cloud.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <p>
 * 系统启动类
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-03-17
 */
@EnableFeignClients
@SpringBootApplication
public class SystemApp {
    public static void main(String[] args) {
        SpringApplication.run(SystemApp.class, args);
    }
}
