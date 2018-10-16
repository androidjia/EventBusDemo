package com.jj.eventbusdemo;

/**
 * Created by Administrator on 2018/10/16.
 */

public class MsgBean {
    private String name;
    public MsgBean(){
        super();
    }
    public MsgBean(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
