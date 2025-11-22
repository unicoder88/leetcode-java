package com.rkabanov.leetcode.p757;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SolutionTest {
    @Test
    public void testExample1() {
        var solution = new Solution();
        int result = solution.intersectionSizeTwo(new int[][]{
                {1, 3},
                {3, 7},
                {8, 9},
        });
        assertEquals(5, result);
    }

    @Test
    public void testExample2() {
        var solution = new Solution();
        int result = solution.intersectionSizeTwo(new int[][]{
                {1, 3},
                {1, 4},
                {2, 5},
                {3, 5},
        });
        assertEquals(3, result);
    }

    @Test
    public void testExample3() {
        var solution = new Solution();
        int result = solution.intersectionSizeTwo(new int[][]{
                {1, 2},
                {2, 3},
                {2, 4},
                {4, 5},
        });
        assertEquals(5, result);
    }

    @Test
    public void testSolution4() {
        var solution = new Solution();
        int result = solution.intersectionSizeTwo(new int[][]{
                {6, 21},
                {1, 15},
                {15, 20},
                {10, 21},
                {0, 7},
        });
        assertEquals(4, result);
    }

    @Test
    public void testSolution5() {
        var solution = new Solution();
        int result = solution.intersectionSizeTwo(new int[][]{
                {0, 10},
                {0, 2},
                {2, 10},
                {0, 6},
                {0, 5},
                {4, 8},
                {0, 3},
                {6, 8},
                {1, 10},
                {0, 1},
        });
        assertEquals(4, result);
    }

    @Test
    public void testSolution6() {
        var solution = new Solution();
        int result = solution.intersectionSizeTwo(new int[][]{
                {2, 15},
                {9, 17},
                {0, 6},
                {17, 25},
                {0, 25},
        });
        assertEquals(5, result);
    }
}
