package com.youhuo.cloud.system.auth.service;

/**
 * <p>
 * 有户相关
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-03-20
 */
public interface AdminUserService {

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
    boolean isPasswordMatch(String rawPassword, String encodedPassword);
}
