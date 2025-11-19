package com.rkabanov.leetcode.p2154;

import java.util.HashSet;

/**
 * <a href="https://leetcode.com/problems/keep-multiplying-found-values-by-two/description/">2154. Keep Multiplying Found Values by Two</a>
 */
public class Solution {
    public int findFinalValue(int[] nums, int original) {
        // unsorted (1,1000) numbers [5, 3, 6, 1, 12]
        HashSet<Integer> set = new HashSet<>(nums.length);
        for (int num : nums) {
            set.add(num);
        }

        // given 3, search 3, 6, 12, 24, 48, ...
        int candidate = original;

        while (set.contains(candidate)) {
            candidate *= 2;
        }

        return candidate;
    }
}
