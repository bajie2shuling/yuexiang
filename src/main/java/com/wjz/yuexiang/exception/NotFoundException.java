package com.wjz.yuexiang.exception;

/**
 * Created by Jinzi Wu at 15:27 on 2018/4/25.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
