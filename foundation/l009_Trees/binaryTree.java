import java.util.*;

public class binaryTree {
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

    public static void preOrder(Node root, ArrayList<Integer> ans) {
        if (root == null)
            return;

        ans.add(root.data);
        preOrder(root.left, ans);
        preOrder(root.right, ans);
    }

    public static void inOrder(Node root, ArrayList<Integer> ans) {
        if (root == null)
            return;

        inOrder(root.left, ans);
        ans.add(root.data);
        inOrder(root.right, ans);
    }

    public static void postOrder(Node root, ArrayList<Integer> ans) {
        if (root == null)
            return;

        postOrder(root.left, ans);
        postOrder(root.right, ans);
        ans.add(root.data);
    }

    public static int size(Node node) {
        if (node == null)
            return 0;

        int leftSize = size(node.left);
        int rightSize = size(node.right);

        return leftSize + rightSize + 1;

        // return node == null ? 0 : size(node.left) + size(node.right) + 1;
    }

    public static int sum(Node node) {
        if (node == null)
            return 0;

        int leftSum = sum(node.left);
        int rightSum = sum(node.right);

        return leftSum + rightSum + node.data;
        // return node == null ? 0 : sum(node.left) + sum(node.right) + node.data;
    }

    public static int max(Node node) {
        if (node == null)
            return -(int) 1e9;

        int leftMax = max(node.left);
        int rightMax = max(node.right);

        return Math.max(Math.max(leftMax, rightMax), node.data);

        // return node == null ? -(int) 1e9 : Math.max(node.data,
        // Math.max(max(node.left), max(node.right)));
    }

    public static int max2(Node node) {
        if (node == null)
            return -(int) 1e9;

        int maxEle = node.data;
        maxEle = Math.max(maxEle, max2(node.left));
        maxEle = Math.max(maxEle, max2(node.left));

        return maxEle;

        // return node == null ? -(int) 1e9 : Math.max(node.data,
        // Math.max(max(node.left), max(node.right)));
    }

    public static int min(Node node) {
        if (node == null)
            return (int) 1e9;

        int leftMin = min(node.left);
        int rightMin = min(node.right);

        return Math.min(Math.min(leftMin, rightMin), node.data);

        // return node == null ? (int) 1e9 : Math.max(node.data,
        // Math.min(min(node.left), min(node.right)));
    }

    // Height in Terms of Edges return -1 at null, Height in terms of Nodes return 0
    // at null
    public static int height(Node node) {
        return node == null ? -1 : Math.max(height(node.left), height(node.right)) + 1;
    }

    // Find Data in BT -
    public static boolean find(Node node, int data) {
        if (node == null)
            return false;
        if (node.data == data)
            return true;

        return find(node.left, data) || find(node.right, data);
    }

    // Count Leaves in Binary Tree -
    public static int countLeaves(Node node) {
        if (node == null)
            return 0;

        if (node.left == null && node.right == null)
            return 1;

        return countLeaves(node.left) + countLeaves(node.right);
    }

    // Print the nodes having exactly one child in a Binary tree -
    public static void exactlyOneChild(Node node, ArrayList<Integer> ans) {
        if (node == null || (node.left == null && node.right == null))
            return;
        if (node.left == null || node.right == null)
            ans.add(node.data);
        exactlyOneChild(node.left, ans);
        exactlyOneChild(node.right, ans);

    }

    // OR
    public static int countExactlyOneChild(Node node) {
        if (node == null || (node.left == null && node.right == null))
            return 0;

        int left = countExactlyOneChild(node.left);
        int right = countExactlyOneChild(node.right);
        int sum = left + right;
        if (node.left == null || node.right == null)
            sum += 1;
        return sum;
    }

    // Nodetorootpath In Binary Tree -
    // With helper function:
    public static boolean nodeToRootPath(Node node, int data, ArrayList<Integer> ans) {
        if (node == null)
            return false;

        if (node.data == data) {
            ans.add(node.data);
            return true;
        }

        boolean res = nodeToRootPath(node.left, data, ans) || nodeToRootPath(node.right, data, ans);
        if (res)
            ans.add(node.data);

        return res;
    }

