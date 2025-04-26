package com.sheepion.simplequiz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sheepion.simplequiz.enums.QuestionType;
import com.sheepion.simplequiz.model.QuizQuestion;

/**
 * 题目表数据访问接口
 */
@Mapper
public interface QuizQuestionMapper {
    
    /**
     * 插入一条题目记录
     *
     * @param quizQuestion 题目信息
     * @return 影响的行数
     */
    int insert(QuizQuestion quizQuestion);
    
    /**
     * 根据ID更新题目信息
     *
     * @param quizQuestion 题目信息
     * @return 影响的行数
     */
    int updateById(QuizQuestion quizQuestion);
    
    /**
     * 根据ID查询题目信息
     *
     * @param id 题目ID
     * @return 题目信息
     */
    QuizQuestion selectById(@Param("id") Long id);
    
    /**
     * 条件查询题目列表，所有条件均为可选
     *
     * @param quizBankId 题库ID，可选
     * @param title 题目标题关键字，可选
     * @param type 题目类型，可选
     * @return 题目列表
     */
    List<QuizQuestion> selectByCondition(
        @Param("quizBankId") Long quizBankId,
        @Param("title") String title,
        @Param("type") QuestionType type
    );
    
    /**
     * 根据ID逻辑删除题目
     *
     * @param id 题目ID
     * @return 影响的行数
     */
    int deleteById(@Param("id") Long id);
} 