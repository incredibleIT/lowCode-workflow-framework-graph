package com.lowcode.workflow.runner.graph.template;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.lowcode.workflow.runner.graph.handler.JsonTypeHandler;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 流程边（连接线）实体类
 * 对应数据库表：edges
 */
@Data
public class Edge {

    /**
     * 边唯一ID
     */
    private String id;

    /**
     * 流程唯一ID，全局唯一
     */
    private String flowId;

    /**
     * 源节点 ID
     */
    private String source;

    /**
     * 目标节点 ID
     */
    private String target;

    /**
     * 边类型：default/smoothstep/straight/自定义
     */
    private String type;

    /**
     * 业务数据中用户自定义数据的key
     */
    private String edgeDataFieldKey;

    /**
     * 业务数据
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private Map<String, Object> data;

    /**
     * 边的样式，如 { stroke: "#ff0000", strokeWidth: 2 }
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private Map<String, Object> style;

    /**
     * 自定义CSS类名
     */
    private String className;

    /**
     * 起始箭头标记
     */
    private String markerStart;

    /**
     * 结束箭头标记，默认为 'arrowclosed'
     */
    private String markerEnd;

    /**
     * 标签文本（若为 VNode 则需前端处理，后端仅存字符串或 JSON）
     */
    private String label;

    /**
     * 标签样式
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private Map<String, Object> labelStyle;

    /**
     * 是否显示标签背景
     */
    private Boolean labelShowBg;

    /**
     * 标签背景样式
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private Map<String, Object> labelBgStyle;

    /**
     * 标签背景内边距x
     */
    private Double labelBgPaddingX;

    /**
     * 标签背景内边距y
     */
    private Double labelBgPaddingY;

    /**
     * 标签背景圆角
     */
    private Double labelBgBorderRadius;

    /**
     * 是否显示流动动画
     */
    private Boolean animated;

    /**
     * 可选值: true, false, source, target
     */
    private String updatable;

    /**
     * 是否可以选中
     */
    private Boolean selectable;

    /**
     * 是否可以删除
     */
    private Boolean deletable;

    /**
     * 是否可以聚焦
     */
    private Boolean focusable;

    /**
     * 是否隐藏此边
     */
    private Boolean hidden;

    /**
     * 层级顺序
     */
    private Integer zIndex;

    /**
     * 起始节点Handler ID
     */
    private String sourceHandle;

    /**
     * 目标节点Handler ID
     */
    private String targetHandle;

    /**
     * 边的可交互区域宽度，默认 20
     */
    private Integer interactionWidth;

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
