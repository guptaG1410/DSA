public class stack {
    private int[] arr;
    private int size;
    private int tos;
    private int maxSize;

    stack(int len) {
        initialise(len);
    }

    stack() {
        initialise(6);
    }

    protected void initialise(int len) {
        this.arr = new int[len];
        this.size = 0;
        this.tos = -1;
        this.maxSize = len;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = tos; i >= 0; i--) {
            sb.append(this.arr[i]);
            if(i != 0)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private void stackIsUnderFlow() throws Exception {
        if(this.size == 0) 
            throw new Exception("Stack is under flow: -1 GG");
    }

    private void stackIsOverFlow() throws Exception {
        if(this.size == this.maxSize)
            throw new Exception("Stack is over flow: -1 GG");
    }

    public int size() {
        return this.size;
    }

    public int maxSize() {
        return this.maxSize;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    private void push_(int data) {
        this.arr[++this.tos] = data;
        this.size++;
    }

    public void push(int data) throws Exception{
        stackIsOverFlow();
        push_(data);
    }

    public int top() throws Exception {
        stackIsUnderFlow();
        return this.arr[this.tos];
    }

    private int pop_(){
        int rv = this.arr[this.tos];
        this.arr[this.tos--] = 0;
        this.size--;
        return rv;
    }

    public int pop() throws Exception {
        stackIsUnderFlow();
        return pop_();
    }
}
