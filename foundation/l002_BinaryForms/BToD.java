import java.util.Scanner;

public class BToD {
    public static Scanner scn = new Scanner(System.in);

    public static int BinaryToDecimal(int n) {
        int pow = 1, ans = 0;
        while(n != 0) {
            ans += (n % 10) * pow;
            n /= 10;
            pow *= 2;
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        System.out.println(BinaryToDecimal(n));
    }
}