    public static ArrayList<Integer> nodeToRootPath(Node node, int data) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (node == null)
            return ans;
        nodeToRootPath(node, data, ans);
        return ans;
    }

    // Without Helper Function:
    public static ArrayList<Integer> solve(Node node, int data) {
        if (node == null)
            return null;

        if (node.data == data) {
            ArrayList<Integer> ans = new ArrayList<Integer>(Arrays.asList(node.data));
            return ans;
        }

        ArrayList<Integer> left = solve(node.left, data);
        if (left != null) {
            left.add(node.data);
            return left;
        }

        ArrayList<Integer> right = solve(node.right, data);
        if (right != null) {
            right.add(node.data);
            return right;
        }

        return null;
    }

    public static ArrayList<Integer> nodeToRootPath_1(Node node, int data) {
        ArrayList<Integer> ans = solve(node, data);
        return ans != null ? ans : new ArrayList<>();
    }

    // K Levels Down
    public ArrayList<Integer> Kdistance(Node root, int k) {
        ArrayList<Integer> ans = new ArrayList<>();
        path(root, k, ans);
        return ans;
    }

    public void path(Node root, int k, ArrayList<Integer> ans) {
        if (root == null || k < 0)
            return;

        if (k == 0) {
            ans.add(root.data);
            return;
        }

        path(root.left, k - 1, ans);
        path(root.right, k - 1, ans);
    }

    // Print Nodes K Distance Away
    // Method 1 - TC : O(N), SC: O(N)
    public static boolean Path(Node node, int data, ArrayList<Node> ans) {
        if (node == null)
            return false;

        if (node.data == data) {
            ans.add(node);
            return true;
        }

        boolean res = Path(node.left, data, ans) || Path(node.right, data, ans);
        if (res)
            ans.add(node);

        return res;
    }

    public static void kLevelsDown(Node node, int k, Node block, ArrayList<Integer> ans) {
        if (node == null || k < 0 || node == block)
            return;

        if (k == 0) {
            ans.add(node.data);
            return;
        }

        kLevelsDown(node.left, k - 1, block, ans);
        kLevelsDown(node.right, k - 1, block, ans);
    }

    public static void printKNodesFar(Node node, int data, int k) {
        ArrayList<Node> list = new ArrayList<>();
        Path(node, data, list);

        ArrayList<Integer> ans = new ArrayList<Integer>();
        Node block = null;
        for (int i = 0; i < list.size(); i++) {
            kLevelsDown(list.get(i), k - i, block, ans);
            block = list.get(i);
        }
    }

    // METHOD 2 -> TC:O(N), SC:O(1)
    public static int printKNodesFar2(Node node, int data, int k, ArrayList<Integer> ans) {
        if (node == null)
            return -1;

        if (node.data == data) {
            kLevelsDown(node, k, null, ans);
            return 1;
        }

        int leftDistance = printKNodesFar2(node.left, data, k, ans);
        if (leftDistance != -1) {
            kLevelsDown(node, k - leftDistance, node.left, ans);
            return leftDistance + 1;
        }

        int rightDistance = printKNodesFar2(node.right, data, k, ans);
        if (rightDistance != -1) {
            kLevelsDown(node, k - rightDistance, node.right, ans);
            return rightDistance + 1;
        }

        return -1;
    }

    // Remove Leaves In Binary Tree
    public static Node removeLeaves(Node node) {
        if (node == null)
            return null;

        if (node.left == null && node.right == null)
            return null;

        node.left = removeLeaves(node.left);
        node.right = removeLeaves(node.right);

        return node;
    }

    // Is A Binary Search Tree---------------
    // METHOD 1 -
    public static Node prev = null;

    public static boolean isBST(Node root) {
        if (root == null)
            return true;

        if (!isBST(root.left))
            return false;

        if (prev != null && prev.data >= root.data)
            return false;

        prev = root;

        if (!isBST(root.right))
            return false;

        return true;
    }

    // METHOD 2 -
    public static class isBSTPair {
        boolean isBST = true;
        int mindataue = (int) 1e9;
        int maxdataue = -(int) 1e9;
    }

    public static isBSTPair isBST_1(Node root) {
        if (root == null)
            return new isBSTPair();

        isBSTPair left = isBST_1(root.left);
        if (!left.isBST)
            return left;

        isBSTPair right = isBST_1(root.right);
        if (!right.isBST)
            return right;

        isBSTPair self = new isBSTPair();
        self.isBST = false;

        if (left.maxdataue < root.data && right.mindataue > root.data) {
            self.isBST = true;
            self.mindataue = Math.min(left.mindataue, root.data);
            self.maxdataue = Math.max(right.maxdataue, root.data);
        }
        return self;
    }

    // Balanced Binary Tree---------------
    // METHOD 1 -> TC: O(N^2), SC: O(N)
    public static boolean isBalanced(Node root) {
        if (root == null)
            return true;

        if (!isBalanced(root.left))
            return false;

        if (!isBalanced(root.right))
            return false;

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        return Math.abs(leftHeight - rightHeight) <= 1;
    }

    // METHOD 2 -> TC : O(N), SC : O(N)
    public static class balPair {
        int height = -1;
        boolean isBal = true;
    }

    public static balPair isBalanced_1(Node root) {
        if (root == null)
            return new balPair();

        balPair left = isBalanced_1(root.left);
        if (!left.isBal)
            return left;
        balPair right = isBalanced_1(root.right);
        if (!right.isBal)
            return right;

        balPair self = new balPair();
        if (Math.abs(left.height - right.height) > 1) {
            self.isBal = false;
            return self;
        }

        self.height = Math.max(left.height, right.height) + 1;
        return self;
    }

    // METHOD 3 - TC : O(N), SC : O(N)
    public int isBal3(Node node) {
        if (node == null)
            return -1;

        int lh = isBal3(node.left);
        if (lh == -2)
            return lh;

        int rh = isBal3(node.right);
        if (rh == -2)
            return rh;

        if (Math.abs(lh - rh) > 1) {
            return -2;
        }

        return Math.max(lh, rh) + 1;
    }

    public boolean isBalanced_(Node root) {

        int ans = isBal3(root);
        return ans != -2 ? true : false;
    }

    // Tilt Of Binary Tree---------------
    // METHOD 1 - TC: O(N), SC: O(N)
    public class tiltPair {
        int sum = 0;
        int tilt = 0;
    }

    public tiltPair findTilt_(Node root) {
        if (root == null)
            return new tiltPair();

        tiltPair left = findTilt_(root.left);
        tiltPair right = findTilt_(root.right);

        tiltPair ans = new tiltPair();
        ans.tilt = left.tilt + right.tilt + Math.abs(left.sum - right.sum);
        ans.sum = left.sum + right.sum + root.data;

        return ans;
    }

    // METHOD 2 - TC: O(N), SC: O(N)
    public static int tilt = 0;

    public static int tilt(Node node) {
        if (node == null)
            return 0;
        int left = tilt(node.left);
        int right = tilt(node.right);

        tilt += Math.abs(left - right);

        return left + right + node.data;
    }

    // Diameter Of A Binary Tree---------------
    // METHOD 1 - TC : O(N^2), SC : O(N)
    public static int diameter(Node node) {
        if (node == null)
            return 0;

        int leftDiameter = diameter(node.left);
        int rightDiameter = diameter(node.right);

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        return Math.max(Math.max(leftDiameter, rightDiameter), leftHeight + rightHeight + 2);
    }

    // METHOD 2 - TC: O(N), SC: O(N)
    // Here, we are using arr instead of class pair because "diameter" and "height"
    // is
    // of same data type.
    // arr - {diameter, height}
    public static int[] diameter_1(Node node) {
        if (node == null)
            return new int[] { 0, -1 };

        int[] ld = diameter_1(node.left);
        int[] rd = diameter_1(node.right);

        int[] myAns = new int[2];
        myAns[0] = Math.max(Math.max(ld[0], rd[0]), ld[1] + rd[1] + 2);
        myAns[1] = Math.max(ld[1], rd[1]) + 1;

        return myAns;
    }

    // METHOD 3 - TC: O(N), SC: O(N)
    // Here, function will calculate diameter while return height of tree.
    public static int diameter = 0;

    public static int diameter_2(Node node) {
        if (node == null)
            return -1;

        int lh = diameter_2(node.left);
        int rh = diameter_2(node.right);

        diameter = Math.max(diameter, lh + rh + 2);

        return Math.max(lh, rh) + 1;
    }

    // Largest Bst Subtree
    public static class lBSTPair {
        boolean isBST = true;
        int max = -(int) 1e9;
        int min = (int) 1e9;
        int maxSize = 0;
        Node maxBSTNode = null;
    }

    public static lBSTPair largestBST(Node node) {
        if (node == null)
            return new lBSTPair();

        lBSTPair left = largestBST(node.left);
        lBSTPair right = largestBST(node.right);

        lBSTPair myAns = new lBSTPair();
        // If left and right subtree is BST then root node will definitely a BST.
        if (left.isBST && right.isBST && left.max < node.data && right.min > node.data) {
            myAns.isBST = true;
            myAns.max = Math.max(right.max, node.data);
            myAns.min = Math.min(left.min, node.data);
            myAns.maxSize = left.maxSize + right.maxSize + 1;
            myAns.maxBSTNode = node;
        }
        // If either left or right isn't a BST then root node has not any chance to be a
        // BST
        else {
            myAns.isBST = false;
            myAns.maxSize = Math.max(left.maxSize, right.maxSize);
            myAns.maxBSTNode = left.maxSize > right.maxSize ? left.maxBSTNode : right.maxBSTNode;
        }

        return myAns;
    }

    // Lowest Common Ancestor of a Binary Tree
    public Node lowestCommonAncestor(Node root, Node p, Node q) {
        if (root == null)
            return root;

        ArrayList<Node> p1 = nodeToRootPath_236(root, p.data);
        ArrayList<Node> p2 = nodeToRootPath_236(root, q.data);

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

    public ArrayList<Node> solve_236(Node node, int data) {
        if (node == null)
            return null;

        if (node.data == data) {
            ArrayList<Node> ans = new ArrayList<>(Arrays.asList(node));
            return ans;
        }

        ArrayList<Node> left = solve_236(node.left, data);
        if (left != null) {
            left.add(node);
            return left;
        }

        ArrayList<Node> right = solve_236(node.right, data);
        if (right != null) {
            right.add(node);
            return right;
        }

        return null;
    }

    public ArrayList<Node> nodeToRootPath_236(Node node, int data) {
        ArrayList<Node> ans = solve_236(node, data);
        return ans != null ? ans : new ArrayList<>();
    }

    // Min distance between two given nodes of a Binary Tree
    int findDist(Node root, int a, int b) {
        Node LCA = lowestCommonAncestor(root, a, b);
        int dist1 = findDist(LCA, a);
        int dist2 = findDist(LCA, b);

        return dist1 + dist2 - 2;
    }

    Node lowestCommonAncestor(Node root, int a, int b) {
        if (root == null)
            return null;

        if (root.data == a || root.data == b)
            return root;

        Node left = lowestCommonAncestor(root.left, a, b);
        Node right = lowestCommonAncestor(root.right, a, b);

        if (left != null && right != null)
            return root;

        return left != null ? left : right;
    }

    int findDist(Node root, int data) {
        if (root == null)
            return 0;

        if (root.data == data)
            return 1;

        int left = findDist(root.left, data);
        int right = findDist(root.right, data);
        if (left == 0 && right == 0)
            return 0;

        return left + right + 1;
    }
}
