package com.ke.lt.buySellStock;

/**
 * 多次买卖，买后一定要卖
 *
 * @author zhangxudong
 * @since 2024/4/2 22:34
 */
public class Solution122 {
	public int maxProfit(int[] prices) {
		int res = 0;
		int pre = Integer.MAX_VALUE;
		for (int price : prices) {
			//只要比之前的大就买入
			if (price > pre) {
				res += price - pre;
			}
			pre = price;
		}
		return res;
	}
}
