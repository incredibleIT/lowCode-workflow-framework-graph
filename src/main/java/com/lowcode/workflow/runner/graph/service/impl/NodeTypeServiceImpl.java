package com.lowcode.workflow.runner.graph.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lowcode.workflow.runner.graph.data.struct.template.NodeType;
import com.lowcode.workflow.runner.graph.mapper.NodeTypeMapper;
import com.lowcode.workflow.runner.graph.service.NodeTypeService;
import org.springframework.stereotype.Service;

@Service
public class NodeTypeServiceImpl extends ServiceImpl<NodeTypeMapper, NodeType> implements NodeTypeService {
}
