package com.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultBean {
    private long statusCode;
    private String message;
    private Object obj;

    public static ResultBean success(String message){
        return new ResultBean(200,message,null);
    }
    public static ResultBean success(String message,Object object){
        return new ResultBean(200,message,object);
    }
    public static ResultBean error(String message){
        return new ResultBean(500,message,null);
    }
    public static ResultBean error(String message,Object object){
        return new ResultBean(500,message,object);
    }

}
