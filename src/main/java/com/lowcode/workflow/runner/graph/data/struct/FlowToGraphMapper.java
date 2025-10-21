package com.lowcode.workflow.runner.graph.data.struct;


import com.lowcode.workflow.runner.graph.Node;
import org.jgraph.graph.Edge;
import org.jgrapht.Graph;
import org.jgrapht.traverse.DepthFirstIterator;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 流程 -> 图列表
 */
public class FlowToGraphMapper {

    /**
     * 维护了一个流程到图列表的映射关系
     */
    private final static Map<String, List<Graph<Node, Edge>>> FLOW_TO__LIST_GRAPH_MAPPER = new ConcurrentHashMap<>();

    /**
     * 获取流程到图的所有映射
     * @return 流程到图的所有映射
     */
    public static Map<String, List<Graph<Node, Edge>>> getFlowToGraphMap() {
        return FLOW_TO__LIST_GRAPH_MAPPER;
    }

    /**
     * 根据流程ID, 获取到所有的图
     * @param flowId 流程ID
     * @return 所有的图
     */
    public static List<Graph<Node, Edge>> getGraphsByFlowId(String flowId) {
        return FLOW_TO__LIST_GRAPH_MAPPER.get(flowId);
    }

    /**
     * 通过流程ID, 向流程到图的映射中添加一个图
     * @param flowId 流程ID
     * @param graph 图
     */

    public static void addGraphByFlowId(String flowId, Graph<Node, Edge> graph) {
        // TODO 涉及到数据库操作, 需要添加数据库中对应的数据
        FLOW_TO__LIST_GRAPH_MAPPER.computeIfAbsent(flowId, k -> new CopyOnWriteArrayList<>()).add(graph);
    }


    /**
     * 删除一个流程对应的所有图
     * @param flowId 流程ID
     */
    public static void removeGraphByFlowId(String flowId) {
        // TODO 涉及到数据库操作, 需要删除数据库中对应的数据
        FLOW_TO__LIST_GRAPH_MAPPER.remove(flowId);
    }

    /**
     * 删除一个流程中特定图的某个节点
     * @param flowId 流程ID
     * @param nodeId 节点ID
     */
    public static void removeGraphNodeByFlowIdAndNodeId(String flowId, String nodeId) {
        // 获取需要删除的节点
        Node removeNode = traverseAllNodesAndDoSomething(flowId, nodeId);
        if (removeNode != null) {
            getGraphsByFlowId(flowId).forEach(graph -> {
                if (graph.containsVertex(removeNode)) {
                    graph.removeVertex(removeNode);
                    System.out.println("节点: " + removeNode + " 已从图中删除");
                }
            });
        }
    }

    /**
     * 遍历一个流程中的所有图, 找到特定节点返回
     * @param flowId 流程ID
     * @param nodeId 节点ID
     */
    private static Node traverseAllNodesAndDoSomething(String flowId, String nodeId) {
        Set<Node> nodeSet = new HashSet<Node>();
        for (Graph<Node, Edge> graph : FlowToGraphMapper.getGraphsByFlowId(flowId)) {
            for (Node node : graph.vertexSet()) {
                if (!nodeSet.contains(node)) {
                    // 对这个节点开启一次DFS
                    DepthFirstIterator<Node, Edge> dfs = new DepthFirstIterator<>(graph, node);
                    while (dfs.hasNext()) {
                        Node currentNode = dfs.next();
                        if (nodeSet.add(currentNode)) {
                            System.out.println("当前节点: " + currentNode);
                            if (nodeId.equals(currentNode.getId())) {
                                return currentNode;
                            }
                        }
                    }
                }
            }

        }
        return null;
    }

}
