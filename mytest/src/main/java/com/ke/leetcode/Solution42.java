package com.ke.leetcode;

import java.util.LinkedList;

/**
 * 经典接雨水
 *
 * @author zhangxudong
 * @since 2024/3/13 22:54
 */
public class Solution42 {

	//单调递减栈
	public int trap(int[] height) {
		LinkedList<Integer> queue = new LinkedList<>();
		int res = 0;
		for (int i = 0; i < height.length; i++) {
			//栈为空或者小于栈顶元素，直接当进去就可以了
			while (!queue.isEmpty() && height[i] > height[queue.peek()]) {
				//栈顶的mid的高度肯定小于当前的
				int mid = queue.pop();
				//如果栈顶的下面没了则接不住水，直接结束
				if (queue.isEmpty()) {
					break;
				}
				//两边高度最低的和中间位置的差值就是接住雨水的深度
				int gao = Math.min(height[i], height[queue.peek()]) - height[mid];
				//深度乘以宽度
				res += (i - queue.peek() - 1) * gao;

			}
			queue.push(i);
		}
		return res;
	}


	public int trap2(int[] height) {
		int n = height.length;
		if (n == 0) {
			return 0;
		}
		//找到每个位置左边和右边的最大值
		int[] leftMax = new int[n];
		leftMax[0] = height[0];
		for (int i = 1; i < n; ++i) {
			leftMax[i] = Math.max(leftMax[i - 1], height[i]);
		}

		int[] rightMax = new int[n];
		rightMax[n - 1] = height[n - 1];
		for (int i = n - 2; i >= 0; --i) {
			rightMax[i] = Math.max(rightMax[i + 1], height[i]);
		}

		int ans = 0;
		for (int i = 0; i < n; ++i) {
			//左右两边较低的高度和当前位置高度的差就是这个位置能放多少水
			ans += Math.min(leftMax[i], rightMax[i]) - height[i];
		}
		return ans;
	}


}
