package com.rkabanov.leetcode.p757;

import java.util.*;

public class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        // 3000 intervals
        // sort by interval start for easy navigation
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        System.out.println("Sorted " + Arrays.deepToString(intervals));

        HashSet<Integer> resultNumbers = new HashSet<>();
        int minResultLength = 0;

        ArrayList<int[]> intervalList = new ArrayList<>(List.of(intervals));

        // for each interval, pick 2 most frequent numbers
        for (int currentIntervalNum = 0; currentIntervalNum < intervalList.size(); currentIntervalNum++) {
            int[] interval = intervalList.get(currentIntervalNum);
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");

            int intervalNumbersRemainingToPick = 2;

            // try to reuse from result numbers
            List<Integer> reuseNumbers = reuseTwoNumbers(interval, resultNumbers);
            System.out.println("  Reuse: " + reuseNumbers.toString());
            resultNumbers.addAll(reuseNumbers);

            intervalNumbersRemainingToPick -= reuseNumbers.size();

            if (intervalNumbersRemainingToPick > 0) {
                // find remaining numbers from the best intersection OF REMAINING INTERVALS ONLY, excluding already used
                ArrayList<Integer> bestNumbers = twoMostFrequentNumbers(
                        interval,
                        intervalList.subList(currentIntervalNum, intervalList.size()),
                        resultNumbers
                );
                System.out.println("  Best numbers: " + bestNumbers.toString());

                // pick remaining numbers from best if available
                while (intervalNumbersRemainingToPick > 0 && !bestNumbers.isEmpty()) {
                    System.out.println("  Adding " + bestNumbers.get(0));
                    resultNumbers.add(bestNumbers.remove(0));
                    intervalNumbersRemainingToPick--;
                    minResultLength++;
                }
            }


        }

        System.out.println("Result set: " + resultNumbers);

        return minResultLength;
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

    HashMap<Integer, Integer> calculateHistogram(List<int[]> intervals) {
        // overlap all intervals and count each number frequency
        HashMap<Integer, Integer> result = new HashMap<>();
        for (int[] interval : intervals) {
            for (int num = interval[0]; num <= interval[1]; num++) {
                result.merge(num, 1, Integer::sum);
            }
        }

        return result;
    }

    ArrayList<Integer> twoMostFrequentNumbers(int[] interval, List<int[]> intervals, Set<Integer> resultNumbers) {
        // best numbers: "1" with score 2, "2" with score 3
        int from = interval[0];
        int to = interval[1];

        int best = -1;
        int secondBest = -1;

        Map<Integer, Integer> histogram = calculateHistogram(intervals);
        System.out.println("  Histogram: " + histogram);

        // go over rest of numbers, update best and second best if met
        for (int num = from; num <= to; num++) {
            if (resultNumbers.contains(num)) {
                continue;
            }

            int score = histogram.get(num);

            // take in first 2 numbers in correct order
            if (best == -1) {
                best = num;
                continue;
            }

            if (secondBest == -1) {
                if (score > histogram.get(best)) {
                    // new number is new best
                    secondBest = best;
                    best = num;
                } else {
                    // new number is second best
                    secondBest = num;
                }
                continue;
            }

            if (score > histogram.get(best)) {
                secondBest = best;
                best = num;
            } else if (score > histogram.get(secondBest)) {
                secondBest = num;
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        if (best != -1) {
            result.add(best);
        }
        if (secondBest != -1) {
            result.add(secondBest);
        }
        return result;
    }
}
