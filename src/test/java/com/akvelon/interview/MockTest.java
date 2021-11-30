package com.akvelon.interview;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MockTest {

    private ClassA testable = new ClassA();

    @Test
    public void strStr() {

        String haystack1 = "trwqqqq";
        String haystack2 = "";
        String haystack3 = "aaaaa";
        String haystack4 = "sdtqq";

        int result1 = testable.strStr1(haystack1, "wq");
        int result2 = testable.strStr1(haystack2, "");
        int result3 = testable.strStr1(haystack3, "bba");
        int result4 = testable.strStr1(haystack4, "sdtqq");
        Assertions.assertEquals(2, result1);
        Assertions.assertEquals(0, result2);
        Assertions.assertEquals(-1, result3);
        Assertions.assertEquals(0, result4);
    }

    @Test
    public void searchInsert() {
        int[] nums1 = {1, 3, 5, 6};
        int target1 = 5;
        int expectedIndex1 = 2;

        int[] nums2 = {1, 3, 5, 6};
        int target2 = 2;
        int expectedIndex2 = 1;

        int[] nums3 = {1, 3, 5, 6};
        int target3 = 7;
        int expectedIndex3 = 4;

        int[] nums4 = {1, 3, 5, 6};
        int target4 = 0;
        int expectedIndex4 = 0;

        int[] nums5 = {1};
        int target5 = 0;
        int expectedIndex5 = 0;

        Assertions.assertEquals(expectedIndex1, testable.searchInsert(nums1, target1));
        Assertions.assertEquals(expectedIndex2, testable.searchInsert(nums2, target2));
        Assertions.assertEquals(expectedIndex3, testable.searchInsert(nums3, target3));
        Assertions.assertEquals(expectedIndex4, testable.searchInsert(nums4, target4));
        Assertions.assertEquals(expectedIndex5, testable.searchInsert(nums5, target5));
    }

    @Test
    public void maxSubArray() {
        int[] input1 = {-2,1,-3,4,-1,2,1,-5,4};
        int[] input2 = {1};
        int[] input3 = {5,4,-1,7,8};
        Assertions.assertEquals(6, testable.maxSubArray(input1));
        Assertions.assertEquals(1, testable.maxSubArray(input2));
        Assertions.assertEquals(23, testable.maxSubArray(input3));
    }

    @Test
    public void lengthOfLastWord() {
        String s1 = "Hello World";
        String s2 = "   fly me   to   the moon  ";
        String s3 = "luffy is still joyboy";

        Assertions.assertEquals(5, testable.lengthOfLastWord(s1));
        Assertions.assertEquals(4, testable.lengthOfLastWord(s2));
        Assertions.assertEquals(6, testable.lengthOfLastWord(s3));
    }

    @Test
    public void plusOne() {
        int[] num1 = {1, 2, 3};
        int[] num2 = {4, 3, 2, 1};
        int[] num3 = {0};
        int[] num4 = {9};
        int[] num5 = {9,9};
        int[] num6 = {1,9,9};
        int[] num7 = {2,3,9};

        Assertions.assertArrayEquals(new int[]{1, 2, 4}, testable.plusOne(num1));
        Assertions.assertArrayEquals(new int[]{4, 3, 2, 2}, testable.plusOne(num2));
        Assertions.assertArrayEquals(new int[]{1}, testable.plusOne(num3));
        Assertions.assertArrayEquals(new int[]{1, 0}, testable.plusOne(num4));
        Assertions.assertArrayEquals(new int[]{1, 0, 0}, testable.plusOne(num5));
        Assertions.assertArrayEquals(new int[]{2, 0, 0}, testable.plusOne(num6));
        Assertions.assertArrayEquals(new int[]{2, 4, 0}, testable.plusOne(num7));
    }

    @Test
    public void isPalindrome() {
        int firstNumber = 121;
        int secondNumber = -121;
        int thirdNumber = 10;
        int fourthNumber = 5;

        Assertions.assertTrue(testable.isPalindrome(firstNumber));
        Assertions.assertFalse(testable.isPalindrome(secondNumber));
        Assertions.assertFalse(testable.isPalindrome(thirdNumber));
        Assertions.assertTrue(testable.isPalindrome(fourthNumber));
    }

}

class ClassA {

    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    public int strStr1(String haystack, String needle) {
        if (haystack == null || haystack.length() == 0)
            return 0;

        int m = haystack.length();
        int n = needle.length();

        for (int i = 0; i <m-n+1; i++) {
            if (haystack.substring(i, i+n).equals(needle))
                return i;
        }

        return -1;
    }

    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int last = nums[nums.length - 1];
        if (target > last) return nums.length;

        int j = 0;
        for (int i = 1; i < nums.length; i++) {
            int first = nums[j];
            if (first == target) return j;
            int second = nums[i];
            if (second == target) return i;
            if (target > first && target < second) return i;
            j++;
        }

        return 0;
    }

    public int maxSubArray(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int size = arr.length;
        if (size == 1) return arr[0];

        int maxSoFar = arr[0];
        int maxEndingHere = arr[0];

        for (int i = 1; i < size; i++) {
            maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);

            if (maxSoFar < maxEndingHere) {
                maxSoFar = maxEndingHere;
            }
        }

        return maxSoFar;
    }

    public int lengthOfLastWord(String s) {
        if (s == null || s.isEmpty()) return 0;

        int size = s.length();
        char[] symbols = s.toCharArray();

        int length = 0;
        boolean endingFlag = false;

        for (int i = size - 1; i >= 0; i--) {
            char symbol = symbols[i];
            if (symbol == ' ' && endingFlag) break;
            if (symbol == ' ') {
                continue;
            }
            length++;
            endingFlag = true;
        }

        return length;
    }

    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) return new int[]{1};

        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                break;
            } else {
                digits[i] = 0;
            }
        }

        if (digits[0] == 0) {
            int[] newDigits = new int[digits.length + 1];
            newDigits[0] = 1;
            return newDigits;
        }
        return digits;
    }

    public boolean isPalindrome(int num) {
        if (num < 0) return false;

        int remainder;
        int reversed = 0;
        int original = num;

        while (num % 10 !=0) {
            remainder = num % 10;
            reversed = reversed * 10 + remainder;
            num /= 10;
        }

        return original == reversed;
    }

}
