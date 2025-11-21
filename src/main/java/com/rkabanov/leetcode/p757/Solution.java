package com.rkabanov.leetcode.p757;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        // 3000 intervals

        // overlap all intervals and count each number frequency
        HashMap<Integer, Integer> histogram = new HashMap<>();
        for (int[] interval : intervals) {
            for (int num = interval[0]; num <= interval[1]; num++) {
                histogram.merge(num, 1, Integer::sum);
            }
        }
        System.out.println("Histogram: " + histogram.toString());

        HashSet<Integer> resultNumbers = new HashSet<>();
        int minResultLength = 0;

        // for each interval, pick 2 most frequent numbers
        for (int[] interval : intervals) {
            int[] bestNumbers = twoMostFrequentNumbers(interval, histogram);
            // 2, 3
            System.out.println("[" + interval[0] + ", " + interval[1] + "] Best numbers: " + bestNumbers[0] + ", " + bestNumbers[1]);
            // add to set, increase by how many new numbers were added
            int sizeBefore = resultNumbers.size();
            resultNumbers.add(bestNumbers[0]);
            resultNumbers.add(bestNumbers[1]);
            minResultLength += resultNumbers.size() - sizeBefore;
        }

        return minResultLength;
    }

    int[] twoMostFrequentNumbers(int[] interval, Map<Integer, Integer> histogram) {
        // best numbers: "1" with score 2, "2" with score 3
        int from = interval[0];
        int to = interval[1];

        // pick first 2 numbers as best and second best, in correct order
        int best = from;
        int secondBest = from + 1;
        // fix initial order
        if (histogram.get(best) < histogram.get(secondBest)) {
            best = from + 1;
            secondBest = from;
        }

        // go over rest of numbers, update best and second best if met
        for (int num = from + 2; num <= to; num++) {
            int score = histogram.get(num);
            if (score > histogram.get(best)) {
                secondBest = best;
                best = num;
            } else if (score > histogram.get(secondBest)) {
                secondBest = num;
            }
        }

        return new int[]{best, secondBest};
    }
}
