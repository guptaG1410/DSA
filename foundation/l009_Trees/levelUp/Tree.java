package levelUp;

import java.util.*;

public class Tree {
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

    // Burn the binary tree starting from the target node

    // Movind downward and burning nodes.
    public static void getBurningTree(Node root, int k, Node block, ArrayList<ArrayList<Integer>> ans) {
        if (root == null || root == block)
            return;

        if (k == ans.size())
            ans.add(new ArrayList<>());

        ans.get(k).add(root.data);
        // Moving down -
        getBurningTree(root.left, k + 1, block, ans);
        getBurningTree(root.right, k + 1, block, ans);
    }

    public static int burning_tree(Node root, Node target, ArrayList<ArrayList<Integer>> ans) {
        if (root == null)
            return -1;

        if (root == target) {
            getBurningTree(root, 0, null, ans);
            return 1;
        }

        int left_Dist = burning_tree(root.left, target, ans);
        if (left_Dist != -1) {
            getBurningTree(root, left_Dist, root.left, ans);
            return left_Dist + 1;
        }

        int right_Dist = burning_tree(root.right, target, ans);
        if (right_Dist != -1) {
            getBurningTree(root, right_Dist, root.right, ans);
            return right_Dist + 1;
        }

        return -1;
    }

    public static ArrayList<ArrayList<Integer>> burning_tree(Node root, Node target) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int res = burning_tree(root, target, ans);
        return res != -1 ? ans : new ArrayList<>();
    }


    // Minimum time to burn a Tree starting from a Leaf node ======
    public static int maxTime = 0;
    public static int solve(Node A, int B) {
        burningTree(A, B);
        return maxTime;
    }
    
    public static void getBurningTree(Node root, int time, Node block) {
        if(root == null || root == block)
            return;
            
        maxTime = Math.max(maxTime, time);
        
        getBurningTree(root.left, time + 1, block);
        getBurningTree(root.right, time + 1, block);
    }
    
    public static int burningTree(Node A, int B) {
        if(A == null)
            return -1;
            
        if(A.data == B) {
            getBurningTree(A, 0, null);
            return 1;
        }
        
        int leftDist = burningTree(A.left, B);
        if(leftDist != -1) {
            getBurningTree(A, leftDist, A.left);
            return leftDist + 1;
        }
        
        int rightDist = burningTree(A.right, B);
        if(rightDist != -1) {
            getBurningTree(A, rightDist, A.right);
            return rightDist + 1;
        }
        
        return -1;
    }
}
