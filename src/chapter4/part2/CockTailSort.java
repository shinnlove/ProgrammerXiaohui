package chapter4.part2;

import java.util.Arrays;

public class CockTailSort {

    public static void sort(int array[])
    {
        int tmp  = 0;
        // 鸡尾酒排序为什么最外层循环时数组的一半？因为来回有2次，假设10个元素，一半是5，一趟来回2次
        for(int i=0; i<array.length/2; i++)
        {
            //有序标记，每一轮的初始是true
            boolean isSorted = true;
            //奇数轮，从左向右比较和交换
            // 这里很重要，一开始这个j=i的时候i是0，左边的第一个元素还不是最小的
            // 当第一轮鸡尾酒排序偶数轮停止后，第一个元素一定是最小的那个元素
            // 因此第二趟j=i就等于从i=1开始了，默认i=0这个元素一定是最小的了
            for(int j=i; j<array.length-i-1; j++)
            {
                if(array[j] > array[j+1])
                {
                    tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    //有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                }
            }
            if(isSorted){
                break;
            }

            //偶数轮之前，重新标记为true
            isSorted = true;
            //偶数轮，从右向左比较和交换
            // 偶数轮是一定从最后一个元素开始，到左边最小元素结束
            // 当然这里是有优化空间的，每一趟鸡尾酒排序从左往右是会有最后一个交换位置的，j可以从这个位置开始，并不一定非要从最右边元素开始
            for(int j=array.length-i-1; j>i; j--)
            {
                if(array[j] < array[j-1])
                {
                    tmp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = tmp;
                    //有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                }
            }
            if(isSorted){
                break;
            }
        }
    }

    public static void main(String[] args){
        int[] array = new int[]{2,3,4,5,6,7,8,1};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
