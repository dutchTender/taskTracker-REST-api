package com.zhang.common.base.rest;

public abstract class AbstractRestResponse<T> {
    private String code;
    private String message;
    private T data;
    private AbstractRestMetaData metaData;

    public AbstractRestResponse(String code, String message, T data, AbstractRestMetaData metaData) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.metaData = metaData;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public AbstractRestMetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(AbstractRestMetaData metaData) {
        this.metaData = metaData;
    }
}
