package com.sheepion.simplequiz.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用响应结果
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "通用响应结果")
public class Result<T> {
    
    @Schema(description = "响应代码", example = "200")
    private Integer code;
    
    @Schema(description = "响应消息", example = "操作成功")
    private String message;
    
    @Schema(description = "响应数据")
    private T data;
    
    /**
     * 成功结果
     *
     * @param data 数据
     * @param <T> 数据类型
     * @return 结果对象
     */
    public static <T> Result<T> success(T data) {
        return Result.<T>builder()
                .code(200)
                .message("操作成功")
                .data(data)
                .build();
    }
    
    /**
     * 成功结果
     *
     * @param message 消息
     * @param data 数据
     * @param <T> 数据类型
     * @return 结果对象
     */
    public static <T> Result<T> success(String message, T data) {
        return Result.<T>builder()
                .code(200)
                .message(message)
                .data(data)
                .build();
    }
    
    /**
     * 失败结果
     *
     * @param code 错误代码
     * @param message 错误消息
     * @param <T> 数据类型
     * @return 结果对象
     */
    public static <T> Result<T> fail(int code, String message) {
        return Result.<T>builder()
                .code(code)
                .message(message)
                .build();
    }
    
    /**
     * 失败结果
     *
     * @param message 错误消息
     * @param <T> 数据类型
     * @return 结果对象
     */
    public static <T> Result<T> fail(String message) {
        return fail(500, message);
    }
} 