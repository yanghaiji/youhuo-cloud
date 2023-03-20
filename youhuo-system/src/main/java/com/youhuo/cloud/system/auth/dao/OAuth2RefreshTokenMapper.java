package com.youhuo.cloud.system.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youhuo.cloud.mybatis.core.query.LambdaQueryWrapperX;
import com.youhuo.cloud.system.entity.oauth2.OAuth2RefreshTokenDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface OAuth2RefreshTokenMapper extends BaseMapper<OAuth2RefreshTokenDO> {

    default int deleteByRefreshToken(String refreshToken) {
        return delete(new LambdaQueryWrapperX<OAuth2RefreshTokenDO>()
                .eq(OAuth2RefreshTokenDO::getRefreshToken, refreshToken));
    }


}
