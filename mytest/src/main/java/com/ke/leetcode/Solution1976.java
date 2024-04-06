package com.ke.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 最短路程，不会出现两个点来会走，否则不是最短
 *
 * @author zhangxudong
 * @since 2024/3/5 15:49
 */
public class Solution1976 {
	HashMap<Integer, List<Integer>> roadMap = new HashMap();
	HashMap<String, Integer> roadLen = new HashMap();

	Integer min = Integer.MAX_VALUE;
	List<Integer> res = new ArrayList<>();

	//回溯 时间复杂度超了
	public int countPaths(int n, int[][] roads) {
		for (int[] road : roads) {
			List<Integer> map1 = roadMap.getOrDefault(road[0], new ArrayList<>());
			map1.add(road[1]);
			roadMap.put(road[0], map1);


			List<Integer> map2 = roadMap.getOrDefault(road[1], new ArrayList<>());
			map2.add(road[0]);
			roadMap.put(road[1], map2);


			roadLen.put(road[0] + "_" + road[1], road[2]);
			roadLen.put(road[1] + "_" + road[0], road[2]);
		}

		bfs(new ArrayList<>(), 0, n - 1, 0);


		int result = 0;
		for (Integer num : res) {
			if (num.equals(min)) {
				result++;
				result = result % 1000000007;
			}
		}
		return result;
	}

	public void bfs(List<Integer> usedNode, int node, int target, int len) {
		if (len > min) {
			return;
		}
		if (node == target) {
			min = len;
			res.add(len);
			return;
		}
		List<Integer> list = roadMap.get(node);
		if (list == null) {
			return;
		}
		for (int i : list) {
			//node->i
			if (usedNode.contains(i)) {
				continue;
			}
			usedNode.add(i);
			int temLen = roadLen.get(node + "_" + i);
			bfs(usedNode, i, target, len + temLen);
			usedNode.remove(usedNode.size() - 1);
		}
	}

}
