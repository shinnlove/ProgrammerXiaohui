package chapter4.part5;

/**
 * Created by weimengshu on 2018/7/13.
 */
import java.util.Arrays;

public class CountSort {

    public static int[] countSort(int[] array) {
        //1.得到数列的最大值
        int max = array[0];
        for(int i=1; i<array.length; i++){
            if(array[i] > max){
                max = array[i];
            }
        }
        //2.根据数列最大值确定统计数组的长度
        int[] countArray = new int[max+1];
        //3.遍历数列，填充统计数组
        for(int i=0; i<array.length; i++){
            countArray[array[i]]++;
        }
        //4.遍历统计数组，输出结果
        int index = 0;
        int[] sortedArray = new int[array.length];
        for(int i=0; i<countArray.length; i++){
            for(int j=0; j<countArray[i]; j++){
                sortedArray[index++] = i;
            }
        }
        return sortedArray;
    }

    public static int[] countSortV2(int[] array) {
        //1.得到数列的最大值和最小值，并算出差值d
        int max = array[0];
        int min = array[0];
        for(int i=1; i<array.length; i++) {
            if(array[i] > max) {
                max = array[i];
            }
            if(array[i] < min) {
                min = array[i];
            }
        }
        int d = max - min;
        //2.创建统计数组并统计对应元素个数
        int[] countArray = new int[d+1];
        for(int i=0; i<array.length; i++) {
            countArray[array[i]-min]++;
        }
        //3.统计数组做变形，后面的元素等于前面的元素之和
        for(int i=1;i<countArray.length;i++) {
            countArray[i] += countArray[i-1];
        }
        //4.倒序遍历原始数列，从统计数组找到正确位置，输出到结果数组
        int[] sortedArray = new int[array.length];
        for(int i=array.length-1;i>=0;i--) {
            sortedArray[countArray[array[i]-min]-1]=array[i];
            countArray[array[i]-min]--;
        }
        return sortedArray;
    }

    public static int[] countSortV3(int[] array) {
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

    public static void main(String[] args) {
        int[] array = new int[] {4,4,6,5,3,2,8,1,7,5,6,0,10};
        int[] sortedArray = countSort(array);
        int[] sortedArray1 = countSortV3(array);
        System.out.println(Arrays.toString(sortedArray));
        System.out.println(Arrays.toString(sortedArray1));

        int[] array2 = new int[] { 95, 94, 91, 98, 99, 90, 99, 93, 91, 92 };
        int[] sortedArray3 = countSort(array2);
        int[] sortedArray4 = countSortV3(array2);
        System.out.println(Arrays.toString(sortedArray3));
        System.out.println(Arrays.toString(sortedArray4));

        int[] array3 = new int[] { 95, 94, 91, 98, 99, 90, 99, 93, 91, 92 };
        int[] sortedArray5 = countSort(array3);
        int[] sortedArray6 = countSortV3(array3);
        System.out.println(Arrays.toString(sortedArray5));
        System.out.println(Arrays.toString(sortedArray6));
    }
}
