package com.ke.lt;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-postorder-traversal/description
 * 关键点还是找到左右子树。在有中序遍历的情况下，找到左右很简单，只需要找到中序的根，左右两边就是他的子树。
 *
 * 前序和后序因为根不在中间不好找，但是天无绝人之路。往下多找一轮就能定位子树长度
 *
 *
 *
 *
 * @author zhangxudong
 * @since 2024/2/22 18:17
 */
public class Solution889 {
	HashMap<Integer, Integer> map = new HashMap();
	public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
		for(int i=0;i<postorder.length;i++){
			map.put(postorder[i],i);
		}
		return myTree(preorder,0,preorder.length-1,postorder,0,postorder.length-1);
	}

	public TreeNode myTree(int[] pre, int preStart, int preEnd, int[] postorder, int postStart, int postEnd) {
		if (preStart > preEnd) {
			return null;
		}
		int rootNum = pre[preStart];
		TreeNode root = new TreeNode(rootNum);
		if (preStart == preEnd) {
			return root;
		}

		//根左右中左子树的下一轮又是根左右,其中根这个值是第一轮左右根中左的最后一个
		int index = map.get(pre[preStart + 1]);
		//第一轮左子树数目
		int len = index - postStart + 1;

		root.left = myTree(pre, preStart + 1, preStart + len , postorder, postStart, postStart + len-1);
		root.right = myTree(pre, preStart + len+1 , preEnd, postorder, postStart + len, postEnd - 1);
		return root;
	}
}
