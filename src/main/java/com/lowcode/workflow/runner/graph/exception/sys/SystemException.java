package com.lowcode.workflow.runner.graph.exception.sys;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemException extends RuntimeException {

    private int code;

    public SystemException(int code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(String message) {
        super(message);
        this.code = 500;
    }
}
