package com.sheepion.simplequiz.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sheepion.simplequiz.dto.QuizQuestionUpdateDTO;
import com.sheepion.simplequiz.enums.QuestionType;
import com.sheepion.simplequiz.mapper.QuizQuestionMapper;
import com.sheepion.simplequiz.model.QuizBank;
import com.sheepion.simplequiz.model.QuizQuestion;
import com.sheepion.simplequiz.service.QuizBankService;
import com.sheepion.simplequiz.service.QuizQuestionService;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;

/**
 * 题目服务实现类
 */
@Service
@RequiredArgsConstructor
public class QuizQuestionServiceImpl implements QuizQuestionService {

    private final QuizQuestionMapper quizQuestionMapper;
    private final QuizBankService quizBankService;

    @Override
    @Transactional
    public QuizQuestion create(QuizQuestion quizQuestion) {
        // 验证题库是否存在
        QuizBank quizBank = quizBankService.getById(quizQuestion.getQuizBankId());
        Objects.requireNonNull(quizBank, "题库不存在");
        
        quizQuestionMapper.insert(quizQuestion);
        return quizQuestion;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public QuizQuestion update(QuizQuestionUpdateDTO updateDTO) {
        // 先查询确保题目存在
        QuizQuestion existingQuestion = getById(updateDTO.getId());
        Objects.requireNonNull(existingQuestion, "题目不存在");
        
        // 不允许更新题库 ID
        BeanUtil.copyProperties(updateDTO, existingQuestion, "quizBankId");
        
        quizQuestionMapper.updateById(existingQuestion);
        return getById(existingQuestion.getId());
    }

    @Override
    public QuizQuestion getById(Long id) {
        return quizQuestionMapper.selectById(id);
    }

    @Override
    public List<QuizQuestion> getByQuizBankId(Long quizBankId) {
        QuizBank quizBank = quizBankService.getById(quizBankId);
        Objects.requireNonNull(quizBank, "题库不存在");
        
        return quizQuestionMapper.selectByCondition(quizBankId, null, null);
    }

    @Override
    public List<QuizQuestion> search(Long quizBankId, String title, QuestionType type) {
        
        return quizQuestionMapper.selectByCondition(quizBankId, title, type);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        
        return quizQuestionMapper.deleteById(id) > 0;
    }
    
} 