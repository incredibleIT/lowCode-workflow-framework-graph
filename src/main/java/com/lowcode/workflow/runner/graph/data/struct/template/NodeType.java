package com.lowcode.workflow.runner.graph.data.struct.template;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.lowcode.workflow.runner.graph.handler.JsonTypeHandler;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 节点类型元数据实体类
 * 对应数据库表：node_types
 * 描述每种节点的配置结构、执行器、UI 等信息
 */
@Data
public class NodeType {

    /**
     * 节点类型唯一标识，如 http_request, condition
     */
    private String typeKey;

    /**
     * 显示名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 节点类型的分类, 例如基础, 网络, 数据库
     */
    private String category;

    /**
     * 节点类型业务所需字段, 用于节点取出 input_data 字段中所需的业务数据
     * 通常为 JSON Schema 格式，例如：
     * {
     *   "url": { "type": "string", "required": true },
     *   "method": { "type": "string", "enum": ["GET", "POST"] }
     * }
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private Map<String, Object> configSchema;

    /**
     * 前端图标（如图标类名或 URL）
     */
    private String icon;

    /**
     * 前端自定义渲染组件（可选）
     */
    private String component;

    /**
     * 后端执行器类全限定名（如 com.example.executor.HttpRequestExecutor）
     */
    private String executorClass;

    /**
     * 是否支持重试
     */
    private Boolean supportsRetry;

    /**
     * 是否为异步执行节点
     */
    private Boolean isAsync;

    /**
     * 是否为终止节点（流程在此结束）
     */
    private Boolean isTerminal;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}