package com.youhuo.cloud.system.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.youhuo.cloud.mybatis.dataobject.TenantBaseDO;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * <p>
 * 登录表
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-03-19
 */
@TableName(value = "system_users", autoResultMap = true)
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserEntity extends TenantBaseDO {
    /**
     * 用户ID
     */
    @TableId
    private Long id;
    /**
     * 用户账号
     */
    private String username;
    /**
     * 加密后的密码
     * <p>
     * 因为目前使用 {@link BCryptPasswordEncoder} 加密器，所以无需自己处理 salt 盐
     */
    private String password;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 备注
     */
    private String remark;
    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 岗位编号数组
     * todo 后期可以重写
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Set<Long> postIds;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 用户性别
     * <p>
     * 枚举类 {@link SexEnum}
     */
    private Integer sex;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 帐号状态
     * <p>
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 最后登录IP
     */
    private String loginIp;
    /**
     * 最后登录时间
     */
    private LocalDateTime loginDate;
}
