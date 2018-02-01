package com.skhu.vote.model;

import lombok.Data;

@Data
public class DefaultResponse {

    private StatusEnum status;
    private Object data;
    private String msg;

    public DefaultResponse() {
        this.status = StatusEnum.FAIL;
        this.data = null;
        this.msg = null;
    }

}