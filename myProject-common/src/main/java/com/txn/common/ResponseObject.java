package com.txn.common;

import java.io.Serializable;

public class ResponseObject<T> implements Serializable {
    private static final long serialVersionUID = 3240173080634963941L;
    private int status;
    private T data;
    private String msg;
    private String details;

    public ResponseObject() {
        this.status = ResultCode.BUSINESS_SUCESS.getErrorCode();
    }

    public ResponseObject(T data) {
        this.status = ResultCode.BUSINESS_SUCESS.getErrorCode();
        this.data = data;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void success(T data, String msg) {
        this.setStatus(ResultCode.BUSINESS_SUCESS.getErrorCode());
        this.setData(data);
        this.setMsg(msg);
    }

    public void success(T data, String msg, String details) {
        this.success(data, msg);
        this.setDetails(details);
    }

    public void success(T data) {
        this.setStatus(ResultCode.BUSINESS_SUCESS.getErrorCode());
        this.setData(data);
        this.setMsg(ResultCode.BUSINESS_SUCESS.getError());
    }

    public void failed(T data, String msg, String details) {
        this.failed(data, msg);
        this.setDetails(details);
    }

    public void failed(T data, String msg) {
        this.setStatus(ResultCode.BUSINESS_SUCESS.getErrorCode());
        this.setData(data);
        this.setMsg(msg);
    }

    public void failed(String msg) {
        this.setStatus(ResultCode.BUSINESS_FAILURE.getErrorCode());
        this.setData(this.data);
        this.setMsg(msg);
    }
}