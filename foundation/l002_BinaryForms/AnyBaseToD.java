import java.util.Scanner;

public class AnyBaseToD {
    public static int getValueIndecimal(int n, int b) {
        // write your code here
        int ans = 0, pow = 1;
        while(n != 0) {
            ans += (n % 10) * pow;
            n /= 10;
            pow *= b;
        }
        return ans;
    }
    
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int b = scn.nextInt();
        int dn = getValueIndecimal(n, b);
        System.out.println(dn);
     }
}
