package com.example.atjava.models;

public class Json {
    public String msg;
    public String msf;

    public Json(){}

    public Json(String msg, String msf) {
        this.msg = msg;
        this.msf = msf;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsf() {
        return msf;
    }

    public void setMsf(String msf) {
        this.msf = msf;
    }
}
