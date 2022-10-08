public class quickSort {
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static int getLen(ListNode head) {
        if (head == null)
            return 0;

        ListNode curr = head;
        int len = 0;
        while (curr != null) {
            curr = curr.next;
            len++;
        }
        return len;
    }

    public static ListNode[] getSegregate(ListNode head, int pivotIdx) {
        if (head == null || head.next == null)
            return new ListNode[] { null, head, null };

        ListNode pivotNode = head;
        while (pivotIdx-- > 0)
            pivotNode = pivotNode.next;

        ListNode larger = new ListNode(-1);
        ListNode smaller = new ListNode(-1);
        ListNode sp = smaller, lp = larger, curr = head;
        while (curr != null) {
            if (curr != pivotNode && curr.val <= pivotNode.val) {
                sp.next = curr;
                sp = sp.next;
            } else if (curr != pivotNode) {
                lp.next = curr;
                lp = lp.next;
            }
            curr = curr.next;
        }

        sp.next = lp.next = pivotNode.next = null;

        return new ListNode[] { smaller.next, pivotNode, larger.next };
    }

    public static ListNode[] mergeLists(ListNode[] left, ListNode pivotNode, ListNode[] right) {
        ListNode head = null, tail = null;
        if (left[0] != null && right[0] != null) {
            head = left[0];
            left[1].next = pivotNode;
            pivotNode.next = right[0];
            tail = right[1];
        } else if (left[0] == null && right[0] != null) {
            head = pivotNode;
            pivotNode.next = right[0];
            tail = right[1];
        } else if (left[0] != null && right[0] == null) {
            head = left[0];
            left[1].next = pivotNode;
            tail = pivotNode;
        } else
            head = tail = pivotNode;

        return new ListNode[] { head, tail };
    }

    public static ListNode[] quickSort_(ListNode head) {
        if (head == null || head.next == null)
            return new ListNode[] { head, head };

        int len = getLen(head);
        ListNode[] segregatedNodes = getSegregate(head, len / 2);

        ListNode[] left = quickSort_(segregatedNodes[0]);
        ListNode[] right = quickSort_(segregatedNodes[2]);

        return mergeLists(left, segregatedNodes[1], right);
    }

    public static ListNode quickSort(ListNode head) {
        return quickSort_(head)[0];
    }
}
