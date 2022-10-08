
public class l002_StringBuilder {

    // STRING IMMUTABLE TEST
    // Here, it will require some time (3256 millisec) to execute the code because
    // every time new object is created when a number is added to string new memory
    // has to be create in HEAP(intempool) then it will add the new number to the
    // string.
    public static void stringImmutable1() {
        String str = "";
        for (int i = 0; i < (int) 1e5; i++) {
            str += i;
        }
        System.out.println(str);
    }

    // Here, it will require time (9 millisec) to execute the code because
    // StringBuilder would create new object only when the existing size is
    // occupied.
    public static void stringImmutable2() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < (int) 1e5; i++) {
            sb.append(i);
        }
        System.out.println(sb.toString());
    }

    // String Builder Basic Functions.
    public static void stringBuilderBasicFunction() {
        StringBuilder sb = new StringBuilder();
        sb.append("a"); //
        sb.append("bcd"); // O(m), where m is length of string which i want to append.
        System.out.println(sb.toString()); // O(n)
        sb.setCharAt(2, 'e'); // O(1), this will set the character at index 2.
        System.out.println(sb.toString()); // O(n)
        sb.deleteCharAt(2); // O(n), this will delete a character from index 2.
        System.out.println(sb.toString());
    }

    // Toggle Cases
    public static String toggleCase(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 'A' && ch <= 'Z')
                sb.append((char) (ch - 'A' + 'a'));
            else if (ch >= 'a' && ch <= 'z')
                sb.append((char) (ch - 'a' + 'A'));
        }

        return sb.toString();
    }

    // String With Difference Of Every Two Consecutive Characters
    public static String solution(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));
        for (int i = 1; i < str.length(); i++) {
            sb.append(str.charAt(i) - str.charAt(i - 1));
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        stringImmutable1();
        stringImmutable2();
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }
}
