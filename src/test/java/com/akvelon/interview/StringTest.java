package com.akvelon.interview;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * You are given two strings s1 and s2 of equal length. A string swap is an operation where you choose two indices in a string (not necessarily different) and swap the characters at these indices.

 Return true if it is possible to make both strings equal by performing at most one string swap on exactly one of the strings. Otherwise, return false.


 Example 1:

 Input: s1 = "bank", s2 = "kanb"
 Output: true
 Explanation: For example, swap the first character with the last character of s2 to make "bank".
 Example 2:

 Input: s1 = "attack", s2 = "defend"
 Output: false
 Explanation: It is impossible to make them equal with one string swap.
 Example 3:

 Input: s1 = "kelb", s2 = "kelb"
 Output: true
 Explanation: The two strings are already equal, so no string swap operation is required.
 Example 4:

 Input: s1 = "abcd", s2 = "dcba"
 Output: false

 **/
public class StringTest {

    private StringSolution testable = new StringSolution();

    @Test
    public void areAlmostEquals() {
        Assertions.assertTrue(testable.areAlmostEquals("bank", "kanb"));
        Assertions.assertFalse(testable.areAlmostEquals("attack", "defend"));
        Assertions.assertTrue(testable.areAlmostEquals("kelb", "kelb"));
        Assertions.assertFalse(testable.areAlmostEquals("abcd", "dcba"));
        Assertions.assertFalse(testable.areAlmostEquals("caa", "aaz"));
    }

}

class StringSolution {

    public boolean areAlmostEquals(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        if (s1.equals(s2)) return true;
        if (s1.length() != s2.length()) return false;

        int numberOfSwaps = 0;
        final List<Integer> indices = new ArrayList<>();

        for (int i = 0; i < s1.length(); i++) {
            char ch1 = s1.charAt(i);
            char ch2 = s2.charAt(i);

            if (ch1 != ch2) {
                numberOfSwaps++;
                indices.add(i);
            }

            if (numberOfSwaps > 2) return false;
        }

        return (numberOfSwaps == 0 || numberOfSwaps == 2)
                && s1.charAt(indices.get(0)) == s2.charAt(indices.get(1))
                && s1.charAt(indices.get(1)) == s2.charAt(indices.get(0));

    }

}

