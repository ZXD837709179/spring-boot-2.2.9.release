package com.ke.lt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 又是递归图，又是动态规划
 * https://leetcode.cn/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/?envType=daily-question&envId=2023-12-07
 *
 * @author zhangxudong
 * @since 2023/12/7 22:54
 */
class Solution1466 {
	private int res = 0;
	public int minReorder(int n, int[][] connections) {
		List<Integer>[] road1 = new ArrayList[n];
		List<Integer>[] road2 = new ArrayList[n];
		//注意这里 这样写Arrays.fill(road1,new ArrayList<>());会导致所有的road1[i]都是同一个对象
		Arrays.setAll(road1, e -> new ArrayList<>());
		Arrays.setAll(road2, e -> new ArrayList<>());
		for (int[] connection : connections) {
			road1[connection[0]].add(connection[1]);
			road2[connection[1]].add(connection[0]);
		}
		dfs(road1,road2,0,-1);
		return n-1-res;
	}

	public void dfs(List<Integer>[] road1, List<Integer>[] road2, int cur,int fu) {
		for(int next:road1[cur]){
			if (fu == next) {
				continue;
			}
			dfs(road1,road2,next,cur);
		}
		for(int next:road2[cur]){
			if(fu==next){
				continue;
			}
			res++;
			dfs(road1,road2,next,cur);
		}
	}

	public static void main2(String[] args) {
		int[][] connections = new int[][]{{0,1},{1,3},{2,3},{4,0},{4,5}};
		System.out.println(new Solution1466().minReorder(6, connections));
		System.out.println(0^129);
	}

	public List<Integer> getGoodIndices(int[][] variables, int target) {
		ArrayList<Integer> result = new ArrayList<>();
		for (int i=0;i<variables.length;i++){
			int[] variable = variables[i];
			double res = 0;
			res = Math.pow(variable[0], variable[1]);
			res = res%10;
			res = Math.pow(res,variable[2]);
			res = res%variable[3];
			if (Math.abs(res-target)<0.0001){
				result.add(i);
			}
		}
		return result;
	}

	public int myPow(int a,int b,int c){
		int res = a;
		for(int i=1;i<b;i++){
			int tmp = res*a;
			res = tmp%c;
		}
		return res;
	}


	public long countSubarrays(int[] nums, int k) {
		long res = 0;
		int maxValue = 0;
		ArrayList<Integer> list = new ArrayList<>();
		for(int num:nums){
			maxValue = Math.max(maxValue,num);
		}
		for(int i=0;i<nums.length;i++){
			if (nums[i] == maxValue){
				list.add(i);
			}
		}
		if (list.size()<k){
			return 0;
		}
		//list是最大值的位置
		for(int i=0;i<=list.size()-k;i++){
			int zuo = list.get(i);
			for(int j=i+k-1;j<list.size();j++){
				int you = list.get(j);

				int temp1 = 1;
				if(i != 0){
					temp1 = zuo-list.get(i-1)+1;
				}

				int temp2 = 1;
				if (j!=list.size()-1){
					temp2 = list.get(j+1)-you+1;
				}
				res += (long) temp1 *temp2;

			}
		}
		return res;
	}

	public String nearestPalindromic(String n) {
		char[] charArray = n.toCharArray();
		int len = charArray.length;

		if (len<2){
			return String.valueOf(Long.valueOf(n)-1);
		}

		String prefix = n.substring(0,len/2);
		Character c ='a';
		if (len%2!=0){
			c =charArray[len/2+1];
			prefix +=c;
		}
		System.out.println(prefix);

		if (String.valueOf(Long.valueOf(prefix)-1).length()!=prefix.length()){
			return String.valueOf((long)(Math.pow(10,n.length())-1));
		}
		if (String.valueOf(Long.valueOf(prefix)+1).length()!=prefix.length()){
			return String.valueOf((long)(Math.pow(10,n.length())+1));
		}

		String prefix1 = String.valueOf(Long.valueOf(prefix)+1);
		String prefix2 = String.valueOf(Long.valueOf(prefix)-1);
		ArrayList<Long> list = new ArrayList<>();

		list.add(build(prefix1,c));
		list.add(build(prefix,c));
		list.add(build(prefix2,c));
		list.add(9L);
		list.add(11L);

		Long distance = (long) Math.pow(10,18)-1;
		Long res = (long) Math.pow(10,18)-1;


		for(Long l: list){
			if (l == Long.valueOf(n)){
				continue;
			}
			if (Math.abs(Long.valueOf(n)-l)<distance || (Math.abs(Long.valueOf(n)-l)==distance) && l<res){
				res = l;
				distance = Math.abs(Long.valueOf(n)-l);
			}
		}
		return String.valueOf(res);
	}
	public Long build(String prefix,Character c){
		String s;
		//奇数个
		if (c != 'a'){
			prefix = prefix.substring(0,prefix.length()-1);
			s = prefix + c+ new StringBuilder(prefix).reverse().toString();
		}else{
			s = prefix + new StringBuilder(prefix).reverse().toString();
		}


		System.out.println(s);
		return Long.valueOf(s);
	}

}
