import java.util.*;

public class l004_Questions {

    // Sudoku Solver - 37
    public static class pair1 {
        int r = 0;
        int c = 0;

        pair1(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static boolean isValidToPlaceNumber(char[][] board, int r, int c, int num) {
        // row
        for (int i = 0; i < 9; i++)
            if (board[r][i] - '0' == num)
                return false;

        // col
        for (int i = 0; i < 9; i++)
            if (board[i][c] - '0' == num)
                return false;

        // mat
        r = (r / 3) * 3; // This will help us to shifting our matrix from row-wise,
        c = (c / 3) * 3; // This will help us to shifting our matrix from column-wise.

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[r + i][c + j] - '0' == num) // Here, in if condition we use shifting to traverse the whole
                                                      // matrix.
                    return false;
            }
        }

        return true;
    }

    public static boolean solveSudoku(char[][] board, int idx, ArrayList<pair1> arr) {
        if (idx == arr.size()) {
            return true;
        }

        pair1 p = arr.get(idx);
        int r = p.r;
        int c = p.c;

        for (int num = 1; num <= 9; num++) {
            if (isValidToPlaceNumber(board, r, c, num)) {
                board[r][c] = (char) (num + '0');
                if (solveSudoku(board, idx + 1, arr))
                    return true;
                board[r][c] = '.';
            }
        }

        return false;
    }

    public static void solveSudoku(char[][] board) {
        ArrayList<pair1> arr = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    arr.add(new pair1(i, j)); // i * 9 + j
                }
            }
        }

        solveSudoku(board, 0, arr);
    }

    // Cryptarithematic
    public static int getNumber(String str, HashMap<Character, Integer> map) {
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            res = res * 10 + map.get(str.charAt(i));
        }
        return res;
    }

    public static void solution(String unique, int idx, HashMap<Character, Integer> charIntMap, boolean[] usedNumbers,
            String s1, String s2, String s3) {
        // write your code here
        if (idx == unique.length()) {
            int x = getNumber(s1, charIntMap);
            int y = getNumber(s2, charIntMap);
            int z = getNumber(s3, charIntMap);

            if (x + y == z) {
                for (int i = 0; i < 26; i++) {
                    char ch = (char) (i + 'a');
                    if (charIntMap.containsKey(ch))
                        System.out.print(ch + "-" + charIntMap.get(ch) + " ");
                }
                System.out.println();
            }
            return;
        }

        char ch = unique.charAt(idx);
        for (int num = 0; num < 10; num++) {
            if (!usedNumbers[num]) {
                usedNumbers[num] = true;
                charIntMap.put(ch, num);

                solution(unique, idx + 1, charIntMap, usedNumbers, s1, s2, s3);

                charIntMap.remove(ch);
                usedNumbers[num] = false;
            }

        }
    }

    // 2 set of equal sum
    public static int equalSet(int[] arr, int idx, int sum1, String set1, int sum2, String set2) {
        if (idx == arr.length) {
            if (sum1 == sum2) {
                System.out.println(set1 + " = " + set2);
                return 1;
            }
            return 0;
        }

        int count = 0;
        count += equalSet(arr, idx + 1, sum1 + arr[idx], set1 + arr[idx] + " ", sum2, set2);
        count += equalSet(arr, idx + 1, sum1, set1, sum2 + arr[idx], set2 + arr[idx] + " ");

        return count;
    }

    // OR //

    public static int equalSet_01(int[] arr, int idx, int[] subsetSum, ArrayList<ArrayList<Integer>> ans) {
        if (idx == arr.length) {
            int s = subsetSum[0];
            for (int ele : subsetSum)
                if (s != ele)
                    return 0;

            for (ArrayList<Integer> a : ans)
                System.out.print(a + " ");
            System.out.println();
            return 1;
        }

        int count = 0;
        for (int i = 0; i < subsetSum.length; i++) {
            ArrayList<Integer> set = ans.get(i);
            set.add(arr[idx]);
            subsetSum[i] += arr[idx];
            count += equalSet_01(arr, idx + 1, subsetSum, ans);
            subsetSum[i] -= arr[idx];
            set.remove(set.size() - 1);
            // This check is applied so that the first element in any set will remain fixed.
            // This does so to prevent duplicacy or mirror images.
            if (set.size() == 0)
                break;
        }
        return count;

    }

    // K Subsets With Equal Sum
    public static void solution(int[] arr, int idx, int[] subsetSum, ArrayList<ArrayList<Integer>> ans) {
        // write your code here
        if (idx == arr.length) {
            int s = subsetSum[0];
            for (int ele : subsetSum)
                if (s != ele)
                    return;

            for (ArrayList a : ans)
                System.out.print(a + " ");
            System.out.println();
            return;
        }

        for (int i = 0; i < subsetSum.length; i++) {
            ArrayList<Integer> set = ans.get(i);
            set.add(arr[idx]);
            subsetSum[i] += arr[idx];

            solution(arr, idx + 1, subsetSum, ans);

            subsetSum[i] -= arr[idx];
            set.remove(set.size() - 1);
            if (set.size() == 0)
                break;
        }
    }

    public static void equalSet_01(int[] arr) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < 2; i++)
            ans.add(new ArrayList<>());

        int sum = 0;
        for (int ele : arr)
            sum += ele;

        if (sum % 2 != 0)
            return;

        int[] subsetSum = new int[2];
        System.out.println(equalSet_01(arr, 0, subsetSum, ans));
    }


    // K-partitions
    static int count = 1;

	public static void solution(int i, int n, ArrayList<ArrayList<Integer>> ans) {
		if(i > n) {
            // This check is to verify that if the last subset is empty or not. If it is then answer is not possible.
			if(ans.get(ans.size() - 1).size() == 0)
				return;
			
			System.out.print(count++ + ". ");
			for(ArrayList<Integer> a : ans) 
				System.out.print(a + " ");
			System.out.println();
			return;
		}
		for(ArrayList<Integer> a : ans) {
			a.add(i);
			solution(i + 1, n, ans);
			a.remove(a.size() - 1);
			if(a.size() == 0)
				break;
		}
	}

    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, 40, 50, 60, 70, 80 };
        // System.out.println(equalSet(arr, 0, 0, "", 0, ""));
        // To prevent mirror answers we'd fix the one number in array.
        // System.out.println(equalSet(arr, 1, 10, "10 ", 0, ""));

        equalSet_01(arr);
    }

}
