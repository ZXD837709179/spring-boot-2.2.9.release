package com.ke.leetcode;

import java.util.ArrayDeque;

/**
 * https://leetcode.cn/problems/design-front-middle-back-queue/description/
 * 麻烦的是中间元素的操作，这里用两个双向链表代替一个，好操作中间的数;
 * 右边数目-左边数目<=1,要判断边界条件
 * @author zhangxudong
 * @since 2023/11/28 13:50
 */
class FrontMiddleBackQueue {

	private ArrayDeque<Integer> left = new ArrayDeque<>();
	private ArrayDeque<Integer> right = new ArrayDeque();

	public void balance(){
		if (left.size()>right.size()){
			right.addFirst(left.pollLast());
		}
		if (right.size()-left.size()>1){
			left.addLast(right.pollFirst());
		}
	}

	public FrontMiddleBackQueue() {
		left = new ArrayDeque();
		right = new ArrayDeque();
	}

	public void pushFront(int val) {
		left.addFirst(val);
		balance();
	}

	public void pushMiddle(int val) {
		if (left.size() < right.size()) {
			left.addLast(val);
		} else {
			right.addFirst(val);
		}
	}

	public void pushBack(int val) {
		right.addLast(val);
		balance();
	}

	public int popFront() {
		if (right.isEmpty()) { // 整个队列为空
			return -1;
		}
		int res = left.isEmpty() ? right.pollFirst() : left.pollFirst();
		balance();
		return res;
	}

	public int popMiddle() {
		if (right.isEmpty()) { // 整个队列为空
			return -1;
		}
		int res;
		if (left.size()==right.size()){
			res =  left.pollLast();
		}else{
			res = right.pollFirst();
		}
		balance();
		return res;

	}

	public int popBack() {
		if (right.size()==0){
			return -1;
		}
		int res = right.pollLast();
		balance();
		return res;
	}
}
