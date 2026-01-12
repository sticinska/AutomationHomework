package org.homework.common.util;

public enum HttpStatus {
    OK(200, "OK"),
    BAD_REQUEST(400, "Bad Request"),
    NOT_SUPPORTED(405, "Not Supported");

    private final int code;

    HttpStatus(int code, String description) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }


}
