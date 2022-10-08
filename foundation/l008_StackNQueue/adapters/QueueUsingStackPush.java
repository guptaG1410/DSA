public class QueueUsingStackPush {
    LinkedList<Integer> st = new LinkedList<>();
    LinkedList<Integer> temp = new LinkedList<>();
    
    int topEle = 0;

    public QueueUsingStackPush() {
        
    }
    
    //O(1)
    /** Push element x to the back of queue. */
    public void push(int x) {
        if(st.size() == 0)
            topEle = x;
        st.addFirst(x);
    }
    
    private void transfer(LinkedList<Integer> st1, LinkedList<Integer> st2) {
        while(st1.size() != 0) {
            st2.addFirst(st1.removeFirst());
        }
    }
    
    
    //O(n)
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        transfer(st, temp);
        int rEle = temp.removeFirst();
        while(temp.size() != 0)
            push(temp.removeFirst());
        return rEle;
    }
    
    /** Get the front element. */
    public int peek() {
        return topEle;
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return st.size() == 0;
    }
}
