package com.youhuo.cloud.system.auth.controller;

import com.youhuo.cloud.common.log.Logger;
import com.youhuo.cloud.common.result.Result;
import com.youhuo.cloud.system.auth.dto.AuthUserDTO;
import com.youhuo.cloud.system.auth.service.AuthUserSystemService;
import com.youhuo.cloud.system.auth.vo.AuthUserResVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 登录操作
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-03-19
 */
@Tag(name = "登录操作")
@RestController
@RequestMapping(value = "/system/api/")
public class AuthController {

    @Autowired
    private AuthUserSystemService authUserSystemService;

    @Logger(name = "login")
    @Operation(summary = "登录")
    @PostMapping(value = "login")
    public Result<AuthUserResVO> login(@RequestBody @Valid AuthUserDTO authUserDTO){
        return Result.success(authUserSystemService.login(authUserDTO));
    }

}
