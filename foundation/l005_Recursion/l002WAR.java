public class l002WAR {

    public static void printArr(int[] arr, int idx) {
        if (idx == arr.length)
            return;

        System.out.println(arr[idx]);
        printArr(arr, idx + 1);
    }

    public static void printReverseArr(int[] arr, int idx) {
        if (idx == arr.length)
            return;

        printReverseArr(arr, idx + 1);
        System.out.println(arr[idx]);
    }

    // Maximum element of an Array.
    public static int maxOfArray(int[] arr, int idx) {
        if (idx == arr.length)
            return -(int) 1e9;

        int recAns = maxOfArray(arr, idx + 1);
        return Math.max(recAns, arr[idx]);
    }

    // Minimum element of an Array
    public static int minOfArray(int[] arr, int idx) {
        if (idx == arr.length)
            return (int) 1e9;

        int recAns = minOfArray(arr, idx + 1);
        return Math.min(recAns, arr[idx]);
    }

    // Find element
    public static boolean findData(int[] arr, int idx, int data) {
        if (idx == arr.length)
            return false;

        // if(arr[idx] == data)
        // return true;
        return arr[idx] == data ? true : findData(arr, idx + 1, data);
    }

    public static boolean findData2(int[] arr, int idx, int data) {
        if (idx == arr.length)
            return false;

        boolean recRes = findData2(arr, idx + 1, data);
        if (recRes)
            return true;

        return arr[idx] == data;
    }

    // First Index of an element
    public static int firstIndex(int[] arr, int idx, int x) {
        if (idx == arr.length)
            return -1;
        if (arr[idx] == x)
            return idx;
        return firstIndex(arr, idx + 1, x);
    }

    // Last Index of an element
    public static int lastIndex(int[] arr, int idx, int x) {
        if (idx == arr.length)
            return -1;

        int recAns = lastIndex(arr, idx + 1, x);
        if (recAns != -1)
            return recAns;

        return arr[idx] == x ? idx : -1;
    }

    // All Indices Of Array
    public static int[] allIndices(int[] arr, int data, int idx, int count) {
        if (idx == arr.length)
            return new int[count];
            
        if (arr[idx] == data)
            count++;
        int[] recAns = allIndices(arr, data, idx + 1, count);
        if (arr[idx] == data)
            recAns[count--] = idx;
        return recAns;
    }

    public static void main(String[] args) {
        int[] arr = { 9, 6, 4, 3, 0, 9, 9, 2, 1, 8 };
        // printArr(arr, 0);
        // printReverseArr(arr, 0);
        System.out.println(maxOfArray(arr, 0));
        System.out.println(minOfArray(arr, 0));
        System.out.println(findData(arr, 0, 18));
    }
}
