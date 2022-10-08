package levelUp;

import java.util.*;
import java.util.LinkedList;

public class l001 {
    // Next Greater Element To The Right
    public static int[] NGOR(int[] arr) {
        LinkedList<Integer> st = new LinkedList<>();
        int[] ans = new int[arr.length];
        Arrays.fill(ans, -1);

        for (int i = 0; i < arr.length; i++) {
            while (st.size() != 0 && arr[i] > arr[st.getFirst()])
                ans[st.removeFirst()] = arr[i];

            st.addFirst(i);
        }

        return ans;
    }

    // Next Smaller Element To The Right
    public static int[] NSOR(int[] arr) {
        LinkedList<Integer> st = new LinkedList<>();
        int[] ans = new int[arr.length];
        Arrays.fill(ans, arr.length);

        for (int i = 0; i < arr.length; i++) {
            while (st.size() != 0 && arr[i] < arr[st.getFirst()])
                ans[st.removeFirst()] = i;

            st.addFirst(i);
        }

        return ans;
    }

    // Next Greater Element To The Left
    public static int[] NGOL(int[] arr) {
        LinkedList<Integer> st = new LinkedList<>();
        int[] ans = new int[arr.length];
        Arrays.fill(ans, -1);

        for (int i = arr.length - 1; i >= 0; i--) {
            while (st.size() != 0 && arr[st.getFirst()] <= arr[i])
                ans[st.removeFirst()] = arr[i];

            st.addFirst(i);
        }
        return ans;
    }

    // Next Smaller Element To The Left
    public static int[] NSOL(int[] arr) {
        LinkedList<Integer> st = new LinkedList<>();
        int[] ans = new int[arr.length];
        Arrays.fill(ans, -1);

        for (int i = arr.length - 1; i >= 0; i--) {
            while (st.size() != 0 && arr[st.getFirst()] >= arr[i])
                ans[st.removeFirst()] = i;

            st.addFirst(i);
        }
        return ans;
    }

