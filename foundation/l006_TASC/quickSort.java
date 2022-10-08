import java.net.PasswordAuthentication;

public class quickSort {
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int partitionOverPivot(int[] arr, int si, int ei, int pIdx) {
        swap(arr, ei, pIdx); // We can either remove this line cauz we always make pivot to our end index so
                             // there is no need to swap it again. It is wriiten here to make our code more
                             // generic.

        int p = si - 1, itr = si;
        while (itr <= ei) {
            if (arr[itr] <= arr[ei])
                swap(arr, itr, ++p);

            itr++;
        }

        return p;
    }

    public static void quickSort(int[] arr, int si, int ei) {
        if (si >= ei)
            return;

        int pIdx = ei;
        int p = partitionOverPivot(arr, si, ei, pIdx);

        quickSort(arr, si, p - 1);
        quickSort(arr, p + 1, ei);

    }

    // Quick Select - [find the k-th largest element in the given array using the
    // quick-select algorithm].
    public static int quickSelect(int[] arr, int si, int ei, int idx) {
        int pIdx = ei;
        int p = partitionOverPivot(arr, si, ei, pIdx);
        if(p == idx)
            return p;
        else if(p < idx) 
            return quickSelect(arr, p + 1, ei, idx);
        else 
            return quickSelect(arr, si, p - 1, idx);
    }

    public static int quickSelect(int[] arr, int k) {
        int n = arr.length, idx = n - k;

        quickSelect(arr, 0, n - 1, idx);
        return arr[idx];
    }

    public static void main(String[] args) {
        // int[] arr = { -12, 2, 7, 4, 34, 23, 0, -1, 1, -50, 16, 23, 7, 4, 2, 3 };
        // quickSort(arr, 0, arr.length - 1);
        // for (int e : arr)
        //     System.out.print(e + "  ");

        int[] arr = { 10, 12, -8, -7, 3, 5, 4, 8 };
        System.out.println(quickSelect(arr, 4));
    }
}
