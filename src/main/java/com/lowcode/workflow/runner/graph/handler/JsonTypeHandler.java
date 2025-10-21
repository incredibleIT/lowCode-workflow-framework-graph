package com.lowcode.workflow.runner.graph.handler;

import com.alibaba.fastjson2.JSON;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * JSON 类型的处理器
 * 用于处理数据库中存储的JSON类型数据
 */
@MappedTypes(Map.class)
public class JsonTypeHandler extends BaseTypeHandler<Map<String, Object>> {
    /**
     * 存入数据库时调用
     * @param preparedStatement 预编译语句
     * @param i 参数索引
     * @param stringObjectMap 要设置的参数值
     * @param jdbcType 参数的 JDBC 类型
     * @throws SQLException 如果发生 SQL 异常
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Map<String, Object> stringObjectMap, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, JSON.toJSONString(stringObjectMap));
    }

    /**
     * 从数据库中获取结果时调用
     * @param resultSet 结果集
     * @param s 列名
     * @return 解析后的 JSON 映射
     * @throws SQLException 如果发生 SQL 异常
     */
    @Override
    public Map<String, Object> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String json = resultSet.getString(s);

        return json != null ? JSON.parseObject(json) : null;
    }

    /**
     * 从数据库中获取结果时调用
     * @param resultSet 结果集
     * @param i 列索引
     * @return 解析后的 JSON 映射
     * @throws SQLException 如果发生 SQL 异常
     */
    @Override
    public Map<String, Object> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String json = resultSet.getString(i);
        return json != null ? JSON.parseObject(json) : null;
    }

    /**
     * CallableStatement是用于执行存储过程的语句
     * @param callableStatement 可调用语句
     * @param i 参数索引
     * @return 解析后的 JSON 映射
     * @throws SQLException 如果发生 SQL 异常
     */
    @Override
    public Map<String, Object> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String json = callableStatement.getString(i);
        return json != null ? JSON.parseObject(json) : null;
    }
}
