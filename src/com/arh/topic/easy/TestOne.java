package com.arh.topic.easy;

import java.util.*;

import javax.sound.midi.Track;

import com.sun.deploy.util.ArrayUtil;

//Difficulty - easy
public class TestOne {

    public static void main(String[] args) {
        TestOne to = new TestOne();
        int[] a = new int[]{9, 9};
//        System.out.println(to.removeElement(a, 2));
//        for (int x : a) {
//            System.out.println(x);
//        }'""\n""'
        System.out.println((to.climbStairs(37)));
    }

    /**
     * id=70 lang=java
     * You are climbing a stair case. It takes n steps to reach to the top.
     * <p>
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     * <p>
     * Note: Given n will be a positive integer.
     */
    public int climbStairs(int n) {
//        if (n == 1) {
//            return 1;
//        } else if (n == 2) {
//            return 2;
//        } else {
//            return climbStairs(n - 1) + climbStairs(n - 2) ;
//        }
        return 0;
    }

    /**
     * id=69 lang=java
     * Implement int sqrt(int x).
     * <p>
     * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
     * <p>
     * Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
     * <p>
     * 牛顿迭代法
     * 取一个初始值X0,由点(X0，f(X0))和导数f'(X0)取得直线方程,求得直线方程的根X1,以X1为起始点迭代下去
     * 最后得:X = (Xk + a/XK)/2
     * 泰勒公式也能算？
     */
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        double cur = x;
        double las;
        do {
            las = cur;
            cur = (cur + x / cur) / 2;
        } while (Math.abs(las - cur) > Double.MIN_VALUE);
        return (int) cur;
    }

    /**
     * id=67 lang=java
     * Given two binary strings, return their sum (also a binary string).
     * <p>
     * The input strings are both non-empty and contains only characters 1 or 0.
     */
    public String addBinary(String a, String b) {
        int aLength = a.length();
        int bLength = b.length();
        int[] temp = new int[Math.abs(bLength - aLength)];
        StringBuffer sb = new StringBuffer();
        for (int x : temp) {
            sb.append(x);
        }
        if (aLength < bLength) {
            sb.append(a);
            a = sb.toString();
        } else {
            sb.append(b);
            b = sb.toString();
        }
        sb.setLength(0);
        int plusN = 0;
        int bs = 2;//进制
        for (int i = a.length() - 1; i >= 0; i--) {
            int aStrNumber = Integer.parseInt(String.valueOf(a.charAt(i)));
            int bStrNumber = Integer.parseInt(String.valueOf(b.charAt(i)));
            int sum = aStrNumber + bStrNumber + plusN;
            if (sum > bs - 1) {
                plusN = 1;
                sb.insert(0, sum - bs);
            } else {
                plusN = 0;
                sb.insert(0, sum);
            }

        }
        if (plusN != 0) {
            sb.insert(0, 1);
        }
        return sb.toString();
    }

    public String addBinaryAnotherSolution(String a, String b) {
        StringBuffer sb = new StringBuffer();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int plusN = 0;
        int sum = 0;
        while (i >= 0 || j >= 0) {
            int aStrNumber = 0;
            int bStrNumber = 0;
            if (i >= 0) {
                aStrNumber = a.charAt(i--) - '0';
            }
            if (j >= 0) {
                bStrNumber = b.charAt(j--) - '0';
            }
            sum = aStrNumber + bStrNumber + plusN;
            sb.insert(0, sum % 2);
            plusN = sum / 2;
        }
        if (plusN != 0) {
            sb.insert(0, plusN);
        }

        return sb.toString();
    }


    /**
     * id=66 lang=java
     * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
     * <p>
     * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
     * <p>
     * You may assume the integer does not contain any leading zero, except the number 0 itself.
     */
    public int[] plusOne(int[] digits) {
        boolean flag = false;
        for (int i = digits.length - 1; i >= 0; i--) {
            int number = digits[i];
            if (number == 9) {
                digits[i] = 0;
                if (i == 0) {
                    flag = true;
                }
            } else {
                digits[i] = digits[i] + 1;
                break;
            }

        }
        if (flag) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            //其余元素都是0
//            for (int i = 0; i < digits.length; i++) {
//                result[i + 1] = digits[i];
//            }
            return result;
        }

        return digits;
    }


    /**
     * id=58 lang=java
     * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
     * <p>
     * If the last word does not exist, return 0.
     */
    public int lengthOfLastWord(String s) {
        return s.trim().length() - s.trim().lastIndexOf(" ") - 1;
    }


    /**
     * id=53 lang=java
     * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
     * <p>
     * (1)分治法的基本思想是将问题划分为一些子问题，子问题的形式与原问题一样，只是规模更小，递归地求解出子问题，如果子问题的规模足够小，则停止递归，直接求解，最后将子问题的解组合成原问题的解。
     * 对于最大子数组，我们要寻求子数组A[low...high]的最大子数组。令mid为该子数组的中央位置，我们考虑求解两个子数组A[low...mid]和A[mid+1...high]。A[low...high]的任何连续子数组A[i...j]所处的位置必然是以下三种情况之一：
     * <p>
     * 完全位于子数组A[low...mid]中,因此low<=i<=j<=mid.
     * 完全位于子数组A[mid+1...high]中,因此mid<i<=j<=high.
     * 跨越了中点，因此low<=i<=mid<j<=high.
     * <p>
     * (2)遍历，求出每一个以该元素结尾的和最大子元素 动态规划算法 时间复杂度为O(N)
     */
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        int currentSum;
        for (int i = 1; i < nums.length; i++) {
            currentSum = sum[i - 1] + nums[i];
            sum[i] = Math.max(currentSum, nums[i]);
        }
        int result = sum[0];
        for (int i = 1; i < sum.length; i++) {
            result = Math.max(result, sum[i]);
        }
        return result;
    }

    public int maxSubArrayByD(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        return countArraySum(nums, 0, nums.length - 1);
    }

    private int countArraySum(int[] nums, int l, int r) {
        if (l == r) {
            return nums[l];
        }
        int m = (r - l) / 2 + l;
        return Math.max(countArraySum(nums, l, m),
                Math.max(countCrossArraySum(nums, l, r), countArraySum(nums, m + 1, r)));
    }

    private int countCrossArraySum(int[] nums, int l, int r) {
        int m = (r - l) / 2 + l;
        int leftMaxSum = Integer.MIN_VALUE;
        int rightMaxSum = Integer.MIN_VALUE;
        int tempSum = 0;
        for (int i = m; i >= l; i--) {
            tempSum = tempSum + nums[i];
            if (tempSum > leftMaxSum) {
                leftMaxSum = tempSum;
            }
        }
        tempSum = 0;
        for (int i = m + 1; i <= r; i++) {
            tempSum = tempSum + nums[i];
            if (tempSum > rightMaxSum) {
                rightMaxSum = tempSum;
            }
        }
        return leftMaxSum + rightMaxSum;
    }

    /**
     * id=38 lang=java
     * The count-and-say sequence is the sequence of integers with the first five terms as following:
     * <p>
     * 1.     1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     * 1 is read off as "one 1" or 11.
     * 11 is read off as "two 1s" or 21.
     * 21 is read off as "one 2, then one 1" or 1211.
     */
    public String countAndSay(int n) {
        String result = "1";
        for (int i = 1; i < n; i++) {
            result = generateCountAndSayByLastTerm(result);
        }
        return result;
    }

    public String generateCountAndSayByLastTerm(String str) {
        char[] chars = str.toCharArray();
        char target = chars[0];
        StringBuffer result = new StringBuffer();
        int count = 1;
        for (int i = 1; i < chars.length; i++) {
            char currentChar = chars[i];
            if (currentChar == target) {
                count++;
            } else {
                result.append(count)
                        .append(chars[i - 1]);
                count = 1;
                target = chars[i];
            }
        }
        result.append(count)
                .append(chars[chars.length - 1]);
        return result.toString();
    }

    /**
     * id=35 lang=java
     * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
     * You may assume no duplicates in the array.
     */
    public int searchInsert(int[] nums, int target) {
        return searchTarget(nums, 0, nums.length - 1, target);
    }

    private int searchTarget(int[] nums, int startIndex, int endIndex, int target) {
        if (endIndex - startIndex <= 1) {
            if (nums[startIndex] == target) {
                return startIndex;
            } else if (nums[endIndex] == target) {
                return endIndex;
            } else if (nums[startIndex] > target) {
                return startIndex;
            } else if ((nums[endIndex] > target)) {
                return endIndex;
            } else if (nums[endIndex] < target) {
                return endIndex + 1;
            }
        }
        int midIndex = (startIndex + endIndex) / 2;
        int mid = nums[midIndex];
        if (mid < target) {
            return searchTarget(nums, midIndex, endIndex, target);
        } else if (mid > target) {
            return searchTarget(nums, startIndex, midIndex, target);
        } else {
            return midIndex;
        }
    }

    /**
     * id=28 lang=java
     * Implement strStr().
     * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
     */
    public int strStr(String haystack, String needle) {
        if (needle == null || "".equals(needle)) {
            return 0;
        }
        int length = needle.length();
        for (int i = 0; i <= haystack.length() - length; i++) {
            String currentStr = haystack.substring(i, i + length);
            if (currentStr.equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * id=27 lang=java
     * Given an array nums and a value val, remove all instances of that value in-place and return the new length.
     * <p>
     * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
     * <p>
     * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
     */
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int targetIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            int currentElement = nums[i];
            if (currentElement == val) {
                continue;
            } else {
                if (i != targetIndex) {
                    nums[targetIndex] = currentElement;
                }
                targetIndex++;
            }
        }
        return targetIndex;
    }

    /**
     * id=26 lang=java
     * Given a sorted array nums, remove the duplicates in-place such that each
     * element appear only once and return the new length.
     * <p>
     * Do not allocate extra space for another array, you must do this by modifying
     * the input array in-place with O(1) extra memory.
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int target = nums[0];
        int targetIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            int currentElement = nums[i];
            if (currentElement == target) {
                continue;
            } else {
                ++targetIndex;
                nums[targetIndex] = currentElement;
                target = nums[i];
            }

        }
        return targetIndex + 1;
    }

    /**
     * id=21 lang=java
     * Merge two sorted linked lists and return it as a new list. The new list
     * should be made by splicing together the nodes of the first two lists.
     * <p>
     * Example:
     * <p>
     * Input: 1->2->4, 1->3->4
     * Output: 1->1->2->3->4->4
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * id=20 lang=java
     * Given a string containing just the characters '(', ')', '{', '}', '[' and
     * ']', determine if the input string is valid.
     * <p>
     * An input string is valid if:
     * <p>
     * <p>
     * Open brackets must be closed by the same type of brackets.
     * Open brackets must be closed in the correct order.
     * <p>
     * <p>
     * Note that an empty string is also considered valid.
     */
    public boolean isValid(String s) {
        if (s == null || s.trim() == "") {
            return true;
        }
        char[] chars = new char[126];
        chars[')'] = '(';
        chars['}'] = '{';
        chars[']'] = '[';
        List<Character> ops = new ArrayList<>();
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if ('(' == c || '{' == c || '[' == c) {
                ops.add(c);
            } else if (')' == c || '}' == c || ']' == c) {
                int size = ops.size();
                if (size < 1) {
                    return false;
                }
                char target = ops.get(ops.size() - 1);
                if (chars[c] != target) {
                    return false;
                } else {
                    ops.remove(ops.size() - 1);
                }
            }
        }
        if (ops.size() > 0) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * id=14 lang=java
     * Write a function to find the longest common prefix string amongst an array
     * of strings.
     * <p>
     * If there is no common prefix, return an empty string "".
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length < 1) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        Arrays.sort(strs);
        String result = "";

        String str = strs[0];
        for (int j = 0; j < str.length(); j++) {
            String target = str.substring(0, j + 1);
            if (strs[strs.length - 1].startsWith(target)) {
                result = target;
            } else {
                break;
            }
        }
        return result;
    }

    /**
     * id=13 lang=java
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
     * id=9 lang=java
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
     * id=7 lang=java
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
     * id=1 lang=java
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

    public void printIntArray(int[] array) {
        if (array != null) {
            for (int obj : array) {
                System.out.println(obj);
            }
        }
    }
}
