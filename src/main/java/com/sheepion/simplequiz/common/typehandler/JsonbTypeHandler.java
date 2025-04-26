package com.sheepion.simplequiz.common.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * PostgreSQL的JSONB类型处理器
 * 用于处理Java对象与PostgreSQL的JSONB类型之间的转换
 */
@MappedTypes({Object.class, Map.class})
public class JsonbTypeHandler extends BaseTypeHandler<Object> {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        try {
            // 将Java对象转换为JSON字符串
            String jsonStr = MAPPER.writeValueAsString(parameter);
            // 设置到PreparedStatement中，使用PostgreSQL的cast函数，将字符串转换为JSONB类型
            // 使用CAST函数明确将字符串转换为JSONB
            ps.setObject(i, jsonStr, java.sql.Types.OTHER);
        } catch (JsonProcessingException e) {
            throw new SQLException("Failed to convert object to json", e);
        }
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        // 从ResultSet中获取JSONB数据
        String jsonStr = rs.getString(columnName);
        return parseJson(jsonStr);
    }

    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String jsonStr = rs.getString(columnIndex);
        return parseJson(jsonStr);
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String jsonStr = cs.getString(columnIndex);
        return parseJson(jsonStr);
    }

    /**
     * 将JSON字符串解析为Java对象
     */
    private Object parseJson(String json) {
        if (!StringUtils.hasText(json)) {
            return null;
        }
        try {
            // 解析JSON字符串为Map对象
            return MAPPER.readValue(json, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse json", e);
        }
    }
} 