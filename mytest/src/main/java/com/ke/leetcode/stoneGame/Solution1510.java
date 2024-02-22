package com.ke.leetcode.stoneGame;

import com.ke.leetcode.TreeNode;
import org.aspectj.weaver.ast.Var;

import java.util.Arrays;
import java.util.HashMap;

/**
 * https://leetcode.cn/problems/stone-game-iv/description/
 *
 * @author zhangxudong
 * @since 2024/2/20 16:14
 */
public class Solution1510 {
	public boolean winnerSquareGame(int n) {
		int[] dp = new int[n + 1];
		//假设全部是必败
		Arrays.fill(dp, -1);
		dp[0] = -1;

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j * j <= i; j++) {
				//必胜的对立面是必败，如果前面有一个是必败，那么当前一定是必胜
				if(dp[i-j*j] == -1){
					dp[i] = 1;
					break;
				}
			}
		}
		return dp[n] ==1;

	}


}
