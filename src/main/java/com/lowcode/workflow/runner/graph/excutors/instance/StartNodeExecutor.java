package com.lowcode.workflow.runner.graph.excutors.instance;


import com.lowcode.workflow.runner.graph.annotation.NodeExecutorType;
import org.springframework.stereotype.Component;

@Component
@NodeExecutorType(value = "start")
public class StartNodeExecutor {
}
