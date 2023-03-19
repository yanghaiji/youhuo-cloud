package com.youhuo.cloud.gateway.routes;

import com.youhuo.cloud.common.exception.ErrorCode;
import com.youhuo.cloud.common.exception.ServerException;
import com.youhuo.cloud.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


/**
 * <p>
 * 实现动态路由
 * https://blog.csdn.net/tianyaleixiaowu/article/details/83412301
 * </p>
 * todo 后期讲修改成 {@link JdbcDynamicRouteService}
 *
 * @author haiji
 */
@Slf4j
@Service
public class DynamicRouteService implements ApplicationEventPublisherAware {

    @Resource
    private RouteDefinitionWriter routeDefinitionWriter;

    private ApplicationEventPublisher publisher;

    /**
     * 监听事件刷新配置
     */
    public void refreshRoutes() {
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    /**
     * 增加路由
     */
    public Result add(RouteDefinition definition) {
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        refreshRoutes();
        return Result.success();
    }


    /**
     * 更新路由
     */
    public Result update(RouteDefinition definition) {
        try {
            this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
        } catch (Exception e) {
            definition.getId();
            log.error(definition.getId(), e.getMessage(), e);
            throw new ServerException(new ErrorCode(2, "gateway update fail!"));
        }
        try {
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            refreshRoutes();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ServerException(new ErrorCode(2, "gateway update fail!"));
        }

        return Result.success();
    }

    /**
     * 删除路由
     */
    public Result delete(String id) {
        try {
            this.routeDefinitionWriter.delete(Mono.just(id)).subscribe();
            refreshRoutes();
            return Result.success();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ServerException(new ErrorCode(2, "gateway delete fail!"));
        }

    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }


    @PostConstruct
    public void init() {
        // todo 从缓存或者数据库中拉取已经存在的路由配置

    }

}