package com.ke.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.cn/problems/sum-of-subarray-minimums
 * 考察单调栈
 * @author zhangxudong
 * @since 2023/11/28 14:57
 */
public class Solution907 {
	private static final int MOD = 1000000007;
	public int sumSubarrayMins(int[] arr) {
		// 处理边界情况
		if(arr == null || arr.length ==0){
			return 0;
		}
		int n = arr.length;
		// 左边界
		int[] left = new int[n];
		// 右边界
		int[] right = new int[n];
		Deque<Integer> stack = new LinkedList<>();

		//通过单调递减栈，循环找到左边界,栈顶新增的一定是最大的，要是比较小就弹出之前大的
		for(int i=0;i<n;i++){
			while(!stack.isEmpty() && arr[stack.peek()]>arr[i]){
				stack.pop();
			}
			if(stack.isEmpty()){
				left[i]=-1;
			}else{
				left[i] = stack.peek();
			}
			//下标入栈
			stack.push(i);
		}
		stack.clear();

		//通过单调递增栈，找到右边界
		for(int i=n-1;i>=0;i--){
			while(!stack.isEmpty() && arr[stack.peek()]>=arr[i]){
				stack.pop();
			}
			//设立最右边界
			if(stack.isEmpty()){
				right[i] = n;
			}else{
				right[i] = stack.peek();
			}
			stack.push(i);

		}
		long ans = 0;
		for (int i = 0; i < n; i++) {
			ans = (ans + (long)(i - left[i]) * (right[i] - i) * arr[i]) % MOD;
		}
		return (int)ans;
	}
}
