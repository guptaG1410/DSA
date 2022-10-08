import java.util.Scanner;

public class DToAnyBase {
    
    public static int getValueInBase(int n, int b){
        // write code here
        int ans = 0, pow = 1;
        while(n != 0) {
            ans += (n % b) * pow;
            n /= b;
            pow *= 10;
        }
        return ans;
    }
    
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int b = scn.nextInt();
        int dn = getValueInBase(n, b);
        System.out.println(dn);
    }
}