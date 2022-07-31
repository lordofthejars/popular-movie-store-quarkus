package com.redhat;

import java.io.Serializable;

public class CustomException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;
    private int status = 400;

    public CustomException() {
        super();
    }
    public CustomException(String msg) {
        super(msg);
    }
    public CustomException(String msg, int status) {
        super(msg);
        this.status = status;
    }
    public CustomException(String msg, Exception e)  {
        super(msg, e);
    }
    public CustomException(String msg, Exception e, int status)  {
        super(msg, e);
        this.status = status;
    }

    public int status() {
        return status;
    }
}
