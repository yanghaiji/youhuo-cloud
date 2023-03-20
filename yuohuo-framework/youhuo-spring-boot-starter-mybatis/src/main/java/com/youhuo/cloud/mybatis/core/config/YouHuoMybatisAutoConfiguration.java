package com.youhuo.cloud.mybatis.core.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.youhuo.cloud.mybatis.core.handler.DefaultDBFieldHandler;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * MyBaits 配置类
 *
 * @author haiji
 */
@AutoConfiguration
@MapperScan(value = "com.youhuo.cloud", annotationClass = Mapper.class,
        lazyInitialization = "${mybatis.lazy-initialization:false}") // Mapper 懒加载，目前仅用于单元测试
public class YouHuoMybatisAutoConfiguration {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor()); // 分页插件
        return mybatisPlusInterceptor;
    }

    @Bean
    public MetaObjectHandler defaultMetaObjectHandler(){
        return new DefaultDBFieldHandler(); // 自动填充参数类
    }

//    @Bean
//    @ConditionalOnProperty(prefix = "mybatis-plus.global-config.db-config", name = "id-type", havingValue = "INPUT")
//    public IKeyGenerator keyGenerator(ConfigurableEnvironment environment) {
//        DbType dbType = IdTypeEnvironmentPostProcessor.getDbType(environment);
//        if (dbType != null) {
//            switch (dbType) {
//                case POSTGRE_SQL:
//                    return new PostgreKeyGenerator();
//                case ORACLE:
//                case ORACLE_12C:
//                    return new OracleKeyGenerator();
//                case H2:
//                    return new H2KeyGenerator();
//                case KINGBASE_ES:
//                    return new KingbaseKeyGenerator();
//            }
//        }
//        // 找不到合适的 IKeyGenerator 实现类
//        throw new IllegalArgumentException(StrUtil.format("DbType{} 找不到合适的 IKeyGenerator 实现类", dbType));
//    }

}
