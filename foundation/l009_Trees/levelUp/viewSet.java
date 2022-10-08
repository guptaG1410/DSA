package levelUp;

import java.util.*;

public class viewSet {
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        // Constructor chaining
        Node(int data) {
            this(data, null, null);
        }
    }

    // Level Order Traversal
    public static void level_Order(Node root) {
        LinkedList<Node> que = new LinkedList<>();

        que.addLast(new Node(root.data));

        while (que.size() != 0) {
            Node top = que.removeFirst();

            System.out.print(top.data + "");

            if (top.left != null) {
                que.addLast(new Node(top.left.data));
            }

            if (top.right != null) {
                que.addLast(new Node(top.right.data));
            }
        }
    }

    public static void level_Order_2(Node root) {
        LinkedList<Node> que = new LinkedList<>();

        que.addLast(root);
        que.addLast(null);
        int level = 1;

        while (que.size() != 0) {
            Node top = que.removeFirst();
            if (top == null) {
                System.out.println(level++ + "--->");
                if (que.size() != 0)
                    que.addLast(null);
                continue;
            }

            System.out.print(top.data + "");

            if (top.left != null)
                que.addLast(top.left);

            if (top.right != null)
                que.addLast(top.right);
        }
    }

    // Left View of Binary Tree===========
    // APPROACH 1 ->
    public static ArrayList<Integer> leftView(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        LinkedList<Node> que = new LinkedList<>();

        que.addLast(root);

        while (que.size() != 0) {
            int size = que.size();
            boolean isFirst = true;

            while (size-- > 0) {
                Node top = que.removeFirst();

                if (isFirst) {
                    ans.add(top.data);
                    isFirst = false;
                }

                if (top.left != null)
                    que.addLast(top.left);

                if (top.right != null)
                    que.addLast(top.right);
            }
        }

        return ans;
    }

    // APPROACH -> 2
    public static ArrayList<Integer> leftView1(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();

        getLV(root, 0, ans);

        return ans;
    }

    public static void getLV(Node root, int level, ArrayList<Integer> ans) {
        if (root == null)
            return;

        if (level == ans.size())
            ans.add(root.data);

        getLV(root.left, level + 1, ans);
        getLV(root.right, level + 1, ans);
    }

    // VERTICAL ORDER ==================
    // Recursively -----------
    // {min, max}
    public static void findWidth(Node root, int vl, int[] minMax) {
        if (root == null)
            return;

        minMax[0] = Math.min(minMax[0], vl);
        minMax[1] = Math.max(minMax[1], vl);

        findWidth(root.left, vl - 1, minMax);
        findWidth(root.right, vl + 1, minMax);
    }

    // Function to get all nodes stored vertically at their respective indices.
    public static void find_verticalOrder(Node root, int vl, ArrayList<ArrayList<Integer>> ans, int shift) {
        if (root == null)
            return;

        ans.get(vl + shift).add(root.data);

        find_verticalOrder(root.left, vl - 1, ans, shift);
        find_verticalOrder(root.right, vl + 1, ans, shift);
    }

    public static ArrayList<ArrayList<Integer>> verticalOrder(Node root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;

        int[] minMax = new int[2];

        findWidth(root, 0, minMax);

        int width = minMax[1] - minMax[0] + 1;

        for (int i = 0; i < width; i++)
            ans.add(new ArrayList<>());

        // calling recursive function.
        find_verticalOrder(root, 0, ans, Math.abs(minMax[0]));

        // calling non-recursive function.
        find_verticalOrder1(root, ans, Math.abs(minMax[0]));
        return ans;
    }

    // Without recursion (USING LEVEL ORDER).
    public static class vlPair {
        Node node;
        int vl;

        vlPair(Node node, int vl) {
            this.node = node;
            this.vl = vl;
        }
    }

    public static void find_verticalOrder1(Node root, ArrayList<ArrayList<Integer>> ans, int shift) {
        if (root == null)
            return;

        LinkedList<vlPair> que = new LinkedList<>();
        que.addLast(new vlPair(root, 0));

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vlPair top = que.removeFirst();
                Node node = top.node;
                int vl = top.vl;

                ans.get(vl + shift).add(node.data);

                if (node.left != null)
                    que.addLast(new vlPair(node.left, vl - 1));

                if (node.right != null)
                    que.addLast(new vlPair(node.right, vl + 1));
            }
        }
    }

    // 987. Vertical Order Traversal of a Binary Tree
    public List<List<Integer>> verticalTraversal(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;

        int[] minMax = new int[2];
        findWidth(root, 0, minMax);

        int width = minMax[1] - minMax[0] + 1;

        for (int i = 0; i < width; i++)
            ans.add(new ArrayList<>());

        PriorityQueue<vlPair> parentQue = new PriorityQueue<>((a, b) -> {
            return a.node.data - b.node.data;
        });

        PriorityQueue<vlPair> childQue = new PriorityQueue<>((a, b) -> {
            return a.node.data - b.node.data;
        });

        parentQue.add(new vlPair(root, Math.abs(minMax[0])));
        while (parentQue.size() != 0) {
            int size = parentQue.size();
            while (size-- > 0) {
                vlPair top = parentQue.remove();

                ans.get(top.vl).add(top.node.data);

                if (top.node.left != null)
                    childQue.add(new vlPair(top.node.left, top.vl - 1));

                if (top.node.right != null)
                    childQue.add(new vlPair(top.node.right, top.vl + 1));
            }

            PriorityQueue<vlPair> temp = parentQue;
            parentQue = childQue;
            childQue = temp;
        }

        return ans;
    }

    // Top View of Binary Tree
    static class vlPairTop {
        Node node;
        int vl;

        vlPairTop(Node node, int vl) {
            this.node = node;
            this.vl = vl;
        }
    }

    static ArrayList<Integer> topView(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        int[] minMax = new int[2];
        findWidth(root, 0, minMax);

        int width = minMax[1] - minMax[0] + 1;

        for (int i = 0; i < width; i++)
            ans.add(null);

        LinkedList<vlPairTop> que = new LinkedList<>();
        que.addLast(new vlPairTop(root, Math.abs(minMax[0])));

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vlPairTop top = que.removeFirst();
                Node node = top.node;
                int vl = top.vl;

                if (ans.get(vl) == null)
                    ans.set(vl, node.data);

                if (node.left != null)
                    que.addLast(new vlPairTop(node.left, vl - 1));

                if (node.right != null)
                    que.addLast(new vlPairTop(node.right, vl + 1));
            }
        }

        return ans;
    }

    // Bottom View of Binary Tree
    public static class vlPair1 {
        Node node;
        int vl;

        vlPair1(Node node, int vl) {
            this.node = node;
            this.vl = vl;
        }
    }

    public static ArrayList<Integer> bottomView(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        int[] minMax = new int[2];
        findWidth(root, 0, minMax);

        int width = minMax[1] - minMax[0] + 1;

        for (int i = 0; i < width; i++)
            ans.add(null);

        LinkedList<vlPair1> que = new LinkedList<>();
        que.addLast(new vlPair1(root, Math.abs(minMax[0])));

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vlPair1 top = que.removeFirst();
                Node node = top.node;
                int vl = top.vl;

                ans.set(vl, node.data);

                if (node.left != null)
                    que.addLast(new vlPair1(node.left, vl - 1));

                if (node.right != null)
                    que.addLast(new vlPair1(node.right, vl + 1));
            }
        }

        return ans;
    }

    // Diagonal Traversal of Binary Tree
    public void diagonal_rec(Node root, int diagLvl, ArrayList<ArrayList<Integer>> diag) {
        if (root == null)
            return;

        if (diagLvl == diag.size())
            diag.add(new ArrayList<>());

        diag.get(diagLvl).add(root.data);

        diagonal_rec(root.left, diagLvl + 1, diag);
        diagonal_rec(root.right, diagLvl, diag);
    }

    public ArrayList<Integer> diagonal(Node root) {
        
        ArrayList<ArrayList<Integer>> diag = new ArrayList<>();
        diagonal_rec(root, 0, diag);

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < diag.size(); i++)
            for (int ele : diag.get(i))
                ans.add(ele);

        return ans;
    }

}
