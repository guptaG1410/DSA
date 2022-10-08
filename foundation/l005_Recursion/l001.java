public class l001 {

    // Example to illustrate how recursion works
    public static void ppppppp(int a, int b) {
        return;
    }

    public static void pppppp(int a, int b) {
        System.out.println(a);
        ppppppp(a + 1, b);
        System.out.println("hi" + a);
    }

    public static void ppppp(int a, int b) {
        System.out.println(a);
        pppppp(a + 1, b);
        System.out.println("hi" + a);
    }

    public static void pppp(int a, int b) {
        System.out.println(a);
        ppppp(a + 1, b);
        System.out.println("hi" + a);
    }

    public static void ppp(int a, int b) {
        System.out.println(a);
        pppp(a + 1, b);
        System.out.println("hi" + a);
    }

    public static void pp(int a, int b) {
        System.out.println(a);
        ppp(a + 1, b);
        System.out.println("hi" + a);
    }

    public static void p(int a, int b) {
        System.out.println(a);
        pp(a + 1, b);
        System.out.println("hi" + a);
    }

            // OR //
    public static void printIncreasing(int a, int b) {
        if(a > b)   return;

        System.out.println(a);
        printIncreasing(a + 1, b);
        System.out.println("hi" + a);
    }

    public static void printDecreasing(int a, int b) {
        if(a > b) return;

        printDecreasing(a + 1, b);
        System.out.println(a);
    }

    public static void printIncreasingDecreasing(int a, int b) {
        if (a > b)
            return;

        System.out.println(a);
        printIncreasingDecreasing(a + 1, b);
        System.out.println(a);
    }

    public static void printIncreasingDecreasingEvenOdd(int a, int b) {
        if(a > b)   return;

        if(a % 2 == 0)
            System.out.println(a);
        printIncreasingDecreasingEvenOdd(a + 1, b);
        if(a % 2 != 0)
            System.out.println(a);
    }

    public static int factorial(int n){
        if(n == 0)
            return 1;
        
        return factorial(n - 1) * n;
    }

    public static int powerLinear(int x, int n){
        if(n == 0) 
            return 1;
            
        return powerLinear(x, n - 1) * x;
    }

    public static int powerLog(int x, int n){
        if(n == 0)
            return 1;
            
        int recAns = powerLog(x, n / 2);
        recAns *= recAns;
            
        return n % 2 == 0 ? recAns : recAns * x;
    }

    // Draw the recursive diagram of this code to understand recursion better.
    // n = 5
    public static int recursionTree(int n) {
        if (n <= 1) {
            System.out.println("Base: " + n);
            return n + 1;
        }

        int count = 0;

        System.out.println("Pre: " + n);
        count += recursionTree(n - 1);

        System.out.println("In: " + n);
        count += recursionTree(n - 2);

        System.out.println("Post: " + n);

        return count + 3;
    }

    public static int fibo(int n) {
        if (n <= 1)
            return n;
        int count = 0;
        count += fibo(n - 1);
        count += fibo(n - 2);

        return count;
    }

    
    public static void main(String[] args) {
        // p(2, 7);
        // printIncreasing(2, 7);
        // System.out.println(recursionTree(5));
        System.out.println(fibo(6));
    }
}
