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
import com.sheepion.simplequiz.dto.QuizBankDTO;
import com.sheepion.simplequiz.model.QuizBank;
import com.sheepion.simplequiz.service.QuizBankService;

import io.github.linpeilie.Converter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * 题库管理控制器
 */
@RestController
@RequestMapping("/api/quiz-banks")
@RequiredArgsConstructor
@Tag(name = "题库管理", description = "题库的增删改查")
public class QuizBankController {

    private final QuizBankService quizBankService;
    private final Converter converter;

    @PostMapping
    @Operation(summary = "创建题库", description = "创建一个新的题库")
    public Result<QuizBankDTO> create(@RequestBody QuizBankDTO quizBankDTO) {
        QuizBank quizBank = converter.convert(quizBankDTO, QuizBank.class);
        QuizBank created = quizBankService.create(quizBank);
        return Result.success(converter.convert(created, QuizBankDTO.class));
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新题库", description = "根据ID更新题库信息")
    public Result<QuizBankDTO> update(
            @Parameter(description = "题库ID") @PathVariable Long id,
            @RequestBody QuizBankDTO quizBankDTO) {
        quizBankDTO.setId(id);
        QuizBank quizBank = converter.convert(quizBankDTO, QuizBank.class);
        try {
            QuizBank updated = quizBankService.update(quizBank);
            return Result.success(converter.convert(updated, QuizBankDTO.class));
        } catch (RuntimeException e) {
            return Result.fail(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取题库", description = "根据ID获取题库详情")
    public Result<QuizBankDTO> getById(@Parameter(description = "题库ID") @PathVariable Long id) {
        QuizBank quizBank = quizBankService.getById(id);
        if (quizBank == null) {
            return Result.fail("题库不存在");
        }
        return Result.success(converter.convert(quizBank, QuizBankDTO.class));
    }

    @GetMapping
    @Operation(summary = "获取题库列表", description = "获取所有题库列表")
    public Result<List<QuizBankDTO>> list() {
        List<QuizBank> quizBanks = quizBankService.list();
        return Result.success(converter.convert(quizBanks, QuizBankDTO.class));
    }

    @GetMapping("/search")
    @Operation(summary = "搜索题库", description = "根据名称搜索题库")
    public Result<List<QuizBankDTO>> search(
            @Parameter(description = "题库名称关键字") @RequestParam(required = false) String name) {
        List<QuizBank> quizBanks = quizBankService.getByName(name);
        return Result.success(converter.convert(quizBanks, QuizBankDTO.class));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除题库", description = "根据ID删除题库")
    public Result<Boolean> delete(@Parameter(description = "题库ID") @PathVariable Long id) {
        boolean result = quizBankService.deleteById(id);
        if (result) {
            return Result.success(true);
        } else {
            return Result.fail("题库不存在或删除失败");
        }
    }
} 