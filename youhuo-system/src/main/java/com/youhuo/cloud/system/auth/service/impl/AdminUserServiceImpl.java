package com.youhuo.cloud.system.auth.service.impl;

import com.youhuo.cloud.system.auth.service.AdminUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * <p>
     * 验证密码是否有效
     * </p>
     *
     * @param rawPassword     输入的密码
     * @param encodedPassword 原密码
     * @return boolean
     * @version 1.0.0
     * @author hai ji
     * @since 2023/3/20
     */
    @Override
    public boolean isPasswordMatch(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
