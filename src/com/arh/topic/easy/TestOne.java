package com.arh.topic.easy;

import java.util.*;

import javax.sound.midi.Track;

import com.sun.deploy.util.ArrayUtil;

//Difficulty - easy
public class TestOne {

    public static void main(String[] args) {
        TestOne to = new TestOne();
        int[] a = new int[]{2, 7, 11, 15};

        //TreeNode node = to.sortedArrayToBST(a);
        //to.printTreeNode(node);
        TreeNode node = to.new TreeNode(5);
        TreeNode node2 = to.new TreeNode(4);
        TreeNode node3 = to.new TreeNode(11);
        TreeNode node4 = to.new TreeNode(7);
        TreeNode node5 = to.new TreeNode(2);
        node3.left = node4;
        node3.right = node5;
        node2.left = node3;
        node.left = node2;
        ListNode ln = to.new ListNode(3);
        ListNode ln2 = to.new ListNode(2);
        ListNode ln3 = to.new ListNode(0);
        ListNode ln4 = to.new ListNode(-4);
        ln.next = ln2;
        ln2.next = ln3;
        ln3.next = ln4;
        ln4.next = ln2;
        //System.out.println(to.hasCycle(ln));

        to.printIntArray(to.twoSumTwo(a, 9));
    }

    /**
     * id=168 lang=java
     * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
     * 取余
     */
    public String convertToTitle(int n) {
        char a = 'A';
        String[] array = new String[26];
        for (int i = 0; i < 26; i++) {
            array[i] = String.valueOf(a);
            a++;
        }
        StringBuffer sb = new StringBuffer();
        int x = n / 26;
        int y = n % 26;
        if (y == 0) {
            sb.append(array[25]);
            x = x - 1;
        } else {
            sb.append(array[y - 1]);
        }
        while (x > 0) {
            y = x % 26;
            x = x / 26;
            if (y == 0) {
                sb.append(array[25]);
                x = x - 1;
            } else {
                sb.append(array[y - 1]);
            }
        }
        sb.reverse();
        return sb.toString();
    }

