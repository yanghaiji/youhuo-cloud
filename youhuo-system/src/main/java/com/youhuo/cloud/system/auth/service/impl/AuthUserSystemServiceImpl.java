package com.youhuo.cloud.system.auth.service.impl;

import com.youhuo.cloud.common.enums.CommonStatusEnum;
import com.youhuo.cloud.common.enums.LoginLogTypeEnum;
import com.youhuo.cloud.common.enums.UserTypeEnum;
import com.youhuo.cloud.common.exception.BusinessException;
import com.youhuo.cloud.common.exception.enums.GlobalErrorCodeConstants;
import com.youhuo.cloud.common.util.constants.OAuth2ClientConstants;
import com.youhuo.cloud.mybatis.core.query.LambdaQueryWrapperX;
import com.youhuo.cloud.system.auth.dao.AuthUserMapper;
import com.youhuo.cloud.system.auth.dto.AuthUserDTO;
import com.youhuo.cloud.system.auth.service.AdminUserService;
import com.youhuo.cloud.system.auth.service.AuthUserSystemService;
import com.youhuo.cloud.system.auth.service.OAuth2TokenService;
import com.youhuo.cloud.system.auth.service.VerificationCodeService;
import com.youhuo.cloud.system.auth.vo.AuthUserResVO;
import com.youhuo.cloud.system.entity.oauth2.OAuth2AccessTokenDO;
import com.youhuo.cloud.system.entity.user.AuthUserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-03-19
 */
@Slf4j
@Service
public class AuthUserSystemServiceImpl implements AuthUserSystemService {

    @Autowired
    private AuthUserMapper authUserDao;
    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private OAuth2TokenService oauth2TokenService;

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
    @Override
    public AuthUserResVO login(AuthUserDTO authUserDTO) {
        // 1.校验验证否是否正确
        VerificationCodeService.verificationCode(authUserDTO.getCaptchaVerification());
        // 2. 校验有户是否存在,校验密码是否正确，校验是否禁用
        AuthUserEntity authenticate = authenticate(authUserDTO);
        // 4.创建refreshToken 与accessToken
        return createTokenAfterLoginSuccess(authenticate.getId(),authUserDTO.getUsername(),LoginLogTypeEnum.LOGIN_USERNAME);
    }

    private AuthUserEntity authenticate(AuthUserDTO authUserDTO) {
        AuthUserEntity authUserEntity = authUserDao.selectOne(new LambdaQueryWrapperX<AuthUserEntity>().eq(AuthUserEntity::getUsername, authUserDTO.getUsername()));
        //判断有户是否存在
        if (Objects.isNull(authUserEntity)) {
            throw new BusinessException(GlobalErrorCodeConstants.USER_PWD_ERROR);
        }
        //密码是否正确
        if (!adminUserService.isPasswordMatch(authUserDTO.getPassword(), authUserEntity.getPassword())) {
            throw new BusinessException(GlobalErrorCodeConstants.USER_PWD_ERROR);
        }
        //是否被锁定
        if (authUserEntity.getStatus().equals(CommonStatusEnum.DISABLE.getStatus())) {
            throw new BusinessException(GlobalErrorCodeConstants.USER_LOCK);
        }
        return authUserEntity;
    }

    private AuthUserResVO createTokenAfterLoginSuccess(Long userId, String username, LoginLogTypeEnum logType) {
        // 创建访问令牌
        OAuth2AccessTokenDO accessTokenDO = oauth2TokenService.createAccessToken(userId, getUserType().getValue(),
                OAuth2ClientConstants.CLIENT_ID_DEFAULT, null);
        // 构建返回结果
        AuthUserResVO vo = new AuthUserResVO();
        BeanUtils.copyProperties(accessTokenDO,vo);
        return vo;
    }


    private UserTypeEnum getUserType() {
        return UserTypeEnum.ADMIN;
    }

}
