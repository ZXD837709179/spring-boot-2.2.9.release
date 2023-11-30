package com.ke.leetcode;


import java.util.*;

/**
 *
 * https://leetcode.cn/problems/determine-if-two-strings-are-close/?envType=daily-question&envId=2023-11-30
 *
 * @author zhangxudong
 * @since 2023/11/28 14:57
 */
public class Solution1657 {
	public boolean closeStrings(String word1, String word2) {
		if(word1.length()!=word2.length()){
			return false;
		}
		char[] chars1 = word1.toCharArray();
		char[] chars2 = word2.toCharArray();
		Map<Character,Integer> map1 = new HashMap();
		Map<Character,Integer> map2 = new HashMap();
		for(int i=0;i<chars1.length;i++){
			Integer value1 = map1.get(chars1[i]);
			if(value1 == null){
				map1.put(chars1[i],1);
			}else{
				map1.put(chars1[i],value1+1);
			}

			Integer value2 = map2.get(chars2[i]);
			if(value2 == null){
				map2.put(chars2[i],1);
			}else{
				map2.put(chars2[i],value2+1);
			}

		}
		//key要相同
		Set<Character> set1 = map1.keySet();
		Set<Character> set2 = map2.keySet();
		for (Character c:set1) {
			if(!set2.contains(c)){
				return false;
			}
		}


		//保证value值相同大小的数目一样
		HashMap<Integer,Integer> valueMap1 = new HashMap();
		HashMap<Integer,Integer> valueMap2 = new HashMap();
		Set<Map.Entry<Character, Integer>> entries1 = map1.entrySet();
		for (Map.Entry<Character, Integer> entry:entries1){
			Integer value = entry.getValue();
			if (valueMap1.containsKey(value)){
				valueMap1.put(value,valueMap1.get(value)+1);
			}else{
				valueMap1.put(value,1);
			}
		}

		Set<Map.Entry<Character, Integer>> entries2 = map2.entrySet();
		for (Map.Entry<Character, Integer> entry:entries2){
			Integer value = entry.getValue();
			if (valueMap2.containsKey(value)){
				valueMap2.put(value,valueMap2.get(value)+1);
			}else{
				valueMap2.put(value,1);
			}
		}
		Set<Map.Entry<Integer, Integer>> valueEntry = valueMap1.entrySet();
		for (Map.Entry<Integer, Integer> entry:valueEntry){
			Integer key = entry.getKey();
			Integer value = entry.getValue();
			if (!valueMap2.containsKey(key)){
				return false;
			}
			if (!valueMap2.get(key).equals(value)){
				return false;
			}
		}
		return true;

	}

	/**
	 * 上面的简化版
	 * @param word1
	 * @param word2
	 * @return
	 */
	public boolean closeStrings2(String word1, String word2) {
		//每一个字符的数目
		int[] count1 = new int[26], count2 = new int[26];
		for (char c : word1.toCharArray()) {
			count1[c - 'a']++;
		}
		for (char c : word2.toCharArray()) {
			count2[c - 'a']++;
		}
		for (int i = 0; i < 26; i++) {
			//如果某个字符只在其中一个String出现，则一定不对
			if (count1[i] > 0 && count2[i] == 0 || count1[i] == 0 && count2[i] > 0) {
				return false;
			}
		}
		//现在跟字符没关系了，只跟出现的数目有关
		Arrays.sort(count1);
		Arrays.sort(count2);
		return Arrays.equals(count1, count2);
	}
}
