package com.txn.exception;

public class ZjPortException extends RuntimeException {
    private String msg;
    private int code = -2;

    public ZjPortException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ZjPortException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public ZjPortException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public ZjPortException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
