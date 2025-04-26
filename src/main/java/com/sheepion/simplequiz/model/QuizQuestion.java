package com.sheepion.simplequiz.model;

import java.time.OffsetDateTime;

import com.sheepion.simplequiz.enums.QuestionType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 题目实体类
 */
@Data
@Schema(description = "题目")
public class QuizQuestion {
    
    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;
    
    @Schema(description = "所属题库ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long quizBankId;
    
    @Schema(description = "题目标题", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;
    
    @Schema(description = "题目内容", requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;
    
    @Schema(description = "题目类型", requiredMode = Schema.RequiredMode.REQUIRED)
    private QuestionType type;
    
    @Schema(description = "选项内容，JSON格式")
    private Object options;
    
    @Schema(description = "正确答案", requiredMode = Schema.RequiredMode.REQUIRED)
    private String answer;
    
    @Schema(description = "题目解析")
    private String analysis;
    
    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private OffsetDateTime createdAt;
    
    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private OffsetDateTime updatedAt;
    
    @Schema(description = "是否已删除", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean deleted;
} 