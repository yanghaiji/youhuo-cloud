package com.youhuo.cloud.mybatis.dataobject;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 多租户全局id
 *
 * @author haiji
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class TenantBaseDO extends BaseDO {

    /**
     * 多租户编号
     */
    private Long tenantId;

}