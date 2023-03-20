package com.youhuo.cloud.system.auth.service;

import com.youhuo.cloud.system.auth.dto.AuthUserDTO;
import com.youhuo.cloud.system.auth.vo.AuthUserResVO;
import com.youhuo.cloud.system.entity.oauth2.OAuth2AccessTokenDO;

/**
 * <p>
 *
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-03-19
 */
public interface AuthUserSystemService {
    /**
     * <p>
     * 登录
     * </p>
     *
     * @param authUserDTO 有户信息
     * @return com.youhuo.cloud.system.auth.vo.AuthUserResDTO
     * @version 1.0.0
     * @author hai ji
     * @since 2023/3/19
     */
    AuthUserResVO login(AuthUserDTO authUserDTO);
}
