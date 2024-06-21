package com.ke.lt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/count-valid-paths-in-a-tree
 *
 *
 * @author zhangxudong
 * @since 2024/2/27 11:21
 */
public class Solution2867 {
	// 埃氏筛
	private static final int N = 100001;
	private static boolean[] isPrime = new boolean[N];
	static {
		//找到每一个质数
		Arrays.fill(isPrime, true);
		isPrime[1] = false;
		for (int i = 2; i * i < N; i++) {
			if (isPrime[i]) {
				for (int j = i * i; j < N; j += i) {
					isPrime[j] = false;
				}
			}
		}
	}

	public long countPaths(int n, int[][] edges) {
		//每一个节点找到他能去的路径
		List<Integer>[] G = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++) {
			G[i] = new ArrayList<>();
		}
		//无向图
		for (int[] edge : edges) {
			int i = edge[0], j = edge[1];
			G[i].add(j);
			G[j].add(i);
		}

		List<Integer> seen = new ArrayList<>();
		long res = 0;
		long[] count = new long[n + 1];
		for (int i = 1; i <= n; i++) {
			//不是质数，结束这个数的处理
			if (!isPrime[i]) {
				continue;
			}
			long cur = 0;
			//包含i这个质数，j这个非质数
			for (int j : G[i]) {
				// 下一个节点是质数，不处理
				if (isPrime[j]) {
					continue;
				}
				// 从j这个非质数开始出发
				if (count[j] == 0) {
					seen.clear();
					dfs(G, seen, j, 0);
					// seen是走过节点的路径
					long cnt = seen.size();
					for (int k : seen) {
						//联通的路径
						count[k] = cnt;
					}
				}
				//当前节点联通数目乘以别的节点联通数目。此时i在节点的中间
				res += count[j] * cur;
				//因为两两相乘，所以加上之前算过的
				cur += count[j];
			}
			//质数是一端，非质数是一端，所以需要加上质数的数目
			res += cur;
		}
		return res;
	}

	/**
	 * 遍历可以触达的节点
	 * @param G 每个节点对应的可以走的节点
	 * @param seen 存储触达的节点
	 * @param i 当前节点
	 * @param pre 上一个走过的节点，不能重复走
	 */
	private void dfs(List<Integer>[] G, List<Integer> seen, int i, int pre) {
		seen.add(i);
		for (int j : G[i]) {
			if (j != pre && !isPrime[j]) {
				dfs(G, seen, j, i);
			}
		}
	}
}
