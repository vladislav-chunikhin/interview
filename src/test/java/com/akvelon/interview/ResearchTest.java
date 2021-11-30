package com.akvelon.interview;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;

public class ResearchTest {

    @Test
    public void twoSum() {
        int[] nums = {1, 2, 5, 7, 11, -3};
        int target = 9;

        int[] indices1 = new Solution().twoSumFirstApproach(nums, target);
        int[] indices2 = new Solution().twoSumSecondApproach(nums, target);
        int[] indices3 = new Solution().twoSumThirdApproach(nums, target);
        int[] expectedIndices = {1, 3};

        Assertions.assertNotNull(indices1);
        Arrays.stream(indices1).forEach(System.out::println);
        Assertions.assertArrayEquals(expectedIndices, indices1);

        Assertions.assertNotNull(indices2);
        Arrays.stream(indices2).forEach(System.out::println);
        Assertions.assertArrayEquals(expectedIndices, indices2);

        Assertions.assertNotNull(indices3);
        Arrays.stream(indices3).forEach(System.out::println);
        Assertions.assertArrayEquals(expectedIndices, indices3);
    }

    @Test
    public void palindromeNumber() {
        int firstNumber = 121;
        int secondNumber = -121;
        int thirdNumber = 10;
        int fourthNumber = 5;

        Assertions.assertTrue(new Solution().isPalindromeFirstApproach(firstNumber));
        Assertions.assertFalse(new Solution().isPalindromeFirstApproach(secondNumber));
        Assertions.assertFalse(new Solution().isPalindromeFirstApproach(thirdNumber));
        Assertions.assertTrue(new Solution().isPalindromeFirstApproach(fourthNumber));
    }

    @Test
    public void validateParentheses() {
        String target = "";
        String target0 = "[";
        String target1 = "[()]";
        String target2 = "[(()]";
        String target3 = "[(()]";
        String target4 = "{()]";
        String target5 = "{[{()}]}";

        Assertions.assertFalse(new Solution().validateParenthesis(target));
        Assertions.assertFalse(new Solution().validateParenthesis(target0));
        Assertions.assertTrue(new Solution().validateParenthesis(target1));
        Assertions.assertFalse(new Solution().validateParenthesis(target2));
        Assertions.assertFalse(new Solution().validateParenthesis(target3));
        Assertions.assertFalse(new Solution().validateParenthesis(target4));
        Assertions.assertTrue(new Solution().validateParenthesis(target5));
    }

    @Test
    public void longestCommonPrefix() {
        String[] input1 = {"gomwgggwwtwqtwqtq","gomora", "gomishka", "gomisilia"};
        String[] input2 = {"gomora", "goishka", "travishka"};
        String[] input3 = {"cat", "dog", "house"};

        Assertions.assertEquals("gom", new Solution().findLongestCommonPrefixSecondApproach(input1));
        Assertions.assertEquals("", new Solution().findLongestCommonPrefixSecondApproach(input2));
        Assertions.assertEquals("", new Solution().findLongestCommonPrefixSecondApproach(input3));
    }


    @Test
    public void validateParentheses2() {
        String target = "";
        String target1 = "()";
        String target2 = "()[]{}";
        String target3 = "(]";
        String target4 = "([)]";
        String target5 = "{[]}";

        Assertions.assertFalse(new Solution().validateParenthesis2(target));
        Assertions.assertTrue(new Solution().validateParenthesis2(target1));
        Assertions.assertTrue(new Solution().validateParenthesis2(target2));
        Assertions.assertFalse(new Solution().validateParenthesis2(target3));
        Assertions.assertFalse(new Solution().validateParenthesis2(target4));
        Assertions.assertTrue(new Solution().validateParenthesis2(target5));
    }

