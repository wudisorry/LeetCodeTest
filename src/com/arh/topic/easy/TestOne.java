package com.arh.topic.easy;

public class TestOne {

    public static void main(String[] args) {
        System.out.println(isPalindrome(1200021));
    }

    /**
     * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     */
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        } else if (x == 0) {
            return true;
        } else {
            int number = 10;
            int j = 1;
            while (x / number > 0) {
                int a = x / number;
                int b = x % number;
                int i = 0;
                while (a / 10 >= Math.pow(10, j - 1)) {
                    a = a / 10;
                    i++;
                }
                if (number != 10) {
                    a = a % (number / 10);
                    b = b / (number / 10);
                }

                if (a != b) {
                    return false;
                }
                j++;
                number = (int) Math.pow(number, j);
            }
            return true;
        }

    }

    /**
     * [7] 整数反转
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * 注意:
     * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回
     */
    public int reverse(int x) {
        return 0;
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
