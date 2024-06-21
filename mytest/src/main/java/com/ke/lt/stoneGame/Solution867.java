package com.ke.lt.stoneGame;

/**
 * https://leetcode.cn/problems/stone-game/description/
 *
 * @author zhangxudong
 * @since 2024/2/20 16:14
 */
public class Solution867 {

	/**
	 * 方法一 多轮迭代，getStone方法内部有大量的重复计算
	 */

	int left = 0;
	int right = 0;
	public boolean stoneGame(int[] piles) {
		getStone(piles, 0, piles.length - 1, 0);
		return left > right;
	}
	public int getStone(int[] piles, int start, int end, int turns) {
		if (left > right) {
			return 0;
		}
		int value = Math.max(piles[start] + getStone(piles, start + 1, end, turns + 1), piles[end] + getStone(piles, start, end - 1, turns + 1));

		if (turns % 2 == 0) {
			left += value;
		} else {
			right += value;
		}
		return value;
	}


	/**
	 * 对于输入是数组的，可以使用二维数组，存储中间过程的结果
	 */
	public boolean stoneGame2(int[] piles) {
		int len = piles.length;
		//dp[i][j]为i到j之间的差
		int[][] dp = new int[len][len];

		//转移方程,前一次和后一次的差值
		// dp[i][j] = Math.max(piles[i]-dp[i+1][j],piles[j]-dp[i][j-1]);
		for (int i = len - 1; i >= 0; i--) {
			for (int j = i; j < len; j++) {
				if (i == j) {
					dp[i][j] = piles[i];
				} else {
					dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
				}
			}
		}
		return dp[0][len-1]>0;
	}




}
