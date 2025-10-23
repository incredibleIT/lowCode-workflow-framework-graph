package com.lowcode.workflow.runner.graph.exception;


import com.lowcode.workflow.runner.graph.exception.sys.SystemException;
import com.lowcode.workflow.runner.graph.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理路径或请求参数校验异常 （如@PathVariable、@RequestParam等）
     * @param e ConstraintViolationException 校验异常
     * @return Result<Void> 校验失败结果
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<Void> handleConstraintViolationException(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("; "));

        log.error("路径或请求参数校验失败: {}", message);
        return Result.error(400, "路径或请求参数校验失败: " + message);
    }


    /**
     * 处理@RequestBody参数校验异常
     * @param e MethodArgumentNotValidException 校验异常
     * @return Result<Void> 校验失败结果
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ":" + error.getDefaultMessage())
                .collect(Collectors.joining(";"));


        log.error("请求参数校验失败: {}", message);
        return Result.error("参数校验错误: " + message);
    }


    @ExceptionHandler(SystemException.class)
    public Result<Void> handleSystemException(SystemException e) {
        log.error("系统异常: {}", e.getMessage());
        return Result.error(e.getCode(), "这是一次系统异常: " + e.getMessage());
    }

    /**
     * 兜底运行时异常
     * @param e RuntimeException 运行时异常
     * @return Result<Void> 异常结果
     */
    @ExceptionHandler(RuntimeException.class)
    public Result<Void> exceptionHandler(RuntimeException e) {
        log.error("全局捕获异常(兜底): {}", e.getMessage(), e);
        return Result.error(500, e.getMessage());
    }
}
