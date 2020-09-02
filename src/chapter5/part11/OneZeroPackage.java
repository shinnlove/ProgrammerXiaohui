/**
 * eBay.com Inc.
 * Copyright (c) 1995-2020 All Rights Reserved.
 */
package chapter5.part11;

/**
 * 给你一个可装载重量为 W 的背包和 N 个物品，每个物品有重量和价值两个属性。其中第 i 个物品的重量为 wt[i]，价值为 val[i]，现在让你用这个背包装物品，最多能装的价值是多少？
 * 
 * N = 3, W = 4
 * wt = [2, 1, 3]
 * val = [4, 2, 3]
 * 
 * 
 * @author Tony, Zhao
 * @version $Id: OneZeroPackage.java, v 0.1 2020-09-01 8:08 PM Tony, Zhao Exp $$
 */
public class OneZeroPackage {

    /**
     * 给你一个可装载重量为 W 的背包和 N 个物品，每个物品有重量和价值两个属性。其中第 i 个物品的重量为 wt[i]，价值为 val[i]，现在让你用这个背包装物品，最多能装的价值是多少？
     *
     * 状态转移方程式：
     * F(W, N) = max(F(W, N-1), F(W-wt[n], N-1) + val[n])
     * 
     * @param weight        背包可装重量
     * @param wt            物品重量
     * @param val           物品价值
     * @return              背包所装物品最大价值
     */
    public static int maxValue(int weight, int[] wt, int[] val) {
        int[] results = new int[weight + 1];

        // 在一维数组简化空间版本中，i从0或1开始都可以，因为初始化数组不给值就是0
        // 所以在从右边往左边逐个计算给值得时候，初始化数组就相当于原来版本中的第一行
        // i从1从0都可以，j必须大于0
        for (int i = 1; i <= wt.length; i++) {
            for (int j = weight; j > 0; j--) {
                if (j >= wt[i - 1]) {
                    // special warning: here value is follow with outsider circle variable i, not j
                    results[j] = Math.max(results[j], results[j - wt[i - 1]] + val[i - 1]);
                }
            }
        }

        // at last, return weight value
        return results[weight];
    }

    public static void main(String[] args) {
        int W = 4;
        int[] wt = new int[] { 2, 1, 3 };
        int[] val = new int[] { 4, 2, 3 };

        int result = maxValue(W, wt, val);
        System.out.println("Max benefits=" + result);
    }

}