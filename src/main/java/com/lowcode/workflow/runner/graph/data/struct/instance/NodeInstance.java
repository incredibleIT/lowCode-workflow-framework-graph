package com.lowcode.workflow.runner.graph.data.struct.instance;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.lowcode.workflow.runner.graph.handler.JsonTypeHandler;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 节点执行记录实体类
 * 对应数据库表：node_executions
 * 每次流程实例运行中，每个节点生成一条执行记录，用于追踪、重试、审计
 */
@Data
public class NodeInstance {

    /**
     * 节点执行记录唯一ID（建议 UUID）
     */
    private String id;

    /**
     * 所属流程实例ID，引用 flow_instances.id
     */
    private String instanceId;

    /**
     * 对应的流程定义节点ID，引用 nodes.id
     */
    private String nodeId;

    /**
     * 节点类型快照（便于后续分析，避免定义变更影响历史）
     */
    private String nodeType;

    /**
     * 节点执行状态：pending / running / completed / failed / skipped / waiting / retrying
     */
    private NodeInstanceStatus status;

    /**
     * 节点实际接收到的输入数据（可能来自上游或全局参数）
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private Map<String, Object> inputData;

    /**
     * 节点执行产生的输出数据
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private Map<String, Object> outputData;

    /**
     * 节点执行失败时的错误详情（堆栈/业务错误）
     */
    private String errorMessage;

    /**
     * 已重试次数
     */
    private Integer retryCount;

    /**
     * 最大允许重试次数（可从节点定义继承）
     */
    private Integer maxRetries;

    /**
     * 节点开始执行时间
     */
    private LocalDateTime startedAt;

    /**
     * 节点结束时间
     */
    private LocalDateTime endedAt;

    /**
     * 记录创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 记录最后更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    @Getter
    public enum NodeInstanceStatus {
        pending("pending"),
        running("running"),
        completed("completed"),
        failed("failed"),
        skipped("skipped"),
        waiting("waiting"),
        retrying("retrying");

        @EnumValue
        private final String value;

        NodeInstanceStatus(String value) {
            this.value = value;
        }
    }
}