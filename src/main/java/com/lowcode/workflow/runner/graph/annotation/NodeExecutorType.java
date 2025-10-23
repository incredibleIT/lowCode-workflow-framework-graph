package com.lowcode.workflow.runner.graph.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NodeExecutorType {
    /**
     * 节点执行器类型，用于标识节点的执行器类型, 例如：http_request, condition, 等, 必须与 NodeType 中的 typeKey 一致
     * @return 节点执行器类型
     */
    String value();
}
