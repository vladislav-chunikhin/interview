package com.akvelon.interview;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class SundayTest {

    private SundaySolution testable = new SundaySolution();

    @Test
    public void climbStairs() {
        Assertions.assertEquals(2, testable.climbStairs(2));
        Assertions.assertEquals(3, testable.climbStairs(3));
        Assertions.assertEquals(13, testable.climbStairs(6));
    }

    @Test
    public void isPalindrome() {
        Assertions.assertTrue(testable.isPalindrome("A man, a plan, a canal: Panama"));
        Assertions.assertFalse(testable.isPalindrome("race a car"));
        Assertions.assertTrue(testable.isPalindrome(" "));
        Assertions.assertFalse(testable.isPalindrome("0P"));
        Assertions.assertTrue(testable.isPalindrome("P"));
    }

    @Test
    public void isAnagram() {
        Assertions.assertTrue(testable.isAnagram("anagram", "nagaram"));
        Assertions.assertFalse(testable.isAnagram("rat", "car"));
    }

    @Test
    public void reverseVowelsOfString() {
        Assertions.assertEquals("holle", testable.reverseVowelsOfString("hello"));
        Assertions.assertEquals("leotcede", testable.reverseVowelsOfString("leetcode"));
    }

    @Test
    public void longestPalindrome() {
        Assertions.assertEquals(7, testable.longestPalindrome("abccccdd"));
        Assertions.assertEquals(3, testable.longestPalindrome("ccc"));
        Assertions.assertEquals(9, testable.longestPalindrome("ababababa"));
    }

    @Test
    public void reversePrefix() {
        Assertions.assertEquals("dcbaefd", testable.reversePrefix("abcdefd", 'd'));
        Assertions.assertEquals("szwuktxcjfpamlonbgyieqdvhr", testable.reversePrefix("rzwuktxcjfpamlonbgyieqdvhs", 's'));
        Assertions.assertEquals("abcd", testable.reversePrefix("abcd", 'z'));
    }

    @Test
    public void missingNumber() {
        Assertions.assertEquals(2, testable.missingNumber(new int[]{3,0,1}));
        Assertions.assertEquals(2, testable.missingNumber(new int[]{0,1}));
        Assertions.assertEquals(8, testable.missingNumber(new int[]{9,6,4,2,3,5,7,0,1}));
        Assertions.assertEquals(1, testable.missingNumber(new int[]{0}));
    }
}

class SundaySolution {

    private final Map<Integer, Integer> memo = new HashMap<>();

    public int climbStairs(int n) {
        if (n<2) {
            return 1;
        } else if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int result = climbStairs(n-1) + climbStairs(n-2);
        memo.put(n, result);
        return result;
    }

    public boolean isPalindrome(final String s) {
        if (s == null || s.isEmpty()) return false;
        if (s.isBlank()) return true;

        final String cleanS = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();

        int size = cleanS.length() / 2;

        for (int i = 0; i < size; i++) {
            char startChar = cleanS.charAt(i);
            char endChar = cleanS.charAt(cleanS.length() - 1 - i);
            if (startChar != endChar) return false;
        }
        return true;
    }

    public boolean isAnagram(String s, String t) {
        if (s == null || t == null) return false;
        if (s.equals(t)) return true;

        char[] sAsChars = s.toCharArray();
        char[] tAsChars = t.toCharArray();

        Arrays.sort(sAsChars);
        Arrays.sort(tAsChars);

        return Arrays.equals(sAsChars, tAsChars);
    }

    public void reverseString(char[] s) {
        if (s.length == 0 || s.length == 1) return;

        for (int i = 0; i < s.length / 2; i++) {
            char startChar = s[i];
            char endChar = s[s.length - 1 - i];
            s[i] = endChar;
            s[s.length - 1 - i] = startChar;
        }
    }

    public String reverseVowelsOfString(String s) {
        if (s == null || s.isBlank()) return s;

        int i = 0;
        int j = s.length() - 1;

        char[] result = s.toCharArray();

        while(i < j) {
            if (!isVowelChar(result[i])) {
                i++;
                continue;
            }

            if (!isVowelChar(result[j])) {
                j--;
                continue;
            }

            char ch = result[i];
            result[i] = result[j];
            result[j] = ch;

            i++;
            j--;
        }

        return String.copyValueOf(result);
    }

    @SuppressWarnings("all")
    private boolean isVowelChar(char ch) {
        return ch == 'a' || ch =='A'
                || ch == 'e' || ch == 'E'
                || ch == 'i' || ch == 'I'
                || ch == 'o' || ch == 'O'
                || ch == 'u' || ch == 'U';
    }

    public int longestPalindrome(String s) {
        if (s == null || s.isBlank()) return 0;
        if (s.length() == 1) return 1;

        HashSet<Character> hs = new HashSet<>();

        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (hs.contains(ch)) {
                hs.remove(ch);
                count++;
            } else {
                hs.add(ch);
            }
        }

        return hs.isEmpty() ? count * 2 : count * 2 + 1;
    }

    public String reversePrefix(String word, char ch) {
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for( char c : word.toCharArray()){
            sb.append(c);
            if( c == ch && !flag){
                sb.reverse();
                flag = true;
            }
        }
        return sb.toString();
    }

    public Collection<String> fizzBuzz(int n) {
        if	(n <= 0) throw new RuntimeException("Number is wrong");

        Collection<String> result = new LinkedList<>();

        for(int i = 1; i <= n; i++) {
            int remainder1 = i % 3;
            int remainder2 = i % 5;

            if(remainder1 != 0 && remainder2 != 0) {
                result.add(String.valueOf(i));
                continue;
            }

            if(remainder1 == 0 && remainder2 == 0) {
                result.add("FizzBuzz");
                continue;
            }

            if(remainder1 == 0) {
                result.add("Fizz");
                continue;
            }

            result.add("Buzz");
        }

        return result;
    }

    public int missingNumber(int[] nums) {
        int sumAfter = (nums.length * (nums.length + 1)) / 2;
        int sumBefore = 0;
        for (int num : nums) sumBefore +=num;
        return sumAfter - sumBefore;
    }

}
