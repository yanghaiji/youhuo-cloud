package com.youhuo.cloud.api.gateway;

import com.youhuo.cloud.common.result.Result;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * <p>
 * 动态路由实现
 * </p>
 * //todo 需要换成自定义封装的实体进行重写 {@link GatewayRouteEntity} 并讲 {@link DynamicRouteService} 进行重写
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-03-19
 */
public interface IGatewayRouteClient {

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
    @PostMapping(value = "/gateway/api/save")
    <T> Result<T> add(GatewayRouteEntity definition);

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
    @PostMapping(value = "/gateway/api/update")
    <T> Result<T> update(GatewayRouteEntity definition);

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
    @DeleteMapping(value = "/gateway/api/delete/{id}")
    <T> Result<T> delete(Long id);

}
