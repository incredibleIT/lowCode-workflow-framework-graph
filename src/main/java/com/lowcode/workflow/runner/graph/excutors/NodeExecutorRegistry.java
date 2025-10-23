package com.lowcode.workflow.runner.graph.excutors;

import com.lowcode.workflow.runner.graph.annotation.NodeExecutorType;
import com.lowcode.workflow.runner.graph.exception.sys.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class NodeExecutorRegistry {

    /**
     * 维护一个type -> executor的注册表
     */
    private final Map<String, NodeExecutor> executors = new ConcurrentHashMap<>();


    @Autowired
    private ApplicationContext applicationContext;

    /**
     *  在NodeExecutorRegistry注册表被加载到容器后自动加载所有带有@NodeExecutorType注解的执行器类
     */
    @PostConstruct
    public void init() {
        log.info("——————————————开始加载所有的节点执行器————————————————");
        applicationContext.getBeansWithAnnotation(NodeExecutorType.class).values().forEach(executor -> {

            log.info("节点执行器: {}", executor.getClass().getName());

            if (executor instanceof NodeExecutor) {
                NodeExecutorType nodeExecutorType = executor.getClass().getAnnotation(NodeExecutorType.class);
                executors.put(nodeExecutorType.value(), (NodeExecutor) executor);
            }
        });
        log.info("——————————————加载完成所有的节点执行器————————————————");
    }


    /**
     * 根据节点类型键获取对应的节点执行器
     * @param typeKey 节点类型键
     * @return 节点执行器实例
     */
    public NodeExecutor get(String typeKey) {
        if (!executors.containsKey(typeKey)) {
            throw new SystemException(600, "节点执行器类型不存在");
        }
        return executors.get(typeKey);
    }
}
