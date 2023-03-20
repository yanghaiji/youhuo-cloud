package com.youhuo.cloud.system.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * <p>
 * token返回值
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-03-19
 */
@Schema(description = "管理后台 - 登录 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthUserResVO {

    @Schema(description = "用户编号", required = true, example = "1024")
    private Long userId;

    @Schema(description = "访问令牌", required = true, example = "happy")
    private String accessToken;

    @Schema(description = "刷新令牌", required = true, example = "nice")
    private String refreshToken;

    @Schema(description = "过期时间", required = true)
    private LocalDateTime expiresTime;

    @Schema(description = "令牌类型", required = true)
    private String tokenType = "bearer";
}
