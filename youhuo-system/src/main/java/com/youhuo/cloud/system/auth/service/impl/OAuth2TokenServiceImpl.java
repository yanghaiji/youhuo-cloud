package com.youhuo.cloud.system.auth.service.impl;

import cn.hutool.core.util.IdUtil;
import com.youhuo.cloud.mybatis.core.handler.DefaultDBFieldHandler;
import com.youhuo.cloud.system.auth.dao.OAuth2AccessTokenMapper;
import com.youhuo.cloud.system.auth.dao.OAuth2RefreshTokenMapper;
import com.youhuo.cloud.system.auth.service.OAuth2ClientService;
import com.youhuo.cloud.system.auth.service.OAuth2TokenService;
import com.youhuo.cloud.system.entity.oauth2.OAuth2AccessTokenDO;
import com.youhuo.cloud.system.entity.oauth2.OAuth2ClientDO;
import com.youhuo.cloud.system.entity.oauth2.OAuth2RefreshTokenDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-03-20
 */
@Service
public class OAuth2TokenServiceImpl implements OAuth2TokenService {

    @Autowired
    private OAuth2ClientService oauth2ClientService;
    @Autowired
    private OAuth2AccessTokenMapper oauth2AccessTokenMapper;
    @Autowired
    private OAuth2RefreshTokenMapper auth2RefreshTokenMapper;

    @Autowired
    private DefaultDBFieldHandler defaultDBFieldHandler;
    /**
     * <p>
     * 创建token
     * </p>
     *
     * @param userId   userid
     * @param userType 类型
     * @param clientId 客户端id
     * @param scopes   scopes 类型
     * @return com.youhuo.cloud.system.entity.oauth2.OAuth2AccessTokenDO
     * @version 1.0.0
     * @author hai ji
     * @since 2023/3/20
     */
    @Override
    public OAuth2AccessTokenDO createAccessToken(Long userId, Integer userType, String clientId, List<String> scopes) {
        // 验证clientId的有效性
        OAuth2ClientDO clientDO = oauth2ClientService.validOAuthClientFromCache(clientId);
        // 创建刷新令牌
        OAuth2RefreshTokenDO refreshTokenDO = createOAuth2RefreshToken(userId, userType, clientDO, scopes);
        // 创建访问令牌
        return createOAuth2AccessToken(refreshTokenDO, clientDO);
    }

    /**
     * 刷新访问令牌
     * <p>
     * 参考 DefaultTokenServices 的 refreshAccessToken 方法
     *
     * @param refreshToken 刷新令牌
     * @param clientId     客户端编号
     * @return 访问令牌的信息
     */
    @Override
    public OAuth2AccessTokenDO refreshAccessToken(String refreshToken, String clientId) {
        return null;
    }

    /**
     * 获得访问令牌
     * <p>
     * 参考 DefaultTokenServices 的 getAccessToken 方法
     *
     * @param accessToken 访问令牌
     * @return 访问令牌的信息
     */
    @Override
    public OAuth2AccessTokenDO getAccessToken(String accessToken) {
        return null;
    }

    /**
     * 校验访问令牌
     *
     * @param accessToken 访问令牌
     * @return 访问令牌的信息
     */
    @Override
    public OAuth2AccessTokenDO checkAccessToken(String accessToken) {
        return null;
    }

    /**
     * 移除访问令牌
     * 注意：该流程中，会移除相关的刷新令牌
     * <p>
     * 参考 DefaultTokenServices 的 revokeToken 方法
     *
     * @param accessToken 刷新令牌
     * @return 访问令牌的信息
     */
    @Override
    public OAuth2AccessTokenDO removeAccessToken(String accessToken) {
        return null;
    }


    private OAuth2AccessTokenDO createOAuth2AccessToken(OAuth2RefreshTokenDO refreshTokenDO, OAuth2ClientDO clientDO) {
        OAuth2AccessTokenDO accessTokenDO = new OAuth2AccessTokenDO()
                .setAccessToken(generateAccessToken())
                .setUserId(refreshTokenDO.getUserId()).setUserType(refreshTokenDO.getUserType())
                .setClientId(clientDO.getClientId()).setScopes(refreshTokenDO.getScopes())
                .setRefreshToken(refreshTokenDO.getRefreshToken())
                .setExpiresTime(LocalDateTime.now().plusSeconds(clientDO.getAccessTokenValiditySeconds()));
//        accessTokenDO.setTenantId(TenantContextHolder.getTenantId()); // 手动设置租户编号，避免缓存到 Redis 的时候，无对应的租户编号
        oauth2AccessTokenMapper.insert(accessTokenDO);
        // 记录到 Redis 中
//        oauth2AccessTokenRedisDAO.set(accessTokenDO);
        return accessTokenDO;
    }

    private OAuth2RefreshTokenDO createOAuth2RefreshToken(Long userId, Integer userType, OAuth2ClientDO clientDO, List<String> scopes) {
        OAuth2RefreshTokenDO refreshToken = new OAuth2RefreshTokenDO().setRefreshToken(generateRefreshToken())
                .setUserId(userId).setUserType(userType)
                .setClientId(clientDO.getClientId()).setScopes(scopes)
                .setExpiresTime(LocalDateTime.now().plusSeconds(clientDO.getRefreshTokenValiditySeconds()));
        auth2RefreshTokenMapper.insert(refreshToken);
        return refreshToken;
    }

    private static String generateAccessToken() {
        return IdUtil.fastSimpleUUID();
    }

    private static String generateRefreshToken() {
        return IdUtil.fastSimpleUUID();
    }

}
