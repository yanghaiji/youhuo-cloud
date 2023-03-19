package com.youhuo.cloud.gateway.controller;

import com.youhuo.cloud.common.result.Result;
import com.youhuo.cloud.gateway.routes.DynamicRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * gateway 维护列表
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-03-17
 */
@RestController
public class GatewayRoutesController {

    @Autowired
    private DynamicRouteService dynamicRouteService;

    @PostMapping(value = "/gateway/api/save")
    public Result save(@RequestBody RouteDefinition definition) {
        return dynamicRouteService.add(definition);
    }

    @PostMapping(value = "/gateway/api/update")
    public Result update(@RequestBody RouteDefinition definition) {
        return dynamicRouteService.update(definition);
    }

    @DeleteMapping(value = "/gateway/api/delete/{id}")
    public Result delete(@PathVariable String id) {
        return dynamicRouteService.delete(id);
    }

}
