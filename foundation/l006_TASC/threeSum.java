import java.util.*;

// IN THIS PROBLEM, WE'VE TO FIND 3-PAIRS THAT WILL SUM UP EQUAL TO TARGET.

public class threeSum {
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int segregate(int[] arr, int si, int ei, int pivot){
        swap(arr, pivot, ei);
        int p = -1, itr = 0;
        while(itr <= ei) {
            if(arr[itr] <= arr[ei])
                swap(arr, itr, ++p);
            itr++;
        }
        return p;
    }

    public static void quickSort(int[] arr, int si, int ei) {
        if(si > ei)
            return;
        
        int pIdx = ei;
        int p = segregate(arr, si, ei, pIdx);

        quickSort(arr, si, p - 1);
        quickSort(arr, p + 1, ei);
    }

    public static ArrayList<int[]> twoSum(int[] arr, int target, int si, int ei) {
        ArrayList<int[]> ans = new ArrayList<>();
        while(si < ei) {
            int sum = arr[si] + arr[ei];
            if(sum == target)
                ans.add(new int[]{arr[si++], arr[ei--]});
            else if(sum < target)
                si++;
            else
                ei--;
        }
        return ans;
    }

    public static ArrayList<int[]> threeSum(int[] arr, int target, int si, int ei) {
        ArrayList<int[]> ans = new ArrayList<>();
        for(int i = si; i <= ei; i++) {
            ArrayList<int[]> smallAns = twoSum(arr, target - arr[i], i + 1, ei);

            for(int[] a : smallAns)
                ans.add(new int[]{arr[i], a[0], a[1]});
        }
        return ans;
    }

    public static void threeSum(int[] arr, int target) {
        int n = arr.length;
        quickSort(arr, 0, n - 1);

        ArrayList<int[]> ans = threeSum(arr, target, 0, n - 1);

        for(int[] a : ans) 
            System.out.println("[ " + a[0] + ", " + a[1] + ", " + a[2] + " ]");
    }

    public static void main(String[] args) {
        int[] arr = { -2, -3, 7, 5, 8, 15, 3, 2, 9, 10, 19 };
        threeSum(arr, 25);
    }
}
