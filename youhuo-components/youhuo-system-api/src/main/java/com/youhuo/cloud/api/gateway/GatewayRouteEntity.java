package com.youhuo.cloud.api.gateway;

import com.youhuo.cloud.common.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 网关动态路由
 *
 * @author haiji
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class GatewayRouteEntity extends AbstractEntity {
    private static final long serialVersionUID = -2952097064941740301L;

    /**
     * 路由ID
     */
//    @TableId(type = IdType.ID_WORKER)
    private Long routeId;

    /**
     * 路由名称
     */
    private String routeName;

    /**
     * 路径
     */
    private String path;

    /**
     * 服务ID
     */
    private String serviceId;

    /**
     * 完整地址
     */
    private String url;

    /**
     * 忽略前缀
     */
    private Integer stripPrefix;

    /**
     * 0-不重试 1-重试
     */
    private Integer retryable;

    /**
     * 状态:0-无效 1-有效
     */
    private Integer status;

    /**
     * 默认数据0-否 1-是 禁止删除
     */
    private Integer isPersist;

    /**
     * 路由说明
     */
    private String routeDesc;
}
