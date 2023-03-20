package com.youhuo.cloud.system.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youhuo.cloud.system.entity.user.AuthUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-03-20
 */
@Mapper
@Component
public interface AuthUserMapper extends BaseMapper<AuthUserEntity> {

}
