package org.homework.common.util;

public enum HttpStatus {
    OK(200),
    BAD_REQUEST(400),
    NOT_SUPPORTED(405);

    private final int code;

    HttpStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }


}
