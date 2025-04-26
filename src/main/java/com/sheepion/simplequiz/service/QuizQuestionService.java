package com.sheepion.simplequiz.service;

import java.util.List;

import com.sheepion.simplequiz.dto.QuizQuestionUpdateDTO;
import com.sheepion.simplequiz.enums.QuestionType;
import com.sheepion.simplequiz.model.QuizQuestion;

/**
 * 题目服务接口
 */
public interface QuizQuestionService {
    
    /**
     * 创建题目
     *
     * @param quizQuestion 题目信息
     * @return 创建后的题目
     */
    QuizQuestion create(QuizQuestion quizQuestion);
    
    /**
     * 更新题目<br>
     * 不允许更新题库 ID
     *
     * @param updateDTO 题目更新信息
     * @return 更新后的题目
     */
    QuizQuestion update(QuizQuestionUpdateDTO updateDTO);
    
    /**
     * 根据ID获取题目
     *
     * @param id 题目ID
     * @return 题目信息
     */
    QuizQuestion getById(Long id);
    
    /**
     * 根据题库ID获取题目列表
     *
     * @param quizBankId 题库ID
     * @return 题目列表
     */
    List<QuizQuestion> getByQuizBankId(Long quizBankId);
    
    /**
     * 条件查询题目
     *
     * @param quizBankId 题库ID，可选
     * @param title 题目标题关键字，可选
     * @param type 题目类型，可选
     * @return 题目列表
     */
    List<QuizQuestion> search(Long quizBankId, String title, QuestionType type);
    
    /**
     * 根据ID删除题目
     *
     * @param id 题目ID
     * @return 是否删除成功
     */
    boolean deleteById(Long id);
} 