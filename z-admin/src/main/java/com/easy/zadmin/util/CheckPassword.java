package com.easy.zadmin.util;

import com.easy.zadmin.common.res.ResponseResult;

/**
 * @Author sanye
 * @Date 2023/8/26 15:57
 * @Version 1.0
 */
public class CheckPassword {
    /**
     * 包含大小写字母及数字且在6-12位
     * 是否包含
     *
     * @param str
     * @return
     */
    public static boolean checkPass(String str) {
        String regex = "^[a-zA-Z]{8,16}$";

        return  str.matches(regex);

    }
}

