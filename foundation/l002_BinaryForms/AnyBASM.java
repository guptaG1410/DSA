import java.util.*;

public class AnyBASM {
    public static int AnyBAddition(int b, int n1, int n2) {
        int pow = 1, ans = 0, carry = 0;
        while (n1 != 0 || n2 != 0 || carry != 0) {
            int sum = carry + n1 % 10 + n2 % 10;
            n1 /= 10;
            n2 /= 10;
            carry = sum / b;
            ans += (sum % b) * pow;
            pow *= 10;
        }
        return ans;
    }

    // 67. Add Binary
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        while(i >= 0 || j >= 0 || carry != 0) {
            int sum = carry;
            if(i >= 0)      
                sum += a.charAt(i--) - '0';
            if(j >= 0)      
                sum += b.charAt(j--) - '0';
            carry =  sum / 2;
            sb.append(sum % 2);
        }
        return sb.reverse().toString();
    }
    

    public static int AnyBSubtraction(int b, int n1, int n2) {
        int pow = 1, ans = 0, borrow = 0;
        while (n2 != 0) {
            int diff = n2 % 10 - (n1 >= 0 ? n1 % 10 : 0) + borrow;
            if (diff < 0) {
                diff += b;
                borrow = -1;
            } else {
                borrow = 0;
            }
            n1 /= 10;
            n2 /= 10;
            ans += diff * pow;
            pow *= 10;
        }
        return ans;
    }

    public static int getSum(int b, int n1, int n2) {
        int ans = 0, carry = 0, pow = 1;
        while(n1 != 0 || n2 != 0 || carry != 0) {
            int sum = (n1 % 10) + (n2 % 10) + carry;
            n1 /= 10;
            n2 /= 10;
            carry = sum / b;
            ans += (sum % b) * pow;
            pow *= 10;
        }
        return ans;
    }
    
    public static int getMultiplySD(int b, int n1, int d) {
        int ans = 0, carry = 0, pow = 1;
        while(n1 != 0 || carry != 0) {
            int mul = (n1 % 10) * d + carry;
            n1 /= 10;
            carry = mul / b;
            ans += (mul % b) * pow;
            pow *= 10;
        }
        return ans;
    }
  
    public static int getProduct(int b, int n1, int n2) {
      int ans = 0, pow = 1;
      while(n2 != 0) {
          int recAns = getMultiplySD(b, n1, n2 % 10) * pow;
          n2 /= 10;
          ans = getSum(b, ans, recAns);
          pow *= 10;
      }
      return ans;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int b = scn.nextInt();
        int n1 = scn.nextInt();
        int n2 = scn.nextInt();

        System.out.println(AnyBAddition(b, n1, n2));
        System.out.println(AnyBSubtraction(b, n1, n2));
    }
}
