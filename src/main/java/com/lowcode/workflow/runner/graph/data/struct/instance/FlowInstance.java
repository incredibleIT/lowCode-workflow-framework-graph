package com.lowcode.workflow.runner.graph.data.struct.instance;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.lowcode.workflow.runner.graph.data.struct.template.Flow;
import com.lowcode.workflow.runner.graph.data.struct.template.Node;
import com.lowcode.workflow.runner.graph.handler.JsonTypeHandler;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 流程运行实例实体类
 * 对应数据库表：flow_instances
 * 每次流程执行生成一条记录，记录运行时状态、输入输出、执行时间等
 */

@Data
public class FlowInstance {

    /**
     * 通过流程定义模版创造流程实例
     * @param flow 流程定义模版
     */
    public FlowInstance(Flow flow) {
        this.id = UUID.randomUUID().toString();
        this.flowId = flow.getId();
        this.status = FlowInstanceStatus.running;
        this.triggeredBy = flow.getOwnerId();
        this.triggerType = flow.getTriggerType();
        // 流程级配置，如超时时间、重试策略、全局变量定义等
        this.inputParams = flow.getConfig();
        this.startedAt = LocalDateTime.now();
        this.nodes = flow.getNodes();
    }

    /**
     * 流程实例唯一ID（如 UUID 或业务生成的 trace ID）
     */
    private String id;

    /**
     * 关联的流程定义ID，引用 flows.id
     */
    private String flowId;

    /**
     * 实例整体状态：running / completed / failed / cancelled / paused / waiting
     */
    private FlowInstanceStatus status;

    /**
     * 触发人ID（用户/系统/定时任务ID）
     */
    private String triggeredBy;

    /**
     * 触发方式：manual / api / schedule / event / webhook
     */
    private String triggerType;

    /**
     * 启动流程时传入的全局输入参数（JSON 对象）
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private Map<String, Object> inputParams;

    /**
     * 流程最终输出结果（可选，用于快速查询结果）
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private Map<String, Object> outputResult;

    /**
     * 实例级错误信息（如流程启动失败、全局异常）
     */
    private String errorMessage;

    /**
     * 实例开始执行时间
     */
    private LocalDateTime startedAt;

    /**
     * 实例结束时间（成功/失败/取消时更新）
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

    @TableField(exist = false)
    private List<Node> nodes;

    @Getter
    public enum FlowInstanceStatus {
        running("running"),
        completed("completed"),
        failed("failed"),
        cancelled("cancelled"),
        paused("paused"),
        waiting("waiting");

        @EnumValue
        private final String value;

        FlowInstanceStatus(String value) {
            this.value = value;
        }
    }
}
