package com.github.daianaegermichels.api.domain;

public class ValidationException extends RuntimeException {
    public ValidationException(String msg) {
    super(msg);
    }
}
