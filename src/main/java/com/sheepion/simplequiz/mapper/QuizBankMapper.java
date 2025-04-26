package com.sheepion.simplequiz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sheepion.simplequiz.model.QuizBank;

/**
 * 题库表数据访问接口
 */
@Mapper
public interface QuizBankMapper {
    
    /**
     * 插入一条题库记录
     *
     * @param quizBank 题库信息
     * @return 影响的行数
     */
    int insert(QuizBank quizBank);
    
    /**
     * 根据ID更新题库信息
     *
     * @param quizBank 题库信息
     * @return 影响的行数
     */
    int updateById(QuizBank quizBank);
    
    /**
     * 根据ID查询题库信息
     *
     * @param id 题库ID
     * @return 题库信息
     */
    QuizBank selectById(@Param("id") Long id);
    
    /**
     * 查询所有未删除的题库列表
     *
     * @return 题库列表
     */
    List<QuizBank> selectList();
    
    /**
     * 根据名称模糊查询题库列表
     *
     * @param name 题库名称关键字
     * @return 题库列表
     */
    List<QuizBank> selectByName(@Param("name") String name);
    
    /**
     * 根据ID逻辑删除题库
     *
     * @param id 题库ID
     * @return 影响的行数
     */
    int deleteById(@Param("id") Long id);
} 