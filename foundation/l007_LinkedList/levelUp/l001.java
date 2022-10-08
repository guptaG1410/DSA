public class l001 {
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    // -------------------SEGREGATE ZEROS AND ONES
    public static ListNode segregate01(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode zeros = new ListNode(-1), ones = new ListNode(-1), zp = zeros, op = ones, curr = head;

        while (curr != null) {
            if (curr.val == 0) {
                zp.next = curr;
                zp = zp.next;
            } else {
                op.next = curr;
                op = op.next;
            }
            curr = curr.next;
        }
        zp.next = op.next = null;
        zp.next = ones.next;

        return zeros.next;
    }

    // -------------------------------- Segregate 0's, 1's, 2's node
    public static ListNode segregate012(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode zero = new ListNode(-1), one = new ListNode(-1), two = new ListNode(-1), zp = zero, op = one, tp = two,
                curr = head;

        while (curr != null) {
            if (curr.val == 0) {
                zp.next = curr;
                zp = zp.next;
            } else if (curr.val == 1) {
                op.next = curr;
                op = op.next;
            } else {
                tp.next = curr;
                tp = tp.next;
            }

            curr = curr.next;
        }

        zp.next = op.next = tp.next = null;

        op.next = two.next;
        zp.next = one.next;

        return zero.next;
    }

    // --------------------------------------- MergeSort Linked List
    public static ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        slow.next = null;

        return mergeTwoLists(mergeSort(head), mergeSort(mid));
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            return l1 != null ? l1 : l2;

        ListNode dummy = new ListNode(-1), p = dummy, c1 = l1, c2 = l2;

        while (c1 != null && c2 != null) {
            if (c1.val < c2.val) {
                p.next = c1;
                c1 = c1.next;
            } else {
                p.next = c2;
                c2 = c2.next;
            }

            p = p.next;
        }

        p.next = c1 != null ? c1 : c2;
        return dummy.next;
    }

    // ------------------------------------------ Merge K Sorted Lists
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            return l1 != null ? l1 : l2;

        ListNode dummy = new ListNode(-1), p = dummy, c1 = l1, c2 = l2;

        while (c1 != null && c2 != null) {
            if (c1.val < c2.val) {
                p.next = c1;
                c1 = c1.next;
            } else {
                p.next = c2;
                c2 = c2.next;
            }

            p = p.next;
        }

        p.next = c1 != null ? c1 : c2;
        return dummy.next;
    }

    public static ListNode mergeKLists(ListNode[] lists, int si, int ei) {
        if (si == ei)
            return lists[si];

        int mid = (si + ei) / 2;
        ListNode leftList = mergeKLists(lists, si, mid);
        ListNode rightList = mergeKLists(lists, mid + 1, ei);

        return mergeTwoLists(leftList, rightList);
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0)
            return null;

        return mergeKLists(lists, 0, lists.length - 1);
    }

    // --------------------------------Reverse Node Of Linkedlist In K Group
    public static ListNode tt = null, th = null;

    public static int length(ListNode head) {
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

    public static void addFirst(ListNode node) {
        if (th == null)
            th = tt = node;
        else {
            node.next = th;
            th = node;
        }
    }

    public static ListNode reverseInKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1)
            return null;

        int len = length(head);
        ListNode curr = head, oh = null, ot = null;
        while (len >= k) {
            int tempK = k;
            while (tempK-- > 0) {
                ListNode forw = curr.next;
                curr.next = null;
                addFirst(curr);
                curr = forw;
            }

            if (oh == null) {
                oh = th;
                ot = tt;
            } else {
                ot.next = th;
                ot = tt;
            }
            th = tt = null;
            len -= k;
        }
        ot.next = curr;

        return oh;
    }

    // ---------------------Reverse In Range
    public static ListNode th = null, tt = null;

    public static void addFirst(ListNode node) {
        if (th == null)
            th = tt = node;
        else {
            node.next = th;
            th = node;
        }
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null || left == right)
            return head;

        ListNode dummy = new ListNode(-1), prev = dummy, curr = head;
        prev.next = head;
        int i = 1;
        while (i <= right) {
            while (i >= left && i <= right) {
                ListNode forw = curr.next;
                curr.next = null;
                addFirst(curr);
                curr = forw;
                i++;
            }
            if (i > right) {
                prev.next = th;
                tt.next = curr;
                break;
            }
            i++;
            prev = curr;
            curr = curr.next;
        }
        return dummy.next;
    }

    // -------------------------Remove All Duplicates From Sorted Linkedlist
    public static ListNode removeDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode dummy = new ListNode(-1), prev = dummy, curr = head.next;
        prev.next = head;
        while (curr != null) {
            boolean flag = false;
            while (curr != null && prev.next.val == curr.val) {
                flag = true;
                curr = curr.next;
            }
            if (flag)
                prev.next = curr;
            else
                prev = prev.next;
            if (curr != null)
                curr = curr.next;
        }
        return dummy.next;
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

    // ------------------------Subtraction in Linked List
    public static Node subLinkedList(Node l1, Node l2) {
        // code here
        l1 = removeLeadingZeros(l1);
        l2 = removeLeadingZeros(l2);
        Node c1 = null, c2 = null;
        if (isBigger(l1, l2)) {
            c1 = reverse(l1);
            c2 = reverse(l2);
        } else {
            c1 = reverse(l2);
            c2 = reverse(l1);
        }

        Node dummy = new Node(-1), prev = dummy;
        int borrow = 0;
        while (c1 != null || c2 != null) {
            int diff = borrow + (c1 != null ? c1.data : 0) - (c2 != null ? c2.data : 0);
            if (diff < 0) {
                borrow = -1;
                diff += 10;
            } else {
                borrow = 0;
            }
            prev.next = new Node(diff);
            prev = prev.next;
            if (c1 != null)
                c1 = c1.next;
            if (c2 != null)
                c2 = c2.next;
        }
        Node ans = reverse(dummy.next);
        prev = dummy;
        prev.next = null;
        Node c = ans;
        while (c != null) {
            if (c.data != 0) {
                prev.next = c;
                break;
            }
            Node forw = c.next;
            c.next = null;
            c = forw;
        }

        return dummy.next != null ? dummy.next : new Node(0);
    }

    public static int len(Node head) {
        if (head == null)
            return 0;

        Node curr = head;
        int len = 0;
        while (curr != null) {
            curr = curr.next;
            len++;
        }
        return len;
    }

    public static Node removeLeadingZeros(Node a) {
        if (a != null && a.data == 0)
            return removeLeadingZeros(a.next);
        else
            return a;
    }

    public static boolean isBigger(Node l1, Node l2) {
        int len1 = len(l1);
        int len2 = len(l2);

        if (len1 > len2)
            return true;
        else if (len1 < len2)
            return false;

        Node c1 = l1, c2 = l2;
        while (c1 != null) {
            if (c1.data > c2.data)
                return true;
            else if (c1.data < c2.data)
                return false;

            c1 = c1.next;
            c2 = c2.next;
        }
        return true;
    }

    public static Node reverse(Node head) {
        if (head == null || head.next == null)
            return head;

        Node prev = null, curr = head;
        while (curr != null) {
            Node forw = curr.next;
            curr.next = prev;
            prev = curr;
            curr = forw;
        }
        return prev;
    }

    // ------------------------------Multiply Two Linkedlist
    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode forw = curr.next;
            curr.next = prev;
            prev = curr;
            curr = forw;
        }
        return prev;
    }

    public static void addList(ListNode prevList, ListNode prodList) {
        int carry = 0;
        while (prodList != null || carry != 0) {
            int sum = carry + (prodList != null ? prodList.val : 0) + (prevList.next != null ? prevList.next.val : 0);
            carry = sum / 10;
            sum %= 10;
            if (prevList.next != null)
                prevList.next.val = sum;
            else
                prevList.next = new ListNode(sum);

            prevList = prevList.next;
            if (prodList != null)
                prodList = prodList.next;
        }
    }

    public static ListNode multiplyByDigit(ListNode l1, int dig) {
        ListNode dummy = new ListNode(-1), prev = dummy, curr = l1;
        int carry = 0;
        while (curr != null || carry != 0) {
            int prod = carry + (curr != null ? curr.val : 0) * dig;
            carry = prod / 10;
            prod %= 10;
            prev.next = new ListNode(prod);
            prev = prev.next;
            if (curr != null)
                curr = curr.next;
        }

        return dummy.next;
    }

    public static ListNode multiplyTwoLL(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);

        ListNode ans = new ListNode(-1), prev = ans;
        while (l2 != null) {
            ListNode prodList = multiplyByDigit(l1, l2.val);
            addList(prev, prodList);
            prev = prev.next;
            l2 = l2.next;
        }

        return reverse(ans.next);
    }

    // -------------------------Copy Linkedlist With Random Pointers
    public static void copyList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            ListNode forw = curr.next;
            ListNode node = new ListNode(curr.val);

            curr.next = node;
            node.next = forw;

            curr = forw;
        }
    }

    public static void assignRandom(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            if (curr.random != null)
                curr.next.random = curr.random.next;

            curr = curr.next.next;
        }
    }

    public static ListNode extractCopyList(ListNode head) {
        ListNode dummy = new ListNode(-1), prev = dummy, curr = head;
        while (curr != null) {
            ListNode forw = curr.next.next;

            prev.next = curr.next;
            curr.next = forw;

            curr = forw;
            prev = prev.next;
        }
        return dummy.next;
    }

    public static ListNode copyRandomList(ListNode head) {
        copyList(head);
        assignRandom(head);

        return extractCopyList(head);
    }

    // -------------------------Is Cycle Present In Linkedlist
    public static boolean isCyclePresentInLL(ListNode head) {
        if (head == null || head.next == null)
            return false;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast)
                return true;
        }
        return false;
    }

    // -----------------------------Cycle Node In Linkedlist
    public static ListNode CycleNode(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast)
                break;
        }
        if (slow != fast)
            return null;

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

}