    /**
     * id=167 lang=java
     * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
     * <p>
     * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
     */
    public int[] twoSumTwo(int[] numbers, int target) {
        int[] result = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            int x = numbers[i];
            int y = target - x;
            int r = dichotomy(numbers, i + 1, numbers.length - 1, y);
            if (r != -1) {
                result[0] = i + 1;
                result[1] = r + 1;
                break;
            }
        }
        return result;
    }

    public int[] twoSumTwo2(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length - 1;
        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum == target) {
                return new int[]{l + 1, r + 1};
            } else if (sum < target) {
                l++;
            } else if (sum > target) {
                r--;
            }
        }
        return new int[]{0, 0};
    }

    private int dichotomy(int[] numbers, int l, int r, int target) {
        if (l > r) {
            return -1;
        } else if (l == r && numbers[l] == target) {
            return l;
        } else if (l == r && numbers[l] != target) {
            return -1;
        }
        int m = (r - l) / 2 + l;
        if (target < numbers[m]) {
            return dichotomy(numbers, l, m - 1, target);
        } else if (target > numbers[m]) {
            return dichotomy(numbers, m + 1, r, target);
        } else {
            return m;
        }
    }

    /**
     * id=160 lang=java
     * Write a program to find the node at which the intersection of two singly linked lists begins.
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode nodeA = headA.next;
        int nodeALength = 1;
        while (nodeA != null) {
            nodeA = nodeA.next;
            nodeALength++;
        }
        ListNode nodeB = headB.next;
        int nodeBLength = 1;
        while (nodeB != null) {
            nodeB = nodeB.next;
            nodeBLength++;
        }

        int dif = Math.abs(nodeALength - nodeBLength);
        if (nodeALength > nodeBLength) {
            return getIntersectionNodeByOrder(headB, headA, dif);
        } else {
            return getIntersectionNodeByOrder(headA, headB, dif);
        }
    }

    private ListNode getIntersectionNodeByOrder(ListNode smallNode, ListNode bigNode, int dif) {
        while (dif > 0) {
            bigNode = bigNode.next;
            dif--;
        }
        if (smallNode == bigNode) {
            return smallNode;
        }
        while (smallNode.next != null && bigNode.next != null) {
            smallNode = smallNode.next;
            bigNode = bigNode.next;
            if (smallNode == bigNode) {
                return smallNode;
            }
        }
        return null;
    }

    /**
     * id=155 lang=java
     * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
     * <p>
     * push(x) -- Push element x onto stack.
     * pop() -- Removes the element on top of the stack.
     * top() -- Get the top element.
     * getMin() -- Retrieve the minimum element in the stack.
     * 在插入一个比当前最小数还要小的数时，先插入当前最小，再插入新的数。取数时则要判断是否是最小数.
     */
    class MinStack {

        private Stack<Integer> stack = new Stack();

        private Integer min = Integer.MAX_VALUE;

        /**
         * initialize your data structure here.
         */
        public MinStack() {

        }

        public void push(int x) {
            if (x <= min) {
                stack.push(min);
                min = x;
            }
            stack.push(x);
        }

        public void pop() {
            int x = stack.pop();
            if (x == min) {
                min = stack.pop();
            }

        }

        public int top() {
            return stack.lastElement();
        }

        public int getMin() {
            return min;
        }
    }

    /**
     * id=141 lang=java
     * Given a linked list, determine if it has a cycle in it.
     * <p>
     * To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        HashSet hashSet = new HashSet();
        ListNode c = head;
        hashSet.add(c);
        boolean result = false;
        while (c.next != null) {
            if (hashSet.contains(c.next)) {
                result = true;
                break;
            }
            hashSet.add(c.next);
            c = c.next;
        }
        return result;
    }

    /**
     * 链表就想到快慢指针（设快指针速度为满指针2倍）
     * 猜想：如果列表有环，则快慢指针移动会在某个节点相遇(环外节点为p，环内节点为q)
     * 证明：设相遇时慢指针走了t个节点，则有(t-(p-1)) mod q = (2t-(p-1)) mod q => (2t-(p-1)-(t-(p-1))) mod q = 0
     * 由等式知道t满足t是q的倍数并且要比p大（要进到环内一定要走过p个节点）
     * 所以一定会存在这个t，所以有环快慢指针一定会相遇
     */
    public boolean betterHasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slowNode = head;
        ListNode fastNode = head;
        while (fastNode != null) {
            slowNode = slowNode.next;
            fastNode = fastNode.next;
            if (fastNode == null) {
                return false;
            }
            fastNode = fastNode.next;
            if (fastNode == slowNode) {
                return true;
            }
        }
        return false;
    }

    /**
     * 这个解法会改变链表的结构
     * 遇到一个节点就把这个节点的next指向自己，如果没有环是不会遇到节点指向自己的
     */
    public boolean betterHasCycle2(ListNode head) {
        if (head == null) {
            return false;
        }
        while (head != null) {
            ListNode nextNode = head.next;
            if (head == nextNode) {
                return true;
            }
            head.next = head;
            head = nextNode;
        }
        return false;
    }

    /**
     * id=136 lang=java
     * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
     */
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        int result = nums[nums.length - 1];
        for (int i = 0; i < nums.length - 1; i = i + 2) {
            if (nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }
        return result;
    }

    /**
     * id=125 lang=java
     * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
     * <p>
     * Note: For the purpose of this problem, we define empty string as valid palindrome.
     */
    public boolean isPalindrome(String s) {
        String myS = s.replaceAll("[^A-Za-z0-9]", "");
        boolean result = true;
        char[] chars = myS.toCharArray();
        int l = 0;
        int r = chars.length - 1;
        while (l < r) {
            if (!String.valueOf(chars[l]).equalsIgnoreCase(String.valueOf(chars[r]))) {
                result = false;
                break;
            } else {
                l++;
                r--;
            }
        }
        return result;
    }

    /**
     * id=122 lang=java
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * <p>
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
     * <p>
     * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
     */
    public int maxProfitTwo(int[] prices) {
        int result = 0;
        if (prices != null && prices.length != 0) {
            int bought = prices[0];
            boolean flag = true;
            for (int i = 1; i < prices.length; i++) {
                if (flag && prices[i] > bought) {
                    result = result + prices[i] - bought;
                    flag = false;
                } else if (flag && prices[i] < bought) {
                    bought = prices[i];
                } else if (!flag && prices[i] > prices[i - 1]) {
                    result = result - prices[i - 1] + prices[i];
                } else if (!flag) {
                    bought = prices[i];
                    flag = true;
                }
            }
        }
        return result;
    }

    /**
     * 贪心思想
     */
    public int maxProfitTwo2(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            result = result + Math.max(0, prices[i] - prices[i - 1]);
        }
        return result;
    }

    /**
     * id=121 lang=java
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * <p>
     * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
     */
    public int maxProfit(int[] prices) {
        int[] maxPArray = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            int currentP = prices[i];
            int currentM = Integer.MIN_VALUE;
            for (int j = i + 1; j < prices.length; j++) {
                int t = prices[j] - currentP;
                if (t > currentM) {
                    currentM = t;
                }
            }
            maxPArray[i] = currentM;
        }
        int result = 0;
        for (int i = 0; i < maxPArray.length; i++) {
            if (maxPArray[i] > result) {
                result = maxPArray[i];
            }
        }
        return result;
    }

    public int betterMaxProfit(int[] prices) {
        int maxP = 0;
        if (prices.length != 0) {
            int bought = prices[0];
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] < bought) {
                    bought = prices[i];
                } else if (prices[i] - bought > maxP) {
                    maxP = prices[i] - bought;
                }
            }
        }
        return maxP;
    }

    /**
     * 要求出数组中最小的买入点，最大的卖出点，且卖出再买入后面
     * 为了结构一致（都是用max）,比较买入点的负数形式，买入点越小，对应负数越大
     */
    public int betterMaxProfit2(int[] prices) {
        int bought = Integer.MIN_VALUE;
        int maxP = 0;
        for (int i = 0; i < prices.length; i++) {
            bought = Math.max(bought, -prices[i]);
            maxP = Math.max(maxP, prices[i] + bought);
        }
        return maxP;
    }

    /**
     * id=119 lang=java
     * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
     * <p>
     * Note that the row index starts from 0.
     */
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex < 0) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        result.add(1);
        while (rowIndex > 0) {
            List<Integer> tempList = new ArrayList<>();
            tempList.add(1);
            for (int i = 0; i < result.size() - 1; i++) {
                tempList.add(result.get(i) + result.get(i + 1));
            }
            tempList.add(1);
            result = tempList;
            rowIndex--;
        }
        return result;
    }

    /**
     * id=118 lang=java
     * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows > 0) {
            List<Integer> temp = new ArrayList<>();
            temp.add(1);
            result.add(temp);
            generate(--numRows, result);
        }
        return result;
    }

    public void generate(int numRows, List<List<Integer>> result) {
        if (numRows > 0) {
            List<Integer> temp = new ArrayList<>();
            temp.add(1);
            List<Integer> lastTemp = result.get(result.size() - 1);
            for (int i = 0; i < lastTemp.size() - 1; i++) {
                temp.add(lastTemp.get(i) + lastTemp.get(i + 1));
            }
            temp.add(1);
            result.add(temp);
            generate(--numRows, result);
        }
    }

    /**
     * id=112 lang=java
     * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        } else if (root.left == null && root.right == null && sum == root.val) {
            return true;
        } else {
            if (!hasPathSum(root.left, sum - root.val)) {
                return hasPathSum(root.right, sum - root.val);
            } else {
                return true;
            }
        }
    }

    /**
     * id=111 lang=java
     * Given a binary tree, find its minimum depth.
     * <p>
     * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
     */
    public int currentMinDepth = Integer.MAX_VALUE;

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return minDepth(root, 1);
    }

    private int minDepth(TreeNode node, int depth) {
        if (node == null) {
            return Integer.MAX_VALUE;
        } else if (node.left == null && node.right == null) {
            currentMinDepth = depth;
            return depth;
        } else {
            depth++;
            if (depth >= currentMinDepth) {
                return Integer.MAX_VALUE;
            }
            return Math.min(minDepth(node.left, depth), minDepth(node.right, depth));
        }
    }

    /**
     * id=110 lang=java
     * Given a binary tree, determine if it is height-balanced.
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            TreeNode leftNode = root.left;
            TreeNode rightNode = root.right;
            if (Math.abs(maxDepth(leftNode) - maxDepth(rightNode)) <= 1) {
                return isBalanced(leftNode) && isBalanced(rightNode);
            } else {
                return false;
            }
        }
    }

    /**
     * id=108 lang=java
     * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
     * <p>
     * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        int rootIndex = nums.length - 1 - (nums.length - 1 - 0) / 2;
        TreeNode root = new TreeNode(nums[rootIndex]);
        root.left = generateLeftRightNode(nums, 0, rootIndex - 1);
        root.right = generateLeftRightNode(nums, rootIndex + 1, nums.length - 1);
        return root;
    }

    public TreeNode generateLeftRightNode(int[] nums, int p, int q) {
        if (p > q) {
            return null;
        }
        int m = q - (q - p) / 2;
        TreeNode node = new TreeNode(nums[m]);
        node.left = generateLeftRightNode(nums, p, m - 1);
        node.right = generateLeftRightNode(nums, m + 1, q);
        return node;
    }

    /**
     * id=107 lang=java
     * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        } else {
            putNodeInList(root, result, 0);
        }
        Collections.reverse(result);
        return result;
    }

    public void putNodeInList(TreeNode node, List<List<Integer>> result, int number) {
        if (node != null) {
            List<Integer> list = null;
            if (result.size() > number) {
                list = result.get(number);
            }

            if (list == null) {
                list = new ArrayList<>();
                result.add(list);
            }
            list.add(node.val);
            TreeNode left = node.left;
            TreeNode right = node.right;
            number++;
            putNodeInList(left, result, number);
            putNodeInList(right, result, number);
        }
    }


    /**
     * id=104 lang=java
     * Given a binary tree, find its maximum depth.
     * <p>
     * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
     * <p>
     * Note: A leaf is a node with no children.
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }
    }

    /**
     * id=101 lang=java
     * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        TreeNode nodeLeft = root.left;
        TreeNode nodeRight = root.right;
        return compareLeftRightNode(nodeLeft, nodeRight);

    }

    public boolean compareLeftRightNode(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p != null && q == null) {
            return false;
        } else if (p == null && q != null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        } else {
            if (compareLeftRightNode(p.left, q.right)) {
                return compareLeftRightNode(p.right, q.left);
            } else {
                return false;
            }
        }
    }

    /**
     * id=100 lang=java
     * Given two binary trees, write a function to check if they are the same or not.
     * <p>
     * Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null && q != null) {
            return false;
        } else if (p != null && q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        } else {
            if (isSameTree(p.left, q.left)) {
                return isSameTree(p.right, q.right);
            } else {
                return false;
            }
        }
    }


    /**
     * id=88 lang=java
     * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
     * <p>
     * Note:
     * <p>
     * The number of elements initialized in nums1 and nums2 are m and n respectively.
     * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
     * 归并排序，用nums1做存储数组，如果从小比较，移动较多。所以从大比较
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index1 = m - 1;
        int index2 = n - 1;
        int maxIndex = m + n - 1;
        while (maxIndex >= 0) {
            int curValue1 = Integer.MIN_VALUE;
            if (index1 >= 0) {
                curValue1 = nums1[index1];
            }
            int curValue2 = Integer.MIN_VALUE;
            if (index2 >= 0) {
                curValue2 = nums2[index2];
            }
            if (curValue1 <= curValue2) {
                nums1[maxIndex] = curValue2;
                index2--;
            } else {
                nums1[maxIndex] = curValue1;
                index1--;
            }
            maxIndex--;
        }

    }

    /**
     * id=83 lang=java
     * Given a sorted linked list, delete all duplicates such that each element appear only once.
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode lastNode = head;
        ListNode curNode = head;
        while (curNode.next != null) {
            curNode = curNode.next;
            if (curNode.val != lastNode.val) {
                lastNode.next = curNode;
                lastNode = curNode;
            }
        }
        if (lastNode.val == curNode.val) {
            lastNode.next = null;
        }
        return head;
    }

    /**
     * id=70 lang=java
     * You are climbing a stair case. It takes n steps to reach to the top.
     * <p>
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     * <p>
     * Note: Given n will be a positive integer.
     * <p>
     * 假设有x个台阶，走法数等于(走(x-1)个台阶走法术+1步)加上(走(x-2)个台阶走法术+2步)
     * 斐波那契数列 f(x) = f(x-1)+f(x-2)
     * 递归会有重复计算，所以从1到目标数算
     */
    public int climbStairs(int n) {
        if (n < 2) {
            return 1;
        }
        int a = 1;
        int b = 1;
        for (int i = 2; i < n; i++) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return a + b;
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

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public void printIntArray(int[] array) {
        if (array != null) {
            for (int obj : array) {
                System.out.println(obj);
            }
        }
    }

    public void printTreeNode(TreeNode node) {
        if (node != null) {
            int depth = maxDepth(node);
            if (depth == 1) {
                System.out.println(node.val);
                return;
            }
            int blankNumber = (int) (Math.pow(2, depth) - 1);
            List<TreeNode> nodes = new ArrayList<>();
            nodes.add(node);
            printTreeNode(nodes, depth - 1);
        }
    }

    private void printTreeNode(List<TreeNode> nodes, int n) {
        if (nodes == null || nodes.size() < 1) {
            return;
        }
        System.out.println("");
        int blankNumber = (int) (Math.pow(2, n) - 1);
        for (int i = 0; i < blankNumber; i++) {
            System.out.print(" ");
        }
        List<TreeNode> nextNodes = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            TreeNode tempNode = nodes.get(i);
//            if (i > 0 && i % 2 == 0) {
//                System.out.print(" ");
//            }
            if (tempNode == null) {
                System.out.print("  ");
                continue;
            } else {
                System.out.print(tempNode.val + " ");
                nextNodes.add(tempNode.left);
                nextNodes.add(tempNode.right);
            }

        }
        printTreeNode(nextNodes, --n);

    }
}
