package com.rkabanov.leetcode.p1437;

class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        if (k == 0) {
            // special case, who knew
            return true;
        }

        // fast-forward to first 1
        int pos = 0;
        while (0 == nums[pos]) {
            pos++;

            if (pos >= nums.length) {
                // went through whole list but no single 1 encountered. they think it's good
                return true;
            }
        }

        // current pos is known to hold 1, so let's move on to next
        pos++;
        if (pos >= nums.length) {
            // list is over, and no other 1 encountered - we're good
            return true;
        }

        int distance = 0;

        // now we're looking at next number after 1
        for (; pos < nums.length; pos++) {
            if (1 == nums[pos]) {
                // meet next 1 - measure distance
                if (distance < k) {
                    return false;
                }

                // good, long enough, but now let's start count over
                distance = 0;
            } else {
                distance++;
            }
        }

        return true;
    }
}
