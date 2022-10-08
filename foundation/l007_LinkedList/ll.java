public class ll {
    private class Node {
        int data = 0;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node curr = this.head;
        while (curr != null) {
            sb.append(curr.data);
            if (curr.next != null)
                sb.append(", ");
            curr = curr.next;
        }
        sb.append("]");
        return sb.toString();
    }

    // -----------------Add node to the start of the linked list
    private void addFirstNode(Node node) {
        if (this.size == 0)
            this.head = this.tail = node;
        else {
            node.next = this.head;
            this.head = node;
        }
        this.size++;
    }

    public void addFirst(int data) {
        // Node node = new Node(data);
        addFirstNode(new Node(data));
    }

    // -------------------Add node to the end of the linked list.
    private void addLastNode(Node node) {
        if (this.size == 0)
            this.head = this.tail = node;
        else {
            this.tail.next = node;
            this.tail = node;
        }
        this.size++;
    }

    public void addLast(int data) {
        // Node node = new Node(data);
        addLastNode(new Node(data));
    }

    // ---------------------ADD NODE AT ANY INDEX.
    private void addAtNode(Node node, int idx) {
        if (idx == 0)
            addFirstNode(node);
        else if (idx == this.size)
            addLastNode(node);
        else {
            Node prev = getAtNode(idx - 1);
            Node curr = prev.next;
            prev.next = node;
            node.next = curr;
            this.size++;
        }
    }

    public void addAt(int data, int idx) {
        if (idx < 0 || idx > this.size)
            return;

        addAtNode(new Node(data), idx);
    }

    // --------------------REMOVE NODE FROM START OF THE LINKED LIST.
    private Node removeFirstNode() {
        Node node = this.head;
        if (this.size == 1)
            this.head = this.tail = null;
        else {
            this.head = this.head.next;
            node.next = null;
        }
        this.size--;
        return node;
    }

    public int removeFirst() {
        if (this.size == 0)
            return -1;

        // Node node = new Node();
        // node = removeFirstNode();
        // return node.data;

        return removeFirstNode().data;
    }

    // --------------------REMOVE NODE FROM END OF THE LINKED LIST.
    private Node removeLastNode() {
        Node node = this.tail;
        if (this.size == 1)
            this.head = this.tail = null;
        else {
            Node secondLast = getAtNode(this.size - 2);
            this.tail = secondLast;
            secondLast.next = null;
        }
        this.size--;
        return node;
    }

    public int removeLast() {
        if (this.size == 0)
            return -1;
        return removeLastNode().data;
    }

    // ---------------------REMOVE NODE AT ANY INDEX.
    private Node removeAtNode(int idx) {
        if (idx == 0)
            return removeFirstNode();
        else if (idx == this.size - 1)
            return removeLastNode();
        else {
            Node prev = getAtNode(idx - 1);
            Node curr = prev.next;
            Node forw = curr.next;

            curr.next = null;
            prev.next = forw;

            this.size--;
            return curr;
        }
    }

    public int removeAt(int idx) {
        if (idx < 0 || idx >= this.size)
            return -1;
        return removeAtNode(idx).data;
    }

    // --------------------GET NODE FROM START.
    private Node getFirstNode() {
        return this.head;
    }

    public int getFirst() {
        if (this.size == 0)
            return -1;
        return getFirstNode().data;
    }

    // --------------------GET NODE FROM END.
    private Node getLastNode() {
        return this.tail;
    }

    public int getLast() {
        if (this.size == 0)
            return -1;
        return getLastNode().data;
    }

    // --------------------GET NODE FROM ANY INDEX.
    private Node getAtNode(int idx) {
        Node node = this.head;
        while (idx-- > 0)
            node = node.next;
        return node;
    }

    public int getAt(int idx) {
        if (idx < 0 || idx >= this.size)
            return -1;

        return getAtNode(idx).data;
    }

    // -------------------ODD EVEN LINKED LIST
    public void oddEven() {
        if (this.size == 0 || this.size == 1)
            return;

        Node even = new Node(-1);
        Node ep = even;

        Node odd = new Node(-1);
        Node op = odd;

        Node curr = this.head;
        while (curr != null) {
            if (curr.data % 2 == 0) {
                ep.next = curr;
                ep = ep.next;
            } else {
                op.next = curr;
                op = op.next;
            }
            curr = curr.next;
        }

        op.next = even.next;
        ep.next = null;

        this.head = odd.next;
        if (even.next != null)
            this.tail = ep;
        else
            this.tail = op;
    }

    // -----------------REVERSE LINKED LIST.
    public void reversePI() {
        // write your code here
        if (this.head == null || this.head.next == null)
            return;

        Node prev = null;
        Node curr = this.head;
        while (curr != null) {
            Node forw = curr.next;
            curr.next = prev;

            prev = curr;
            curr = forw;
        }
        this.tail = this.head;
        this.head = prev;
    }

    // RECURSIVE APPROACH.
    private void reversePRHelper(Node node) {
        if (node.next == null)
            return;

        reversePRHelper(node.next);
        Node forw = node.next;
        forw.next = node;
    }

    public void reversePR() {
        reversePRHelper(head);
        head.next = null;
        Node temp = head;
        head = tail;
        tail = temp;
    }

    // -----------------ADD TWO LINKED LIST.
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reversePI(l1);
        l2 = reversePI(l2);

        ListNode dummy = new ListNode(-1);
        ListNode dp = dummy, c1 = l1, c2 = l2;
        int carry = 0;
        while (c1 != null || c2 != null || carry != 0) {
            int sum = carry + (c1 != null ? c1.val : 0) + (c2 != null ? c2.val : 0);
            carry = sum / 10;
            sum %= 10;
            dp.next = new ListNode(sum);
            dp = dp.next;
            if (c1 != null)
                c1 = c1.next;
            if (c2 != null)
                c2 = c2.next;
        }
        ListNode head = dummy.next;
        l1 = reversePI(l1);
        l2 = reversePI(l2);
        head = reversePI(head);
        return head;
    }

    // Using Recursive space
    public static int addTwoLists(Node one, int s1, Node two, int s2, LinkedList ans) {
        if (one == null || two == null)
            return 0;

        int carry = 0;
        if (s1 > s2) {
            carry = addTwoLists(one.next, s1 - 1, two, s2, ans);
            int sum = carry + one.data;
            carry = sum / 10;
            sum %= 10;
            ans.addFirst(sum);
        } else {
            carry = addTwoLists(one.next, s1 - 1, two.next, s2 - 1, ans);
            int sum = carry + one.data + two.data;
            carry = sum / 10;
            sum %= 10;
            ans.addFirst(sum);
        }
        return carry;
    }

    public static LinkedList addTwoLists(LinkedList one, LinkedList two) {
        // Here, Linkedlist is another class which is mentioned in the question itself.
        // We're assuming that one.size() > two.size() if not then we'll swap them.
        if (one.size() < two.size()) {
            LinkedList temp = one;
            one = two;
            two = temp;
        }
        LinkedList ans = new LinkedList();
        int carry = addTwoLists(one.head, one.size(), two.head, two.size(), ans);
        if (carry == 1)
            ans.addFirst(carry);

        return ans;
    }

    // ------------------------FOLD A LINKED LIST
    public Node reverse(Node node) {
        if (node == null || node.next == null)
            return node;

        Node curr = node, prev = null;
        while (curr != null) {
            Node forw = curr.next;

            curr.next = prev;

            prev = curr;
            curr = forw;
        }

        return prev;
    }

    public Node midNode(Node node) {
        if (node == null || node.next == null)
            return node;
        Node slow = node, fast = node;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public void fold() {
        // write your code here
        Node mid = midNode(this.head);
        Node nextHead = mid.next;
        mid.next = null;

        nextHead = reverse(nextHead);
        Node c1 = head, c2 = nextHead;
        while (c2 != null) {
            Node f1 = c1.next, f2 = c2.next;

            c1.next = c2;
            c2.next = f1;

            c1 = f1;
            c2 = f2;
        }
        if (size() % 2 == 0)
            this.tail = mid.next;
        else
            this.tail = mid;
    }

    // -------------------Segregate Node Of Linkedlist Over Last Index.
    public ListNode getTail(ListNode head) {
        ListNode tail = head;
        while (tail.next != null)
            tail = tail.next;

        return tail;
    }

    public ListNode segregateOnLastIndex(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode tail = getTail(head);
        ListNode smaller = new ListNode(-1);
        ListNode sp = smaller;

        ListNode larger = new ListNode(-1);
        ListNode lp = larger;

        ListNode curr = head;
        while (curr != null) {
            if (curr.val <= tail.val) {
                sp.next = curr;
                sp = sp.next;
            } else {
                lp.next = curr;
                lp = lp.next;
            }
            curr = curr.next;
        }
        sp.next = larger.next;
        lp.next = null;
        return sp;
    }

    // --------------------Segregate Node Of Linkedlist Over Pivot Index.
    public static ListNode segregate(ListNode head, int pivotIdx) {
        if (head == null || head.next == null)
            return head;

        int idx = 0, data = 0;
        ListNode curr = head;
        while (curr != null) {
            if (idx == pivotIdx) {
                data = curr.val;
                break;
            }
            curr = curr.next;
            idx++;
        }

        ListNode smaller = new ListNode(-1);
        ListNode sp = smaller;

        ListNode larger = new ListNode(-1);
        ListNode lp = larger;

        ListNode pivot = null;
        curr = head;
        idx = 0;
        while (curr != null) {
            if (idx == pivotIdx)
                pivot = curr;
            else if (curr.val <= data) {
                sp.next = curr;
                sp = sp.next;
            } else {
                lp.next = curr;
                lp = lp.next;
            }
            curr = curr.next;
            idx++;
        }
        sp.next = pivot;
        pivot.next = larger.next;
        lp.next = null;

        return smaller.next;
    }
}
