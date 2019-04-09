package com.arh.topic.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.sun.deploy.util.ArrayUtil;

public class TestOne {

    public static void main(String[] args) {
        String[] strs = new String[]{"bb","ac","aad"};
        Arrays.sort(strs);
        System.out.println("aa".subSequence(0,2));
        //System.out.println(romanToInt("MCMXCIV"));
    }


    /**
     *
     * Write a function to find the longest common prefix string amongst an array
     * of strings.
     *
     * If there is no common prefix, return an empty string "".
     */
    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            for (int j = 0; j < str.length(); j++) {
                String key = str.substring(0, j + 1);
            }
        }
        return null;
    }

    public int findLastStr(String[] strs, int beginIndex, int endIndex, String target) {
        if (beginIndex < 0 || endIndex < 0) {
            return -1;
        } else if (endIndex == beginIndex) {
            if (strs[beginIndex].startsWith(target)) {
                return beginIndex;
            } else {
                return -1;
            }
        } else {
            int middle = (beginIndex + endIndex) / 2;
            if (strs[middle].startsWith(target)) {
                return findLastStr(strs, middle, endIndex, target);
            } else {
                return findLastStr(strs, beginIndex, middle - 1, target);
            }
        }
    }

    /**
     * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     */
    public static int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        int result = 0;
        for (int i = 0; i <= s.length() - 1; i++) {
            char c = s.charAt(i);
            if ("I".equals(String.valueOf(c)) || "X".equals(String.valueOf(c)) || "C".equals(String.valueOf(c))) {
                if (i + 1 <= s.length() - 1) {
                    char c2 = s.charAt(i + 1);
                    int x = map.get(String.valueOf(c2)) / map.get(String.valueOf(c));
                    if (x == 5 || x == 10) {
                        result = result + map.get(String.valueOf(c2)) - map.get(String.valueOf(c));
                        i++;
                        continue;
                    }
                }
            }
            result = result + map.get(String.valueOf(c));

        }
        return result;
    }

    /**
     * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     */
    public static boolean isPalindrome(int x) {
        String numberStr = String.valueOf(x);
        for (int i = 0; i < numberStr.length() / 2; i++) {
            if (numberStr.charAt(i) != numberStr.charAt(numberStr.length() - 1 - i))
                return false;
        }
        return true;

    }

    /**
     * [7] 整数反转
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * 注意:
     * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回
     */
    public int reverse(int x) {
        String number = String.valueOf(x);
        char[] chars = number.toCharArray();
        int startIndex = 0;
        int length = chars.length;
        if (x < 0) {
            startIndex++;
            length--;
        }
        for (int i = startIndex; i < length / 2 + startIndex; i++) {
            char temp = chars[i];
            chars[i] = chars[chars.length - 1 - (i - startIndex)];
            chars[chars.length - 1 - (i - startIndex)] = temp;
        }
        try {
            return Integer.parseInt(String.valueOf(chars));
        } catch (NumberFormatException e) {
            return 0;
        }
    }


    /**
     * [1] 两数之和
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            int y = target - x;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == y) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }
}
