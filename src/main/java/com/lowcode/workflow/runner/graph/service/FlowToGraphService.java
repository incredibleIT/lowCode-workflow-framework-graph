package com.lowcode.workflow.runner.graph.service;


import com.lowcode.workflow.runner.graph.data.struct.FlowToGraphMapper;
import com.lowcode.workflow.runner.graph.data.struct.template.Node;
import org.jgraph.graph.Edge;
import org.jgrapht.Graph;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 按需加载, 提供加载流程图的服务
 */
@Service
public class FlowToGraphService {

    public List<Graph<Node, Edge>> loadFlowToGraphByUserIdAndFlowId(String userId, String flowId) {
        String userIdAndFlowId = userId + ":" + flowId;

        // 先从已经加载的获取
        List<Graph<Node, Edge>> cached = FlowToGraphMapper.getGraphsByFlowId(userIdAndFlowId);
        if (cached != null && !cached.isEmpty()) {
            // 证明已经加载过了, 直接返回
            return cached;
        }


        // 否则, 从数据库中加载, 并放入缓存



        return null;
    }


    // 修改节点, 对数据库进行修改, 之后删除缓存下次访问会重新加载

    // 删除节点, 删除数据库数据, 同步删除缓存

    // 添加节点, 添加数据库数据, 同步添加缓存

    // 修改边, 对数据库进行修改, 之后删除缓存下次访问会重新加载

    // 删除边, 删除数据库数据, 同步删除缓存

    // 添加边, 添加数据库数据, 同步添加缓存






}
