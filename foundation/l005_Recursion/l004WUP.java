import java.lang.reflect.Array;
import java.util.ArrayList;

public class l004WUP {
    // count will give the number of possible solutions and we'll use it in below
    // code which could help us in understanding DP better.
    // Get Susbequence
    public static int getSubsequence(String str, String ans, ArrayList<String> res) {
        if (str.length() == 0) {
            res.add(ans);
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        count += getSubsequence(str.substring(1), ans, res);
        count += getSubsequence(str.substring(1), ans + str.charAt(0), res);

        return count;
    }

    // Print Kpc
    public static String[] keyPad = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

    public static int printKPC(String str, String asf) {
        if (str.length() == 0) {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        String word = keyPad[str.charAt(0) - '0'];
        for (int i = 0; i < word.length(); i++) {
            count += printKPC(str.substring(1), asf + word.charAt(i));
        }
        return count;
    }

    // Print Stair Paths
    public static int printStairPaths(int n, String path) {
        if (n == 0) {
            System.out.println(path);
            return 1;
        }
        int count = 0;
        for (int i = 1; i <= 3 && n - i >= 0; i++)
            count += printStairPaths(n - i, path + i);
        return count;
    }

    // BoardPath
    public static int boardPath(int n, String ans) {
        if (n == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int dice = 1; dice <= 6 && n - dice >= 0; dice++)
            count += boardPath(n - dice, ans + dice);

        return count;
    }

    // BoardPath on Array
    public static int boardPathOnArray(int n, int[] move, String ans) {
        if (n == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < move.length && n - move[i] >= 0; i++)
            count += boardPathOnArray(n - move[i], move, ans + move[i]);
        return count;
    }

    // Print Maze Paths
    public static int printMazePaths(int sr, int sc, int dr, int dc, String psf) {
        if (sr == dr && sc == dc) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        if (sc + 1 <= dc)
            count += printMazePaths(sr, sc + 1, dr, dc, psf + "h");
        if (sr + 1 <= dr)
            count += printMazePaths(sr + 1, sc, dr, dc, psf + "v");
        return count;
    }

    // Print Maze Paths With Jumps
    public static int printMazePathsWithJumps(int sr, int sc, int dr, int dc, String psf) {
        if (sr == dr && sc == dc) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int jump = 1; jump + sc <= dc; jump++)
            count += printMazePathsWithJumps(sr, sc + jump, dr, dc, psf + "h" + jump);

        for (int jump = 1; jump + sr <= dr; jump++)
            count += printMazePathsWithJumps(sr + jump, sc, dr, dc, psf + "v" + jump);

        for (int jump = 1; jump + sc <= dc && jump + sr <= dr; jump++)
            count += printMazePathsWithJumps(sr + jump, sc + jump, dr, dc, psf + "d" + jump);
        return count;
    }

    // Print Permutations => "aba" - [aba, aab, baa, baa, aab, aba]
    public static int printPermutations(String str, String asf) {
        if (str.length() == 0) {
            System.out.print(asf);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < str.length(); i++)
            count += printPermutations(str.substring(0, i) + str.substring(i + 1), asf + str.charAt(i));
        return count;
    }

    // Print Permutations Without Duplicates => "aba" - [aab, aba, baa]
    public static int permutationsWithoutDuplicates(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        char prev = '$';
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != prev) {
                count += permutationsWithoutDuplicates(str.substring(0, i) + str.substring(i + 1), ans + str.charAt(i));
            }
            prev = str.charAt(i);
        }
        return count;
    }

    public static void permutationsWithoutDuplicates(String str) {
        StringBuilder sb = new StringBuilder();
        int[] freq = new int[26];
        for (int i = 0; i < str.length(); i++)
            freq[str.charAt(i) - 'a']++;

        for (int i = 0; i < 26; i++)
            for (int j = 0; j < freq[i]; j++)
                sb.append((char) (i + 'a'));

        System.out.println(permutationsWithoutDuplicates(sb.toString(), ""));
    }

    // Print Encodings
    public static int printEncodings(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        char ch = str.charAt(0);
        if (ch == '0')
            return 0;
        count += printEncodings(str.substring(1), ans + (char) ('a' + ch - '1'));
        if (str.length() > 1) {
            int num = (ch - '0') * 10 + (str.charAt(1) - '0');
            if (num <= 26)
                count += printEncodings(str.substring(2), ans + (char) ('a' + num - 1));
        }
        return count;
    }

    // 46. Permutations
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        getPermutations(nums, 0, res, ans);
        return res;
    }

    public void getPermutations(int[] nums, int idx, List<List<Integer>> res, List<Integer> ans) {
        if (idx == nums.length) {
            List<Integer> base = new ArrayList<>(ans);
            res.add(base);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= -10 && nums[i] <= 10) {
                int val = nums[i];
                nums[i] = -11;
                ans.add(val);
                getPermutations(nums, idx + 1, res, ans);
                ans.remove(ans.size() - 1);
                nums[i] = val;
            }
        }
    }

    // 47. Permutations II
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        Arrays.sort(nums);
        getPermutations(nums, 0, res, ans);
        return res;
    }

    public void getPermutations(int[] nums, int idx, List<List<Integer>> res, List<Integer> ans) {
        if (idx == nums.length) {
            List<Integer> base = new ArrayList<>(ans);
            res.add(base);
            return;
        }
        int prev = -20;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= -10 && nums[i] <= 10 && nums[i] != prev) {
                int val = nums[i];
                nums[i] = -11;
                ans.add(val);
                getPermutations(nums, idx + 1, res, ans);
                ans.remove(ans.size() - 1);
                nums[i] = val;
            }
            prev = nums[i];
        }
    }

    public static void main(String[] args) {
        ArrayList<String> res = new ArrayList<>();
        // System.out.println(getSubsequence("abc", "", res));
        // System.out.println(res);
        // System.out.println(boardPath(4, ""));
        // System.out.println(printPermutations("aba", ""));
        // permutationsWithoutDuplicates("aba");
        System.out.println(printEncodings("655196", ""));
    }
}
