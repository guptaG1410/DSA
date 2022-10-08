package levelUp;

public class construction {
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

    // 105. Construct Binary Tree from Preorder and Inorder Traversal
    public Node buildTree(int[] preorder, int[] inorder) {
        return constructTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public Node constructTree(int[] pre, int preSi, int preEi, int[] in, int inSi, int inEi) {
        if (preSi == preEi)
            return new Node(pre[preSi]);

        if (preSi > preEi)
            return null;

        Node root = new Node(pre[preSi]);
        int idx = inSi;
        while (in[idx] != pre[preSi])
            idx++;

        int leftEle = idx - inSi;

        root.left = constructTree(pre, preSi + 1, preSi + leftEle, in, inSi, idx - 1);
        root.right = constructTree(pre, preSi + leftEle + 1, preEi, in, idx + 1, inEi);

        return root;
    }

    // 106. Construct Binary Tree from Inorder and Postorder Traversal
    public Node buildTree1(int[] inorder, int[] postorder) {
        return constructTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }
    
    public Node constructTree1(int[] in, int inSi, int inEi, int[] post, int postSi, int postEi) {
        if(inSi == inEi || postSi == postEi)
            return new Node(post[postSi]);
        
        if(inSi > inEi || postSi > postEi)
            return null;
        
        Node root = new Node(post[postEi]);
        
        int idx = inSi;
        while(in[idx] != post[postEi])
            idx++;

        int leftEle = idx - inSi;
        
        root.left = constructTree(in, inSi, idx - 1, post, postSi, postSi + leftEle - 1);
        root.right = constructTree(in, idx + 1, inEi, post, postSi + leftEle, postEi - 1);
        
        return root;
    }

    // 108. Convert Sorted Array to Binary Search Tree
    public Node sortedArrayToBST(int[] nums) {
        return constructBST(nums, 0, nums.length - 1);
    }
    
    public Node constructBST(int[] nums, int si, int ei) {
        if(si > ei)
            return null;
        
        int mid = (si + ei) / 2;
        Node root = new Node(nums[mid]);
        
        root.left = constructBST(nums, si, mid - 1);
        root.right = constructBST(nums, mid + 1, ei);
        
        return root;
    }


    // 1008. Construct Binary Search Tree from Preorder Traversal
    // APPROACH - TC : O(N^2), SC : O(N)
    public Node bstFromPreorder(int[] preorder) {
        return constructBST1(preorder, 0, preorder.length - 1);
    }
    
    public Node constructBST1(int[] pre, int si, int ei) {
        if(si > ei)
            return null;
        
        Node root = new Node(pre[si]);
        int idx = si + 1;
        while(idx < pre.length && pre[idx] < pre[si])
            idx++;
        
        root.left = constructBST1(pre, si + 1, idx - 1);
        root.right = constructBST1(pre, idx, ei);
        
        return root;
    }

    // OPTIMIZED APPROACH - TC : O(N), SC : O(N)
    public Node bstFromPreorder1(int[] preorder) {
        return constructBST2(preorder, -(int)1e9, (int)1e9, new int[1]);
    }
    
    public Node constructBST2(int[] preorder, int lowerLmt, int upperLmt, int[] idx) {
        int i = idx[0];
        
        if(i > preorder.length - 1 || preorder[i] < lowerLmt || preorder[i] > upperLmt)
            return null;
        
        Node root = new Node(preorder[i]);
        idx[0]++;
        
        root.left = constructBST2(preorder, lowerLmt, preorder[i], idx);
        root.right = constructBST2(preorder, preorder[i], upperLmt, idx);
        
        return root;
    }

    // 449. Serialize and Deserialize BST
    public void serialize(Node root, StringBuilder sb) {
        if(root == null)
            return;
        
        sb.append(root.data + " ");
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        
        serialize(root, sb);
        
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data.equals(""))
            return null;
        
        String[] arr = data.split(" ");
        int[] preorder = new int[arr.length];
        
        for(int i = 0; i < arr.length; i++)
            preorder[i] = Integer.parseInt(arr[i]);
        
        return bstFromPreorder1(preorder);
    }

    // 297. Serialize and Deserialize Binary Tree
    // Encodes a tree to a single string.
    public void serialize1(Node root, StringBuilder sb) {
        if(root == null) {
            sb.append("null,");
            return;
        }
        sb.append(root.data + ",");
        serialize1(root.left, sb);
        serialize1(root.right, sb);
    }
    
    public String serialize1(Node root) {
        StringBuilder sb = new StringBuilder();
        serialize1(root, sb);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public Node deserialize1(String data) {
        String[] arr = data.split(",");
        return deserialize1(arr, new int[1]);
    }
    
    public Node deserialize1(String[] arr, int[] idx) {
        if(idx[0] >= arr.length || arr[idx[0]].equals("null")) {
            idx[0]++;
            return null;
        }
        
        Node root = new Node(Integer.parseInt(arr[idx[0]++]));
        root.left = deserialize1(arr, idx);
        root.right = deserialize1(arr, idx);
        
        return root;
    }

}
