package chapter4.part2;

import java.util.Arrays;

/**
 * 优化过的BubbleSort。
 */
public class BubbleSort {

    public static void sort(int array[])
    {
        int tmp  = 0;
        //记录最后一次交换的位置
        // 这个lastExchangeIndex从第一趟开始、如果频繁发生交换，也一定是最右边更换的元素，因此能保证右边全部是有序的
        int lastExchangeIndex = 0;

        //无序数列的边界，每次比较只需要比到这里为止，一开始是最后一个元素
        int sortBorder = array.length - 1;
        for(int i = 0; i < array.length; i++)
        {
            //有序标记，每一轮的初始是true
            boolean isSorted = true;

            // 从左往右到无序数组边界
            for(int j = 0; j < sortBorder; j++)
            {
                if(array[j] > array[j+1])
                {
                    tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    //有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                    //把无序数列的边界更新为最后一次交换元素的位置
                    lastExchangeIndex = j;
                }
            }
            sortBorder = lastExchangeIndex;
            if(isSorted){
                break;
            }
        }
    }

    public static void main(String[] args){
        int[] array = new int[]{3,4,2,1,5,6,7,8};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
