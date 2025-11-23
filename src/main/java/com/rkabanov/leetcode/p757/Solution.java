package com.rkabanov.leetcode.p757;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        // 3000 intervals
        // shortest intervals first
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        System.out.println("Sorted " + Arrays.deepToString(intervals));
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[1] - interval[0]));
        System.out.println("Sorted " + Arrays.deepToString(intervals));

        HashSet<Integer> resultNumbers = new HashSet<>();

        List<int[]> intervalList = new ArrayList<>(List.of(intervals));

        // pick most frequent unused number, remove intervals with 2+ numbers
        while (!intervalList.isEmpty()) {
            int bestNumber = mostFrequentNumber(intervalList, resultNumbers);
            resultNumbers.add(bestNumber);

            // remove possible intervals that now contain new numbers from result numbers
            intervalList = remainingIntervals(intervalList, resultNumbers);
            System.out.println("Remaining " + Arrays.deepToString(intervals));
        }

        System.out.println("Result set: " + resultNumbers);

        return resultNumbers.size();
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

    HashMap<Integer, Integer> calculateHistogram(List<int[]> intervals, Set<Integer> resultNumbers) {
        // overlap all intervals and count each number frequency
        HashMap<Integer, Integer> result = new HashMap<>();

        for (int[] interval : intervals) {
            for (int num = interval[0]; num <= interval[1]; num++) {
                if (resultNumbers.contains(num)) {
                    continue;
                }
                result.merge(num, 1, Integer::sum);
            }

        }

        return result;
    }

    int mostFrequentNumber(List<int[]> intervals, Set<Integer> resultNumbers) {
        Map<Integer, Integer> histogram = calculateHistogram(intervals, resultNumbers);
        System.out.println("  Histogram: " + histogram);

        // key with max value
        Map.Entry<Integer, Integer> bestEntry = Collections.max(histogram.entrySet(), Map.Entry.comparingByValue());
        System.out.println("  Best number " + bestEntry.getKey() + " with frequency " + bestEntry.getValue() );
        return bestEntry.getKey();
    }
}
