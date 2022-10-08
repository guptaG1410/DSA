import java.util.*;

public class Arrays {
    public static Scanner scn = new Scanner(System.in);

    public static int[] input(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        return arr;
    }

    public static void display(int[] arr) {
        for (int ele : arr)
            System.out.print(ele + "\t");
    }

    public static int maximum(int[] arr) {
        int maxEle = -(int) 1e9;
        for (int ele : arr) {
            maxEle = Math.max(maxEle, ele);
        }
        return maxEle;
    }

    public static int minimum(int[] arr) {
        int minEle = (int) 1e9;
        for (int ele : arr) {
            minEle = Math.min(minEle, ele);
        }
        return minEle;
    }

    public static int find(int[] arr, int data) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == data)
                return i;
        }
        return -1;
    }

    public static int span(int[] arr) {
        int maxEle = -(int) 1e9, minEle = (int) 1e9;
        for (int ele : arr) {
            maxEle = Math.max(maxEle, ele);
            minEle = Math.min(minEle, ele);
        }
        return maxEle - minEle;
    }

    public static void sum(int[] a, int[] b) {
        int n = a.length, m = b.length;
        int p = Math.max(n, m) + 1;
        int[] ans = new int[p];
        int i = n - 1, j = m - 1, k = p - 1, carry = 0;
        while (k >= 0) {
            int sum = carry + (i >= 0 ? a[i] : 0) + (j >= 0 ? b[j] : 0);
            ans[k] = sum % 10;
            carry = sum / 10;
            i--;
            j--;
            k--;
        }
        for (int idx = 0; idx < p; idx++) {
            if (idx == 0 && ans[idx] == 0)
                continue;
            System.out.println(ans[idx]);
        }

    }

    public static void diff(int[] a, int[] b) {
        int n = a.length, m = b.length;
        int p = Math.max(n, m);
        int[] ans = new int[p];
        int i = n - 1, j = m - 1, k = p - 1, borrow = 0;
        while (j >= 0) {
            int diff = b[j] - (i >= 0 ? a[i] : 0) + borrow;
            if (diff < 0) {
                diff += 10;
                borrow = -1;
            } else {
                borrow = 0;
            }
            ans[k] = diff;
            i--;
            j--;
            k--;
        }
        boolean notZero = false;
        for (int ele : ans) {
            if (ele != 0)
                notZero = true;

            if (notZero)
                System.out.println(ele);
        }

    }

    // Digit Frequency -
    public static int getDigitFrequency(int n, int d) {
        int count = 0;
        while (n != 0) {
            if (n % 10 == d)
                count++;
            n /= 10;
        }
        return count;
    }

    // follow up of Digit Frequency
    public static void digitFreq(long n, int[] query) {
        int[] freq = new int[10];
        while (n != 0) {
            freq[(int) n % 10]++;
            n /= 10;
        }
        for (int ele : query) {
            System.out.println(ele + " -> " + freq[ele]);
        }
    }

    // Subarray
    public static void subarray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j <= arr.length; j++) {
                for (int k = i; k < j; k++)
                    System.out.print(arr[k] + "	");
                System.out.println();
            }
        }
    }

    // Subsets of an Array
    public static void subSet(int[] arr) {
        int limit = (int) Math.pow(2, arr.length);
        for (int i = 0; i < limit; i++) {
            String set = "";
            int num = i;
            for (int j = arr.length - 1; j >= 0; j--) {
                int rem = num % 2;
                num /= 2;
                if (rem != 0)
                    set = arr[j] + "\t" + set;
                else
                    set = "-" + "\t" + set;
            }
            System.out.println(set);
        }
    }

    // 78. Subsets
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        int limit = (int) Math.pow(2, nums.length);

        for (int i = 0; i < limit; i++) {
            int num = i;
            List<Integer> curr = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                int rem = num % 2;
                num /= 2;
                if (rem != 0)
                    curr.add(nums[j]);
            }
            output.add(curr);
        }
        return output;
    }

    // Binary Search
    public static int binarySearch(int[] arr, int data) {
        int si = 0, ei = arr.length - 1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] == data)
                return mid;
            else if (arr[mid] > data)
                ei = mid - 1;
            else
                si = mid + 1;
        }
        return -1;
    }

    // First Index And Last Index
    public static int firstIndex(int[] arr, int data) {
        int si = 0, ei = arr.length - 1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] == data) {
                if (mid - 1 >= 0 && arr[mid - 1] == data)
                    ei = mid - 1;
                else
                    return mid;
            } else if (arr[mid] < data)
                si = mid + 1;
            else
                ei = mid - 1;
        }
        return -1;
    }

    public static int lastIndex(int[] arr, int data) {
        int si = 0, ei = arr.length - 1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] == data) {
                if (mid + 1 < arr.length && arr[mid + 1] == data)
                    si = mid + 1;
                else
                    return mid;
            } else if (arr[mid] < data)
                si = mid + 1;
            else
                ei = mid - 1;
        }
        return -1;
    }

    // 42. Trapping Rain Water - LEETCODE
    // Time Complexity - O(N)
    // Space Complexity - O(2N)
    public int trap(int[] height) {
        int n = height.length, ans = 0;
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        prefix[0] = height[0];
        for (int i = 1; i < n; i++)
            prefix[i] = Math.max(prefix[i - 1], height[i]);
        suffix[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--)
            suffix[i] = Math.max(suffix[i + 1], height[i]);

        for (int i = 0; i < n; i++)
            ans += Math.min(prefix[i], suffix[i]) - height[i];

        return ans;
    }

    // Time Complexity - O(N)
    // Space Complexity - O(1)
    public int trap_Optimized(int[] height) {
        int n = height.length;
        int l = 0, r = n - 1, ans = 0;
        int leftMax = 0, rightMax = 0;
        while (l <= r) {
            if (height[l] <= height[r]) {
                if (height[l] >= leftMax)
                    leftMax = height[l];
                else
                    ans += leftMax - height[l];

                l++;
            } else {
                if (height[r] >= rightMax)
                    rightMax = height[r];
                else
                    ans += rightMax - height[r];

                r--;
            }
        }
        return ans;
    }

    // Aggressive Cows
    public static int aggCows(int[] arr, int n, int cows) {
        // Arrays.sort(arr);
        int si = 1, ei = n - 1, potAns = 0;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (isPossibleToPlace(arr, n, cows, mid)) {
                potAns = mid;
                si = mid + 1;
            } else
                ei = mid - 1;
        }
        return potAns;
    }

    public static boolean isPossibleToPlace(int[] arr, int n, int cows, int dist) {
        int prevCow = arr[0], c = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i] - prevCow >= dist) {
                c++;
                prevCow = arr[i];
            }
            if (c == cows)
                return true;
        }
        return false;
    }

    // Eko-Eko(SPOJ) -[Same as book allocation]
    public static int mirkoNeed(int[] arr, int n, int woods) {
        int max = -(int) 1e9;
        for (int ele : arr)
            max = Math.max(max, ele);

        int si = 0, ei = max, potAns = 0;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (canCutOff(arr, n, woodReq, mid)) {
                potAns = mid;
                si = mid + 1;
            } else
                ei = mid - 1;
        }
        return potAns;
    }

    public static boolean canCutOff(int[] arr, int n, int m, int height) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > height)
                sum += arr[i] - height;
        }
        if (sum >= m)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = input(n);
        // System.out.println(maximum(arr));
        // System.out.println(minimum(arr));
        // System.out.println(find(arr, 8));
        // display(arr);
        // long n = scn.nextInt();
        // int[] query = new int[scn.nextInt()];
        // for(int i = 0; i < query.length; i++)
        // query[i] = scn.nextInt();
        // digitFreq(n, query);
        // int data = scn.nextInt();
        // binarySearch(arr, data);
        // int data = scn.nextInt();
        // int fi = firstIndex(arr, data);
        // if (fi == -1)
        // return;
        // int li = lastIndex(arr, data);

        // System.out.println(fi);
        // System.out.println(li);

        int[] arrr = { 1, 2, 4, 8, 9 };
        System.out.println(aggCows(arrr, arrr.length, 3));
    }

}