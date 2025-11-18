package com.rkabanov.leetcode.p717;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolutionTest {
    @Test
    public void testMinLength() {
        var solution = new Solution();
        boolean result = solution.isOneBitCharacter(new int[]{0});
        assertTrue(result);
    }

    @Test
    public void testExample1() {
        var solution = new Solution();
        boolean result = solution.isOneBitCharacter(new int[]{1, 0, 0});
        assertTrue(result);
    }

    @Test
    public void testExample2() {
        var solution = new Solution();
        boolean result = solution.isOneBitCharacter(new int[]{1, 1, 1, 0});
        assertFalse(result);
    }
}
