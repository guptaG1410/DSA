import java.util.Queue;

public class client {

    public static void stackTest() throws Exception {
        stack st = new stack(10);

        for (int i = 1; i <= 10; i++)
            st.push(i * 8);

        System.out.println(st);
        while (st.size() != 5) {
            System.out.println(st.top());
            System.out.println(st.pop());
        }
    }

    public static void queueTest() throws Exception {
        queue que = new queue(10);
        for (int i = 1; i <= 10; i++)
            que.add(i * 8);

        System.out.println(que);
    }

    
    public static void main(String[] args) throws Exception{
        stackTest();
        queueTest();
    }
}
