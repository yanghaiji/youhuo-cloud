package com.youhuo.cloud.gateway.routes;

import com.google.common.collect.Lists;
import com.youhuo.cloud.api.gateway.GatewayRouteEntity;
import com.youhuo.cloud.api.gateway.IGatewayRouteClient;
import com.youhuo.cloud.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.InMemoryRouteDefinitionRepository;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * jdbc 实现动态路由
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-03-19
 */
@Slf4j
public class JdbcDynamicRouteService implements IGatewayRouteClient, ApplicationEventPublisherAware {

    private static final String SELECT_ROUTES = "";
    private ApplicationEventPublisher publisher;
//    private JdbcTemplate jdbcTemplate;
//    private InMemoryRouteDefinitionRepository repository;

//    public JdbcDynamicRouteService(JdbcTemplate jdbcTemplate, InMemoryRouteDefinitionRepository repository) {
//        this.jdbcTemplate = jdbcTemplate;
//        this.repository = repository;
//    }

    /**
     * 监听事件刷新配置
     */
    public void refreshRoutes() {
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    /**
     * <p>
     * 新增网关
     * </p>
     *
     * @param definition
     * @return com.youhuo.cloud.common.result.Result<T>
     * @version 1.0.0
     * @author hai ji
     * @since 2023/3/19
     */
    @Override
    public <T> Result<T> add(GatewayRouteEntity definition) {
        return null;
    }

    /**
     * <p>
     * 更新网关
     * </p>
     *
     * @param definition
     * @return com.youhuo.cloud.common.result.Result<T>
     * @version 1.0.0
     * @author hai ji
     * @since 2023/3/19
     */
    @Override
    public <T> Result<T> update(GatewayRouteEntity definition) {
        return null;
    }

    /**
     * <p>
     * 删除网关
     * </p>
     *
     * @param id
     * @return com.youhuo.cloud.common.result.Result<T>
     * @version 1.0.0
     * @author hai ji
     * @since 2023/3/19
     */
    @Override
    public <T> Result<T> delete(Long id) {
        return null;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

//    private Mono<Void> loadRoutes() {
//        //从数据库拿到路由配置
//        try {
//            List<GatewayRouteEntity> routeList = jdbcTemplate.query(SELECT_ROUTES, (rs, i) -> {
//                GatewayRouteEntity result = new GatewayRouteEntity();
//                result.setRouteId(rs.getLong("route_id"));
//                result.setPath(rs.getString("path"));
//                result.setServiceId(rs.getString("service_id"));
//                result.setUrl(rs.getString("url"));
//                result.setStatus(rs.getInt("status"));
//                result.setRetryable(rs.getInt("retryable"));
//                result.setStripPrefix(rs.getInt("strip_prefix"));
//                result.setIsPersist(rs.getInt("is_persist"));
//                result.setRouteName(rs.getString("route_name"));
//                return result;
//            });
//
//            if (routeList != null) {
//                // 最后加载路由
//                routeList.forEach(gatewayRoute -> {
//                    RouteDefinition definition = new RouteDefinition();
//                    List<PredicateDefinition> predicates = Lists.newArrayList();
//                    List<FilterDefinition> filters = Lists.newArrayList();
//                    definition.setId(gatewayRoute.getRouteName());
//                    // 路由地址
//                    PredicateDefinition predicatePath = new PredicateDefinition();
//                    Map<String, String> predicatePathParams = new HashMap<>(8);
//                    predicatePath.setName("Path");
//                    predicatePathParams.put("name", StringUtils.isBlank(gatewayRoute.getRouteName()) ? gatewayRoute.getRouteId().toString() : gatewayRoute.getRouteName());
//                    predicatePathParams.put("pattern", gatewayRoute.getPath());
//                    predicatePathParams.put("pathPattern", gatewayRoute.getPath());
//                    predicatePath.setArgs(predicatePathParams);
//                    predicates.add(predicatePath);
//                    // 服务地址
//                    URI uri = UriComponentsBuilder.fromUriString(StringUtils.isNotBlank(gatewayRoute.getUrl()) ? gatewayRoute.getUrl() : "lb://" + gatewayRoute.getServiceId()).build().toUri();
//
//                    FilterDefinition stripPrefixDefinition = new FilterDefinition();
//                    Map<String, String> stripPrefixParams = new HashMap<>(8);
//                    stripPrefixDefinition.setName("StripPrefix");
//                    stripPrefixParams.put(NameUtils.GENERATED_NAME_PREFIX + "0", "1");
//                    stripPrefixDefinition.setArgs(stripPrefixParams);
//                    filters.add(stripPrefixDefinition);
//
//                    definition.setPredicates(predicates);
//                    definition.setFilters(filters);
//                    definition.setUri(uri);
//                    this.repository.save(Mono.just(definition)).subscribe();
//                });
//            }
//            log.info("=============加载动态路由:{}==============", routeList.size());
//        } catch (Exception e) {
//            log.error("加载动态路由错误:{}", e);
//        }
//        return Mono.empty();
//    }
}
