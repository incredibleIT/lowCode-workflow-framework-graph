package com.lowcode.workflow.runner.graph.data.struct.template;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.lowcode.workflow.runner.graph.handler.JsonTypeHandler;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 流程节点实体类
 * 对应数据库表：nodes
 */
@Data
public class Node {

    /**
     * 节点唯一ID，对应 Vue Flow 的 node.id
     */
    private String id;

    /**
     * 流程唯一ID，全局唯一
     */
    private String flowId;

    /**
     * 节点 X 坐标
     */
    private Double positionX;

    /**
     * 节点 Y 坐标
     */
    private Double positionY;

    /**
     * 节点类型：input/output/default/自定义
     */
    private String type;

    /**
     * 用户自定义数据的key
     */
    private String nodeDataFieldKey;

    /**
     * 业务数据，存储为 JSON
     * 建议使用 Map<String, Object> 便于动态读取
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private Map<String, Object> data;

    /**
     * CSS 样式对象，如 { width: 200, height: 100, backgroundColor: "#f0f0f0" }
     */
    @TableField(typeHandler = JsonTypeHandler.class)
    private Map<String, Object> style;

    /**
     * CSS 类名（避免使用 class 关键字）
     */
    private String className;

    /**
     * 父节点 ID，用于嵌套
     */
    private String parentNodeId;

    /**
     * 层级顺序
     */
    private Integer zIndex;

    /**
     * 是否隐藏
     */
    private Boolean hidden;

    /**
     * 默认输出端口的方向(如 top, bottom, left, right)
     */
    private Position sourcePosition;

    /**
     * 默认输入端口的方向(如 top, bottom, left, right)
     */
    private Position targetPosition;

    /**
     * 是否可以被选中
     */
    private Boolean selectable;

    /**
     * 是否可以拖拽
     */
    private Boolean draggable;

    /**
     * 是否可以被连接
     */
    private Boolean connectable;

    /**
     * 是否可以通过删除键删除
     */
    private Boolean deletable;

    /**
     * 是否可聚焦
     */
    private Boolean focusable;

    /**
     * 是否参与自动布局测量
     */
    private Boolean measurable;

    /**
     * 当节点位置变化时是否自动扩展父节点
     */
    private Boolean expandParent;

    /**
     * 变换缩放的原点 origin[0]
     */
    private Double originX;

    /**
     * 变换缩放的原点 origin[1]
     */
    private Double originY;

    /**
     * 节点尺寸width, 一般自动计算
     */
    private Double dimensionsWidth;

    /**
     * 节点尺寸height, 一般自动计算
     */
    private Double dimensionsHeight;

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


    @Getter
    public enum Position {
        top("top"), bottom("bottom"), left("left"), right("right");

        @EnumValue
        private final String value;

        Position(String value) {
            this.value = value;
        }
    }
}