package com.txn.spring;

/**
 * @author yida
 * @Date 2020-12-10 14:36
 * @Description Test1
 */
public class Test1 {


  //最少分得红包数
  private static final double min = 1;
  //最多分得红包数占比
  private static final double percentMax = 0.3;

  public static void allocateMoney(double money, int peopleNum) {
    double minMoney = min;
    double maxMoney = percentMax * money;
    int shareMoney = 0;
    double sum = 0;
    for (int i = 0; i < peopleNum; i++) {
      minMoney = minMoney > money - maxMoney * (peopleNum - i - 1) ? minMoney : (money - maxMoney * (peopleNum - i - 1));
      maxMoney = maxMoney < money - minMoney * (peopleNum - i - 1) ? maxMoney : (money - minMoney * (peopleNum - i - 1));
      shareMoney = (int) Math.floor((maxMoney - minMoney) * Math.random() + minMoney);
      money = money - shareMoney;
      sum += shareMoney;
      System.out.println("第" + (i + 1) + "个人抢到:" + shareMoney + "元");
    }
    System.out.println("要分配的红包总额为:" + sum + "元");
  }

  public static void main(String[] args) {
    allocateMoney(100,10);
  }

}
