import java.util.*;

public class l002_RecursionTree {

    // <---------------------COIN CHANGE-------------------------------------->
    // CASE - I : Here, infinite supply of coins are provided to meet the given
    // target.
    // Permutations of coins when supply is unlimited:
    // NCR Method :-
    public static int infiPermutation(int[] coins, int target, String asf) {
        if (target == 0) {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < coins.length; i++) {
            if (target - coins[i] >= 0)
                count += infiPermutation(coins, target - coins[i], asf + coins[i] + " ");
        }

        return count;
    }

    // Subsequence Method :-
    public static int infiPermutation_subseq(int[] coins, int idx, int target, String asf) {
        if (target == 0 || idx >= coins.length) {
            if (target == 0) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (target - coins[idx] >= 0)
            count += infiPermutation_subseq(coins, 0, target - coins[idx], asf + coins[idx] + " ");
        count += infiPermutation_subseq(coins, idx + 1, target, asf);
        return count;
    }

    // Combinations of coins when supply is unlimited:
    // NCR Method :-
    public static int infiCombination(int[] coins, int target, int idx, String asf) {
        if (target == 0) {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        for (int i = idx; i < coins.length; i++) {
            if (target - coins[i] >= 0)
                count += infiCombination(coins, target - coins[i], i, asf + coins[i] + " ");
        }
        return count;
    }

    // Subsequence Method :-
    public static int infiCombination_subseq(int[] coins, int idx, int target, String asf) {
        if (target == 0 || idx >= coins.length) {
            if (target == 0) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if (target - coins[idx] >= 0)
            count += infiCombination_subseq(coins, idx, target - coins[idx], asf + coins[idx] + " ");
        count += infiCombination_subseq(coins, idx + 1, target, asf);
        return count;
    }

    // CASE - II : Here, finite supply of coins are provided to meet the given
    // target.
    // Permutations of coins when supply is limited:
    // NCR Method :-
    public static int finitePermutation(int[] coins, int target, String asf) {
        if (target == 0) {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] > 0 && target - coins[i] >= 0) {
                int val = coins[i];
                coins[i] = -coins[i];
                count += finitePermutation(coins, target - val, asf + val + " ");
                coins[i] = -coins[i];
            }
        }
        return count;
    }

    // Subsequence Method :-
    public static int finitePermutation_subseq(int[] coins, int idx, int target, String asf) {
        if (target == 0 || idx >= coins.length) {
            if (target == 0) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if (target - coins[idx] >= 0 && coins[idx] > 0) {
            int val = coins[idx];
            coins[idx] = -coins[idx];
            count += finitePermutation_subseq(coins, 0, target - val, asf + val + " ");
            coins[idx] = -coins[idx];
        }
        count += finitePermutation_subseq(coins, idx + 1, target, asf);
        return count;
    }

    // Combinations of coins when supply is unlimited:
    // NCR Method :-
    public static int finiteCombination(int[] coins, int target, int idx, String asf) {
        if (target == 0) {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        for (int i = idx; i < coins.length; i++) {
            if (target - coins[i] >= 0)
                count += finiteCombination(coins, target - coins[i], i + 1, asf + coins[i] + " ");
        }
        return count;
    }

    // Subsequence Method :-
    public static int finiteCombination_subseq(int[] coins, int idx, int target, String asf) {
        if (target == 0 || idx >= coins.length) {
            if (target == 0) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if (target - coins[idx] >= 0)
            count += finiteCombination_subseq(coins, idx + 1, target - coins[idx], asf + coins[idx] + " ");
        count += finiteCombination_subseq(coins, idx + 1, target, asf);
        return count;
    }

    // 39. Combination Sum
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        uniqueCombination_1(candidates, 0, target, ans, res);
        return res;
    }

    public void uniqueCombination_1(int[] arr, int idx, int target, List<Integer> ans, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(ans));
            return;
        }
        for (int i = idx; i < arr.length; i++) {
            if (target - arr[i] >= 0) {
                ans.add(arr[i]);
                uniqueCombination_1(arr, i, target - arr[i], ans, res);
                ans.remove(ans.size() - 1);
            }
        }
    }

    // 40. Combination Sum II
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        Arrays.sort(candidates);
        uniqueCombination_(candidates, 0, target, ans, res);
        return res;
    }

