package com.ke.leetcode.buySellStock;

/**
 * 只买一次，只卖一次
 *
 * @author zhangxudong
 * @since 2024/4/2 22:34
 */
public class Solution121 {
	public int maxProfit(int[] prices) {
		if (prices.length<=1){
			return 0;
		}
		int res = 0;
		int min = prices[0];
		for(int i=1;i<prices.length;i++){
			res = Math.max(prices[i]-min,res);
			min = Math.min(prices[i],min);
		}
		return res;
	}
}
