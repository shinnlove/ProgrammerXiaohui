/**
 * eBay.com Inc.
 * Copyright (c) 1995-2020 All Rights Reserved.
 */
package chapter4.part5;

import java.util.Arrays;

/**
 * Count sort standard algorithm with extra array.
 *
 * 
 * @author Tony, Zhao
 * @version $Id: CountSortStandard.java, v 0.1 2020-09-04 1:08 PM Tony, Zhao Exp $$
 */
public class CountSortStandard {

    public static void main(String[] args) {
        int[] array = new int[] { 4, 4, 6, 5, 3, 2, 8, 1, 7, 5, 6, 0, 10 };
        int[] result = countSort(array);
        System.out.println(Arrays.toString(result));
    }

    public static int[] countSort(int[] array) {
        int len = array.length;
        int min, max;
        min = max = array[0];
        for (int i = 1; i < len; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        int span = max - min + 1;

        int[] statistics = new int[span];
        for (int i = 0; i < len; i++) {
            statistics[array[i] - min]++;
        }

        int[] sorted = new int[len];
        int count = 0;

        for (int i = 0; i < statistics.length; i++) {
            for (int j = 0; j < statistics[i]; j++) {
                sorted[count++] = i + min;
            }
        }

        return sorted;
    }

}