    // 503. Next Greater Element II
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        LinkedList<Integer> st = new LinkedList<>();
        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        for (int i = 0; i < 2 * n; i++) {
            while (st.size() != 0 && nums[st.getFirst()] < nums[i % n])
                ans[st.removeFirst()] = nums[i % n];

            if (i < n)
                st.addFirst(i);
        }
        return ans;
    }

    // Stock span problem
    public static int[] calculateSpan(int price[], int n) {
        int[] ans = new int[n];
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);

        for (int i = 0; i < n; i++) {
            while (st.getFirst() != -1 && price[st.getFirst()] <= price[i])
                st.removeFirst();

            ans[i] = i - st.getFirst();
            st.addFirst(i);
        }
        return ans;
    }

    // 901. Online Stock Span
    class StockSpanner {
        int index = 0;
        LinkedList<int[]> st = new LinkedList<>();

        public StockSpanner() {
            // {INDEX, VALUE}
            st.addFirst(new int[] { -1, -1 });
        }

        public int next(int price) {
            while (st.getFirst()[0] != -1 && st.getFirst()[1] <= price)
                st.removeFirst();

            int span = index - st.getFirst()[0];
            st.addFirst(new int[] { index++, price });
            return span;
        }
    }

    // 739. Daily Temperatures
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        LinkedList<Integer> st = new LinkedList<>();
        Arrays.fill(ans, 0);
        // st.addFirst(-1);

        for (int i = 0; i < n; i++) {
            while (st.size() != 0 && temperatures[st.getFirst()] < temperatures[i])
                ans[st.getFirst()] = i - st.removeFirst();

            st.addFirst(i);
        }
        return ans;
    }

    // 735. Asteroid Collision
    public int[] asteroidCollision(int[] asteroids) {
        LinkedList<Integer> st = new LinkedList<>();

        for (int ele : asteroids) {
            if (ele > 0) {
                st.addFirst(ele);
                continue;
            }

            while (st.size() != 0 && st.getFirst() > 0 && st.getFirst() < -ele)
                st.removeFirst();

            if (st.size() != 0 && st.getFirst() == -ele)
                st.removeFirst();
            else if (st.size() == 0 || st.getFirst() < 0)
                st.addFirst(ele);
        }

        int[] ans = new int[st.size()];
        int idx = ans.length - 1;
        while (st.size() != 0) {
            ans[idx--] = st.removeFirst();
        }
        return ans;
    }

    // 946. Validate Stack Sequences
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length;
        LinkedList<Integer> st = new LinkedList<>();
        int idx = 0;

        for (int ele : pushed) {
            st.addFirst(ele);

            while (st.size() != 0 && idx < n && st.getFirst() == popped[idx]) {
                st.removeFirst();
                idx++;
            }
        }
        return st.size() == 0;
    }

    // 856. Score of Parentheses
    public int scoreOfParentheses(String s) {
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(0);

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(')
                st.addFirst(0);
            else {
                int val = 0;
                int a = st.removeFirst();
                int b = st.removeFirst();

                if (a > 0)
                    val = (2 * a) + b;
                else
                    val = 1 + b;

                st.addFirst(val);
            }
        }
        return st.removeFirst();
    }

    // 921. Minimum Add to Make Parentheses Valid

    public int minAddToMakeValid(String s) {
        LinkedList<Character> st = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(')
                st.addFirst(ch);
            else {
                if (st.size() != 0 && st.getFirst() == '(')
                    st.removeFirst();
                else
                    st.addFirst(ch);
            }
        }
        return st.size();
    }

    // 84. Largest Rectangle in Histogram
    // TC: O(7N), SC: O(2N)
    public int largestRectangleArea(int[] heights) {
        int[] nsor = NSOR(heights); // O(3N)
        int[] nsol = NSOL(heights); // O(3N)

        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            maxArea = Math.max(maxArea, heights[i] * (nsor[i] - nsol[i] - 1));
        }
        return maxArea;
    }

    // OR //

    // TC: O(2N), SC: O(N)
    public int largestRectangleArea_(int[] heights) {
        int n = heights.length;
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);
        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            while (st.getFirst() != -1 && heights[st.getFirst()] >= heights[i]) {
                int height = heights[st.removeFirst()];
                int width = i - st.getFirst() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            st.addFirst(i);
        }

        while (st.getFirst() != -1) {
            int height = heights[st.removeFirst()];
            int width = n - st.getFirst() - 1;
            maxArea = Math.max(maxArea, height * width);
        }

        return maxArea;
    }

    // 85. Maximal Rectangle
    public int maximalRectangle(char[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int[] heights = new int[m];
        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                heights[j] = matrix[i][j] == '0' ? 0 : heights[j] + 1;

            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        return maxArea;
    }

    // 32. Longest Valid Parentheses
    public int longestValidParentheses(String s) {
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);
        int len = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (st.getFirst() != -1 && ch == ')' && s.charAt(st.getFirst()) == '(') {
                st.removeFirst();
                len = Math.max(len, i - st.getFirst());
            } else
                st.addFirst(i);
        }
        return len;
    }

    // 402. Remove K Digits
    public String removeKdigits(String num, int k) {
        ArrayList<Character> st = new ArrayList<>();
        for (int i = 0; i < num.length(); i++) {
            char ch = num.charAt(i);
            while (st.size() != 0 && st.get(st.size() - 1) > ch && k > 0) {
                k--;
                st.remove(st.size() - 1);
            }
            st.add(ch);
        }

        while (k-- > 0)
            st.remove(st.size() - 1);

        StringBuilder sb = new StringBuilder();
        boolean nonZeroVal = false;
        for (Character s : st) {
            if (s == '0' && !nonZeroVal)
                continue;
            nonZeroVal = true;
            sb.append(s);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

    // 316. Remove Duplicate Letters || 1081. Smallest Subsequence of Distinct
    // Characters
    public String removeDuplicateLetters(String s) {
        LinkedList<Character> st = new LinkedList<>();
        int[] freq = new int[26];
        boolean[] vis = new boolean[26];

        for (int i = 0; i < s.length(); i++)
            freq[s.charAt(i) - 'a']++;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            freq[ch - 'a']--;
            if (vis[ch - 'a'])
                continue;

            while (st.size() != 0 && st.getFirst() > ch && freq[st.getFirst() - 'a'] != 0) {
                vis[st.getFirst() - 'a'] = false;
                st.removeFirst();
            }
            st.addFirst(ch);
            vis[ch - 'a'] = true;
        }

        StringBuilder sb = new StringBuilder();
        while (st.size() != 0)
            sb.append(st.removeFirst());

        return sb.reverse().toString();
    }

    // 1249. Minimum Remove to Make Valid Parentheses
    public String minRemoveToMakeValid(String s) {
        LinkedList<Integer> st = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        char[] arr = s.toCharArray();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(')
                st.addFirst(i);
            else if (ch == ')') {
                if (st.size() != 0)
                    st.removeFirst();
                else
                    arr[i] = '$';
            }
        }

        while (st.size() != 0)
            arr[st.removeFirst()] = '$';

        for (int i = 0; i < s.length(); i++) {
            if (arr[i] != '$')
                sb.append(s.charAt(i));
        }

        return sb.toString();
    }

    // 895. Maximum Frequency Stack
    public class FreqStack {
        private class pair implements Comparable<pair> {
            int val = 0;
            int idx = 0;
            int freq = 0;

            pair(int val, int idx, int freq) {
                this.val = val;
                this.idx = idx;
                this.freq = freq;
            }

            public int compareTo(pair o) {
                if (this.freq == o.freq)
                    return o.idx - this.idx;
                else
                    return o.freq - this.freq;
            }
        }

        private PriorityQueue<pair> pq;
        private HashMap<Integer, Integer> freqMap;
        private int idx = 0;

        public FreqStack() {
            pq = new PriorityQueue<>();
            freqMap = new HashMap<>();
        }

        public void push(int val) {
            freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);
            pq.add(new pair(val, idx++, freqMap.get(val)));
        }

        public int pop() {
            pair rp = pq.remove();
            freqMap.put(rp.val, rp.freq - 1);
            if (freqMap.get(rp.val) == 0)
                freqMap.remove(rp.val);

            return rp.val;
        }
    }

    // 155. Min Stack
    class MinStack {
        LinkedList<Long> st;
        long minVal = 0;

        public MinStack() {
            st = new LinkedList<>();
        }

        public void push(int val) {
            long x = val;
            if (st.size() == 0) {
                st.addFirst(x);
                minVal = x;
                return;
            }

            if (minVal > x) {
                st.addFirst(2 * x - minVal);
                minVal = x;
            } else
                st.addFirst(x);

        }

        public void pop() {
            if (st.getFirst() < minVal)
                minVal = 2 * minVal - st.getFirst();

            st.removeFirst();
        }

        public int top() {
            if (st.getFirst() < minVal)
                return (int) minVal;

            return (int) (long) st.getFirst();
        }

        public int getMin() {
            return (int) minVal;
        }
    }

    // 636. Exclusive Time of Functions
    public int[] exclusiveTime(int n, List<String> logs) {
        class logPair {
            int id, timestamp, waitTime;
            boolean isStart = false;

            logPair(String str) {
                String[] arr = str.split(":");
                this.id = Integer.parseInt(arr[0]);
                this.timestamp = Integer.parseInt(arr[2]);
                this.waitTime = 0;
                this.isStart = arr[1].equals("start");
            }
        }
        LinkedList<logPair> st = new LinkedList<>();
        int[] ans = new int[n];

        for (String str : logs) {
            logPair log = new logPair(str);

            if (log.isStart)
                st.addFirst(log);
            else {
                logPair rp = st.removeFirst();
                ans[rp.id] += log.timestamp - rp.timestamp + 1 - rp.waitTime;

                if (st.size() > 0)
                    st.getFirst().waitTime += log.timestamp - rp.timestamp + 1;
            }
        }
        return ans;
    }

    // 853. Car Fleet
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        // {position, time}
        double[][] posTime = new double[n][2];
        for (int i = 0; i < n; i++) {
            posTime[i][0] = position[i] * 1.0;
            posTime[i][1] = ((target - position[i]) * 1.0) / speed[i];
        }

        Arrays.sort(posTime, (a, b) -> {
            return (int) (a[0] - b[0]);
        });

        int carFleet = 1;
        double prevTime = posTime[n - 1][1];
        for (int i = n - 2; i >= 0; i--) {
            if (posTime[i][1] > prevTime) {
                carFleet++;
                prevTime = posTime[i][1];
            }
        }

        return carFleet;
    }
}
