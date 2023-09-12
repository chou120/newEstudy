package com.easy.zadmin.util;

import java.util.Random;

/**
 * @Author sanye
 * @Date 2023/8/27 22:08
 * @Version 1.0
 */
public class MakePassword {
    /**
     * 随机6-16位密码
     * @return
     */
    public static String genSixToSixteenPsw() {
        String val = "";
        Random random = new Random();
        int numbers = random.nextInt(8) +8; // 如果想要7-16位: random.nextInt(9) + 7;
        for (int i = 0; i < numbers; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 字符串
            if ("char".equalsIgnoreCase(charOrNum)) {
                //取得大写字母还是小写字母
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                // 数字
                val += String.valueOf(random.nextInt(10));
            }
        }
        return  val.toLowerCase();
    }

//    public static void main(String[] args) {
//        System.out.println(genSixToSixteenPsw());
//        System.out.println(genSixToSixteenPsw());
//        System.out.println(genSixToSixteenPsw());
//        System.out.println(genSixToSixteenPsw());
//        System.out.println(genSixToSixteenPsw());
//        System.out.println(genSixToSixteenPsw());
//        System.out.println(genSixToSixteenPsw());
//        System.out.println(genSixToSixteenPsw());
//        System.out.println(genSixToSixteenPsw());
//        System.out.println(genSixToSixteenPsw());
//        System.out.println(genSixToSixteenPsw());
//
//
//
//
//    }
}
