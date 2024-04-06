package com.ke.leetcode.buySellStock;

import java.util.stream.IntStream;

/**
 * K次买卖
 *
 * @author zhangxudong
 * @since 2024/4/2 22:34
 */
public class Solution188 {
	public int maxProfit(int k, int[] prices) {
		int len = prices.length;
		if (len<2){
			return 0;
		}
		//len天k次卖出操作，中间为持有或者没有持有股票
		int[][][] dp = new int[len][2][k+1];

		//初始值
		dp[0][1][0] = -prices[0];
		dp[0][0][0] = 0;

		int inf = Integer.MIN_VALUE/2;


		//之所以要设置这个数字的原因 是因为下面递归要用到dp[0][1][j - 1],不设置就是0，因为第一天可能是负数，这样不存在的数就参与比较了
		for (int x = 1; x <= k; x++) {
			// 第一天不可能交易1次及以上
			dp[0][1][x] = inf;
		}




		for (int i = 1; i < len; i++) {
			for (int  j= 0; j <= k; j++) {
				dp[i][1][j] = Math.max(dp[i - 1][1][j], dp[i - 1][0][j] - prices[i]);
				dp[i][0][j] = Math.max(dp[i - 1][0][j], j == 0 ? inf : dp[i - 1][1][j - 1] + prices[i]);

			}
		}
		// 输出结果
		return IntStream.of(dp[len - 1][0]).max().getAsInt();
	}

	public static void main(String[] args) {
		int[] nums = new int[]{2,4,1};
		System.out.println(new Solution188().maxProfit(2, nums));
	}

	//后一天只与前一天有关，所以可以去掉一维
	public int maxProfit2(int k, int[] prices) {
		int len = prices.length;
		if (len<2){
			return 0;
		}
		//len天k次卖出操作，中间为持有或者没有持有股票
		int[][] dp = new int[2][k+1];

		//初始值
		dp[1][0] = -prices[0];
		dp[0][0] = 0;

		int inf = Integer.MIN_VALUE/2;


		//之所以要设置这个数字的原因 是因为下面递归要用到dp[0][1][j - 1],不设置就是0，因为第一天可能是负数，这样不存在的数就参与比较了
		for (int x = 1; x <= k; x++) {
			// 第一天不可能交易1次及以上
			dp[1][x] = inf;
		}




		for (int i = 1; i < len; i++) {
			for (int  j= 0; j <= k; j++) {
				dp[1][j] = Math.max(dp[1][j], dp[0][j] - prices[i]);
				dp[0][j] = Math.max(dp[0][j], j == 0 ? inf : dp[1][j - 1] + prices[i]);

			}
		}
		// 输出结果
		return IntStream.of(dp[0]).max().getAsInt();
	}



}
