public class QueueUsingStackPop {
    LinkedList<Integer> st = new LinkedList<>();
    LinkedList<Integer> temp = new LinkedList<>();
    
    public QueueUsingStackPop() {
        
    }
    

    public void transfer(LinkedList<Integer> s1, LinkedList<Integer> s2) {
        while(s1.size() != 0)
            s2.addFirst(s1.removeFirst());
    }
    // O(N)
    public void push(int x) {
        transfer(st, temp);
        temp.addFirst(x);
        transfer(temp, st);
    }
    
    // O(1);
    public int pop() {
        if(st.size() == 0)
            return -1;
        
        return st.removeFirst();
    }
    
    public int peek() {
        if(st.size() == 0)
            return -1;
        
        return st.getFirst();
    }
    
    public boolean empty() {
        return st.size() == 0;
    }
}
