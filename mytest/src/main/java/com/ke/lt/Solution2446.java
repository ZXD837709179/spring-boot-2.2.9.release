package com.ke.lt;

import java.util.ArrayList;
import java.util.List;

/**
 * hhttps://leetcode.cn/problems/minimize-the-total-price-of-the-trips
 *
 * @author zhangxudong
 * @since 2023/11/29 17:59
 */
public class Solution2446 {
	public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
		List<Integer>[] next = new List[n];
		for(int i=0;i<n;i++){
			next[i] = new ArrayList();
		}

		//把元素位置关系放到集合好找
		for(int[] edge:edges){
			next[edge[0]].add(edge[1]);
			next[edge[1]].add(edge[0]);
		}

		//每个位置元素会走几次
		int[] count = new int[n];
		for(int[] trip:trips){
			dfs(trip[0],-1,trip[1],next,count);
		}

		//已经知道每个位置元素会走几次，乘以大小即可，但是可能减半，但是可能又不相邻，所以使用动态规划
		int[] pair = dp(0,-1,price,next,count);
		return Math.min(pair[0],pair[1]);
	}

	/**
	 * 深度递归先求到终点要走过多少次
	 *
	 */
	public boolean dfs(int node,int parent,int end,List<Integer>[] next,int[] count){
		if(node==end){
			count[node]++;
			return true;
		}
		for(int child:next[node]){
			if(child==parent){
				continue;
			}
			//子类能便利到最后节点
			//因为题目说edges是n-1个，所以只会有一条路
			if(dfs(child,node,end,next,count)){
				count[node]++;
				return true;
			}
		}
		return false;
	}

	//动态规划 同打家劫舍三
	public int[] dp(int node,int parent,int[] price,List<Integer>[] next,int[] count){
		//减半或者不减半
		int[] res = {price[node]*count[node],price[node]*count[node]/2};
		//下一个能走的路径
		for(int child:next[node]){
			if(child==parent){
				continue;
			}
			int[] pair = dp(child,node,price,next,count);
			res[0] +=Math.min(pair[0],pair[1]);//当前node不变
			res[1] +=pair[0];//当前减半
		}
		return res;
	}
}
