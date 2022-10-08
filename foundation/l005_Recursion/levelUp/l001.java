package levelUp;

public class l001 {

	// Path with maximum gold - 1219
	public int getMaximumGold(int[][] grid) {
		int maxGold = 0;
		int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] != 0)
					maxGold = Math.max(maxGold, getMaximumGold(grid, i, j, dir));
			}
		}
		return maxGold;
	}

	public int getMaximumGold(int[][] grid, int sr, int sc, int[][] dir) {
		int maxGold = 0;
		grid[sr][sc] = -grid[sr][sc];
		for (int d = 0; d < dir.length; d++) {
			int r = sr + dir[d][0];
			int c = sc + dir[d][1];

			if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] > 0)
				maxGold = Math.max(maxGold, getMaximumGold(grid, r, c, dir));
		}
		grid[sr][sc] = -grid[sr][sc];
		return maxGold + grid[sr][sc];
	}

	// All Palindromic Permutations
	public static void generatepw(int cs, int ts, HashMap<Character, Integer> fmap, Character oddc, String asf) {
		if (cs > ts) {
			String rev = "";
			for (int i = asf.length() - 1; i >= 0; i--)
				rev += asf.charAt(i);
			String res = asf;
			if (oddc != null)
				res += oddc;
			res += rev;
			System.out.println(res);
			return;
		}
		for (char ch : fmap.keySet()) {
			int freq = fmap.get(ch);
			if (freq > 0) {
				fmap.put(ch, freq - 1);
				generatepw(cs + 1, ts, fmap, oddc, asf + ch);
				fmap.put(ch, freq);
			}
		}
	}

	public static void palindromicPermu(String str) {
		HashMap<Character, Integer> fmap = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			fmap.put(ch, fmap.getOrDefault(ch, 0) + 1);
		}

		Character oddCh = null;
		int odd = 0;
		int len = 0;
		for (char ch : fmap.keySet()) {
			int freq = fmap.get(ch);
			if (freq % 2 != 0) {
				oddCh = ch;
				odd++;
			}
			fmap.put(ch, freq / 2);
			len += freq / 2;
		}
		if (odd > 1)
			System.out.println("-1");
		else
			generatepw(1, len, fmap, oddCh, "");
	}

	// All Palindromic Partitions
	public static boolean isPalin(String str) {
		int i = 0, j = str.length() - 1;
		while (i < j) {
			if (str.charAt(i++) != str.charAt(j--))
				return false;
		}
		return true;
	}

	public static void solution(String str, String asf) {
		if (str.length() == 0) {
			System.out.println(asf);
			return;
		}
		for (int i = 0; i < str.length(); i++) {
			String pre = str.substring(0, i + 1);
			if (isPalin(pre)) {
				solution(str.substring(i + 1), asf + "(" + pre + ") ");
			}
		}
	}

	// https://www.interviewbit.com/problems/subset/
	public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> A) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		ArrayList<Integer> ans = new ArrayList<>();
		Collections.sort(A);
		getSubsets(A, 0, ans, res);
		return res;
	}

	public void getSubsets(ArrayList<Integer> A, int idx, ArrayList<Integer> ans, ArrayList<ArrayList<Integer>> res) {
		res.add(new ArrayList<>(ans));
		for (int i = idx; i < A.size(); i++) {
			ans.add(A.get(i));
			getSubsets(A, i + 1, ans, res);
			ans.remove(ans.size() - 1);
		}
	}

	// Friends Pairing - 2
	static int counter = 1;

	public static void solution(int i, int n, boolean[] used, String asf) {
		if (i > n) {
			System.out.println(counter++ + "." + asf);
			return;
		}

		if (!used[i]) {
			used[i] = true;
			solution(i + 1, n, used, asf + "(" + i + ") ");
			for (int j = i + 1; j <= n; j++) {
				if (!used[j]) {
					used[j] = true;
					solution(i + 1, n, used, asf + "(" + i + "," + j + ") ");
					used[j] = false;
				}
			}
			used[i] = false;
		} else
			solution(i + 1, n, used, asf);
	}

	// Word Break - I
	public static void wordBreak(String str, String ans, HashSet<String> dict) {
		if (str.length() == 0) {
			System.out.println(ans);
			return;
		}
		for (int i = 0; i < str.length(); i++) {
			String pre = str.substring(0, i + 1);
			if (dict.contains(pre))
				wordBreak(str.substring(i + 1), ans + pre + " ", dict);
		}
	}

	// Largest Number Possible After At Most K Swaps
	public static String max;

	public static void findMaximum(String str, int k, int idx) {
		if (k == 0)
			return;

		for (int i = idx; i < str.length(); i++) {
			int idx1 = -1;
			char maxCh = '0';
			for (int j = i + 1; j < str.length(); j++) {
				if (str.charAt(i) < str.charAt(j) && maxCh < str.charAt(j)) {
					idx1 = j;
					maxCh = str.charAt(j);
				}
			}
			if (idx1 != -1) {
				for (int j = idx1; j < str.length(); j++) {
					if (str.charAt(j) == maxCh) {
						String temp = swap(str, i, j);
						if (isGreater(temp, max))
							max = temp;
						findMaximum(temp, k - 1, i + 1);
					}
				}
			}
		}

	}

	public static String swap(String str, int i, int j) {
		StringBuilder sb = new StringBuilder(str);
		char c1 = str.charAt(i);
		char c2 = str.charAt(j);

		sb.setCharAt(i, c2);
		sb.setCharAt(j, c1);

		return sb.toString();
	}

	public static boolean isGreater(String temp, String str) {
		if (temp.length() > str.length())
			return true;
		else if (temp.length() < str.length())
			return false;

		for (int i = 0; i < str.length(); i++) {
			if (temp.charAt(i) > str.charAt(i))
				return true;
			else if (temp.charAt(i) < str.charAt(i))
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		palindromicPermu(scn.next());

		int n = scn.nextInt();
		HashSet<String> dict = new HashSet<>();
		for (int i = 0; i < n; i++) {
			dict.add(scn.next());
		}
		String sentence = scn.next();
		wordBreak(sentence, "", dict);

		max = str;
		findMaximum(str, k, 0);
		System.out.println(max);
	}

}
