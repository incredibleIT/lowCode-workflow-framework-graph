package com.lowcode.workflow.runner.graph.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lowcode.workflow.runner.graph.mapper.FlowMapper;
import com.lowcode.workflow.runner.graph.service.FlowService;
import com.lowcode.workflow.runner.graph.data.struct.template.Flow;
import org.springframework.stereotype.Service;

@Service
public class FlowServiceImpl extends ServiceImpl<FlowMapper, Flow> implements FlowService {
}
