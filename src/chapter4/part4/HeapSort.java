package chapter4.part4;

/**
 * Created by weimengshu on 2018/7/13.
 */
import java.util.Arrays;

public class HeapSort {

    /**
     * 大堆顶、小数下沉的调整。
     * 
     * 总计15个节点，倒数第二层Level = (N - 2) / 2 = (15 - 2) / 2 = 13/2 = 6，
     * 所以是第七个元素开始，也是倒数第二层的末置位元素。
     * 
     *                          1
     *                  2             3
     *              4     5      6      7
     *            8  9  10 11  12 13  14 15
     *          
     *   如果parentIndex = 1，也就是数组的第二个数
     *   childIndex = 2 * parentIndex +1 = 2 * 1 + 1 = 3，也就是数组的第四个数
     *   刚好是左孩子，如果childIndex+1<length，代表有右孩子，
     *   如果右孩子大于左孩子、则取左子树和右子树中大的值与parent交换；
     * 
     * @param array     待调整的堆
     * @param parentIndex    要下沉的父节点
     * @param length    堆的有效大小
     */
    public static void downAdjust(int[] array, int parentIndex, int length) {
        // temp保存父节点值，用于最后的赋值
        int temp = array[parentIndex];
        // 定位顺序数组中左孩子的下标 2 * parent + 1
        int childIndex = 2 * parentIndex + 1;

        // while循环左孩子还存在、不是叶子节点、一直持续构建大堆顶
        while (childIndex < length) {
            // 如果有右孩子，且右孩子大于左孩子的值，则定位到右孩子（大堆顶选大的交换上来）
            if (childIndex + 1 < length && array[childIndex + 1] > array[childIndex]) {
                childIndex++;
            }
            // 如果父节点大于等于任何一个孩子的值，直接跳出、不需要交换
            if (temp >= array[childIndex])
                break;

            // 如果父节点值小于子节点、则需要真正交换，单向赋值即可

            // step1: 孩子指针值给到parent指针值
            array[parentIndex] = array[childIndex];
            // step2: 孩子指针给到parent指针
            parentIndex = childIndex;
            // step3: 去寻找孩子指针的左孩子指针
            childIndex = 2 * childIndex + 1;
        }

        // 最终把根节点的值放到了最后、没有交换就是自己、如果有交换就是被换出去、起初相对比叶子节点都要小的一个值
        array[parentIndex] = temp;
    }

    /**
     * 堆排序(升序)：
     * 用最大堆构建、每次堆顶的元素放到最后，所以最后是升序1->10；
     * 用最小堆构建、每次堆顶的元素放到最后，所以最后是降序10->1；
     * 
     * 每次调整堆的时间大致近似于O(n)，一共N个元素、需要调整Log2N次，因此时间复杂度Nlog2(N)
     * 
     * @param array     待调整的堆
     */
    public static void heapSort(int[] array) {
        // 1.把无序数组构建成最大堆。
        // 第一次初始化的时候从倒数最后第二层末尾节点开始即可，默认元素是满二叉树 (N - 2) / 2
        for (int i = (array.length-2)/2; i >= 0; i--) {
            downAdjust(array, i, array.length);
        }
        System.out.println(Arrays.toString(array));
        // 2.循环交换集合尾部元素到堆顶，并调节堆产生新的堆顶。

        // 每次都会有最大的元素被交换到最后一个位置、而后减少一个元素再进行堆调整
        for (int i = array.length - 1; i > 0; i--) {
            // 最后一个元素和第一元素进行交换、交换后默认最后一个元素已经排序
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            // 下沉调整最大堆
            downAdjust(array, 0, i);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1,3,2,6,5,7,8,9,10,0};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
