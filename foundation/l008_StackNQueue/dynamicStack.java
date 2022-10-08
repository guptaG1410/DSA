// Here, dynamic stack is implemented by inheriting the stack class.

public class dynamicStack extends stack {

    // super keyword is use to access the members of stack class (only public and
    // protected).
    // Here, super() will call the default constructor of stack class.
    public dynamicStack() {
        super();
    }

    // Here, super(len) will call the parameterized constructor of stack class.
    public dynamicStack(int len) {
        super(len);
    }

    // to make stack dynamic in nature we've to increase its size whenever size of
    // stack reaches its maximum capacity.
    @Override
    // Here, what we'll do is first transfer the contents of stack in another stack
    // then double the maximum capacity of original stack. Then again transfer the
    // contents of temporary stack to original stack.
    public void push(int data) throws Exception {
        if(super.size() == super.maxSize()) {
            int[] temp = new int[super.maxSize()];
            int idx = super.size() - 1;
            while(super.size() != 0) 
                temp[idx--] = super.pop();
            
            super.initialise(super.maxSize() * 2);
            for(int e : temp) 
                super.push(e); 
        }
        super.push(data);
    }

}
