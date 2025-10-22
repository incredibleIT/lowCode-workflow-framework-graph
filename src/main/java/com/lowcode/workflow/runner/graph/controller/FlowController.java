package com.lowcode.workflow.runner.graph.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lowcode.workflow.runner.graph.data.struct.template.Flow;
import com.lowcode.workflow.runner.graph.exception.custom.CustomException;
import com.lowcode.workflow.runner.graph.result.PageResult;
import com.lowcode.workflow.runner.graph.result.Result;
import com.lowcode.workflow.runner.graph.service.FlowService;
import com.lowcode.workflow.runner.graph.service.NodeService;
import com.lowcode.workflow.runner.graph.validation.CreatGroup;
import com.lowcode.workflow.runner.graph.validation.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
// TODO 用户ID
@RestController
@RequestMapping("/api/flow")
public class FlowController {

    @Autowired
    private FlowService flowService;
    @Autowired
    private NodeService nodeService;


    /**
     * 获取所有已经定义的流程模版
     * @return 已定义的流程模版
     */
    @GetMapping("/list")
    public Result<List<Flow>> list() {
        return Result.success(flowService.list());
    }


    /**
     * 分页获取所有已经定义的流程模版
     * @param page 页数
     * @param size 每页数量
     * @return 已定义的流程模版
     */
    @GetMapping("/page")
    public PageResult<Flow> page(@RequestParam(defaultValue = "1") @Min(value = 1, message = "页数不能小于1") Integer page,
                                 @RequestParam(defaultValue = "10") @Min(value = 1, message = "每页数量不能小于1") @Max(value = 100, message = "每页数量不能大于100") Integer size) {
        return PageResult.from(flowService.page(new Page<>(page, size)));
    }


    /**
     * 根据流程ID获取流程详情
     * @param flowId 流程ID
     * @return 流程详情
     */
    @GetMapping("/detail/{flowId}")
    public Result<Flow> detail(@PathVariable("flowId") @NotNull(message = "流程ID不能为空") Integer flowId) {
        Flow flow = flowService.getById(flowId);
        if (flow == null) {
            throw new CustomException("这里是一个业务异常, 原因: 流程不存在");
        }
        return Result.success(flow);
    }

    /**
     * 创建一个新的流程模版
     * @param flow 流程模版
     */
    @PostMapping("/create")
    public Result<Void> create(@Validated(CreatGroup.class) @RequestBody Flow flow) {
        if (flow == null) {
            throw new CustomException("这里是一个业务异常, 原因: 流程模版不能为空");
        }
        flowService.save(flow);

        // TODO 级联保存所有节点和边
        nodeService.saveBatch(flow.getNodes());


        return Result.success();
    }


    /**
     * 更新一个已存在的流程模版
     * @param flow 流程模版
     */
    @PutMapping("/update")
    public Result<Void> update(@Validated(UpdateGroup.class) @RequestBody Flow flow) {
        if (flow == null) {
            throw new CustomException("这里是一个业务异常, 原因: 流程模版不能为空");
        }
        flowService.updateById(flow);
        return Result.success();
    }

    /**
     * 删除一个已存在的流程模版
     * @param flowId 流程ID
     */
    @DeleteMapping("/delete/{flowId}")
    public Result<Void> delete(@PathVariable("flowId") @NotNull(message = "流程ID不能为空") Integer flowId) {
        flowService.removeById(flowId);
        // TODO 级联删除所有关联的节点和边
        return Result.success();
    }
}
