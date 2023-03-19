//package com.youhuo.cloud.system.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Report 模块的 Security 配置
// */
//@Configuration("reportSecurityConfiguration")
//public class SecurityConfiguration {
//
//    @Bean("reportAuthorizeRequestsCustomizer")
//    public AuthorizeRequestsCustomizer authorizeRequestsCustomizer() {
//        return new AuthorizeRequestsCustomizer() {
//
//            @Override
//            public void customize(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
//                // Swagger 接口文档
//                registry.antMatchers("/v3/api-docs/**").permitAll() // 元数据
//                        .antMatchers("/swagger-ui.html").permitAll(); // Swagger UI
//                // Spring Boot Actuator 的安全配置
//                registry.antMatchers("/actuator").anonymous()
//                        .antMatchers("/actuator/**").anonymous();
//                // Druid 监控
//                registry.antMatchers("/druid/**").anonymous();
//                // 积木报表
//                registry.antMatchers("/jmreport/**").permitAll();
//            }
//
//        };
//    }
//
//}
