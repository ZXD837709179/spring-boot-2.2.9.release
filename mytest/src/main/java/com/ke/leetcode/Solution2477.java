package com.ke.leetcode;

import java.util.*;

/**
 * https://leetcode.cn/problems/minimum-fuel-cost-to-report-to-the-capital
 *
 * @author zhangxudong
 * @since 2023/11/29 17:59
 */
public class Solution2477 {

	/**
	 * 这个题目突破点是每一个点到首都只有一条路，否则会很麻烦
	 * 先把数据换成邻接表，然后从首都开始递归，每次递归都计算当前节点的油量
	 * 从根节点向下递归，上面的节点加上下面节点的递归值，同时计算每一个节点的油量
	 *
	 *
	 * 这个结束之后看看2646
	 */
	public long minimumFuelCost(int[][] roads, int seats) {


		List<Integer>[] graph = new ArrayList[roads.length + 1];
		Arrays.setAll(graph, e -> new ArrayList<>());
		//把路线从二维数组转换成邻接表，好取数
		for (int[] road : roads) {
			//road[0] -> road[1]
			graph[road[0]].add(road[1]);
			//road[1] -> road[0]
			graph[road[1]].add(road[0]);
		}

		//递归
		dfs(graph, seats, 0, -1);
		return ans;
	}

	private long ans = 0;

	public long dfs(List<Integer>[] graph, int seats, int cur, int pre) {
		long size = 1;
		for (int next : graph[cur]) {
			//next是当前节点能走到的下一个节点，但是不能再去父节点了
			if (next == pre) {
				continue;
			}
			//把子节点的数量加上
			size += dfs(graph, seats, next, cur);
		}
		//不是根节点，计算当前节点的油量
		if (cur > 0) {
			ans += (size - 1) / seats + 1;
		}
		return size;
	}



	public int[] dp(int node, int parent, int[] price, List<Integer>[] next, int[] count) {
		int[] res = {price[node] * count[node], price[node] * count[node] / 2};
		for (int child : next[node]) {
			if (child == parent) {
				continue;
			}
			int[] pair = dp(child, node, price, next, count);
			res[0] += Math.min(pair[0], pair[1]); // node 没有减半，因此可以取子树的两种情况的最小值
			res[1] += pair[0]; // node 减半，只能取子树没有减半的情况
		}
		return res;
	}


}
