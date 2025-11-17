package com.rkabanov.leetcode.p1437;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolutionTest {
    @Test
    public void shouldHandleKZero() {
        var solution = new Solution();
        boolean result = solution.kLengthApart(new int[]{1, 1, 1, 1, 1}, 0);
        assertTrue(result);
    }

    @Test
    public void shouldHandleAllZeros() {
        var solution = new Solution();
        // special case, who knew
        boolean result = solution.kLengthApart(new int[]{0, 0, 0}, 2);
        assertTrue(result);
    }

    @Test
    public void shouldHandleEndingZero() {
        var solution = new Solution();
        // 1 stands tight to 1, that's 0 distance, too close
        boolean result = solution.kLengthApart(new int[]{1, 1, 1, 0}, 3);
        assertFalse(result);
    }

    @Test
    public void shouldCase36() {
        var solution = new Solution();
        // no other 1 closer than 1. actually no other 1 at all
        boolean result = solution.kLengthApart(new int[]{1, 0, 0, 0}, 1);
        assertTrue(result);
    }
}
