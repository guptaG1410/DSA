import java.util.Scanner;

public class DToB {
    public static Scanner scn = new Scanner(System.in);

    public static int DecimalToBinary(int n) {
        int pow = 1, ans = 0;
        while(n != 0) {
            ans += (n % 2) * pow;
            n /= 2;
            pow *= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        System.out.println(DecimalToBinary(n));
    }
}
