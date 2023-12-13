package com.ruoyi.system.utils;

import java.util.Random;

/**
 * @author Santa Antilles
 * @Date 2023/12/13-16:33
 */
public class CourseIdGenerator {
    public static String generateString() {
        // 生成两位随机数字
        String randomDigits = generateRandomDigits(2);

        // 生成六位随机数字
        String randomSixDigits = generateRandomDigits(6);

        // 构建最终的字符串
        String result = "120" + randomDigits + "-" + randomSixDigits;

        return result;
    }

    private static String generateRandomDigits(int length) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10); // 生成 0 到 9 的随机数字
            stringBuilder.append(digit);
        }

        return stringBuilder.toString();
    }
}
