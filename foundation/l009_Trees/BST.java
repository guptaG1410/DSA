import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class BST {
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

    // TC : O(logN), SC : O(1)
    public static int size(Node node) {
        return node == null ? 0 : size(node.left) + size(node.right) + 1;
    }

    // TC : O(logN), SC : O(1)
    public static int height(Node node) {
        return node == null ? 0 : Math.max(height(node.left), height(node.right)) + 1;
    }

    // TC : O(logN), SC : O(1)
    public static int max(Node node) {
        while (node.right != null)
            node = node.right;

        return node.data;
    }

    // TC : O(logN), SC : O(1)
    public static int min(Node node) {
        while (node.left != null)
            node = node.left;

        return node.data;
    }

    // TC : O(logN), SC : O(1)
    public static int sum(Node node) {
        return node == null ? 0 : sum(node.left) + sum(node.right) + node.data;
    }

    // TC : O(logN), SC : O(1)
    public static boolean find(Node node, int data) {
        while (node != null) {
            if (node.data == data)
                return true;

            else if (node.data < data)
                node = node.right;

            else
                node = node.left;
        }

        return false;
    }

    // TC : O(logN), SC : O(1)
    public static ArrayList<Node> nodeToRootPath(Node node, int data) {
        ArrayList<Node> ans = new ArrayList<>();
        boolean flag = false;
        while (node != null) {
            ans.add(node);
            if (node.data == data) {
                flag = true;
                break;
            } else if (node.data < data)
                node = node.right;

            else
                node = node.left;
        }
        if (!flag)
            return new ArrayList<>();

        Collections.reverse(ans);
        return ans;
    }

    // Lowest Common Ancestor of a Binary Search Tree
    // TC : O(HEIGHT), SC : O(1)
    public Node lowestCommonAncestor(Node root, Node p, Node q) {
        while (root != null) {
            if (root.data > p.data && root.data > q.data)
                root = root.left;
            else if (root.data < p.data && root.data < q.data)
                root = root.right;
            else
                break;
        }

        return root;
    }

    // TC : O(HEIGHT), SC : O(HEIGHT)
    public Node lowestCommonAncestor_(Node root, Node p, Node q) {
        if (root == null)
            return null;

        if (root.data > p.data && root.data > q.data)
            return lowestCommonAncestor_(root.left, p, q);

        if (root.data < p.data && root.data < q.data)
            return lowestCommonAncestor_(root.right, p, q);

        return root;
    }

    // Print BST elements in given range
    // TC : O(N), SC : O(N)
    public static void rangeBST(Node root, int low, int high, ArrayList<Integer> ans) {
        if (root == null)
            return;

        if (low < root.data)
            rangeBST(root.left, low, high, ans);

        if (low <= root.data && high >= root.data)
            ans.add(root.data);

        if (high > root.data)
            rangeBST(root.right, low, high, ans);

    }

    // Insert into a Binary Search Tree
    public static Node insertIntoBST(Node root, int data) {
        if (root == null)
            return new Node(data, null, null);

        if (root.data < data)
            root.right = insertIntoBST(root.right, data);

        else if (root.data > data)
            root.left = insertIntoBST(root.left, data);

        return root;
    }

    // Delete Node in a BST
    public static Node deleteNode(Node root, int data) {
        if (root == null)
            return null;

        if (root.data < data)
            root.right = deleteNode(root.right, data);

        else if (root.data > data)
            root.left = deleteNode(root.left, data);

        else {
            if (root.left == null || root.right == null)
                return root.left == null ? root.right : root.left;

            int minEle = min(root.right);
            root.data = minEle;

            root.right = deleteNode(root.right, minEle);
        }

        return root;
    }

    // Binary Search Tree to Greater Sum Tree
    public Node bstToGst(Node root) {
        bstToGST(root, new int[1]);
        return root;
    }

    public void bstToGST(Node root, int[] sum) {
        if (root == null)
            return;

        bstToGST(root.right, sum);

        root.data += sum[0];
        sum[0] = root.data;

        bstToGST(root.left, sum);
    }

    // Inorder Successor in BST
    public Node inorderSuccessor(Node root, Node x) {
        if (root == null)
            return null;

        if (root.data == x.data) {
            if (root.right != null)
                return getLeftMost(root.right);

            return null;
        }

        if (root.data > x.data) {
            Node data = inorderSuccessor(root.left, x);
            if (data == null)
                return root;

            return data;
        } else
            return inorderSuccessor(root.right, x);
    }

    public Node getLeftMost(Node root) {
        while (root.left != null)
            root = root.left;

        return root;
    }

    // Inorder Predecessor in BST
    public Node inorderPredecessor(Node root, Node x) {
        if (root == null)
            return null;

        if (root.data == x.data) {
            if (root.left != null)
                return getRightMost(root.left);

            return null;
        }

        if (root.data < x.data) {
            Node data = inorderSuccessor(root.right, x);
            if (data == null)
                return root;

            return data;
        } else
            return inorderSuccessor(root.left, x);
    }

    public Node getRightMost(Node root) {
        while (root.right != null)
            root = root.right;

        return root;
    }

    // Predecessor and Successor Using Morris Traversal
    // TC: O(N), SC : O(1)
    public Node[] inorderPreSucc(Node root, Node x) {
        Node[] ans = new Node[2];

        Node predecessor = null;
        Node successor = null;
        Node previous = null;

        Node curr = root;
        while (curr != null) {
            Node leftNode = curr.left;
            if (leftNode == null) {
                // Print curr.data
                if (curr == x)
                    predecessor = previous;

                if (predecessor == x)
                    successor = curr;

                previous = curr;
                curr = curr.right;
            } else {
                Node rightMost = getRightMostNode(leftNode, curr);

                if (rightMost.right == null) {
                    rightMost.right = curr;
                    curr = leftNode;
                } else {
                    // Print curr.data
                    rightMost.right = null;
                    if (curr == x)
                        predecessor = previous;

                    if (predecessor == x)
                        successor = curr;

                    previous = curr;
                    curr = curr.right;
                }
            }
        }

        ans[0] = predecessor;
        ans[1] = successor;

        return ans;
    }

    public Node getRightMostNode(Node leftNode, Node curr) {
        while (leftNode.right != null && leftNode.right != curr)
            leftNode = leftNode.right;

        return leftNode;
    }

    // O(logn);

    public Node[] inorderPreSucc(Node root, int x) {
        Node[] ans = new Node[2]; // index 0-> pre, index 1-> succ;

        Node pred = null;
        Node succ = null;

        Node curr = root;

        while (curr != null) {
            if (curr.data < x) {
                pred = curr;
                curr = curr.right;
            } else if (curr.data > x) {
                succ = curr;
                curr = curr.left;
            } else {
                Node leftMost_node = getLeftMost(curr.right);

                if (leftMost_node != null)
                    succ = leftMost_node;

                Node rightMost_node = getRightMost(curr.left);

                if (rightMost_node != null)
                    pred = rightMost_node;
            }
        }

        ans[0] = pred;
        ans[1] = succ;

        return ans;
    }

    // Trim a Binary Search Tree
    public Node trimBST(Node root, int low, int high) {
        if (root == null)
            return null;

        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);

        if (root.data < low) {
            Node rightChild = root.right;
            root = null;
            return rightChild;
        } else if (root.data > high) {
            Node leftChild = root.left;
            root = null;
            return leftChild;
        }

        return root;
    }

    // 230. Kth Smallest Element in a BST
    // Iterative Solution
    // TC : O(N), SC : O(N)
    public int kthSmallest(Node root, int k) {
        LinkedList<Node> st = new LinkedList<>();
        pushAllLeft(st, root);

        while (st.size() != 0) {
            Node top = st.getFirst();
            st.removeFirst();
            k--;
            if (k == 0)
                return top.data;

            if (top.right != null)
                pushAllLeft(st, top.right);
        }
        return -1;
    }

    public void pushAllLeft(LinkedList<Node> st, Node root) {
        while (root != null) {
            st.addFirst(root);
            root = root.left;
        }
    }

    // Recusrive:
    // TC : O(N), SC : O(1)
    public int kthSmallest1(Node root, int k) {
        return dfs(root, k, new int[1]);
    }

    public int dfs(Node root, int k, int[] tempK) {
        if (root == null || k < 0)
            return -1;

        int left = dfs(root.left, k, tempK);
        if (left != -1)
            return left;

        if (k == ++tempK[0])
            return root.data;

        return dfs(root.right, k, tempK);
    }

}
