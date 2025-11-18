package com.rkabanov.leetcode.p717;

/**
 * <a href="https://leetcode.com/problems/1-bit-and-2-bit-characters/description/">1-bit and 2-bit Characters</a>
 */
public class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        // 0 = 0
        // 1,0,0 = 10, 0
        // 1,1,1,0 = 11 10

        if (bits.length == 1) {
            return true;
        }

        int pos = 0;
        boolean lastOccurrenceOneBit = false;

        while (pos < bits.length) {
            int c1 = bits[pos];
            if (0 == c1) {
                // this is "first character", go on
                lastOccurrenceOneBit = true;
                pos++;
            } else {
                // this is 1, next char 1 or 0, regardless this is two-bit special character, jump over
                lastOccurrenceOneBit = false;
                pos += 2;
            }
        }

        return lastOccurrenceOneBit;
    }
}
