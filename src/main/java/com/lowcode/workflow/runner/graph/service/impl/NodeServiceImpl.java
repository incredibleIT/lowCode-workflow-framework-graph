package com.lowcode.workflow.runner.graph.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lowcode.workflow.runner.graph.Node;
import com.lowcode.workflow.runner.graph.mapper.NodeMapper;
import com.lowcode.workflow.runner.graph.service.NodeService;
import org.springframework.stereotype.Service;


@Service
public class NodeServiceImpl extends ServiceImpl<NodeMapper, Node> implements NodeService {
}
