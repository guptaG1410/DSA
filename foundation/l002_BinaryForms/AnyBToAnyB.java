import java.util.*;

public class AnyBToAnyB {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int sourceBase = scn.nextInt();
       int  destBase= scn.nextInt();
       int mid = AnyBaseToDecimal(n, sourceBase);
       System.out.println(DecimalToAnyBase(mid, destBase));
     }  
     
     public static int AnyBaseToDecimal(int n, int src) {
         int ans = 0, pow = 1;
         while(n != 0) {
             ans += (n % 10) * pow;
             n /= 10;
             pow *= src;
         }
         return ans;
     }
     
     public static int DecimalToAnyBase(int n, int dest) {
         int ans = 0, pow = 1;
         while(n != 0) {
             ans += (n % dest) * pow;
              n /= dest;
              pow *= 10;
         }
         return ans;
     }
}
