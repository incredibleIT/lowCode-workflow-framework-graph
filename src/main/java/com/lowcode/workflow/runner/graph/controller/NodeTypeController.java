package com.lowcode.workflow.runner.graph.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lowcode.workflow.runner.graph.data.struct.template.NodeType;
import com.lowcode.workflow.runner.graph.result.PageResult;
import com.lowcode.workflow.runner.graph.result.Result;
import com.lowcode.workflow.runner.graph.service.NodeTypeService;
import com.lowcode.workflow.runner.graph.validation.CreatGroup;
import com.lowcode.workflow.runner.graph.validation.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/nodeType")
public class NodeTypeController {

    @Autowired
    private NodeTypeService nodeTypeService;

    /**
     * 获取所有的节点类型
     * @return 所有的节点类型
     */
    @GetMapping("/list")
    public Result<List<NodeType>> list() {
        return Result.success(nodeTypeService.list());
    }


    /**
     * 分页获取节点类型
     * @param page 页码
     * @param size 每页数量
     * @return 分页结果
     */
    @GetMapping("/page")
    public PageResult<NodeType> page(@RequestParam(defaultValue = "1") @Min(value = 1, message = "页码不能小于1") Integer page,
                                     @RequestParam(defaultValue = "10") @Min(value = 1, message = "每页数量不能小于1") @Max(value = 100, message = "每页数量不能大于100") Integer size) {
        return PageResult.from(nodeTypeService.page(new Page<>(page, size)));
    }

    /**
     * 通过节点类型键获取节点类型详情
     * @param typeKey 节点类型键
     * @return 节点类型详情
     */
    @GetMapping("/detail/{typeKey}")
    public Result<NodeType> detail(@PathVariable("typeKey") @NotNull(message = "节点类型键不能为空") String typeKey) {
        return Result.success(nodeTypeService.getById(typeKey));
    }


    /**
     * 创建节点类型
     * @param nodeType 节点类型
     * @return 成功创建节点类型
     */
    @PostMapping("/create")
    public Result<Void> create(@Validated(CreatGroup.class) @RequestBody NodeType nodeType) {
        nodeTypeService.save(nodeType);
        return Result.success();
    }

     /**
     * 更新节点类型
     * @param nodeType 节点类型
     * @return 成功更新节点类型
     */
    @PutMapping("/update")
    public Result<Void> update(@Validated(UpdateGroup.class) @RequestBody NodeType nodeType) {
        nodeTypeService.updateById(nodeType);
        return Result.success();
    }

}
