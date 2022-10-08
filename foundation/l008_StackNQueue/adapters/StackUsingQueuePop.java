public class StackUsingQueuePop {
    LinkedList<Integer> que = new LinkedList<>();
    LinkedList<Integer> temp = new LinkedList<>();

    public StackUsingQueuePop() {
        
    }

    public int size() {
      return que.size();
    }
    
    public void transfer(LinkedList<Integer> st1, LinkedList<Integer> st2) {
        while(st1.size() != 0) {
            st2.addLast(st1.removeFirst());
        }
    }

    // O(n)
    public void push(int val) {
      temp.addLast(val);
      transfer(que, temp);
      transfer(temp, que);
    }

    // O(1)
    public int pop() {
      return que.removeFirst();
    }

    /** Get the top element. */
    public int top() {
      return que.getFirst();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return que.size() == 0;
    }
}
