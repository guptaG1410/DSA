public class mergeSort {

    public static int[] mergeTwoSortedArrays(int[] arr1, int[] arr2) {
        if(arr1.length == 0 || arr2.length == 0)
            return arr1.length == 0 ? arr2 : arr1;

        int m = arr1.length, n = arr2.length;
        int[] ans = new int[n + m];
        int i = 0, j = 0, k = 0;
        while(i < m && j < n) {
            if(arr1[i] < arr2[j])
                ans[k++] = arr1[i++];
            else
                ans[k++] = arr2[j++];
        }

        while(i < m) 
            ans[k++] = arr1[i++];
        while(j < n)
            ans[k++] = arr2[j++];

        return ans;
    }

    public static int[] mergeSort(int[] arr, int si, int ei) {
        if(si == ei)
            return new int[]{arr[si]};
        
        int mid = (si + ei) / 2;
        return mergeTwoSortedArrays(mergeSort(arr, si, mid), mergeSort(arr, mid + 1, ei));
    }
    
    public static void main(String[] args) {
        int[] arr = { -12, 2, 7, 4, 34, 23, 0, -1, 1, -50, 16, 23, 7, 4, 2, 3 };
        arr = mergeSort(arr, 0, arr.length - 1);

        for(int e : arr) 
            System.out.print(e + "  ");
    }
}
