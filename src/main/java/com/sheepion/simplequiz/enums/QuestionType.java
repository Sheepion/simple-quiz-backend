package com.sheepion.simplequiz.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * 题目类型枚举
 */
@Getter
@Schema(description = "题目类型")
public enum QuestionType {
    
    /**
     * 单选题
     */
    SINGLE_CHOICE("单选题"),
    
    /**
     * 多选题
     */
    MULTIPLE_CHOICE("多选题"),
    
    /**
     * 判断题
     */
    JUDGMENT("判断题"),
    
    /**
     * 填空题
     */
    FILL_BLANK("填空题"),
    
    /**
     * 简答题
     */
    SHORT_ANSWER("简答题");
    
    /**
     * 类型描述
     */
    private final String description;
    
    QuestionType(String description) {
        this.description = description;
    }
} 