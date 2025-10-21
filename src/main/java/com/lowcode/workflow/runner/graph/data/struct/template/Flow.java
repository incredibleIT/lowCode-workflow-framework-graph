package com.lowcode.workflow.runner.graph.data.struct.template;

import com.baomidou.mybatisplus.annotation.*;
import com.lowcode.workflow.runner.graph.handler.JsonTypeHandler;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 流程定义实体类
 * 对应数据库表：flows
 * 存储流程图的元信息，不包含节点和边的具体数据（由 nodes/edges 表维护）
 */
@Data
@TableName("flows")
public class Flow {

    /**
     * 流程唯一ID，全局唯一，可与业务系统对接
     */
    @TableId
    private String id;

    /**
     * 流程名称，如“请假审批流”
     */
    @NotBlank(message = "流程名称不能为空")
    @Size(max = 100, message = "流程名称长度不能超过100个字符")
    private String name;

    /**
     * 流程描述
     */
    @NotBlank(message = "流程描述不能为空")
    @Size(max = 200, message = "流程描述长度不能超过200个字符")
    private String description;

    /**
     * 语义化版本号，支持流程版本管理（如 "1.0.0"）
     */
    @NotBlank(message = "流程版本不能为空")
    private String version;

    /**
     * 流程状态：draft / published / archived / disabled
     */
    @NotBlank(message = "流程状态不能为空")
    private FlowStatus status;

    /**
     * 流程所有者ID（用户/团队/租户ID）
     */
    private String ownerId;

    /**
     * 多租户场景下的租户ID，单租户可忽略
     */
    private String tenantId;

    /**
     * 触发方式：manual / api / schedule / event
     */
    @NotBlank(message = "流程触发类型不能为空")
    private String triggerType;

    /**
     * 执行模式：sync / async / step-by-step
     */
    @NotBlank(message = "流程执行模式不能为空")
    private String executionMode;

    /**
     * 扩展元数据，如分类标签、图标、颜色等
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private Map<String, Object> metadata;

    /**
     * 流程级配置，如超时时间、重试策略、全局变量定义等
     */
    private Map<String, Object> config;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 最后更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    /**
     * 发布时间（当 status = published 时记录）
     */
    private LocalDateTime publishedAt;


    @Getter
    public enum FlowStatus {
        draft("draft"),
        published("published"),
        archived("archived"),
        disabled("disabled");

        @EnumValue
        private final String value;

        FlowStatus(String value) {
            this.value = value;
        }
    }
}
