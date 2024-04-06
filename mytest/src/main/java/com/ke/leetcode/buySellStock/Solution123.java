package com.ke.leetcode.buySellStock;

/**
 * 两次买卖
 *
 * @author zhangxudong
 * @since 2024/4/2 22:34
 */
public class Solution123 {
	//也不能算是标准的动态规划
	public int maxProfit(int[] prices) {
		//第一次买入的最大利润
		int f1 = -prices[0];
		//第一次卖出的最大利润
		int f2 = 0;
		//第二次买入的最大利润，是在第一次卖出的基础上
		int f3 = -prices[0];
		//第二次卖出的最大利润
		int f4 = 0;
		for(int i=1;i<prices.length;i++){
			f1 = Math.max(f1,-prices[i]);
			f2 = Math.max(f2,f2+prices[i]);
			f3 = Math.max(f3,f2-prices[i]);
			f4 = Math.max(f4,prices[i]+f3);
		}
		return f4;
	}
}