    @Test
    public void removeDuplicatesFromSortedArray() {
        int[] input = {1, 1, 2, 2, 2, 2, 3, 4 ,5 ,5};
        int[] expectedResult = {1, 2, 3, 4, 5};

        int[] input2 = {1, 2, 3, 4, 5, 5, 6, 7, 8, 8};

        int result = new Solution().removeDuplicatesFromSortedArray(input);
        Assertions.assertEquals(5, result);

        for (int i = 0; i < result; i++) {
            Assertions.assertEquals(expectedResult[i], input[i]);
        }

        int result2 = new Solution().removeDuplicatesFromSortedArray(input2);
        Arrays.stream(input2).forEach(System.out::println);
    }

    @Test
    public void removeElement() {
        int[] nums = {1, 2, 77, 2, 0, -4, 67, 2, 5, 6, 2};
        int removedNum = 2;
        int expectedResult = 7;
        Assertions.assertEquals(expectedResult, new Solution().removeElement(nums, removedNum));
        Arrays.stream(nums).forEach(System.out::println);
    }

}

class Solution {
    public int[] twoSumFirstApproach(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int firstNum = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int secondNum = nums[j];
                int sum = firstNum + secondNum;
                if (sum == target) return new int[] {i, j};
            }
        }
        return null;
    }

    public int[] twoSumSecondApproach(int[] nums, int target) {
        HashMap<Integer, Integer> storage = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            storage.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (storage.containsKey(complement) && storage.get(complement) != i) {
                return new int[] {i, storage.get(complement)};
            }
        }
        return null;
    }

    public int[] twoSumThirdApproach(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public boolean isPalindromeFirstApproach(int num) {
        if (num < 0) return false;

        int reversed = 0;
        int remainder;
        int original = num;

        while (num != 0) {
            remainder = num % 10;
            reversed = reversed * 10 + remainder;
            num /= 10;
        }

        return original == reversed;
    }

    public boolean validateParenthesis(String target) {
        if (target == null) return false;
        if (target.length() % 2 != 0) return false;

        for (int i = 0; i < target.length() / 2; i++) {
            char startSymbol = target.charAt(i);
            char endSymbol = target.charAt((target.length() - 1) - i);

            switch (startSymbol) {
                case '(':
                    if (endSymbol != ')')
                        return false;
                    break;
                case '[':
                    if (endSymbol != ']')
                        return false;
                    break;
                case '{':
                    if (endSymbol != '}')
                        return false;
                    break;
                default:
                    return false;
            }
        }

        return !target.isEmpty();
    }

    public String findLongestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    public String findLongestCommonPrefixSecondApproach(String[] str) {
        if (str == null || str.length == 0) return "";

        Arrays.sort(str);

        int end = Math.min(str[0].length(), str[str.length - 1].length());
        int i = 0;

        while(i<end && str[0].charAt(i) == str[str.length-1].charAt(i))
            i++;
        String pre = str[0].substring(0, i);
        return pre;
    }

    public boolean validateParenthesis2(String target) {
        if (target == null || target.isEmpty()) return false;

        Deque<Character> deque = new ArrayDeque<>();

        for (int i = 0; i < target.length(); i++) {
            char symbol = target.charAt(i);

            if (symbol == '(' || symbol == '[' || symbol == '{') {
                deque.push(symbol);
                continue;
            }

            if (deque.isEmpty()) return false;

            switch (symbol) {
                case ')':
                    char check = deque.poll();
                    if (check == '[' || check == '{') return false;
                    break;
                case ']':
                    char check1 = deque.poll();
                    if (check1 == '(' || check1 == '{') return false;
                    break;
                case '}':
                    char check2 = deque.poll();
                    if (check2 == '(' || check2 == '[') return false;
                    break;
            }
        }

        return deque.isEmpty();
    }

    public int removeDuplicatesFromSortedArray(int[] input) {
        if (input.length == 0) return 0;

        int i = 0;

        for (int j = 1; j < input.length; j++) {
            if (input[j] != input[i]) {
                i++;
                input[i] = input[j];
            }
        }
        return i + 1;
    }

    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

}
