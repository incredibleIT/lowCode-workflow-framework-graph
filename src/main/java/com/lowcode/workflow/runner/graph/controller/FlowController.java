package com.lowcode.workflow.runner.graph.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lowcode.workflow.runner.graph.data.struct.template.Flow;
import com.lowcode.workflow.runner.graph.exception.custom.CustomException;
import com.lowcode.workflow.runner.graph.result.PageResult;
import com.lowcode.workflow.runner.graph.result.Result;
import com.lowcode.workflow.runner.graph.service.FlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// TODO 用户ID
@RestController
@RequestMapping("/api/flow")
public class FlowController {

    @Autowired
    private FlowService flowService;


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
    public PageResult<Flow> page(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        return PageResult.from(flowService.page(new Page<>(page, size)));
    }


    /**
     * 根据流程ID获取流程详情
     * @param flowId 流程ID
     * @return 流程详情
     */
    @GetMapping("/detail/{flowId}")
    public Result<Flow> detail(@PathVariable Integer flowId) {
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
    public Result<Void> create(@RequestBody Flow flow) {
        if (flow == null) {
            throw new CustomException("这里是一个业务异常, 原因: 流程模版不能为空");
        }
        flowService.save(flow);
        return Result.success();
    }


    /**
     * 更新一个已存在的流程模版
     * @param flow 流程模版
     */
    @PutMapping("/update")
    public Result<Void> update(@RequestBody Flow flow) {
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
    public Result<Void> delete(@PathVariable("flowId") Integer flowId) {
        if (flowId == null) {
            throw new CustomException("这里是一个业务异常, 原因: 流程ID不能为空");
        }
        flowService.removeById(flowId);
        // TODO 级联删除所有关联的节点和边
        return Result.success();
    }
}
