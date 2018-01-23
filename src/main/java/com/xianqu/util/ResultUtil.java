package com.xianqu.util;

import com.xianqu.bean.Result;

public class ResultUtil {
    public static Result success(){
        Result res = new Result();
        res.setCode(200);
        res.setMsg("请求成功!");
        return res;
    }
}
