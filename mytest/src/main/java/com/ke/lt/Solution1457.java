package com.ke.lt;


import java.util.HashSet;
import java.util.Set;

/**
 *
 *https://leetcode.cn/problems/pseudo-palindromic-paths-in-a-binary-tree
 * 回文：只有一个数字奇数或者全部是偶数,而不是只能有一个数字是奇数。
 * @author zhangxudong
 * @since 2023/11/28 14:57
 */
public class Solution1457 {
	int res = 0;
	public int pseudoPalindromicPaths (TreeNode root) {
		Set<Integer> set = new HashSet();
		isHui(set,root);
		return res;
	}

	public void isHui(Set<Integer> set,TreeNode root){
		if (null==root){
			return;
		}
		if(root.left==null && root.right==null){
			if (set.contains(root.val) && set.size()==2){
				res++;
			}
			if (!set.contains(root.val) && set.size()==0){
				res++;
			}
		}else{
			if(set.contains(root.val)){
				set.remove(root.val);
				isHui(set,root.left);
				isHui(set,root.right);
				set.add(root.val);
			}else{
				set.add(root.val);
				isHui(set,root.left);
				isHui(set,root.right);
				set.remove(root.val);
			}
		}

	}


	/**
	 * 方法2，用位运算，位运算常用来判断奇偶
	 * 二进制数字中每一个位置index对应数字，出现偶数则是0，奇数则是1.
	 * https://leetcode.cn/circle/discuss/CaOJ45/
	 * @param root
	 * @return
	 */
	public int pseudoPalindromicPaths2(TreeNode root) {
		return dfs(root, 0);
	}

	private int dfs(TreeNode root, int bits) {
		if (root == null)
			return 0;
		bits ^= 1 << root.val;// 对应位置如果是0就变成1，如果是1就变成0。
		// bits & (bits - 1) 表示消bits二进制中最右边的 1 。如果结果是0表示bits只有1个1或者全部是0
		if (root.left == null && root.right == null)
			return (bits & (bits - 1)) == 0 ? 1 : 0;
		return dfs(root.left, bits) + dfs(root.right, bits);
	}
}
