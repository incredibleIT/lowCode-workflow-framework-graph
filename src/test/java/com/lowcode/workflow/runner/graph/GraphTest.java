package com.lowcode.workflow.runner.graph;

import com.lowcode.workflow.runner.graph.data.struct.FlowToGraphMapper;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.Edge;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class GraphTest {



    @Test
    public void test01() {
        DefaultDirectedGraph<Node, Edge> nodeEdgeDefaultDirectedGraph = new DefaultDirectedGraph<>(DefaultEdge.class);
        nodeEdgeDefaultDirectedGraph.addVertex(new Node() {
            @Override
            public String getId() {
                return "nodeId";
            }
        });

        FlowToGraphMapper.addGraphByFlowId("flowId", nodeEdgeDefaultDirectedGraph);
        System.out.println(FlowToGraphMapper.getGraphsByFlowId("flowId"));
        FlowToGraphMapper.removeGraphNodeByFlowIdAndNodeId("flowId", "nodeId");
        System.out.println(FlowToGraphMapper.getGraphsByFlowId("flowId"));
    }
}