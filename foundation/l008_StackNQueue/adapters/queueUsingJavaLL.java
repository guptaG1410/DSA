import java.util.LinkedList;

public class queueUsingJavaLL {
    public static class queue {
        LinkedList<Integer> ll = new LinkedList<>();

        public int size() {
            return ll.size();
        }

        public boolean isEmpty() {
            return ll.isEmpty();
        }

        public void add(int data) {
            ll.addLast(data);
        }

        public int peek() {
            return ll.getFirst();
        }

        public int remove() {
            return ll.removeFirst();
        }
    }

    public static void main(String[] args) {
        queue que = new queue();

        for (int i = 1; i <= 10; i++)
            que.add(i * 9);

        System.out.println(que.peek());
        System.out.println(que.remove());
        System.out.println(que.peek());
    }
}
