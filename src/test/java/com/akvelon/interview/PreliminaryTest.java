package com.akvelon.interview;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PreliminaryTest {

    private final PreliminarySolution testable = new PreliminarySolution();

    @Test
    public void twoSum() {
        int[] nums = {1, 2, 5, 7, 11, -3};
        int target = 9;
        int[] expectedIndices = {1, 3};
        Assertions.assertArrayEquals(expectedIndices, testable.twoSum(nums, target));
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> testable.twoSum(null, target),
                "Nums should have elements"
        );
    }

    @Test
    public void palindromeNumber() {
        int firstNumber = 121;
        int secondNumber = -121;
        int thirdNumber = 10;
        int fourthNumber = 5;

        Assertions.assertTrue(testable.isPalindrome(firstNumber));
        Assertions.assertFalse(testable.isPalindrome(secondNumber));
        Assertions.assertFalse(testable.isPalindrome(thirdNumber));
        Assertions.assertTrue(testable.isPalindrome(fourthNumber));
    }

    @Test
    public void longestCommonPrefix() {
        String[] input1 = {"gomwgggwwtwqtwqtq","gomora", "gomishka", "gomisilia"};
        String[] input2 = {"gomora", "goishka", "travishka"};
        String[] input3 = {"cat", "dog", "house"};

        Assertions.assertEquals("gom", testable.longestCommonPrefix(input1));
        Assertions.assertEquals("", testable.longestCommonPrefix(input2));
        Assertions.assertEquals("", testable.longestCommonPrefix(input3));
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

        Assertions.assertFalse(testable.validateParenthesis(target));
        Assertions.assertFalse(testable.validateParenthesis(target0));
        Assertions.assertTrue(testable.validateParenthesis(target1));
        Assertions.assertFalse(testable.validateParenthesis(target2));
        Assertions.assertFalse(testable.validateParenthesis(target3));
        Assertions.assertFalse(testable.validateParenthesis(target4));
        Assertions.assertTrue(testable.validateParenthesis(target5));
    }

    @Test
    public void removeDuplicatesFromSortedArray() {
        int[] input = {1, 1, 2, 2, 2, 2, 3, 4 ,5 ,5};
        int[] expectedResult = {1, 2, 3, 4, 5};

        int result = testable.removeDuplicatesFromSortedArray(input);
        Assertions.assertEquals(5, result);

        for (int i = 0; i < result; i++) {
            Assertions.assertEquals(expectedResult[i], input[i]);
        }
    }

    @Test
    public void removeElement() {
        int[] nums = {1, 2, 77, 2, 0, -4, 67, 2, 5, 6, 2};
        int removedNum = 2;
        int expectedResult = 7;
        Assertions.assertEquals(expectedResult, testable.removeElement(nums, removedNum));
    }

    @Test
    public void addStrings() {
        Assertions.assertEquals("134", testable.addStrings("11","123"));
    }

    @Test
    public void singleNumber() {
        Assertions.assertEquals(1, testable.singleNumber(new int[] {2,2,1}));
        Assertions.assertEquals(4, testable.singleNumber(new int[] {4,1,2,1,2}));
        Assertions.assertEquals(1, testable.singleNumber(new int[] {1}));
    }
}


class PreliminarySolution {

    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) throw new IllegalArgumentException("Nums should have elements");

        final Map<Integer, Integer> store = new HashMap<>(nums.length);

        for (int i = 0; i < nums.length; i++) {
            store.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int element = nums[i];
            int complement = target - element;

            if (store.containsKey(complement) && store.get(complement) != i) {
                return new int[]{i, store.get(complement)};
            }
        }

        return null;
    }

    public boolean isPalindrome(int number) {
        if (number < 0) return false;

        int reversed = 0;
        int original = number;

        while(number != 0) {
            reversed = reversed * 10 + number % 10;
            number /= 10;
        }

        return original == reversed;
    }

    public String longestCommonPrefix(String[] str) {
        if (str == null || str.length == 0) return "";

        Arrays.sort(str); // N * Log N

        int end = Math.min(str[0].length(), str[str.length - 1].length());
        int i = 0;

        while (i<end && str[0].charAt(i) == str[str.length - 1].charAt(i))
            i++;

        return str[0].substring(0, i);
    }

    public boolean validateParenthesis(String target) {
        if  (target == null || target.isBlank() || target.length() == 1) return false;

        final ArrayDeque<Character> store = new ArrayDeque<>();

        // complexity = O(N)
        for (char a: target.toCharArray()) {
            if (a == '(' || a == '[' || a == '{')
                store.push(a);

            if (store.isEmpty()) return false;

            switch (a) {
                case ')':
                    Character ch = store.poll();
                    if (ch == '[' || ch == '{') return false;
                    break;
                case ']':
                    Character ch1 = store.poll();
                    if (ch1 == '(' || ch1 == '{') return false;
                    break;
                case '}':
                    Character ch2 = store.poll();
                    if (ch2 == '[' || ch2 == '(') return false;
                    break;
            }
        }

        return store.isEmpty();
    }

    public int removeDuplicatesFromSortedArray(int[] input) {
        if (input == null || input.length == 0) return 0;

        int iterator = 0;

        // O(N)
        for (int i = 1; i < input.length; i++) {
            int n1 = input[iterator];
            int n2 = input[i];

            if (n1 != n2) {
                iterator++;
                input[iterator] = input[i];
            }
        }

        return iterator + 1;
    }

    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;

        int i = 0;

        // complexity = O(N)
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }

        return i;
    }

    public String addStrings(String num1, String num2) {
        int s1 = num1.length(), s2 = num2.length();
        int m = s1 - 1, n = s2 - 1, carry = 0;
        StringBuilder sb = new StringBuilder();
        while (m >= 0 || n >= 0) {
            int a = m >= 0 ? num1.charAt(m--) - '0' : 0;
            int b = n >= 0 ? num2.charAt(n--) - '0' : 0;
            int sum = a + b + carry;
            carry = sum > 9 ? 1 : 0;
            sb.append(sum % 10);
        }
        if (carry == 1)
            sb.append(1);
        return sb.reverse().toString();
    }

    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) throw new RuntimeException("Nums must be set");
        if (nums.length == 1) return nums[0];

        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }


}
