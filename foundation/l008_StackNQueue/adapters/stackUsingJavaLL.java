import java.util.LinkedList;

public class stackUsingJavaLL {
    public static class stack {
        LinkedList<Integer> ll = new LinkedList<>();

        public int size() {
            return ll.size();
        }

        public boolean isEmpty() {
            return ll.isEmpty();
        }

        public void push(int data) {
            ll.addFirst(data);
        }

        public int top() {
            return ll.getFirst();
        }

        public int pop() {
            return ll.removeFirst();
        }
    }

    public static void main(String[] args) {
        stack st = new stack();
        for (int i = 1; i <= 10; i++)
            st.push(i * 8);

        System.out.println(st.top());
        System.out.println(st.pop());
        System.out.println(st.top());

    }

}
