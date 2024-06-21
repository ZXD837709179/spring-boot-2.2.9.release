package com.ke.lt;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhangxudong
 * @since 2024/3/12 10:00
 */
public class Test {
	//todo
	public ListNode reverseKGroup(ListNode head, int k) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode pre = new ListNode(0);
		ListNode pre2 = new ListNode(0);
		pre.next = head;
		pre2.next = pre;
		ListNode cur = head;
		ListNode next = null;

		while (cur != null) {
			ListNode tail = cur;
			for (int i = 1; i < k && cur != null; i++) {
				cur = cur.next;
			}
			if (cur == null) {
				pre.next = next;
				break;
			}
			next = cur.next;
			cur.next = null;
			//反转后的头
			ListNode temp = reverse(tail);
			pre.next = temp;
			pre = tail;
			cur = next;
		}
		return pre2.next.next;
	}

	private ListNode reverse(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode cur = head;
		ListNode pre = null;
		while (cur != null) {
			ListNode temp = cur.next;
			cur.next = pre;
			pre = cur;
			cur = temp;
		}
		return pre;
	}

	static ThreadLocal<Object> objectThreadLocal = new ThreadLocal<>();
	static InheritableThreadLocal<Object> objectThreadLocal2 = new InheritableThreadLocal<>();

	static {
		objectThreadLocal.set("haha");
		objectThreadLocal2.set("hehe");
	}


	public int candy(int[] ratings) {
		int len = ratings.length;
		int[] left = new int[len];
		left[0] = 1;
		for (int i = 1; i < len; i++) {
			if (ratings[i] > ratings[i - 1]) {
				left[i] = left[i - 1] + 1;
			} else {
				left[i] = 1;
			}
		}

		int[] right = new int[len];
		right[len - 1] = 1;

		for (int j = len - 2; j >= 0; j--) {
			if (ratings[j] > ratings[j + 1]) {
				right[j] = right[j + 1] + 1;
			} else {
				right[j] = 1;
			}
		}
		int res = 0;
		for (int i = 0; i < len; i++) {
			res += Math.max(left[i], right[i]);
		}
		return res;
	}

	public int romanToInt(String s) {
		Map<Character, Integer> map = new HashMap();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);

		char[] chars = s.toCharArray();
		int pre = 0;
		int res = 0;
		for (int i = 0; i < chars.length; i++) {
			int cur = map.get(chars[i]);
			if (cur <= pre) {
				res += cur;
			} else {
				res += cur;
				res = res - 2 * pre;
			}
			pre = cur;
		}
		return res;
	}

	public int lengthOfLongestSubstring(String s) {
		char[] charArray = s.toCharArray();
		int res = 0;
		HashMap<Character, Integer> indexMap = new HashMap<>();
		int pre = -1;
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			Integer index = indexMap.get(c);
			if (null == index) {
				//没有重复的数据
				res = Math.max(res, i - pre);
				indexMap.put(c, i);
			} else {
				pre = Math.max(pre, index);
				res = Math.max(res, i - pre);
				indexMap.put(c, i);
			}
		}
		return res;
	}


	public List<Integer> findSubstring(String s, String[] words) {
		ArrayList<Integer> res = new ArrayList<>();
		int len = words[0].length();
		int count = words.length;
		int lc = len * count;

		String comp1 = Arrays.stream(words).sorted().collect(Collectors.joining());
		for (int i = 0; i + lc <= s.length(); i++) {
			String target = s.substring(i, i + lc);
			List<String> t1 = new ArrayList<>();
			for (int j = 0; j + len <= target.length(); j = j + len) {
				t1.add(target.substring(j, j + len));
			}

			String comp2 = t1.stream().sorted().collect(Collectors.joining());
			if (comp2.equals(comp1)) {
				res.add(i);
			}
		}
		return res;
	}


	public String minWindow(String s, String t) {
		if (s.equals(t)) {
			return s;
		}
		int len = Integer.MAX_VALUE;
		char[] from = s.toCharArray();
		char[] to = t.toCharArray();
		HashMap<Character, Integer> map = new HashMap<>();
		for (Character c : to) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}

		int l = 0, r = 0;
		String res = s;

		while (r < from.length) {
			char cur = from[r];
			if (map.get(cur) != null) {
				Integer i = map.get(cur);
				if (i > 1) {
					map.put(cur, map.get(cur) - 1);
				} else {
					//已经足够了，可以试试减少l
					map.put(cur, map.get(cur) - 1);
					//判断是否已经全部包含了，找到一个长度
					if (allMatch(map)) {
						while (l <= r) {
							if (r - l + 1 <= res.length()) {
								res = s.substring(l, r + 1);
								len = res.length();
							}
							if (map.get(from[l]) == null) {
								l++;
							} else if (map.get(from[l]) < 0) {
								map.put(from[l], map.get(from[l]) + 1);
								l++;
							} else {
								break;
							}
						}
					}
				}
				r++;
			} else {
				r++;
			}
		}
		return len == Integer.MAX_VALUE ? "" : res;
	}

	private boolean allMatch(HashMap<Character, Integer> map) {
		for (Character c : map.keySet()) {
			if (map.get(c) > 0) {
				return false;
			}
		}
		return true;
	}


	public boolean isIsomorphic(String s, String t) {
		char[] chars = s.toCharArray();
		char[] chart = t.toCharArray();

		HashMap<Character, Character> map = new HashMap<>();
		for (int i = 0; i < chars.length; i++) {
			char from = chars[i];
			char to = chart[i];

			Character h = map.get(from);
			if (h == null) {
				if (map.values().contains(to)) {
					return false;
				} else {
					map.put(from, to);
				}
			} else {
				if (h != to) {
					return false;
				}
			}

		}
		return true;
	}


	public boolean wordPattern(String pattern, String s) {
		char[] from = pattern.toCharArray();
		String[] to = s.split(" ");
		HashMap<Character, String> map1 = new HashMap<>();
		HashMap<String, String> map2 = new HashMap<>();
		for (int i = 0; i < from.length; i++) {
			char c1 = from[i];
			String c2 = to[i];
		}
		return true;
	}


	public List<List<String>> groupAnagrams(String[] strs) {
		HashMap<String, List<String>> map = new HashMap();
		for (String str : strs) {
			char[] charArray = str.toCharArray();
			Arrays.sort(charArray);
			String s = new String(charArray);

			List<String> list = map.getOrDefault(s, new ArrayList<>());
			list.add(str);
			map.put(s, list);
		}
		return new ArrayList<>(map.values());
	}

	public int[] twoSum(int[] nums, int target) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int find = target - nums[i];
			if (map.containsKey(find)) {
				return new int[]{i, map.get(find)};
			} else {
				map.put(nums[i], i);
			}
		}
		return new int[]{-1, -1};
	}


	public boolean containsNearbyDuplicate(int[] nums, int k) {
		if (k == 0) {
			return false;
		}
		int l = 0, r = 0;
		HashSet<Integer> set = new HashSet<>();
		while (r < nums.length) {

			if (set.contains(nums[r])) {
				return true;
			}
			set.add(nums[r]);
			if (r - l >= k) {
				set.remove(nums[l++]);
			}
			r++;
		}
		return false;
	}


	public int longestConsecutive(int[] nums) {
		HashSet<Integer> set = new HashSet<>();

		for (int num : nums) {
			set.add(num);
		}
		int res = 0;
		for (int num : nums) {
			//从num开始是第一个
			if (!set.contains(num - 1)) {
				int start = num;
				while (set.contains(start)) {
					start++;
				}
				res = Math.max(res, start - num);
			}
		}
		return res;
	}


	public List<String> summaryRanges(int[] nums) {

		ArrayList<String> res = new ArrayList<>();
		if (nums.length == 0) {
			return res;
		}
		if (nums.length == 1) {
			res.add(String.valueOf(nums[0]));
		}

		int start = 0;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != nums[i - 1] + 1) {
				if (i - 1 == start) {
					res.add(nums[start] + "");
				} else {
					res.add(nums[start] + "->" + nums[i - 1]);
				}
				start = i;
			}
		}

		if (start == nums.length - 1) {
			res.add(nums[start] + "");
		} else {
			res.add(nums[start] + "->" + nums[nums.length - 1]);
		}


		return res;

	}


	public int[][] merge(int[][] intervals) {
		Arrays.sort(intervals, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

		ArrayList<int[]> res = new ArrayList<>();
		for (int[] cur : intervals) {
			if (res.size() == 0) {
				res.add(cur);
			} else {

				int[] last = res.get(res.size() - 1);

				if (last[1] < cur[0]) {
					res.add(cur);
				} else {
					int max = Math.max(last[1], cur[1]);
					last[1] = max;
				}

			}
		}
		return res.toArray(new int[0][]);

	}


	public int[][] insert(int[][] intervals, int[] newInterval) {
		if (newInterval.length == 0) {
			return intervals;
		}
		if (intervals.length == 0) {
			int[][] res = new int[1][];
			res[0] = newInterval;
			return res;
		}

		ArrayList<int[]> res = new ArrayList<>();

		boolean used = false;
		for (int[] interval : intervals) {
			if ((newInterval[0] <= interval[0] && newInterval[1] >= interval[0]) || (newInterval[0] <= interval[1] && newInterval[1] >= interval[1])) {
				//黏起来了
				int l = Math.min(newInterval[0], interval[0]);
				int r = Math.min(newInterval[1], interval[1]);
				newInterval = new int[]{l, r};
				used = true;
			} else {
				if (used) {
					res.add(newInterval);
				}
				res.add(interval);
			}
		}
		if (used && res.size() != 0) {
			res.add(newInterval);
		}
		return res.toArray(new int[0][]);
	}

	public int[][] inser2t(int[][] intervals, int[] newInterval) {
		int left = newInterval[0];
		int right = newInterval[1];
		boolean placed = false;
		List<int[]> ansList = new ArrayList<int[]>();
		for (int[] interval : intervals) {
			if (interval[0] > right) {
				// 在插入区间的右侧且无交集
				if (!placed) {
					ansList.add(new int[]{left, right});
					placed = true;
				}
				ansList.add(interval);
			} else if (interval[1] < left) {
				// 在插入区间的左侧且无交集
				ansList.add(interval);
			} else {
				// 与插入区间有交集，计算它们的并集
				left = Math.min(left, interval[0]);
				right = Math.max(right, interval[1]);
			}
		}
		if (!placed) {
			ansList.add(new int[]{left, right});
		}
		int[][] ans = new int[ansList.size()][2];
		for (int i = 0; i < ansList.size(); ++i) {
			ans[i] = ansList.get(i);
		}
		return ans;
	}

	public int findMinArrowShots(int[][] points) {
		if (points.length == 0) {
			return 0;
		}

		Arrays.sort(points, (o1, o2) -> {
			if (o1[0] > o2[0]) {
				return 1;
			} else if (o1[0] < o2[0]) {
				return -1;
			} else {
				return 0;
			}
		});

		int sum = 1;
		int[] tmp = new int[2];
		tmp = points[0];
		for (int i = 1; i < points.length; i++) {
			if (tmp[1] < points[i][0]) {
				sum++;
				tmp[0] = points[i][0];
				tmp[1] = points[i][1];
			} else {
				tmp[0] = Math.max(points[i][0], tmp[0]);
				tmp[1] = Math.min(points[i][1], tmp[1]);
			}
		}
		return sum;
	}

	public boolean isValid(String s) {
		char[] charArray = s.toCharArray();
		Stack<Character> stack = new Stack<>();

		for (char c : charArray) {
			if (c == '{' || c == '[' || c == '(') {
				stack.push(c);
			} else {
				if (stack.isEmpty()) {
					return false;
				}
				Character pop = stack.pop();
				if ((c == '}' && pop == '{') || (c == ']' && pop == '[') || (c == ')' && pop == '(')) {
					continue;
				} else {
					return false;
				}

			}
		}
		return true;
	}


	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int pre = 0;
		ListNode head = new ListNode(0);
		ListNode res = head;
		while (l1 != null || l2 != null || pre != 0) {
			int sum = pre;

			if (l1 != null) {
				sum += l1.val;
			}
			if (l2 != null) {
				sum += l2.val;
			}
			pre = sum / 10;

			head.next = new ListNode(sum % 10);
			head = head.next;
		}
		return res.next;
	}


	public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		ListNode head = new ListNode(0);
		ListNode cur = head;
		while (list1 != null || list2 != null) {
			if (list1 == null) {
				cur.next = list2;
				break;
			}
			if (list2 == null) {
				cur.next = list1;
				break;
			}

			if (list1.val < list2.val) {
				cur.next = new ListNode(list1.val);
				list1 = list1.next;
				cur = cur.next;
			} else {
				cur.next = new ListNode(list2.val);
				list2 = list2.next;
				cur = cur.next;
			}
		}
		return head.next;
	}


	class Node {
		int val;
		Node next;
		Node random;

		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}


	public Node copyRandomList(Node head) {
		if (head == null) return null;
		Node cur = head;
		while (cur != null) {
			Node next = cur.next;
			Node copy = new Node(cur.val);
			cur.next = copy;
			copy.next = next;
			cur = next;
		}


		cur = head;
		while (cur != null) {
			if (cur.random != null) {
				cur.next.random = cur.random.next;
			} else {
				cur.next.random = null;
			}
			cur = cur.next.next;
		}

		Node copyHead = head.next;
		Node oldHead = head;
		Node res = copyHead;

		while (oldHead != null && copyHead != null) {
			Node yuanNext = copyHead.next;
			if (yuanNext == null) {
				break;
			}
			Node xinNext = copyHead.next.next;
			oldHead.next = yuanNext;
			copyHead.next = xinNext;
			oldHead = yuanNext;
			copyHead = xinNext;
		}
		oldHead.next = null;
		return res;
	}


	public ListNode reverseBetween(ListNode head, int left, int right) {
		if (left == right) {
			return head;
		}
		ListNode dummy = new ListNode(0, head);
		ListNode p0 = dummy;
		for (int i = 1; i < left; i++) {
			p0 = p0.next;
		}

		ListNode pre = new ListNode(0);
		pre.next = p0.next;
		ListNode cur = p0.next;
		for (int i = 0; i < right - left + 1; i++) {
			ListNode next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		p0.next.next = cur;
		p0.next = pre;
		return dummy.next;

	}

	private ListNode reverseListNode(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode pre = null;
		while (head != null) {
			ListNode next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}


	public ListNode reverseKGroup2(ListNode head, int k) {
		ListNode cur = head;
		int n = 0;
		while (cur != null) {
			n++;
			cur = cur.next;
		}
		ListNode dummy = new ListNode(0, head);
		ListNode p0 = dummy;
		ListNode pre = dummy;
		cur = head;
		while (n >= k) {
			n = n - k;

			for (int i = 0; i < k; i++) {
				ListNode next = cur.next;
				cur.next = pre;
				pre = cur;
				cur = next;
			}
			ListNode nextPre = p0.next;
			p0.next.next = pre;
			p0.next = pre;
			pre = nextPre;

		}
		return dummy.next;

	}


	public ListNode removeNthFromEnd(ListNode head, int n) {
		Stack<ListNode> stack = new Stack();
		while (head != null) {
			ListNode temp = head.next;
			head.next = null;
			stack.push(head);
			head = temp;

		}
		int i = 1;
		ListNode next = null;
		while (!stack.isEmpty()) {
			ListNode pop = stack.pop();
			if (i == n) {
				i++;
			} else {
				i++;
				pop.next = next;
				next = pop;
			}
		}
		return next;

	}


	public ListNode deleteDuplicates(ListNode head) {
		ListNode dummy = new ListNode(-101, head);


		ListNode cur = dummy;
		while (cur.next != null && cur.next.next != null) {
			if (cur.next.val == cur.next.next.val) {
				//从cur.next开始判断
				while (cur.next.next != null && cur.next.next.val == cur.next.val) {
					cur.next.next = cur.next.next.next;
				}
				cur.next = cur.next.next;
			} else {
				cur = cur.next;
			}
		}
		return dummy.next;
	}


	public ListNode rotateRight(ListNode head, int k) {
		int len = 0;
		ListNode cur = head;
		ListNode tail = null;
		while (cur != null) {
			len++;
			tail = cur;
			cur = cur.next;
		}


		k = len - k % len;

		cur = head;

		for (int i = 0; i < k; i++) {
			cur = cur.next;
		}

		ListNode newHead = cur.next;
		cur.next = null;
		tail.next = head;
		return newHead;


	}

	public ListNode partition(ListNode head, int x) {
		ListNode smallDummy = new ListNode(0);
		ListNode preSmall = smallDummy;
		ListNode bigDummy = new ListNode(0);
		ListNode preBig = bigDummy;

		while (head != null) {

			ListNode next = head.next;
			head.next = null;
			if (head.val < x) {
				preSmall.next = head;
				preSmall = head;
			} else {
				preBig.next = head;
				preBig = head;
			}
			head = next;
		}
		preSmall.next = bigDummy.next;

		return smallDummy.next;

	}

	public boolean wordBreak(String s, List<String> wordDict) {
		int len = s.length();
		int[] dp = new int[len + 1];
		dp[0] = 1;
		HashSet<String> set = new HashSet<>(wordDict);
		for (int i = 1; i <= len; i++) {
			for (int j = 0; j < i; j++) {
				if (dp[j] == 1 && set.contains(s.substring(j, i))) {
					dp[i] = 1;
					break;
				}
			}
		}
		return dp[len] == 1;
	}

	public int coinChange(int[] coins, int amount) {
		if (amount == 0) {
			return 0;
		}
		int[] dp = new int[amount + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);

		for (int i = 1; i <= amount; i++) {
			for (int coin : coins) {
				if (i == coin) {
					dp[i] = 1;
				} else if (dp[i - coin] != Integer.MAX_VALUE) {
					dp[i] = Math.min(dp[i], dp[i - coin] + 1);
				}
			}
		}
		return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
	}

	public int lengthOfLIS(int[] nums) {

		if (nums.length == 0) {
			return 0;
		}
		int n = nums.length;

		int[] dp = new int[n];
		dp[0] = 1;


		for (int i = 1; i < n; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					dp[i] = Math.max(dp[j] + 1, dp[i]);
				}
			}
		}
		return dp[n - 1];
	}

	public String longestPalindrome(String s) {

		char[] charArray = s.toCharArray();
		int len = charArray.length;
		String res = String.valueOf(charArray[0]);

		for (int start = 0; start < len; start++) {
			for (int i = 1; start - i >= 0 && start + i < len; i++) {
				if (charArray[start - i] == charArray[start + i]) {
					res = res.length() > 2 * i + 1 ? res : s.substring(start - i, start + i + 1);
				} else {
					break;
				}
			}
		}

		for (int left = 0; left < len - 1; left++) {
			for (int l = 0; left - l >= 0 && left + l + 1 < len; l++) {
				if (charArray[left -l] == charArray[left + l+1]) {
					res =res.length()>2*(l+1)?res:s.substring(left-l,left+l+1);
				} else {
					break;
				}
			}
		}
		return res;
	}


	public int minimumTotal(List<List<Integer>> triangle) {
		int len = triangle.size();
		int[][] dp = new int[len][len];
		if(len==1){
			return triangle.get(0).get(0);
		}
		int res = Integer.MAX_VALUE;

		dp[0][0] =triangle.get(0).get(0);
		for(int i=1;i<len;i++){
			for(int j=0;j<=i;j++){
				if(j == 0){
					dp[i][j] = dp[i-1][0]+triangle.get(i).get(j);
				}else if(j==i){
					dp[i][j] = dp[i-1][i-1]+triangle.get(i).get(j);
				}else{
					dp[i][j] = Math.min(dp[i-1][j]+triangle.get(i).get(j),dp[i-1][j-1]+triangle.get(i).get(j));
				}
				if(i == len-1){
					res = Math.min(res,dp[i][j]);
				}
			}
		}
		return res;
	}


	public int minPathSum(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;

		int[][] dp = new int[m][n];

		dp[0][0] = grid[0][0];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i==0 && j==0){
					dp[0][0] = grid[0][0];
				}else if (i == 0){
					dp[i][j] = dp[i][j-1]+grid[i][j];
				}else if(j == 0){
					dp[i][j] = dp[i-1][j]+grid[i][j];
				}else{
					dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1])+grid[i][j];
				}
			}
		}
		return dp[m][n];
	}


	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;

		int[] dp = new int[n];
		for(int j=0;j<n;j++){
			if (obstacleGrid[0][j]==1){
				dp[j]=0;
			}else if (j>0 && dp[j-1]==0){
				dp[j] = 0;
			}else {
				dp[j] = 1;
			}
		}

		for (int i = 1; i < m; i++) {
			if (obstacleGrid[i][0] == 1) {
				dp[0] = 0;
			}
			for (int j = 1; j < n; j++) {
				if (obstacleGrid[i][j] == 1) {
					dp[j] = 0;
					continue;
				}
				dp[j] = dp[j]+dp[j-1];
			}
		}
		return dp[n-1];
	}



	public boolean isInterleave(String s1, String s2, String s3) {
		char[] char1 = s1.toCharArray();
		char[] char2 = s2.toCharArray();
		char[] char3 = s3.toCharArray();
		if (char1.length + char2.length != char3.length) {
			return false;
		}

		//可以考虑使用boolean，好处是可以通过&跟前一个位置的进行运算，不用if else
		int[][] dp = new int[char1.length+1][char2.length+1];
		dp[0][0] = 1;

		for(int i=1;i<=char1.length;i++){
			for(int j=1;j<=char2.length;j++){
				//考虑的字符串 i+j+1
				if ((char3[i+j-1] == char2[j-1] && dp[i][j-1]==1 ) || ( char3[i+j-1] == char1[i-1] && dp[i-1][j]==1)){
					dp[i][j] = 1;
				}
			}
		}
		return dp[char1.length][char2.length]==1;
	}


	public int minDistance(String word1, String word2) {
		char[] char1 = word1.toCharArray();
		char[] char2 = word2.toCharArray();
		int m = char1.length;
		int n = char2.length;
		int[][] dp = new int[m + 1][n + 1];


		dp[0][0] = 0;
		for(int i=0;i<=m;i++){
			dp[i][0] = i;
		}
		for(int i=0;i<=n;i++){
			dp[0][i] = i;
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (char1[i-1] == char2[j-1]){
					//
					dp[i][j] = dp[i-1][j-1];
				}else{
					dp[i][j] = 1+Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1]));
				}
			}
		}
		return dp[m][n];
	}

	Map<TreeNode,Integer> leftMap = null;
	Map<TreeNode,Integer> rightMap = null;
	public int maxPathSum(TreeNode root) {
		leftMap = new HashMap();
		rightMap = new HashMap();
		cal(root);
		int res = Integer.MIN_VALUE;
		LinkedList<TreeNode> list = new LinkedList();
		list.add(root);
		while(!list.isEmpty()){
			TreeNode temp = list.pop();


			if(temp.left!=null){
				list.add(temp.left);
			}
			if(temp.right!=null){
				list.add(temp.right);
			}

			res = Math.max(res,temp.val+leftMap.get(root)+rightMap.get(root));

		}
		return res;

	}

	public int cal(TreeNode root){
		if(root == null){
			return 0;
		}
		int leftNum = cal(root.left);
		int rightNum = cal(root.right);
		leftMap.put(root,leftNum);
		rightMap.put(root,rightNum);
		return root.val + Math.max(leftNum,rightNum);
	}


}
