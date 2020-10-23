package com.quanmin.paresfile.common.exception;

/**
 * @author quanmin
 * @since 2019-03-26 14:38
 */
public class NormalException extends RuntimeException {

    public NormalException() {
    }

    public NormalException(String message) {
        super(message);
    }
}