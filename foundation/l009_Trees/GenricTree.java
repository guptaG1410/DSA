import java.util.ArrayList;
import java.util.Arrays;

public class GenricTree {

    public static class Node {
        int data = 0;
        ArrayList<Node> children;

        Node(int data) {
            this.data = data;
            this.children = new ArrayList<>();
        }
    }

    // Height in Terms of Edges return -1 at null, Height in terms of Nodes return 0
    // at null
    public static int height(Node root) {
        int height = -1;

        for (Node child : root.children)
            height = Math.max(height(child), height);

        return height + 1;
    }

    public static int size(Node root) {
        int size = 0;

        for (Node child : root.children)
            size += size(child);

        return size + 1;
    }

    public static int maximum(Node root) {
        int max = root.data;

        for (Node child : root.children)
            max = Math.max(maximum(child), max);

        return max;
    }

    public static int minimum(Node root) {
        int min = root.data;

        for (Node child : root.children)
            min = Math.min(minimum(child), min);

        return min;
    }

    public static int sum(Node root) {
        int sum = root.data;
        for (Node child : root.children)
            sum += sum(child);

        return sum;
    }

    public static boolean find(Node root, int data) {
        if (root.data == data)
            return true;

        boolean res = false;
        for (Node child : root.children)
            res = res || find(child, data);

        return res;
    }

    // Node To Root Path In Generic Tree
    // Approach 1-
    public static ArrayList<Integer> nodeToRootPath(Node node, int data) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        nodeToRootPath(node, ans, data);
        return ans;
    }

    public static boolean nodeToRootPath(Node node, ArrayList<Integer> ans, int data) {
        // if (node == null)
        // return false;

        if (node.data == data) {
            ans.add(node.data);
            return true;
        }

        boolean res = false;
        for (Node child : node.children)
            res = res || nodeToRootPath(child, ans, data);

        if (res)
            ans.add(node.data);

        return res;
    }

    // Approach 2 -
    public static ArrayList<Node> nodeToRootPath_(Node node, int data) {
        if (node.data == data) {
            return new ArrayList<>(Arrays.asList(node));
        }

        ArrayList<Node> ans = new ArrayList<>();
        for (Node child : node.children) {
            ans = nodeToRootPath_(child, data);
            if (ans.size() != 0)
                break;
        }

        if (ans.size() != 0)
            ans.add(node);

        return ans;

    }

    // Lowest Common Ancestor (generic Tree)
    public static Node lca(Node node, int d1, int d2) {
        ArrayList<Node> p1 = nodeToRootPath_(node, d1);
        ArrayList<Node> p2 = nodeToRootPath_(node, d2);

        int i = p1.size() - 1;
        int j = p2.size() - 1;

        Node LCA = null;
        while (i >= 0 && j >= 0 && p1.get(i) == p2.get(j)) {
            LCA = p2.get(j);
            i--;
            j--;
        }

        return LCA;
    }

    // Distance Between Two Nodes In A Generic Tree
    public static int distanceBetweenNodes(Node node, int d1, int d2) {
        Node LCA = lca(node, d1, d2);
        int dist1 = findDist(LCA, d1);
        int dist2 = findDist(LCA, d2);

        return dist1 + dist2 - 2;
    }

    public static int findDist(Node root, int data) {
        if (root == null)
            return 0;

        if (root.data == data)
            return 1;

        int res = 0;
        for (Node child : root.children)
            res += findDist(child, data);

        if (res != 0)
            return res + 1;

        return res;
    }

    // Are Trees Similar In Shape
    public static boolean areSimilar(Node n1, Node n2) {
        if (n1.children.size() != n2.children.size())
            return false;

        boolean res = true;
        for (int i = 0; i < n1.children.size();)
            return res && areSimilar(n1.children.get(i), n2.children.get(i));

        return res;
    }


    // Are Trees Mirror In Shape
    public static boolean areMirror01(Node n1, Node n2) {
        if(n1.children.size() != n2.children.size())
            return false;

        boolean res = true;
        int size = n1.children.size();
        for(int i = 0; i < n1.children.size(); i++) 
            res = res && areMirror01(n1.children.get(i), n2.children.get(size - 1 - i));
        
        return res;
    }

    // Are Trees Mirror In Shape
    public static boolean areMirror(Node n1, Node n2) {
        if (n1.children.size() != n2.children.size())
            return false;

        boolean res = true;
        int j = n1.children.size() - 1;
        for (int i = 0; i < n1.children.size(); i++) {
            res = res && areMirror(n1.children.get(i), n2.children.get(j));
            j--;
        }
        return res;
    }

    // Ceil And Floor In Generic Tree
    static int ceil = (int) 1e9;
    static int floor = -(int) 1e9;

    public static void ceilAndFloor(Node node, int data) {
        if (node.data > data)
            ceil = Math.min(ceil, node.data);

        if (node.data < data)
            floor = Math.max(floor, node.data);

        for (Node child : node.children)
            ceilAndFloor(child, data);
    }

    // Kth Largest Element In Tree
    // TC : O(KN), SC : O(1)
    public static int floor(Node node, int ub) {
        int maxRes = -(int) 1e9;
        for (Node child : node.children)
            maxRes = Math.max(maxRes, floor(child, ub));

        return node.data < ub ? Math.max(node.data, maxRes) : maxRes;
    }

    public static int kthLargest(Node node, int k) {
        int upperBound = (int) 1e9;
        for (int i = 0; i < k; i++)
            upperBound = floor(node, upperBound);

        return upperBound;
    }

    // Linearize A Generic Tree
    public static Node getTail(Node node) {
        while (node.children.size() != 0)
            node = node.children.get(0);

        return node;
    }

    public static void linearize(Node node) {
        for (Node child : node.children)
            linearize(child);

        for (int i = node.children.size() - 1; i > 0; i--) {
            Node tail = getTail(node.children.get(i - 1));
            tail.children.add(node.children.get(i));

            node.children.remove(i);
        }
    }
}
