public class l001 {

    // Sort 01
    public static void sort01(int[] arr) {
        int p = -1, itr = 0;
        while (itr < arr.length) {
            if (arr[itr] == 0)
                swap(arr, ++p, itr);
            itr++;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Sort 012
    public static void sortColors(int[] arr) {
        int p = -1, itr = 0, p2 = arr.length - 1;
        while (itr <= p2) {
            if (arr[itr] == 0)
                swap(arr, ++p, itr++);
            else if (arr[itr] == 2)
                swap(arr, itr, p2--);
            else
                itr++;
        }
    }

    // Merge Two Sorted Arrays
    public static int[] mergeTwoSortedArrays(int[] a, int[] b) {
        int m = a.length, n = b.length;
        int i = 0, j = 0, k = 0;
        int[] c = new int[m + n];
        while (i < m && j < n) {
            if (a[i] < b[j])
                c[k++] = a[i++];
            else
                c[k++] = b[j++];
        }
        while (i < m)
            c[k++] = a[i++];

        while (j < n)
            c[k++] = b[j++];

        return c;
    }

    // 88. Merge Sorted Array
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j])
                nums1[k--] = nums1[i--];
            else
                nums1[k--] = nums2[j--];
        }
    }

    // Bubble Sort
    // TC : O(N^2)
    public static void bubbleSort(int[] arr) {

        for (int li = arr.length - 1; li > 0; li--) {
            for (int i = 1; i <= li; i++) {
                if (arr[i - 1] > arr[i])
                    swap(arr, i, i - 1);
            }
        }
    }

    public static void bubbleSort_opti(int[] arr) {
        for (int li = arr.length - 1; li > 0; li--) {
            boolean isSwapDone = false;
            for (int i = 1; i <= li; i++) {
                if (arr[i - 1] > arr[i]) {
                    swap(arr, i, i - 1);
                    isSwapDone = true;
                }
            }
            if (!isSwapDone)
                break;
        }
    }

    // Selection Sort
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++)
                if (arr[min] > arr[j])
                    min = j;

            swap(arr, i, min);
        }
    }

    // Insertion Sort
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j - 1] > arr[j])
                    swap(arr, j - 1, j);
                else
                    break;
            }
        }
    }

    // Partition An Array
    public static void partitionOnArray(int[] arr, int pivot) {
        int p = -1, itr = 0;
        while(itr < arr.length) {
            if(arr[itr] <= pivot)
                swap(arr, itr, ++p);
            itr++;
        }
    }

    public static void partitionOnArray2(int[] arr, int pivot) {
        int i = 0, j = 0;
        while (i < arr.length) {
            if (arr[i] <= pivot)
                swap(arr, i++, j++);
            else
                i++;
        }
    }

    // Partition over pivot index
    public static void partitionOverPivot(int[] arr, int idx) {
        int p = -1, itr = 0, n = arr.length, li = n - 1;
        swap(arr, idx, li);
        while(itr < li) {
            if(arr[itr] <= arr[li]) 
                swap(arr, ++p, itr);
            itr++;
        }
        swap(arr, ++p, li);
    }

    // 2161. Partition Array According to Given Pivot
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length, pivotFreq = 0, i = 0;
        int[] ans = new int[n];
        for(int num : nums) {
            if(num < pivot)
                ans[i++] = num;
            else if(num == pivot)
                pivotFreq++;
        }
        
        while(pivotFreq-- > 0)
            ans[i++] = pivot;
        
        for(int num : nums) {
            if(num > pivot)
                ans[i++] = num;
        }
        
        return ans;
    }

    public static void main(String[] args) {
        // int[] arr = { 0, 1, 0, 1, 0, 1, 1, 0, 1 };
        int[] arr = { -5, 10, -8, 6, 2, 13, 6, 8, 9 };
        // sort01(arr);
        partitionOverPivot(arr, 3);;
        for (int e : arr)
            System.out.print(e + " ");
    }
}
