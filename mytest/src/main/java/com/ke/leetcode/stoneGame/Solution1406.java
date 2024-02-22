package com.ke.leetcode.stoneGame;

/**
 * https://leetcode.cn/problems/stone-game-iii/
 * 有一个跳跃游戏跟这个有点类似，从前往后有多种方法，不好确定，所以从后往前推
 *
 * @author zhangxudong
 * @since 2024/2/20 16:14
 */
public class Solution1406 {
	public String stoneGameIII(int[] stoneValue) {
		int len = stoneValue.length;
		/**
		 * 这个定义很重要！定义要符合模拟的规则
		 * 从前往后取石子，当玩家某次是从i开始取石子时，两人的差值dp[i]
		 * 即 dp[i] 为剩余石子的下标范围是 [i,n−1]时当前玩家与对方玩家的得分之差最大值。
		 */
		int[] dp = new int[len + 1];

		for (int i = len - 1; i >= 0; i--) {
			int currSum = 0;
			int maxDiff = Integer.MIN_VALUE;
			int minEnd = i, maxEnd = Math.min(i + 2, len - 1);

			for (int j = minEnd; j <= maxEnd; j++) {
				currSum += stoneValue[j];
				maxDiff = Math.max(maxDiff, currSum - dp[j + 1]);
			}

			dp[i] = maxDiff;
		}

		if (dp[0] > 0) {
			return "Alice";
		} else if (dp[0] < 0) {
			return "Bob";
		} else {
			return "Tie";
		}
	}


	public String stoneGameIII2(int[] stoneValue) {
		int len = stoneValue.length;
		/**
		 * 即 dp[i] 为剩余石子的下标范围是 [i,n−1]时当前玩家与对方玩家的得分之差最大值。
		 */
		int[] dp = new int[len + 1];

		for (int i = 1; i <= stoneValue.length; i++) {
			int start = Math.max(1, i - 2);
			int sum = 0;
			dp[i] = Integer.MIN_VALUE;
			for (int j = i; j >= start; j--) {
				sum += stoneValue[j-1];
				dp[i] = Math.max(dp[i],sum-dp[j-1]);
			}
		}
		//无法确定最后一次是谁取，这里判断不了了，所以要从后往前推倒
//		if (dp[len] > 0) {
//			return "Alice";
//		} else if (dp[len] < 0) {
//			return "Bob";
//		} else {
//			return "Tie";
//		}
		return null;
	}

	//另一种dp的定义，也可以跑得通
	public String stoneGameIII3(int[] stoneValue) {
		int len = stoneValue.length;
		/**
		 * 即 dp[i] 为剩余石子的下标范围是 [i,n−1]时当前玩家获得石子的【总和】
		 */
		int[] dp = new int[len + 1];
		int sum = 0;
		for (int i = len - 1; i >= 0; i--) {
			int end = Math.min(len - 1, i + 2);
			dp[i] = Integer.MIN_VALUE;
			//后半程全部的石子数目
			sum += stoneValue[i];
			for (int j = i; j <= end; j++) {

				dp[i] = Math.max(dp[i], sum - dp[j + 1]);
			}
		}

		if (2 * dp[0] > sum) {
			return "Alice";
		} else if (2 * dp[0] < sum) {
			return "Bob";
		} else {
			return "Tie";
		}
	}
}

