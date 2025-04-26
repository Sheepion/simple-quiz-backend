package com.sheepion.simplequiz.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sheepion.simplequiz.common.Result;
import com.sheepion.simplequiz.dto.QuizQuestionDTO;
import com.sheepion.simplequiz.dto.QuizQuestionUpdateDTO;
import com.sheepion.simplequiz.enums.QuestionType;
import com.sheepion.simplequiz.model.QuizQuestion;
import com.sheepion.simplequiz.service.QuizQuestionService;

import io.github.linpeilie.Converter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * 题目管理控制器
 */
@RestController
@RequestMapping("/api/quiz-questions")
@RequiredArgsConstructor
@Tag(name = "题目管理", description = "题目的增删改查")
public class QuizQuestionController {

    private final QuizQuestionService quizQuestionService;
    private final Converter converter;

    @PostMapping
    @Operation(summary = "创建题目", description = "创建一个新的题目")
    public Result<QuizQuestionDTO> create(@RequestBody QuizQuestionDTO quizQuestionDTO) {
        try {
            QuizQuestion quizQuestion = converter.convert(quizQuestionDTO, QuizQuestion.class);
            QuizQuestion created = quizQuestionService.create(quizQuestion);
            return Result.success(converter.convert(created, QuizQuestionDTO.class));
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新题目", description = "根据ID更新题目信息")
    public Result<QuizQuestionDTO> update(
            @Parameter(description = "题目ID") @PathVariable Long id,
            @RequestBody QuizQuestionUpdateDTO quizQuestionUpdateDTO) {
        try {
            quizQuestionUpdateDTO.setId(id);
            QuizQuestion updated = quizQuestionService.update(quizQuestionUpdateDTO);
            return Result.success(converter.convert(updated, QuizQuestionDTO.class));
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取题目", description = "根据ID获取题目详情")
    public Result<QuizQuestionDTO> getById(@Parameter(description = "题目ID") @PathVariable Long id) {
        QuizQuestion quizQuestion = quizQuestionService.getById(id);
        if (quizQuestion == null) {
            return Result.fail("题目不存在");
        }
        return Result.success(converter.convert(quizQuestion, QuizQuestionDTO.class));
    }

    @GetMapping("/bank/{quizBankId}")
    @Operation(summary = "获取题库下的题目", description = "获取指定题库下的所有题目")
    public Result<List<QuizQuestionDTO>> getByQuizBankId(
            @Parameter(description = "题库ID") @PathVariable Long quizBankId) {
        try {
            List<QuizQuestion> questions = quizQuestionService.getByQuizBankId(quizBankId);
            return Result.success(converter.convert(questions, QuizQuestionDTO.class));
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }

    @GetMapping("/search")
    @Operation(summary = "搜索题目", description = "按条件搜索题目")
    public Result<List<QuizQuestionDTO>> search(
            @Parameter(description = "题库ID") @RequestParam(required = false) Long quizBankId,
            @Parameter(description = "题目标题关键字") @RequestParam(required = false) String title,
            @Parameter(description = "题目类型") @RequestParam(required = false) QuestionType type) {
        try {
            List<QuizQuestion> questions = quizQuestionService.search(quizBankId, title, type);
            return Result.success(converter.convert(questions, QuizQuestionDTO.class));
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除题目", description = "根据ID删除题目")
    public Result<Boolean> delete(@Parameter(description = "题目ID") @PathVariable Long id) {
        boolean result = quizQuestionService.deleteById(id);
        if (result) {
            return Result.success(true);
        } else {
            return Result.fail("题目不存在或删除失败");
        }
    }
} 