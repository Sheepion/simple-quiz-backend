package com.sheepion.simplequiz.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sheepion.simplequiz.mapper.QuizBankMapper;
import com.sheepion.simplequiz.model.QuizBank;
import com.sheepion.simplequiz.service.QuizBankService;

import lombok.RequiredArgsConstructor;

/**
 * 题库服务实现类
 */
@Service
@RequiredArgsConstructor
public class QuizBankServiceImpl implements QuizBankService {

    private final QuizBankMapper quizBankMapper;

    @Override
    @Transactional
    public QuizBank create(QuizBank quizBank) {
        quizBankMapper.insert(quizBank);
        return quizBank;
    }

    @Override
    @Transactional
    public QuizBank update(QuizBank quizBank) {
        // 先查询确保存在
        QuizBank existingQuizBank = getById(quizBank.getId());
        if (existingQuizBank == null) {
            throw new RuntimeException("题库不存在");
        }
        
        quizBankMapper.updateById(quizBank);
        return getById(quizBank.getId());
    }

    @Override
    public QuizBank getById(Long id) {
        return quizBankMapper.selectById(id);
    }

    @Override
    public List<QuizBank> list() {
        return quizBankMapper.selectList();
    }

    @Override
    public List<QuizBank> getByName(String name) {
        return quizBankMapper.selectByName(name);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        // 先查询确保存在
        QuizBank existingQuizBank = getById(id);
        if (existingQuizBank == null) {
            return false;
        }
        
        return quizBankMapper.deleteById(id) > 0;
    }
} 