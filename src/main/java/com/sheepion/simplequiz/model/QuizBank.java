package com.sheepion.simplequiz.model;

import java.time.OffsetDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 题库实体类
 */
@Data
@Schema(description = "题库")
public class QuizBank {
    
    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;
    
    @Schema(description = "题库名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
    
    @Schema(description = "题库描述")
    private String description;
    
    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private OffsetDateTime createdAt;
    
    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private OffsetDateTime updatedAt;
    
    @Schema(description = "是否已删除", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean deleted;
} 