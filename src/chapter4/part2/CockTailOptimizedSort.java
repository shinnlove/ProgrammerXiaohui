/**
 * eBay.com Inc.
 * Copyright (c) 1995-2020 All Rights Reserved.
 */
package chapter4.part2;

import java.util.Arrays;

/**
 * 鸡尾酒排序优化。
 * 
 * 优化点：
 * 1. 没有交换的情况下，无论是左边到右边，还是右边到左边都不需要再去白跑一趟
 * 2. 右边有序数组的索引检测，是从左往右到无序数组边界，本来是<(array.length - 1) - i，现在变成<rightSortedIndex
 * 3. 从右边往左边的时候是从j=rightSortedIndex开始遍历，这样缩减右边开始范围。
 * 
 * @author Tony, Zhao
 * @version $Id: CockTailOptimizedSort.java, v 0.1 2020-09-02 5:46 PM Tony, Zhao Exp $$
 */
public class CockTailOptimizedSort {

    public static void sort(int array[]) {

        // 鸡尾酒排序只要数组的一半长度就行了
        for (int i = 0; i < array.length / 2; i++) {

            boolean itemChanged = false;
            // 默认已排序是最右边最后的一个元素
            int rightSortedIndex = array.length - 1;

            // 从左往右，从有序的最小元素后开始，所以是j=i，一趟下沉一个最大的元素，所以还要减去i
            for (int j = i; j < rightSortedIndex; j++) {

                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    itemChanged = true;
                    rightSortedIndex = j;
                }

            }
            if (!itemChanged) {
                break;
            }

            // 鸡尾酒从右往左排序
            itemChanged = false;
            for (int j = rightSortedIndex; j > i; j--) {

                if (array[j] < array[j - 1]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                    itemChanged = true;
                }

            }
            if (!itemChanged) {
                break;
            }

        }

    }

    public static void main(String[] args) {
        int[] array = new int[] { 2, 3, 4, 5, 6, 7, 8, 1 };
        sort(array);
        System.out.println(Arrays.toString(array));
    }

}