package com.ke.lt;

import java.util.Arrays;

/**
 * 一旦设计子问题，采用自顶而下的递归或者自底而上的动态规划
 * 递归会产生大量的重复计算，可以通过数据存储中间的计算过程，加快速度
 * https://leetcode.cn/problems/edit-distance/description/?envType=study-plan-v2&envId=top-interview-150
 * @author zhangxudong
 * @since 2024/4/2 21:59
 */
public class Solution72 {

	//自底向上的动态规划
	public int minDistance(String word1, String word2) {
		char[] char1 = word1.toCharArray();
		char[] char2 = word2.toCharArray();
		int m = char1.length;
		int n = char2.length;
		int[][] dp = new int[m + 1][n + 1];


		dp[0][0] = 0;
		for(int i=0;i<=m;i++){
			dp[i][0] = i;
		}
		for(int i=0;i<=n;i++){
			dp[0][i] = i;
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (char1[i-1] == char2[j-1]){
					//如果最后一个想等，那么就相当于没有最后一个
					dp[i][j] = dp[i-1][j-1];
				}else{
					//操作是：删除最后一个或者更改两个的最后一个
					dp[i][j] = 1+Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1]));
				}
			}
		}
		return dp[m][n];
	}

	//自顶向下的递归,重复计算很多
	public int minDistance2(String word1, String word2) {
		if (word1.isEmpty() || word2.isEmpty()) {
			return Math.max(word1.length(), word2.length());
		}
		int m = word1.length();
		int n = word2.length();
		if (word1.charAt(m - 1) == word2.charAt(n - 1)) {
			return minDistance2(word1.substring(0, m - 1), word2.substring(0, n - 1));
		}
		return 1 + Math.min(Math.min(minDistance2(word1, word2.substring(0, n - 1)), minDistance2(word1.substring(0, m - 1), word2)),
				minDistance2(word1.substring(0, m - 1), word2.substring(0, n - 1)));
	}


	//自顶向下的递归 用数据存储计算过程
	//太多substring,可以定义全局char[],避免字符串操作
	public int minDistance3(String word1, String word2) {
		int m = word1.length();
		int n = word2.length();
		int[][] cal = new int[m+1][n+1];
		for(int i=0;i<=m;i++){
			Arrays.fill(cal[i],-1);
		}

		return dfs(cal,word1,word2);
	}

	public int dfs(int[][] cal, String word1, String word2) {
		if (cal[word1.length()][word2.length()]!= -1) {
			return cal[word1.length()][word2.length()];
		}

		if (word1.isEmpty() || word2.isEmpty()) {
			return cal[word1.length()][word2.length()] = Math.max(word1.length(), word2.length());
		}

		if (word1.charAt(word1.length() - 1) == word2.charAt(word2.length() - 1)) {
			return cal[word1.length()][word2.length()] = dfs(cal,word1.substring(0, word1.length() - 1), word2.substring(0, word2.length() - 1));
		}
		return cal[word1.length()][word2.length()] = 1 + Math.min(Math.min(dfs(cal, word1, word2.substring(0, word2.length() - 1)), dfs(cal, word1.substring(0, word1.length() - 1), word2)),
				dfs(cal, word1.substring(0, word1.length() - 1), word2.substring(0, word2.length() - 1)));
	}










}
