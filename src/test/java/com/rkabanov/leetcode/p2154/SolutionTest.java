package com.rkabanov.leetcode.p2154;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
    @Test
    public void testExample1() {
        var solution = new Solution();
        int result = solution.findFinalValue(new int[]{5, 3, 6, 1, 12}, 3);
        assertEquals(24, result);
    }

    @Test
    public void testExample2() {
        var solution = new Solution();
        int result = solution.findFinalValue(new int[]{2, 7, 9}, 4);
        assertEquals(4, result);
    }
}
