public class queue {
    private int[] arr;
    private int size;
    private int front;
    private int back;
    private int maxSize;

    queue(int len) {
        initialise(len);
    }

    queue() {
        initialise(6);
    }

    protected void initialise(int len) {
        this.arr = new int[len];
        this.size = 0;
        this.front = 0;
        this.back = 0;
        this.maxSize = len;
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < this.size; i++) {
            int idx = (this.front + i) % this.maxSize;
            sb.append(this.arr[idx]);
            if(i != this.size - 1) 
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private void queueIsUnderFlow() throws Exception {
        if(this.size == 0)
            throw new Exception("Queue is under flow: -1GG");
    }

    private void queueIsOverFlow() throws Exception {
        if(this.size == this.maxSize)
            throw new Exception("Queue is over flow: -1GG");
    }

    private void add_(int data) {
        this.arr[this.back] = data;
        this.back = (this.back + 1) % this.maxSize;
        this.size++; 
    }

    public void add(int data) throws Exception{
        queueIsOverFlow();
        add_(data);
    }

    public int peek() throws Exception {
        queueIsUnderFlow();
        return this.arr[this.front];
    }

    private int remove_() {
        int rv = this.arr[this.front];
        this.arr[this.front]  = 0;
        this.front = (this.front + 1) % this.maxSize;
        this.size--;
        return rv;
    }

    public int remove() throws Exception {
        queueIsUnderFlow();
        return remove_();
    }
}
