public class questions {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // 876. Middle of the Linked List
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 19. Remove Nth Node From End of List
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null)
            return null;

        ListNode slow = head, fast = head;
        while (n-- > 0)
            fast = fast.next;

        if (fast == null)
            return head = head.next;

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        ListNode node = slow.next;
        slow.next = slow.next.next;
        node.next = null;

        return head;
    }

    // 83. Remove Duplicates from Sorted List
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode dummy = new ListNode(-101);
        ListNode dp = dummy;

        ListNode curr = head;
        while (curr != null) {
            while (curr != null && dp.val == curr.val) {
                ListNode forw = curr.next;
                curr.next = null;
                curr = forw;
            }
            dp.next = curr;
            if (curr != null) {
                curr = curr.next;
                dp = dp.next;
            }
        }
        return dummy.next;
    }

    // 143. Reorder List
    public ListNode mid(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode forw = curr.next;

            curr.next = prev;
            prev = curr;
            curr = forw;
        }
        return prev;
    }

    public void reorderList(ListNode head) {
        ListNode mid = mid(head);
        ListNode nhead = mid.next;
        mid.next = null;

        nhead = reverse(nhead);
        ListNode c1 = head, c2 = nhead;
        while (c2 != null) {
            ListNode f1 = c1.next, f2 = c2.next;

            c1.next = c2;
            c2.next = f1;

            c1 = f1;
            c2 = f2;
        }
    }

    // 21. Merge Two Sorted Lists
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null)
            return list1 != null ? list1 : list2;

        ListNode dummy = new ListNode(-101);
        ListNode c1 = list1, c2 = list2, dp = dummy;
        while (c1 != null && c2 != null) {
            if (c1.val <= c2.val) {
                dp.next = c1;
                c1 = c1.next;
            } else {
                dp.next = c2;
                c2 = c2.next;
            }
            dp = dp.next;
        }
        dp.next = c1 != null ? c1 : c2;

        return dummy.next;
    }


    // 148. Sort List
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        
        ListNode slow = head, fast = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        slow.next = null;
        
        return mergeTwoSortedll(sortList(head), sortList(mid));
    }
    
    public ListNode mergeTwoSortedll(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null) 
            return l1 != null ? l1 : l2;
        
        ListNode dummy = new ListNode(-1);
        ListNode c1 = l1, c2 = l2, dp = dummy;
        while(c1 != null && c2 != null) {
            if(c1.val <= c2.val) {
                dp.next = c1;
                c1 = c1.next;
            } else {
                dp.next = c2;
                c2 = c2.next;
            }
            dp = dp.next;
        }
        dp.next = c1 != null ? c1 : c2;
        
        return dummy.next;
    }

    // 234. Palindrome Linked List
    public boolean isPalindrome(ListNode head) {
        ListNode mid = mid(head);
        ListNode secondHead = mid.next;
        mid.next = null;
        secondHead = reverse(secondHead);
        ListNode c1 = head, c2 = secondHead;
        while(c2 != null) {
            if(c1.val != c2.val)
                return false;
            c1 = c1.next;
            c2 = c2.next;
        }
        return true;
    }
    
    public ListNode mid(ListNode head) {
      if(head == null || head.next == null) 
        return head;

      ListNode slow = head, fast = head;
      while(fast.next != null && fast.next.next != null) {
        slow = slow.next;
        fast = fast.next.next;
      }
      return slow;
    }

    public ListNode reverse(ListNode head) {
      if(head == null || head.next == null) 
        return head;

      ListNode prev = null;
      ListNode curr = head;
      while(curr != null) {
        ListNode forw = curr.next;
        curr.next = prev;
        prev = curr;
        curr = forw;
      }
      return prev;
    }

    // 160. Intersection of Two Linked Lists
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)
            return null;
        
        int a = length(headA);
        int b = length(headB);
        
        ListNode biggerList = a > b ? headA : headB;
        ListNode smallerList = b < a ? headB : headA;
        int diff = Math.abs(a - b);
        
        while(diff-- > 0) 
            biggerList = biggerList.next;
        
        while(biggerList != smallerList) {
            biggerList = biggerList.next;
            smallerList = smallerList.next;
        }
        
        return smallerList != null ? smallerList : null;
    }
    
    public int length(ListNode head) {
        if(head == null)
            return 0;
        
        ListNode curr = head;
        int len = 0;
        while(curr != null) {
            curr = curr.next;
            len++;
        }
        return len;
    }

}
