package com.rkabanov.leetcode.p757;

import java.util.Comparator;
import java.util.Arrays;

public class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        // 3000 intervals

        // sort by interval start for easy navigation
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        System.out.println("Sorted " + Arrays.deepToString(intervals));

        // first interval definitely adds _some_ 2 new numbers
        int result = 2;

        // N^2:
        // - for each interval, check all other intervals for 2 or 1 intersection:
        for (int currentIntervalNum = 1; currentIntervalNum < intervals.length; currentIntervalNum++) {
            int[] x = intervals[currentIntervalNum];
            int bestIntersects = 0;
            for (int otherIntervalNum = 0; otherIntervalNum < intervals.length; otherIntervalNum++) {
                if (currentIntervalNum == otherIntervalNum) {
                    continue;
                }

                // - 2 intersection found - add 0. remove that other interval from the list?
                // - best 1 intersection - add 1 new
                // - nothing found - add 2 new
                int[] y = intervals[otherIntervalNum];
                int intersects = totalIntersects(x, y);
                intersects = Math.min(intersects, 2);

                if (intersects > bestIntersects) {
                    bestIntersects = intersects;
                }
            }

            result += 2 - bestIntersects;
        }

        return result;
    }

    private int totalIntersects(int[] a, int[] b) {
        // [1, 4] vs [3, 4] = 2
        // [9, 10] vs [20, 24] = 0
        int commonFrom = Math.max(a[0], b[0]);
        int commonTo = Math.min(a[1], b[1]);

        return Math.max(0, commonTo - commonFrom + 1);
    }
}
