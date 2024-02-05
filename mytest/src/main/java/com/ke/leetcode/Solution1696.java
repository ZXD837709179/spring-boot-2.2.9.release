package com.ke.leetcode;


import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * https://leetcode.cn/problems/jump-game-vi/?envType=daily-question&envId=2024-02-05
 * @author zhangxudong
 * @since 2023/11/29 17:59
 */
public class Solution1696 {
	public static void main(String[] args) {
		Solution1696 test = new Solution1696();
		int[] nums = {1, -1, -2, 4, -7, 3};
		System.out.println(test.maxResult(nums, 2));
		System.out.println(test.maxResult2(nums, 2));
		System.out.println(test.maxResult4(nums, 2));
	}

	//从后往前
	public int maxResult(int[] nums, int k) {
		return dfs(nums, k, nums.length - 1);
	}
	//每一个后面的位置依赖前面的K个，前面K个依赖再前面，这样递归会有大量的重复计算。
	public int dfs(int[] nums, int k, int end) {
		if (end == 0) {
			return nums[0];
		}

		int max = Integer.MIN_VALUE;

		for (int i = Math.max(0, end - k); i < end; i++) {
			max = Math.max(max, dfs(nums, k, i));
		}
		return max + nums[end];

	}

	//从前往后计算，把前面的计算结果存储起来，后面直接用 复杂度：O(nk)
	public int maxResult2(int[] nums, int k) {
		int[] f = new int[nums.length];
		f[0] = nums[0];

		for (int i = 1; i < nums.length; i++) {
			int min = Integer.MIN_VALUE;
			for (int j = Math.max(i - k, 0); j < i; j++) {
				min = Math.max(min, f[j]);
			}
			f[i] = min + nums[i];
		}
		return f[nums.length - 1];
	}

	//上面还超时。首先至少有O(n)的复杂度，但是超时说明不能将K元素进行遍历，应该直接取出来，怎么取？？堆！这里有范围限制，只能是前面K个，因此用双端队列
	//最后的复杂度O（n）
	public int maxResult3(int[] nums, int k) {
		int n = nums.length;
		int[] dp = new int[n];
		dp[0] = nums[0];
		Deque<Integer> queue = new ArrayDeque<>();
		queue.offerLast(0);
		for (int i = 1; i < n; i++) {
			while (queue.peekFirst() < i - k) {
				queue.pollFirst();
			}
			dp[i] = dp[queue.peekFirst()] + nums[i];
			//小于等于这个数的都可以淘汰了，不会被选上
			while (!queue.isEmpty() && dp[queue.peekLast()] <= dp[i]) {
				queue.pollLast();
			}
			queue.offerLast(i);
		}
		return dp[n - 1];
	}



	//纯粹的堆
	public int maxResult4(int[] nums, int k) {
		if (nums.length==1){
			return nums[0];
		}
		PriorityQueue<He> queue = new PriorityQueue<>();

		queue.add(new He(nums[0],0));
		for(int i=1;i<nums.length-1;i++){
			while (!queue.isEmpty() && i-queue.peek().getIndex()>k){
				queue.poll();
			}
			queue.add(new He(queue.peek().getNum()+nums[i],i));
		}
		while (!queue.isEmpty()){
			if (queue.peek().getIndex()==nums.length){
				return queue.poll().getNum();
			}
			queue.poll();
		}
		return 0;

	}
	class He implements Comparable<He> {
		int num;
		int index;
		public He(int num,int index){
			this.num = num;
			this.index = index;
		}

		public int getNum(){
			return num;
		}

		public int getIndex(){
			return index;
		}

		@Override
		public int compareTo(He o) {
			return o.getNum()-this.num;
		}
	}


}
