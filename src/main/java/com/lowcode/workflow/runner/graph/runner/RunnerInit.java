package com.lowcode.workflow.runner.graph.runner;


import com.lowcode.workflow.runner.graph.data.struct.instance.FlowInstance;
import com.lowcode.workflow.runner.graph.data.struct.template.Flow;
import com.lowcode.workflow.runner.graph.excutors.NodeExecutorRegistry;
import com.lowcode.workflow.runner.graph.service.FlowInstanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 运行前初始化
 * 负责在流程运行前进行必要地初始化操作
 */
@Slf4j
@Component
public class RunnerInit {

    @Autowired
    private NodeExecutorRegistry nodeExecutorRegistry;

    @Autowired
    private FlowInstanceService flowInstanceService;

    public void start(Flow flow) {
        log.info("等待运行的流程: {}", flow.toString());
        FlowInstance flowInstance = new FlowInstance(flow);
        // 存入数据库
//        flowInstanceService.save(flowInstance);
        // 构建一个图数据结构
        log.info("——————————————构建一个图数据结构————————————");
        run(flowInstance);
    }

    private void run (FlowInstance flowInstance) {
    }

}
