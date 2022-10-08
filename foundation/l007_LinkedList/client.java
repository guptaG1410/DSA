public class client {
    
    public static void main(String[] args) {
        ll l1 = new ll();
        l1.addFirst(10);
        System.out.println(l1.getFirst());
        System.out.println(l1.size());
        l1.addLast(40);
        System.out.println(l1.removeAt(2));
        l1.addAt(1, 20);
        System.out.println(l1.getLast());
        System.out.println(l1);
        System.out.println(l1.removeAt(2));
        l1.addLast(50);
        System.out.println(l1);
        l1.addAt(2, 30);
        System.out.println(l1.getFirst());
        System.out.println(l1.getAt(3));
    }
}
