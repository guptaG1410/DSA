import java.util.*;

public class ques {
    public static void main(String args[]) {

        Scanner scn = new Scanner(System.in);
        int[] arr = new int[scn.nextInt()];
        for (int i = 0; i < arr.length; i++)
            arr[i] = scn.nextInt();

        Arrays.sort(arr);
        int rounds = scn.nextInt();
        while (rounds-- > 0) {
            int power = scn.nextInt();
            int[] ans = new int[2];
            int si = 0, ei = arr.length - 1;
            while (si <= ei) {
                int mid = si + (ei - si) / 2;
                if (arr[mid] == power) {
                    for (int i = 0; i <= mid; i++) {
                        ans[1] += arr[i];
                        ans[0]++;
                    }
                } else if (arr[mid] < power)
                    si = mid + 1;
                else
                    ei = mid - 1;
            }
            System.out.println(ans[0] + " " + ans[1]);
        }
    }
}


String str = "abccdd";
int[] freq = new int[26];
for(int i = 0; i < str.length; i++) {
    char ch = str.charAt(i);
    freq[ch - 'a']++;
}

// freq[0] - 1  [a's frequency]
// freq[1] - 1  [b's frequency]
// freq[2] - 2  [c's frequency]
// freq[3] - 2  [d's frequency]