import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class unionFind {
    static int[] parent; // Use an array to keep track of the subsets and which nodes belong to that
                         // subset. Let the array be parent[].
    static int[] size; // Initially, all slots of parent array are initialized to hold the same values
                       // as the node.

    public static int unionFindDSU(ArrayList<ArrayList<Integer>> edges, int N) {
        parent = new int[N];
        size = new int[N];

        for (int i = 0; i < N; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        for (ArrayList<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);

            int p1 = findParent(u);
            int p2 = findParent(v);

            if (p1 != p2)
                merge(p1, p2);
        }

        int no_Of_Components = 0;
        for (int i = 0; i < N; i++) {
            int parent = findParent(i);
            if (parent == i)
                no_Of_Components++;
        }

        return no_Of_Components;

    }

    // Path compression function
    public static int findParent(int u) {
        if (parent[u] == u)
            return u;

        // int ans = findParent(parent[u]);
        // parent[u] = ans;

        return parent[u] = findParent(parent[u]);
    }

    public static void merge(int parent1, int parent2) {
        if (size[parent1] <= size[parent2]) {
            parent[parent1] = parent2;
            size[parent2] += size[parent1];
        } else {
            parent[parent2] = parent1;
            size[parent1] += size[parent2];
        }
    }

    // 684. Redundant Connection--------------------
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        parent = new int[n + 1];
        size = new int[n + 1];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            int p1 = findParent(u);
            int p2 = findParent(v);

            if (p1 == p2)
                return edge;

            merge(p1, p2);
        }

        return new int[] {};
    }

    // 1061. Lexicographically Smallest Equivalent String
    public static String smallestString(String s, String t, String str) {
        // Write your code here.
        parent = new int[26];

        for (int i = 0; i < 26; i++)
            parent[i] = i;

        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            int v = t.charAt(i) - 'a';

            int p1 = findParent(u);
            int p2 = findParent(v);

            if (p1 != p2) {
                parent[p1] = Math.min(p1, p2);
                parent[p2] = Math.min(p1, p2);
            }
        }

        String ans = "";
        for (int i = 0; i < str.length(); i++) {
            int u = str.charAt(i) - 'a';
            ans = ans + (char) (findParent(u) + 'a');
        }

        return ans;
    }

    // 695. Max Area of Island
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        parent = new int[m * n];
        size = new int[m * n];

        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        for (int i = 0; i < m * n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int p1 = findParent(i * n + j);

                    for (int[] d : dir) {
                        int r = i + d[0];
                        int c = j + d[1];

                        if (r >= 0 && c >= 0 && r < m && c < n && grid[r][c] == 1) {
                            int p2 = findParent(r * n + c);

                            if (p1 != p2) {
                                parent[p2] = p1;
                                size[p1] += size[p2];
                            }
                        }
                    }
                }
            }
        }

        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    if (i * n + j == findParent(i * n + j))
                        maxArea = Math.max(maxArea, size[i * n + j]);
                }
            }
        }

        return maxArea;
    }

    // 990. Satisfiability of Equality Equations
    public boolean equationsPossible(String[] equations) {
        parent = new int[26];

        for (int i = 0; i < 26; i++)
            parent[i] = i;

        for (String eqn : equations) {
            if (eqn.charAt(1) == '=') {
                int p1 = findParent(eqn.charAt(0) - 'a');
                int p2 = findParent(eqn.charAt(3) - 'a');

                if (p1 != p2)
                    parent[p2] = p1;
            }
        }

        for (String eqn : equations) {
            if (eqn.charAt(1) == '!') {
                int p1 = findParent(eqn.charAt(0) - 'a');
                int p2 = findParent(eqn.charAt(3) - 'a');

                if (p1 == p2)
                    return false;
            }
        }

        return true;
    }

    // 1202. Smallest String With Swaps
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        parent = new int[n];

        for(int i = 0; i < n; i++) 
            parent[i] = i;

        for(List<Integer> pair : pairs) {
            int p1 = findParent(pair.get(0));
            int p2 = findParent(pair.get(1));

            if(p1 != p2) {
                if(parent[p1] < parent[p2])
                    parent[p2] = p1;
                else 
                    parent[p1] = p2;
            }
        }

        HashMap<Integer, PriorityQueue<Character>> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            int root = findParent(i);
            map.putIfAbsent(root, new PriorityQueue<>());
            map.get(root).add(s.charAt(i));
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            sb.append(map.get(findParent(i)).remove());
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        int N = edges.size();
        System.out.println(unionFindDSU(edges, N));
    }
}
