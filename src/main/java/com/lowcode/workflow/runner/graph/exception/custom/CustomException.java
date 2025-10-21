package com.lowcode.workflow.runner.graph.exception.custom;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException{

    private int code;


    public CustomException(String message) {
        super(message);
        this.code = 500;
    }

    public CustomException(int code, String message) {
        super(message);
        this.code = code;
    }
}
