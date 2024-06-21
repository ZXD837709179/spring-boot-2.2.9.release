package com.ke.lt;

import java.util.*;

/**
 * https://leetcode.cn/problems/sliding-window-maximum/
 *
 * @author zhangxudong
 * @since 2023/11/29 17:59
 */
public class Solution239 {

	public int[] maxSlidingWindow(int[] nums, int k) {
		int[] res = new int[nums.length - k + 1];
		Deque<Integer> queue = new ArrayDeque<>();

		for (int i = 0; i < nums.length; i++) {
			while (!queue.isEmpty() && nums[queue.getLast()] <= nums[i]) {
				queue.removeLast();
			}
			while (!queue.isEmpty() && i - queue.peek() >= k) {
				queue.poll();
			}
			queue.add(i);

			if (i - k + 1 >= 0) {
				System.out.println(nums[queue.peek()]);
				res[i - k + 1] = nums[queue.peek()];
			}

		}
		return res;
	}

	public static void main(String[] args) {
		int[] nums = {7,2,4};
		new Solution239().maxSlidingWindow(nums, 2);
	}

}
