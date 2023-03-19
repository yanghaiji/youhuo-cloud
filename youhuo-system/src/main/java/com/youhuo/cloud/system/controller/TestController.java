package com.youhuo.cloud.system.controller;

import com.youhuo.cloud.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 测试
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-03-17
 */
@Tag(name="test api 文档")
@RestController
@RequestMapping(value = "/system/api/")
public class TestController {

    @Operation(summary = "测试文档")
    @GetMapping(value = "test")
    public Result test() {
        return Result.success();
    }
}
