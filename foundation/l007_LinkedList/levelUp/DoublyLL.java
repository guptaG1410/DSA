
public class DoublyLL {

    private class Node {
        int data = 0;
        Node prev = null;
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

    // -----------------Add node to the start of the linked list
    private void addFirstNode(Node node) {
        if (this.size == 0)
            this.head = this.tail = node;
        else {
            this.head.prev = node;
            node.next = this.head;
            this.head = node;
        }
        this.size++;
    }

    public void addFirst(int data) {
        addFirstNode(new Node(data));
    }

    // -------------------Add node to the end of the linked list.
    private void addLastNode(Node node) {
        if (this.size == 0)
            this.head = this.tail = node;
        else {
            this.tail.next = node;
            node.prev = this.tail;
            this.tail = node;
        }
        this.size++;
    }

    public void addLast(int val) {
        addLastNode(new Node(val));
    }

    // ---------------------ADD NODE AT ANY INDEX.
    private void addNodeAt(Node node, int idx) {
        if (idx == 0)
            addFirstNode(node);
        else if (idx == this.size)
            addLastNode(node);
        else {
            Node forw = getAtNode(idx);
            Node prev = forw.prev;

            prev.next = node;
            node.prev = prev;

            forw.prev = node;
            node.next = forw;

            this.size++;
        }
    }

    public void addAt(int data, int idx) {
        if (idx < 0 || idx > this.size)
            return;
        else
            addNodeAt(new Node(data), idx);
    }

    // --------------------Add Before In Doubly Linkedlist.
    private void addBeforeNode(Node node, Node refNode) {
        if (refNode.prev != null) {
            Node prev = refNode.prev;
            prev.next = node;
            node.prev = prev;
            refNode.prev = node;
            node.next = refNode;
        } else {
            node.next = refNode;
            refNode.prev = node;

            this.head = node;
        }
        this.size++;
    }

    public void addBefore(Node refNode, int data) {
        addBeforeNode(new Node(data), refNode);
    }

    public void addBefore(int idx, int data) {
        Node node = getAtNode(idx);
        addBefore(node, data);
    }

    // --------------------Add After In Doubly Linkedlist.
    private void addAfter(Node node, Node refNode) {
        if (refNode.next != null) {
            Node forw = refNode.next;
            forw.prev = node;
            node.next = forw;
        }
        refNode.next = node;
        node.prev = refNode;
        this.size++;
    }

    public void addAfter(Node refNode, int data) {
        addAfter(new Node(data), refNode);
    }

    public void addAfter(int idx, int data) {
        Node node = getAtNode(idx);
        addAfter(node, data);
    }

    // --------------------REMOVE NODE FROM START OF THE LINKED LIST.
    private Node removeFirstNode() {
        Node node = this.head;
        if (this.size == 1)
            this.head = this.tail = null;
        else {
            this.head = node.next;
            node.next = this.head.prev = null;
        }
        this.size--;
        return node;
    }

    public int removeFirst() {
        if (this.size == 0)
            return -1;

        return removeFirstNode().data;
    }

    // --------------------REMOVE NODE FROM END OF THE LINKED LIST.
    private Node removeLastNode() {
        Node node = this.tail;
        if (this.size == 1)
            this.head = this.tail = null;
        else {
            this.tail = node.prev;
            this.tail.next = node.prev = null;
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
    private Node removeNodeAt(int idx) {
        if (idx == 0)
            return removeFirstNode();
        else if (idx == this.size - 1)
            return removeLastNode();
        else {
            Node curr = getAtNode(idx);
            Node prev = curr.prev;
            Node forw = curr.next;

            prev.next = forw;
            forw.prev = prev;

            curr.next = curr.prev = null;
            this.size--;
            return curr;
        }
    }

    public int removeAt(int idx) {
        if (idx < 0 || idx > this.size - 1)
            return -1;

        return removeNodeAt(idx).data;
    }

    // ----------------------Remove After In Doubly Linkedlist.
    private Node removeAfterNode(Node refNode) {
        Node forw = refNode.next;
        if (forw.next == null) {
            forw.prev = null;
            refNode.next = null;
            this.tail = refNode;
        } else {
            refNode.next = forw.next;
            forw.next.prev = refNode;

            forw.prev = forw.next = null;
        }
        this.size--;
        return forw;
    }

    public int removeAfter(Node refNode) {
        // complete your Code
        if (refNode.next == null) {
            System.out.println("LocationIsInvalid:");
            return -1;
        }
        return removeAfterNode(refNode).data;
    }

    public int removeAfter(int idx) {
        Node node = getAtNode(idx);
        return removeAfter(node);
    }

    // ----------------------Remove Before In Doubly Linkedlist.
    private Node removeBeforeNode(Node refNode) {
        Node prev = refNode.prev;
        if(prev.prev == null) {
          refNode.prev = prev.next = null;
          this.head = refNode;
        } else {
          prev.prev.next = refNode;
          refNode.prev = prev.prev;
  
          prev.prev = prev.next = null;
        }
        this.size--;
        return prev;
      }
  
      public int removeBefore(Node refNode) {
        // complete your code.
        if(refNode.prev == null) {
          System.out.print("LocationIsInvalid: ");
          return -1;
        }
        return removeBeforeNode(refNode).data;
      }
  
      public int removeBefore(int idx) {
        Node node = getAtNode(idx);
        return removeBefore(node);
      }

    // --------------------GET NODE FROM START.
    public int getFirst() {
        if (this.size == 0)
            return -1;
        return this.head.data;
    }

    // --------------------GET NODE FROM END.
    public int getLast() {
        if (this.size == 0)
            return -1;
        return this.tail.data;
    }

    // --------------------GET NODE FROM ANY INDEX.
    private Node getAtNode(int idx) {
        Node curr = this.head;
        while (idx-- > 0)
            curr = curr.next;

        return curr;
    }

    public int getAt(int index) {
        if (this.size == 0)
            return -1;
        else if (index < 0 || index > this.size - 1)
            return -1;

        return getAtNode(index).data;
    }

}
