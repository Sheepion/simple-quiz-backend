package com.sheepion.simplequiz.service;

import java.util.List;

import com.sheepion.simplequiz.model.QuizBank;

/**
 * 题库服务接口
 */
public interface QuizBankService {
    
    /**
     * 创建题库
     *
     * @param quizBank 题库信息
     * @return 创建后的题库
     */
    QuizBank create(QuizBank quizBank);
    
    /**
     * 更新题库
     *
     * @param quizBank 题库信息
     * @return 更新后的题库
     */
    QuizBank update(QuizBank quizBank);
    
    /**
     * 根据ID获取题库
     *
     * @param id 题库ID
     * @return 题库信息
     */
    QuizBank getById(Long id);
    
    /**
     * 获取所有题库列表
     *
     * @return 题库列表
     */
    List<QuizBank> list();
    
    /**
     * 根据名称查询题库
     *
     * @param name 题库名称关键字
     * @return 题库列表
     */
    List<QuizBank> getByName(String name);
    
    /**
     * 根据ID删除题库
     *
     * @param id 题库ID
     * @return 是否删除成功
     */
    boolean deleteById(Long id);
} 