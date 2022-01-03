package com.example.springbootdemo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResult<T> implements Serializable {

    private int code;

    private T data;

    public static <T> ApiResult ok(T data) {
        ApiResult<Object> result = new ApiResult<>();
        result.setCode(200);
        result.setData(data);
        return result;
    }

}
