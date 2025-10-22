package com.lowcode.workflow.runner.graph.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lowcode.workflow.runner.graph.data.struct.template.Node;
import com.lowcode.workflow.runner.graph.data.struct.template.NodeType;
import com.lowcode.workflow.runner.graph.exception.custom.CustomException;
import com.lowcode.workflow.runner.graph.result.Result;
import com.lowcode.workflow.runner.graph.service.NodeService;
import com.lowcode.workflow.runner.graph.service.NodeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/node")
public class NodeController {

    @Autowired
    private NodeService nodeService;

    @Autowired
    private NodeTypeService nodeTypeService;

    /**
     * 查找一个流程模版下所有节点
     * @param flowId 流程模版ID
     * @return 节点列表
     */
    @GetMapping("/list/{flowId}")
    public Result<List<Node>> list(@PathVariable("flowId") @NotNull(message = "流程模版ID不能为空") String flowId) {
        LambdaQueryWrapper<Node> flowWrapper = new LambdaQueryWrapper<>();
        flowWrapper.eq(Node::getFlowId, flowId);
        List<Node> nodeList = nodeService.list(flowWrapper);
        for (Node node : nodeList) {
            LambdaQueryWrapper<NodeType> nodeTypeWrapper = new LambdaQueryWrapper<>();
            nodeTypeWrapper.eq(NodeType::getTypeKey, node.getType());
            node.setNodeType(nodeTypeService.getOne(nodeTypeWrapper));
        }
        return Result.success(nodeList);
    }

    /**
     * 查找一个节点的详细信息, 要拼接节点类型
     * @param nodeId 节点ID
     * @return 节点详细信息
     */
    @GetMapping("/detail/{nodeId}")
    public Result<Node> detail(@PathVariable("nodeId") @NotNull(message = "节点ID不能为空") String nodeId) {
        Node node = nodeService.getById(nodeId);
        if (node == null) {
            throw new CustomException(500, "节点不存在");
        }
        LambdaQueryWrapper<NodeType> nodeTypeWrapper = new LambdaQueryWrapper<>();
        nodeTypeWrapper.eq(NodeType::getTypeKey, node.getType());
        node.setNodeType(nodeTypeService.getOne(nodeTypeWrapper));
        return Result.success(node);
    }

    @PostMapping("/create")
    public Result<Void> create(@RequestBody Node node) {
        nodeService.save(node);
        return Result.success();
    }

    /**
     * 更新一个节点的信息
     * @param node 节点信息
     * @return 空结果
     */
    @PutMapping("/update")
    public Result<Void> update(@RequestBody @NotNull(message = "节点信息不能为空") Node node) {
        nodeService.updateById(node);
        return Result.success();
    }

    /**
     * 删除一个节点
     * @param nodeId 节点ID
     * @return 空结果
     */
    @DeleteMapping("/delete/{nodeId}")
    public Result<Void> delete(@PathVariable("nodeId") @NotNull(message = "节点ID不能为空") String nodeId) {
        nodeService.removeById(nodeId);
        return Result.success();
    }


}
