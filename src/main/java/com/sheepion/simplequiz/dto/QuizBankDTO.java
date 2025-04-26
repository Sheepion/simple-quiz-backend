package com.sheepion.simplequiz.dto;

import java.time.LocalDateTime;

import com.sheepion.simplequiz.model.QuizBank;

import io.github.linpeilie.annotations.AutoMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 题库数据传输对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "题库DTO")
@AutoMapper(target = QuizBank.class)
public class QuizBankDTO {
    
    @Schema(description = "ID")
    private Long id;
    
    @Schema(description = "题库名称", example = "Java编程题库", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
    
    @Schema(description = "题库描述", example = "包含Java基础知识和高级特性的题目")
    private String description;
    
    @Schema(description = "创建时间")
    private LocalDateTime createdAt;
    
    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;
} 