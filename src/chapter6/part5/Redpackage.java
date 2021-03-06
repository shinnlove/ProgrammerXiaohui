package chapter6.part5;

import java.math.BigDecimal;
import java.util.*;

/**
* Created by weimengshu on 2018/4/8.
*/
public class Redpackage {

    /**
     * 拆分红包
     * 
     * 1000/10*2 = 200
     * 200-1 = 199
     * 
     * @param totalAmount  总金额（以分为单位）
     * @param totalPeopleNum  总人数
     */
    public static List<Integer> divideRedPackage(Integer totalAmount, Integer totalPeopleNum){
        List<Integer> amountList = new ArrayList<Integer>();
        Integer restAmount = totalAmount;
        Integer restPeopleNum = totalPeopleNum;
        Random random = new Random();
        for(int i=0; i<totalPeopleNum-1; i++){
            //随机范围：[1，剩余人均金额的两倍)，左闭右开
            // 主要这样的数学期望是一半，还是相对比较均匀的，-1是为了保证至少有1分钱、那random能少取1分上限，随后+1至少分得1分钱
            int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
            restAmount -= amount;
            restPeopleNum --;
            amountList.add(amount);
        }
        amountList.add(restAmount);
        return amountList;
    }

    /**
     * 拆分红包V2，切线段方法
     * 
     * @param totalAmount  总金额（以分为单位）
     * @param totalPeopleNum  总人数
     */
    public static List<Integer> divideRedPackageV2(Integer totalAmount, Integer totalPeopleNum){
        List<Integer> amountList = new ArrayList<Integer>();
        Set<Integer> segments = new HashSet<Integer>();
        Random random = new Random();
        for(int i = 0; i< totalPeopleNum-1; i++){
            int segment =  random.nextInt(totalAmount-2) + 1;
            int delta = random.nextInt(1)==0 ? 1 : -1;
            while(segments.contains(segment) || segment == 0){
                segment = (segment+delta)%totalAmount;
            }
            segments.add(segment);
        }

        List<Integer> segmentList = new ArrayList<Integer>(segments);
        Collections.sort(segmentList);
        for(int i=0; i<segmentList.size(); i++){
            Integer amount;
            if(i==0){
                amount = segmentList.get(0);
            }else {
                amount = segmentList.get(i) - segmentList.get(i-1) ;
            }
            amountList.add(amount);
        }
        amountList.add(totalAmount - segmentList.get(segmentList.size()-1));

        return amountList;
    }

    public static void main(String[] args){
        // 10块钱的红包10个人抢，按分来计算
        List<Integer> amountList = divideRedPackage(1000, 10);
        for(Integer amount : amountList){
            System.out.println("抢到金额：" + new BigDecimal(amount).divide(new BigDecimal(100)));
        }
    }
}
