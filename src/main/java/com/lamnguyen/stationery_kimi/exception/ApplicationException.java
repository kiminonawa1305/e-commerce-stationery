package com.lamnguyen.stationery_kimi.exception;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {
    private int code;
    private String message;

    public ApplicationException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
}
