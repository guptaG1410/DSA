import java.util.*;

public class l003WAL {

    // Get Subsequence
    public static ArrayList<String> getSubsequence(String str) {
        if (str.length() == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        char ch = str.charAt(0);
        ArrayList<String> recAns = getSubsequence(str.substring(1));
        ArrayList<String> ans = new ArrayList<>(recAns); // AL<>(recAns), here "recAns" act as an constructor for "ans"
                                                         // if do this then we shouldn't add the String present in
                                                         // "recAns" separately.
        for (String s : recAns) {
            // ans.add(s); //Here if want to add the "s" in "ans" then we've to remove
            // "recAns" as a constructor for "ans";
            ans.add(ch + s);
        }
        return ans;
    }

    // Letter Combinations of a Phone Number
    public static String[] keyPad = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

    public static ArrayList<String> getKPC(String str) {
        if (str.length() == 0) {
            return new ArrayList<String>(Arrays.asList(""));
        }

        char ch = str.charAt(0);
        String word = keyPad[ch - '0'];
        ArrayList<String> recAns = getKPC(str.substring(1));
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            for (String s : recAns)
                ans.add(word.charAt(i) + s);
        }
        return ans;
    }

    // 91. Decode Ways - Leetcode (Better to approach with DP instead of Recursion
    // otherwise u'll get TLE)
    public List<String> decodeWays(String str) {
        if (str.length() == 0)
            return new ArrayList<>(Arrays.asList(""));

        char ch = str.charAt(0);
        if (ch == '0')
            return new ArrayList<>();
        List<String> recAns1 = decodeWays(str.substring(1));
        List<String> ans = new ArrayList<>();
        for (String s : recAns1)
            ans.add((char) ('a' + ch - '1') + s);

        if (str.length() > 1) {
            int num = (ch - '0') * 10 + (str.charAt(1) - '0');
            if (num <= 26) {
                List<String> recAns2 = decodeWays(str.substring(2));
                for (String s : recAns2)
                    ans.add((char) ('a' + num - 1) + s);
            }
        }
        return ans;
    }

