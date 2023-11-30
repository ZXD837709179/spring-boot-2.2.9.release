package com.ke.leetcode;

import java.util.*;

/**
 * https://leetcode.cn/problems/count-unique-characters-of-all-substrings-of-a-given-string
 *
 * @author zhangxudong
 * @since 2023/11/29 17:59
 */
public class Solution828 {

	/**
	 思路：不要想着有多少个子字符串，那样太麻烦了，解不开；考虑每一个字符出现一次能有多少种组合
	 1、针对每一个字符，求他所有的位置
	 2、每一个字符他左边第一个相同字符和右边第一个相同字符的距离求积就是这个字符能在几个子字符串中出现一次
	 */
	public int uniqueLetterString(String s) {
		char[] chars = s.toCharArray();
		Map<Character, List<Integer>> characterListMap = new HashMap<>();
		for (int i=0;i<chars.length;i++){
			List<Integer> list = characterListMap.getOrDefault(chars[i], new ArrayList<Integer>());
			list.add(i);
			characterListMap.put(chars[i],list);
		}
		Set<Map.Entry<Character, List<Integer>>> entries = characterListMap.entrySet();
		int res = 0;
		for (Map.Entry<Character, List<Integer>> entry: entries) {
			List<Integer> value = entry.getValue();
			//处理两头的边界条件
			value.add(0,-1);
			value.add(s.length());
			for(int i=1;i<value.size()-1;i++){
				res += (value.get(i)-value.get(i-1))*(value.get(i+1)-value.get(i));
			}
		}
		return res;

	}

	public static void main(String[] args) {
		System.out.println(new Solution828().uniqueLetterString("ABC"));
	}

}
