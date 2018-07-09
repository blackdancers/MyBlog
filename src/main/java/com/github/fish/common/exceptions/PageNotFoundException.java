package com.github.fish.common.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PageNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 异常信息
     */
    private String msg;

    /**
     * 具体异常码
     */
    private Integer code;

    public PageNotFoundException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public PageNotFoundException() {
        super();
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }

    public PageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PageNotFoundException(Throwable cause) {
        super(cause);
    }
}