    // In this question, we've to print all possible combinations of letters from
    // given String in numbers same as Nokia KeyPad Problem but in this problem
    // we've extra indices(10th and 11th). So, it's a mixture of above two problems.
    public static String[] keyNotes = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz", "+-*",
            "<>/%" };

    public static ArrayList<String> decodeWays2(String str) {
        if (str.length() == 0)
            return new ArrayList<>(Arrays.asList(""));

        char ch = str.charAt(0);
        String word = keyNotes[ch - '0'];
        ArrayList<String> recAns1 = decodeWays2(str.substring(1));
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < word.length(); i++)
            for (String s : recAns1)
                ans.add(word.charAt(i) + s);

        if (str.length() > 1) {
            int num = (ch - '0') * 10 + (str.charAt(1) - '0');
            if (num == 10 || num == 11) {
                String word1 = keyNotes[num];
                ArrayList<String> recAns2 = decodeWays2(str.substring(2));
                for (int i = 0; i < word1.length(); i++)
                    for (String s : recAns2)
                        ans.add(word1.charAt(i) + s);
            }

        }
        return ans;
    }

    // Get Stair Paths
    public static ArrayList<String> getStairPaths(int n) {
        if (n == 0)
            return new ArrayList<>(Arrays.asList(""));

        ArrayList<String> ans = new ArrayList<String>();
        for (int i = 1; i <= 3 && n - i >= 0; i++) {
            ArrayList<String> recAns = getStairPaths(n - i);
            for (String s : recAns)
                ans.add(i + s);
        }

        return ans;
    }

    // Board Path - Here, we've to reach a given number with the help of a dice.
    public static ArrayList<String> boardPath(int n) {
        if (n == 0)
            return new ArrayList<>(Arrays.asList(""));

        ArrayList<String> myAns = new ArrayList<>();
        for (int dice = 1; dice <= 6 && n - dice >= 0; dice++) {
            ArrayList<String> recAns = boardPath(n - dice);
            for (String s : recAns) {
                myAns.add(dice + s);
            }
        }

        return myAns;
    }

    // Board Path On Array - Here, we've to reach a given number on board but in
    // this our moves is limited within an array.
    public static ArrayList<String> boardPathOnArray(int n, int[] move) {
        if (n == 0)
            return new ArrayList<>(Arrays.asList(""));

        ArrayList<String> myAns = new ArrayList<>();
        for (int i = 0; i < move.length && n - move[i] >= 0; i++) {
            ArrayList<String> recAns = boardPathOnArray(n - move[i], move);
            for (String s : recAns) {
                myAns.add(move[i] + s);
            }
        }

        return myAns;
    }

    // GET MAZE PATHS - You are standing in the top-left corner and have to reach
    // the bottom-right corner. Two moves are allowed 'h' (1-step horizontal)
    // and 'v' (1-step vertical).
    public static ArrayList<String> getMazePaths(int sr, int sc, int dr, int dc) {
        if (sr == dr && sc == dc)
            return new ArrayList<>(Arrays.asList(""));

        ArrayList<String> ans = new ArrayList<>();
        if (sc + 1 <= dc) {
            ArrayList<String> recAns = getMazePaths(sr, sc + 1, dr, dc);
            for (String s : recAns)
                ans.add("h" + s);
        }
        if (sr + 1 <= dr) {
            ArrayList<String> recAns = getMazePaths(sr + 1, sc, dr, dc);
            for (String s : recAns)
                ans.add("v" + s);
        }
        return ans;
    }

    // Follow Up - Two moves are allowed 'h' (1-step horizontal),'v' (1-step
    // vertical) and 'd' (1-step diagonal).
    public static ArrayList<String> getMazePaths_HDV(int sr, int sc, int dr, int dc) {
        if (sr == dr && sc == dc)
            return new ArrayList<>(Arrays.asList(""));

        ArrayList<String> ans = new ArrayList<>();
        if (sc + 1 <= dc) {
            ArrayList<String> recAns = getMazePaths_HDV(sr, sc + 1, dr, dc);
            for (String s : recAns)
                ans.add("h" + s);
        }
        if (sc + 1 <= dc && sr + 1 <= dr) {
            ArrayList<String> recAns = getMazePaths_HDV(sr + 1, sc + 1, dr, dc);
            for (String s : recAns)
                ans.add("d" + s);
        }
        if (sr + 1 <= dr) {
            ArrayList<String> recAns = getMazePaths_HDV(sr + 1, sc, dr, dc);
            for (String s : recAns)
                ans.add("v" + s);
        }

        return ans;
    }

    // Get Maze Path With Jumps - You are standing in the top-left corner and have
    // to reach the bottom-right corner.
    // In a single move you are allowed to jump 1 or more steps horizontally (as h1,
    // h2, .. ), or 1 or more steps vertically (as v1, v2, ..) or 1 or more steps
    // diagonally (as d1, d2, ..).
    public static ArrayList<String> getMazePathsWithJumps(int sr, int sc, int dr, int dc) {
        if (sr == dr && sc == dc)
            return new ArrayList<>(Arrays.asList(""));

        ArrayList<String> ans = new ArrayList<String>();
        for (int jump = 1; sc + jump <= dc; jump++) {
            ArrayList<String> recAns = getMazePathsWithJumps(sr, sc + jump, dr, dc);
            for (String s : recAns)
                ans.add("h" + jump + s);
        }
        for (int jump = 1; sr + jump <= dr; jump++) {
            ArrayList<String> recAns = getMazePathsWithJumps(sr + jump, sc, dr, dc);
            for (String s : recAns)
                ans.add("v" + jump + s);
        }
        for (int jump = 1; sc + jump <= dc && sr + jump <= dr; jump++) {
            ArrayList<String> recAns = getMazePathsWithJumps(sr + jump, sc + jump, dr, dc);
            for (String s : recAns)
                ans.add("d" + jump + s);
        }
        return ans;
    }

    public static void main(String[] args) {
        // for (String s : getKPC("0"))
        // System.out.print(s + "\t");

        for (String s : boardPath(4))
            System.out.println(s);
    }
}
