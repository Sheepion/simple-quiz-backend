package com.sheepion.simplequiz.dto;

import java.time.LocalDateTime;
import java.util.Map;

import com.sheepion.simplequiz.enums.QuestionType;
import com.sheepion.simplequiz.model.QuizQuestion;

import io.github.linpeilie.annotations.AutoMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 题目数据传输对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "题目DTO")
@AutoMapper(target = QuizQuestion.class)
public class QuizQuestionDTO {
    
    @Schema(description = "ID")
    private Long id;
    
    @Schema(description = "所属题库ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long quizBankId;
    
    @Schema(description = "题目标题", example = "Java中的基本数据类型", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;
    
    @Schema(description = "题目内容", example = "以下哪些是Java的基本数据类型？", requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;
    
    @Schema(description = "题目类型", requiredMode = Schema.RequiredMode.REQUIRED)
    private QuestionType type;
    
    @Schema(description = "选项内容，JSON格式", example = "{\"A\":\"int\",\"B\":\"Integer\",\"C\":\"String\",\"D\":\"boolean\"}")
    private Map<String, String> options;
    
    @Schema(description = "正确答案", example = "A,D", requiredMode = Schema.RequiredMode.REQUIRED)
    private String answer;
    
    @Schema(description = "题目解析", example = "Java的基本数据类型包括int, byte, short, long, float, double, char, boolean")
    private String analysis;
    
    @Schema(description = "创建时间")
    private LocalDateTime createdAt;
    
    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;
} 