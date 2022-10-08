public class queueUsingSelfLL {
    public static class Queue {
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

        private void addLast(int data) {
            Node node = new Node(data);
            if(this.size == 0)
                this.head = this.tail = node;
            else {
                this.tail.next = node;
                this.tail = node;
            }
            this.size++;
        }

        public void add(int data) {
            addLast(data);
        }

        public int peek() {
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

        public int remove() {
            return removeFirst().data;
        }
    }

    public static void main(String[] args) {
        Queue que = new Queue();

        for (int i = 1; i <= 10; i++)
            que.add(i * 9);

        System.out.println(que.peek());
        System.out.println(que.remove());
        System.out.println(que.peek());
    }
}
