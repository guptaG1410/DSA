public class dynamicQueue {
    
    public dynamicQueue() {
        super();
    }

    public dynamicQueue(int len) {
        super(len);
    }

    @Override
    public void add(int data) {
        if(super.size() == super.maxSize()) {
            int[] temp = new int[super.maxSize()];
            int idx = 0;
            while(super.size() != 0)
                temp[idx++] = super.remove();

            super.initialise(super.maxSize() * 2);
            for(int e : temp)
                super.add(e);
        }
        super.add(data);
    }
}
