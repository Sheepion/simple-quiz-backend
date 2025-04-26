-- 创建题库表
CREATE TABLE quiz_bank (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted BOOLEAN DEFAULT FALSE NOT NULL
);

-- 添加题库表字段注释
COMMENT ON COLUMN quiz_bank.name IS '题库名称';
COMMENT ON COLUMN quiz_bank.description IS '题库描述';

-- 创建题目表
CREATE TABLE quiz_question (
    id BIGSERIAL PRIMARY KEY,
    quiz_bank_id BIGINT NOT NULL,
    title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    type VARCHAR(20) NOT NULL,
    options JSONB,
    answer TEXT NOT NULL,
    analysis TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted BOOLEAN DEFAULT FALSE NOT NULL
);

-- 添加题目表字段注释
COMMENT ON COLUMN quiz_question.quiz_bank_id IS '所属题库ID';
COMMENT ON COLUMN quiz_question.title IS '题目标题';
COMMENT ON COLUMN quiz_question.content IS '题目内容';
COMMENT ON COLUMN quiz_question.type IS '题目类型：SINGLE_CHOICE(单选), MULTIPLE_CHOICE(多选), JUDGMENT(判断), FILL_BLANK(填空), SHORT_ANSWER(简答)';
COMMENT ON COLUMN quiz_question.options IS '选项内容，JSON格式';
COMMENT ON COLUMN quiz_question.answer IS '正确答案';
COMMENT ON COLUMN quiz_question.analysis IS '题目解析';

-- 创建索引
CREATE INDEX idx_quiz_question_quiz_bank_id ON quiz_question(quiz_bank_id);
CREATE INDEX idx_quiz_bank_deleted ON quiz_bank(deleted) WHERE NOT deleted;
CREATE INDEX idx_quiz_question_deleted ON quiz_question(deleted) WHERE NOT deleted;
