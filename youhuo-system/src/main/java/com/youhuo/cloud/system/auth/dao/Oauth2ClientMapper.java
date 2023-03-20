package com.youhuo.cloud.system.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youhuo.cloud.system.entity.oauth2.OAuth2ClientDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Oauth2Clien
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-03-20
 */
@Mapper
@Component
public interface Oauth2ClientMapper extends BaseMapper<OAuth2ClientDO> {
}
