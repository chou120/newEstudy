package com.easy.zadmin.util;

import java.util.regex.Pattern;

/**
 * @Author sanye
 * @Date 2023/8/26 22:03
 * @Version 1.0
 */
public class CheckEmail {


    public static boolean isValidEmail(String email) {
        if ((email != null) && (!email.isEmpty())) {
            return Pattern.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+\n", email);
        }
        return false;
    }


}
