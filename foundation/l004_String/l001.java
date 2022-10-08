
public class l001 {

    // Print All Palindromic Substrings
    public static boolean isPalin(String str, int i, int j) {
        while(i < j) {
            if(str.charAt(i) != str.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }

	public static void palinSubs(String str){
		for(int i = 0; i < str.length(); i++) {
		    for(int j = i; j < str.length(); j++) {
		        if(isPalin(str, i, j))
		            System.out.println(str.substring(i, j + 1));
		    }
		}
		
	}

    // String Compression
    public static String compression1(String str){          //aaaabbcdeeeff => abcdef
		int n = str.length();
		if(n == 0)
		    return str;
		String ans = "" + str.charAt(0);
		int i = 1;
		while(i < n) {
		    while(i < n && ans.charAt(ans.length() - 1) == str.charAt(i))   i++;
		    if(i < n) {
		        ans += str.charAt(i);
		        i++;
		    }
		}

		return ans;
	}

	public static String compression2(String str){          //aaaabbcdeeeff => a4b2cde3f2
		int n = str.length();
		if(n == 0) 
		    return str;
		int i = 1, count = 0;
		String ans = "" + str.charAt(0);
		while(i < n) {
		    count = 1;
		    while(i < n && str.charAt(i) == ans.charAt(ans.length() - 1)) {
		        i++;
		        count++;
		    }
		    if (count > 1)  ans += count ;
		    if(i < n) {
		        ans += str.charAt(i);
		        i++;
		    }
		}

		return ans;
	}

    // Count hi ["ABChi hi" => 2, "abc hih ho" => 1]
    public static int countHi(String str) {
        int count = 0;
        for(int i = 0; i < str.length() - 1; i++) {
            if(str.substring(i, i + 2).equals("hi")) {
            count++;
            i++;
            }
        }
        return count;
    } 
    
    // Count hi, excluding hit ["abshihithihit" => 2]
    public static int countOfHiWithoutHit(String str) {
        int count = 0;
        for(int i = 0; i < str.length() - 1; i++) {
            if(str.substring(i, i + 2).equals("hi") && str.charAt(i + 2) != 't') {
                i++;
                count++;
            }
        }
        return count;
    }


    public static void main(String[] args) {
        // Scanner scn = new Scanner(System.in);
        String str = "abshihithihithith";
        // palinSubs(str);
        // System.out.println(compression1(str));
        // System.out.println(compression2(str));
        System.out.println(countHi(str));
        System.out.println(countOfHiWithoutHit(str));

    }

}
