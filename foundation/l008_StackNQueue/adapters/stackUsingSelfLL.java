public class stackUsingSelfLL {
    public static class Stack {
        private class Node {
            int data;
            Node next = null;

            Node(int data) {
                this.data = data;
            }
        }

        private Node head = null;
        private Node tail = null;
        private int size = 0;

        public int size() {
            return this.size;
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        private void addFirst(int data) {
            Node node = new Node(data);
            if(this.size == 0)
                this.head = this.tail = node;
            else {
                node.next = this.head;
                this.head = node;
            }
            this.size++;
        }

        public void push(int data) {
            addFirst(data);
        }

        public int top() {
            return this.head.data;
        }

        private Node removeFirst() {
            Node node = this.head;
            if(this.size == 1)
                this.head = this.tail = null;
            else {
                this.head = this.head.next;
                node.next = null;
            }
            this.size--;
            return node;
        }

        public int pop() {
            return removeFirst().data;
        }
    }

    public static void main(String[] args) {
        Stack st = new Stack();
        for (int i = 1; i <= 10; i++)
            st.push(i * 8);

        System.out.println(st.top());
        System.out.println(st.pop());
        System.out.println(st.top());

    }
}
