import java.util.ArrayList;
import java.util.Scanner;

public class l003_ArrayList {

    public static void basicFunctions() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(9);
        list.add(6);
        list.add(8);

        System.out.println(list);
        for (Integer e : list)
            System.out.println(e);
        System.out.println(list.size()); // size() gives the length of an ArrayList.
        System.out.println(list.get(1)); // get(index) show an element at given index.
        list.remove(1); // O(N) //remove(index) removes an element at given index.
        list.set(1, 7); // set(index, element)
        System.out.println(list);

    }

    // Remove Data in an ArrayList with the following constraints:
    // 1. No extra space.
    // 2. Time Complexity - O(1).
    public static ArrayList<Integer> removeData(ArrayList<Integer> list, int data) {
        int i = list.size() - 1;
        while (i >= 0) {
            if (list.get(i) == data) {
                swap(list, i, list.size() - 1);
                list.remove(list.size() - 1);
            }
            i--;
        }
        return list;
    }

    public static void swap(ArrayList<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    // public static ArrayList<Integer> removeData(ArrayList<Integer> list, int
    // data) {
    // for(int i = list.size() - 1; i >= 0 ; i--) {
    // if(list.get(i) == data)
    // list.remove(i);
    // }
    // return list;
    // }

    // Remove Primes
    public static boolean isPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }

    public static void solution(ArrayList<Integer> al) {
        for (int i = al.size() - 1; i >= 0; i--) {
            if (isPrime(al.get(i)))
                al.remove(i);
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        // basicFunctions();
        for (int i = 0; i < 11; i++) {
            int ele = scn.nextInt();
            list.add(ele);
        }
        System.out.println(removeData(list, 10));

    }

}
