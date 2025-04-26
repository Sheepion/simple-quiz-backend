package com.sheepion.simplequiz.common.interceptor;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import lombok.extern.slf4j.Slf4j;

/**
 * MyBatis拦截器，用于自动添加时间字段
 * 自动为INSERT语句添加created_at和updated_at
 * 自动为UPDATE语句添加updated_at
 */
@Slf4j
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class SqlTimeFieldsInterceptor implements Interceptor {
    
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取 StatementHandler
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        
        // 获取原始 SQL
        String originalSql = boundSql.getSql().trim();
        
        String modifiedSql = null;
        // 处理 INSERT 语句
        if (originalSql.toLowerCase().startsWith("insert")) {
            modifiedSql = addCreateFields(originalSql);
        }
        // 处理 UPDATE 语句
        else if (originalSql.toLowerCase().startsWith("update")) {
            modifiedSql = addUpdateFields(originalSql);
        }
        
        if (modifiedSql != null) {
            setSql(boundSql, modifiedSql);
            log.debug("Original SQL: {}", originalSql);
            log.debug("Modified SQL: {}", modifiedSql);
        }
        
        return invocation.proceed();
    }
    
    private void setSql(BoundSql boundSql, String sql) throws Exception {
        Field sqlField = BoundSql.class.getDeclaredField("sql");
        sqlField.setAccessible(true);
        sqlField.set(boundSql, sql);
    }
    
    private String addCreateFields(String sql) {
        if (!sql.toLowerCase().contains("values")) {
            throw new IllegalArgumentException("Invalid INSERT SQL format: " + sql);
        }
        int valuesIndex = sql.toLowerCase().indexOf("values");
        String beforeValues = sql.substring(0, valuesIndex).trim();
        String afterValues = sql.substring(valuesIndex).trim();
        
        if (!beforeValues.contains(")")) {
            throw new IllegalArgumentException("Invalid INSERT SQL format: Missing columns before VALUES in " + sql);
        }
        
        int lastParenthesisIndex = beforeValues.lastIndexOf(")");
        String columns = beforeValues.substring(0, lastParenthesisIndex) + ", created_at, updated_at)";
        
        LocalDateTime now = LocalDateTime.now();
        String formattedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
        String additionalValues = "'" + formattedNow + "', '" + formattedNow + "'";
        String values = afterValues.replaceAll("\\)(?=\\s*,|\\s*$)", ", " + additionalValues + ")");
        
        return columns + " " + values;
    }
    
    private String addUpdateFields(String sql) {
        int setIndex = sql.toLowerCase().indexOf(" set");
        int whereIndex = sql.toLowerCase().indexOf(" where");
        
        if (whereIndex == -1) {
            // 没有 WHERE 子句的情况
            return sql + ", updated_at = '" + LocalDateTime.now() + "'";
        }
        
        String beforeWhere = sql.substring(0, whereIndex);
        String afterWhere = sql.substring(whereIndex);
        
        LocalDateTime now = LocalDateTime.now();
        String formattedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
        
        // 检查是否以逗号结尾
        if (beforeWhere.trim().endsWith(",")) {
            return beforeWhere + " updated_at = '" + formattedNow + "'" + afterWhere;
        } else {
            return beforeWhere + ", updated_at = '" + formattedNow + "'" + afterWhere;
        }
    }
    
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
    
    @Override
    public void setProperties(Properties properties) {
    }
} 