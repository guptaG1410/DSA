import java.util.LinkedList;
import java.util.Arrays;

public class questions {

    // Balanced Brackets
    public static boolean balancedBrack(String exp) {
        if (exp == null || exp.length() <= 3)
            return false;

        LinkedList<Character> st = new LinkedList<>();
        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{')
                st.addFirst(ch);
            else if (ch == ')' || ch == ']' || ch == '}') {
                if (st.size() == 0)
                    return false;
                else if (ch == ')' && st.getFirst() == '(')
                    st.removeFirst();
                else if (ch == ']' && st.getFirst() == '[')
                    st.removeFirst();
                else if (ch == '}' && st.getFirst() == '{')
                    st.removeFirst();
                else
                    return false;
            }
        }
        return st.size() == 0;
    }

    // Duplicate Parenthesis
    public static boolean duplicateParanthesis(String str) {
        LinkedList<Character> st = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            boolean flag = false;

            while (st.size() != 0 && ch == ')' && st.getFirst() != '(') {
                flag = true;
                st.removeFirst();
            }
            if (flag)
                st.removeFirst();
            else if (!flag && ch == ')')
                return true;
            else
                st.addFirst(ch);
        }
        return false;
    }

    // Next Greater Element To The Right
    public static int[] solve(int[] arr) {
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

    // Celebrity Problem
    // TC:O(N), SC: O(N);

    public static boolean knows(int[][] arr, int a, int b) {
        return arr[a][b] == 1 ? true : false;
    }

    public static void findCelebrity(int[][] arr) {
        // if a celebrity is there print it's index (not position), if there is not then
        // print "none"
        LinkedList<Integer> st = new LinkedList<>();
        for (int i = 0; i < arr.length; i++)
            st.addFirst(i);

        while (st.size() > 1) {
            int a = st.removeFirst();
            int b = st.removeFirst();

            if (knows(arr, a, b))
                st.addFirst(b);
            else
                st.addFirst(a);
        }

        if (st.size() == 0) {
            System.out.println("none");
            return;
        }

        int cel = st.removeFirst();

        for (int i = 0; i < arr.length; i++) {
            if (i != cel && (knows(arr, cel, i) || !knows(arr, i, cel))) {
                System.out.println("none");
                return;
            }
        }
        System.out.println(cel);
    }

    // TC: O(N), SC: O(1).
    public static void findCelebrity_(int[][] arr) {
        // if a celebrity is there print it's index (not position), if there is not then
        // print "none"
        int cel = 0;
        for (int i = 1; i < arr.length; i++)
            if (knows(arr, cel, i))
                cel = i;

        for (int i = 0; i < arr.length; i++) {
            if (i != cel && (knows(arr, cel, i) || !knows(arr, i, cel))) {
                System.out.println("none");
                return;
            }
        }
        System.out.println(cel);
    }

    // Merge Overlapping Interval
    // TC:O(N LOGN) + O(N)
    // SC: O(N)
    public static void mergeOverlappingIntervals(int[][] arr) {

        Arrays.sort(arr, (a, b) -> {
            return a[0] - b[0];
        });

        LinkedList<int[]> st = new LinkedList<>();
        for (int[] a : arr) {
            int minStartTime = a[0];
            int maxEndTime = a[1];
            while (st.size() != 0 && a[0] <= st.getFirst()[1]) {
                minStartTime = st.getFirst()[0];
                maxEndTime = Math.max(maxEndTime, st.getFirst()[1]);
                st.removeFirst();
            }
            st.addFirst(new int[] { minStartTime, maxEndTime });
        }

        while (st.size() != 0) {
            int[] a = st.removeLast();
            System.out.println(a[0] + " " + a[1]);
        }
    }

    // OR
    // TC:O(N LOGN) + O(N)
    // SC: O(1)
    public static void mergeOverlappingIntervals_(int[][] arr) {

        Arrays.sort(arr, (a, b) -> {
            return a[0] - b[0];
        });

        int idx = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[idx][1] >= arr[i][0])
                arr[idx][1] = Math.max(arr[idx][1], arr[i][1]);
            else {
                idx++;
                arr[idx] = arr[i];
            }
        }

        for (int i = 0; i <= idx; i++)
            System.out.println(arr[i][0] + " " + arr[i][1]);
    }

    // Next Greater Element To The Left
    public static int[] solve_(int[] arr) {
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
}
