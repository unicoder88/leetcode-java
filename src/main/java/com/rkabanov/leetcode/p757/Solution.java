package com.rkabanov.leetcode.p757;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        // 3000 intervals
        // shortest intervals first
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[1] - interval[0]));
        System.out.println("Sorted " + Arrays.deepToString(intervals));

        HashSet<Integer> resultNumbers = new HashSet<>();
        int minResultLength = 0;

        List<int[]> intervalList = new ArrayList<>(List.of(intervals));

        // for each interval, pick 2 most frequent numbers
        while (!intervalList.isEmpty()) {
            int[] interval = intervalList.get(0);
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");

            int intervalNumbersRemainingToPick = 2;

            // try to reuse from result numbers
            List<Integer> reuseNumbers = reuseTwoNumbers(interval, resultNumbers);
            System.out.println("  Reuse: " + reuseNumbers.toString());

            intervalNumbersRemainingToPick -= reuseNumbers.size();

            // find remaining numbers from the best intersection OF REMAINING INTERVALS ONLY, excluding already used
            while (intervalNumbersRemainingToPick > 0) {
                int bestNumber = mostFrequentNumber(interval, intervalList, resultNumbers);
                System.out.println("  Next best: " + bestNumber);
                resultNumbers.add(bestNumber);
                intervalNumbersRemainingToPick--;
                minResultLength++;

                // remove possible intervals that now contain new numbers from result numbers
                intervalList = remainingIntervals(intervalList, resultNumbers);
                System.out.println("Remaining " + Arrays.deepToString(intervals));
            }

            // safeguard
            intervalList.remove(interval);
//            intervalList.remove(0);
        }

        System.out.println("Result set: " + resultNumbers);

        return minResultLength;
    }

    List<int[]> remainingIntervals(List<int[]> intervals, Set<Integer> resultNumbers) {
        if (resultNumbers.size() < 2) {
            // interval can be removed after at least 2 numbers picked
            return intervals;
        }

        return intervals.stream()
                .filter(interval -> {
                    int from = interval[0];
                    int to = interval[1];

                    int matches = 0;
                    for (int num : resultNumbers) {
                        if (num >= from && num <= to) {
                            matches++;
                            if (matches >= 2) {
                                // remove this interval
                                System.out.println("  Removing interval [" + from + ", " + to + "]");
                                return false;
                            }
                        }
                    }

                    return true;
                })
                .collect(Collectors.toList());
    }

    List<Integer> reuseTwoNumbers(int[] interval, Set<Integer> resultNumbers) {
        int from = interval[0];
        int to = interval[1];

        List<Integer> result = new ArrayList<>();
        for (int num = from; num <= to && result.size() < 2; num++) {
            if (resultNumbers.contains(num)) {
                result.add(num);
            }
        }

        return result;
    }

    HashMap<Integer, Integer> calculateHistogram(int[] currentInterval, List<int[]> intervals) {
        // overlap all intervals and count each number frequency
        HashMap<Integer, Integer> result = new HashMap<>();

        for (int num = currentInterval[0]; num <= currentInterval[1]; num++) {
            for (int[] interval : intervals) {
                if (num >= interval[0] && num <= interval[1]) {
                    result.merge(num, 1, Integer::sum);
                }
            }
        }

        return result;
    }

    int mostFrequentNumber(int[] interval, List<int[]> intervals, Set<Integer> resultNumbers) {
        // best numbers: "1" with score 2, "2" with score 3
        int from = interval[0];
        int to = interval[1];

        int best = -1;

        Map<Integer, Integer> histogram = calculateHistogram(interval, intervals);
        System.out.println("  Histogram: " + histogram);

        // go over rest of numbers, update best and second best if met
        for (int num = from; num <= to; num++) {
            if (resultNumbers.contains(num)) {
                continue;
            }

            // take in first number
            if (best == -1) {
                best = num;
                continue;
            }

            if ((int) histogram.get(num) > histogram.get(best)) {
                best = num;
            }
        }

        return best;
    }
}
