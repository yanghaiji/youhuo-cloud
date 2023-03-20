package com.youhuo.cloud.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <p>
 * bean的配置
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-03-20
 */
@Configuration
public class BeanConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
