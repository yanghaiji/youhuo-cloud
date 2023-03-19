package com.youhuo.cloud.system.service.feign;

import com.youhuo.cloud.api.gateway.IGatewayRouteClient;
import com.youhuo.cloud.common.feign.FeignClientInstance;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * <p>
 * gateway
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023/3/19
 */
@Component
@FeignClient(value = FeignClientInstance.GATEWAY_INSTANCE)
public interface GatewayServiceClient extends IGatewayRouteClient {


}