    public void uniqueCombination_(int[] arr, int idx, int target, List<Integer> ans, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(ans));
            return;
        }
        int prev = 0;
        for (int i = idx; i < arr.length; i++) {
            if (target - arr[i] >= 0 && prev != arr[i]) {
                ans.add(arr[i]);
                uniqueCombination_(arr, i + 1, target - arr[i], ans, res);
                ans.remove(ans.size() - 1);
                prev = arr[i];
            }
        }
    }

    // 216. Combination Sum III
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        uniqueCombination1(ans, res, k, 1, n);
        return res;
    }

    public void uniqueCombination1(List<Integer> ans, List<List<Integer>> res, int k, int idx, int target) {
        if (target == 0 && ans.size() == k) {
            res.add(new ArrayList<>(ans));
            return;
        }

        for (int i = idx; i <= 9; i++) {
            if (target - i >= 0) {
                ans.add(i);
                uniqueCombination1(ans, res, k, i + 1, target - i);
                ans.remove(ans.size() - 1);
            }
        }
    }

    // 77. Combinations
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        uniqueCombination(res, ans, k, 1, n);
        return res;
    }

    public void uniqueCombination(List<List<Integer>> res, List<Integer> ans, int k, int idx, int n) {
        if (ans.size() == k) {
            res.add(new ArrayList<>(ans));
            return;
        }
        for (int i = idx; i <= n; i++) {
            ans.add(i);
            uniqueCombination(res, ans, k, i + 1, n);
            ans.remove(ans.size() - 1);
        }
    }

    // <----------------- QUEEN SET ---------------------->
    // tnb : total no boxes, bno : box no, tnq : total No queen, qpsf : queen placed
    // so far
    public static int queenCombination1D(int tnb, int bno, int tnq, int qpsf, String asf) {
        if (qpsf > tnq) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        for (int i = bno; i <= tnb; i++)
            count += queenCombination1D(tnb, i + 1, tnq, qpsf + 1, asf + " box" + i + "->" + "queen" + qpsf + "\t");
        return count;
    }

    public static int queenCombination1D_subseq(int tnb, int bno, int tnq, int qpsf, String asf) {
        if (qpsf > tnq || bno > tnb) {
            if (qpsf > tnq) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        count += queenCombination1D_subseq(tnb, bno + 1, tnq, qpsf + 1, asf + "b" + bno + "->" + "q" + qpsf + "\t");
        count += queenCombination1D_subseq(tnb, bno + 1, tnq, qpsf, asf);
        return count;
    }

    public static int queenPermutation1D(int tnb, boolean[] vis, int tnq, int qpsf, String asf) {
        if (qpsf > tnq) {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        for (int i = 1; i <= tnb; i++) {
            if (!vis[i]) {
                vis[i] = true;
                count += queenPermutation1D(tnb, vis, tnq, qpsf + 1, asf + "box" + i + "->" + "q" + qpsf + "\t");
                vis[i] = false;
            }
        }
        return count;
    }

    public static int queenPermutation1D_subseq(int tnb, boolean[] vis, int bno, int tnq, int qpsf, String asf) {
        if(qpsf > tnq || bno > tnb) {
            if(qpsf > tnq) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count = 0; 
        if(!vis[bno]) {
            vis[bno] = true;
            count += queenPermutation1D_subseq(tnb, vis, 1, tnq, qpsf + 1, asf + "box" + bno + "->" + "q" + qpsf + "\t");
            vis[bno] = false;
        }
        count += queenPermutation1D_subseq(tnb, vis, bno + 1, tnq, qpsf, asf);
        return count;
    }

    // <----------------- QUEEN SET - 2D ---------------------->
    // tnb : total no boxes, bno : box no, tnq : total No queen, qpsf : queen placed
    // so far, r: row of that box, c: column of that box.
    public static int queenCombination2D(boolean[][] box, int bno, int tnq, String asf) {
        if(tnq == 0) {
            System.out.println(asf);
            return 1;
        }
        int n = box.length, m = box[0].length;
        int count = 0;
        for(int i = bno; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            count += queenCombination2D(box, i + 1, tnq - 1, asf + "(" + r + ", " + c + ") ");
        }
        return count;
    }

    public static int queenCombination2D_subseq(boolean[][] box, int bno, int tnq, String asf) {
        int n = box.length, m = box[0].length;
        if(tnq == 0 || bno == n * m) {
            if(tnq == 0) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count = 0;
        int r = bno / m;
        int c = bno % m;
        count += queenCombination2D_subseq(box, bno + 1, tnq - 1, asf +"(" + r + ", " + c + ") ");
        count += queenCombination2D_subseq(box, bno + 1, tnq, asf);
        return count;
    }

    public static int queenPermutation2D(boolean[][] box, int tnq, String asf) {
        if(tnq == 0) {
            System.out.println(asf);
            return 1;
        }
        int n = box.length, m = box[0].length;
        int count = 0;
        for(int i = 0; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if(!box[r][c]) {
                box[r][c] = true;
                count += queenPermutation2D(box, tnq - 1, asf + "(" + r + ", " + c + ") ");
                box[r][c] = false;
            }
        }
        return count;
    }

    public static int queenPermutation2D_subseq(boolean[][] box, int bno, int tnq, String asf) {
        int n = box.length, m = box[0].length;
        if(tnq == 0 || bno == n * m) {
            if(tnq == 0) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count = 0;
        int r = bno / m;
        int c = bno % m;
        if(!box[r][c]) {
            box[r][c] = true;
            count += queenPermutation2D_subseq(box, 0, tnq - 1, asf +"(" + r + ", " + c + ") ");
            box[r][c] = false;
        }
        count += queenPermutation2D_subseq(box, bno + 1, tnq, asf);
        return count;
    }

    public static void main(String[] args) {
        int[] coins = { 2, 3, 5, 7 };
        // System.out.println(infiCombination(coins, 10, 0, ""));
        // System.out.println(finiteCombination(coins, 10, 0, ""));
        // System.out.println(infiCombination_subseq(coins, 0, 10, ""));
        // System.out.println(finiteCombination_subseq(coins, 0, 10, ""));

        int tnb = 7;
        boolean[] vis = new boolean[tnb + 1];
        // System.out.println(queenCombination1D(tnb, 1, 4, 1, ""));
        // System.out.println(queenCombination1D_subseq(tnb, 1, 4, 1, ""));
        // System.out.println(queenPermutation1D(tnb, vis, 4, 1, ""));
        // System.out.println(queenPermutation1D_subseq(tnb, vis, 1, 4, 1, ""));

        int tnq = 4;
        boolean[][] box = new boolean[4][4];
        // System.out.println(queenPermutation2D(box, tnq, ""));
        System.out.println(queenPermutation2D_subseq(box, 0, tnq, ""));

    }
}
