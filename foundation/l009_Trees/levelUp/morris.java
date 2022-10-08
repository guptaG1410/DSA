package levelUp;

import java.util.*;

public class morris {
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

    // Inorder Traversal
    public static ArrayList<Integer> inOrder(Node root) {
        // Code
        ArrayList<Integer> ans = new ArrayList<>();
        Node curr = root;
        while (curr != null) {
            Node leftNode = curr.left;

            if (leftNode == null) {
                ans.add(curr.data);
                curr = curr.right;
            } else {
                Node rightMost = getRightMost(leftNode, curr);

                if (rightMost.right == null) {
                    rightMost.right = curr;
                    curr = leftNode;
                } else {
                    ans.add(curr.data);
                    rightMost.right = null;
                    curr = curr.right;
                }
            }
        }

        return ans;
    }

    public static Node getRightMost(Node leftNode, Node curr) {
        while (leftNode.right != null && leftNode.right != curr)
            leftNode = leftNode.right;

        return leftNode;
    }


    // Preorder Traversal
    public static ArrayList<Integer> PreOrder(Node root) {
        // Code
        ArrayList<Integer> ans = new ArrayList<>();
        Node curr = root;
        while (curr != null) {
            Node leftNode = curr.left;

            if (leftNode == null) {
                ans.add(curr.data);
                curr = curr.right;
            } else {
                Node rightMost = getRightMost(leftNode, curr);

                if (rightMost.right == null) {
                    ans.add(curr.data);
                    rightMost.right = curr;
                    curr = leftNode;
                } else {
                    rightMost.right = null;
                    curr = curr.right;
                }
            }
        }

        return ans;
    }

}
