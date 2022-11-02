import java.util.ArrayList;
import java.util.Arrays;

public class graph {
    public static class Edge {
        int src;
        int nbr;
        int weight;

        Edge(int src, int nbr, int weight) {
            this.src = src;
            this.nbr = nbr;
            this.weight = weight;
        }
    }

    // ----------Construction--------------
    public static void construction() {
        int N = 8;
        // Here, each index of an array of name graph is able to hold ArrayList but
        // actually not holding any ArrayList.
        ArrayList<Edge>[] graph = new ArrayList[N];

        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>(); // Now here, array is actually holding an empty arraylist.

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 4, 6, 8);
        addEdge(graph, 5, 6, 3);

        boolean[] vis = new boolean[N];
        preorder(graph, 0, vis, 0, "");
    }

    // ---------------Addition of Edge--------------
    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(u, v, w));
        graph[v].add(new Edge(v, u, w));
    }

    // ---------------- Display ----------------
    public static void display(ArrayList<Edge>[] graph, int N) {
        for (int i = 0; i < N; i++) {
            System.out.print(i + " ---> ");
            for (Edge e : graph[i])
                System.out.print("(" + e.nbr + ", " + e.weight + ")");

            System.out.println();
        }
    }

    // ------------- Remove Edge -------------
    public static void removeEdge(ArrayList<Edge>[] graph, int u, int v) {
        int idx1 = findEdge(graph, u, v);
        int idx2 = findEdge(graph, v, u);

        graph[u].remove(idx1);
        graph[v].remove(idx2);
    }

    public static int findEdge(ArrayList<Edge>[] graph, int u, int v) {
        ArrayList<Edge> list = graph[u];
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).nbr == v)
                return i;
        }
        return -1;
    }

    // ------------------- Remove Vertex ------------
    // It's important to note that here we start our for loop from the end of the
    // list(ArrayList) because if we start it from the beginning then we'll
    // definitely miss one of the edge in our ArrayList(easily understandable when
    // the ArrayList has more than two Edge within itself).
    public static void removeVertex(ArrayList<Edge>[] graph, int u) {
        ArrayList<Edge> list = graph[u];
        for (int i = list.size() - 1; i >= 0; i--)
            removeEdge(graph, list.get(i).src, list.get(i).nbr);
    }

    // ------------------- Find only one path possible -------------
    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis) {
        if (src == dest)
            return true;

        vis[src] = true;
        boolean res = false;
        for (Edge e : graph[src]) {
            if (!vis[e.nbr])
                res = res || hasPath(graph, e.nbr, dest, vis);
        }
        return res;
    }

    // ------------------ Find all paths possible ----------------
    public static int allPaths(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis, String psf) {
        if (src == dest) {
            System.out.println(psf + dest);
            return 1;
        }
        int count = 0;
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.nbr])
                count += allPaths(graph, e.nbr, dest, vis, psf + src);
        }
        vis[src] = false;
        return count;
    }

    // ------------------ Preorder of given graph -------------
    public static void preorder(ArrayList<Edge>[] graph, int src, boolean[] vis, int wsf, String psf) {
        System.out.println(src + " --> " + (psf + src) + " @ " + wsf);

        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.nbr])
                preorder(graph, e.nbr, vis, wsf + e.weight, psf + src);
        }

        vis[src] = false;
    }

    // --------------- Postorder of given graph -------------------
    public static void postorder(ArrayList<Edge>[] graph, int src, boolean[] vis, String wsf, String psf) {
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.nbr])
                postorder(graph, e.nbr, vis, wsf + e.weight, psf + src);
        }
        System.out.println(src + "-->" + (psf + src) + "@" + wsf);
        vis[src] = false;
    }

    // ------------- Lightest path -------------------------
    public static class ligthPathPair {
        String psf = "";
        int wsf = (int) 1e9;
    }

    public static ligthPathPair lightestPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis) {
        if (src == dest) {
            ligthPathPair base = new ligthPathPair();
            base.psf += src;
            base.wsf = 0;
            return base;
        }

        vis[src] = true;
        ligthPathPair ans = new ligthPathPair();
        for (Edge e : graph[src]) {
            if (!vis[e.nbr]) {
                ligthPathPair recAns = lightestPath(graph, e.nbr, dest, vis);
                if (recAns.wsf != (int) 1e9 && recAns.wsf + e.weight < ans.wsf) {
                    ans.psf = recAns.psf + src;
                    ans.wsf = recAns.wsf + e.weight;
                }
            }
        }
        vis[src] = false;
        return ans;
    }

    // ---------------- Heaviest Path ----------------
    public static class heavyPathPair {
        String psf = "";
        int wsf = -(int) 1e9;
    }

    public static heavyPathPair heaviestPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis) {
        if (src == dest) {
            heavyPathPair base = new heavyPathPair();
            base.psf += src;
            base.wsf = 0;
            return base;
        }

        vis[src] = true;
        heavyPathPair ans = new heavyPathPair();
        for (Edge e : graph[src]) {
            if (!vis[e.nbr]) {
                heavyPathPair recAns = heaviestPath(graph, e.nbr, dest, vis);
                if (recAns.wsf != -(int) 1e9 && recAns.wsf + e.weight > ans.wsf) {
                    ans.psf = recAns.psf + src;
                    ans.wsf = recAns.wsf + e.weight;
                }
            }
        }
        vis[src] = false;
        return ans;
    }

    // ---------------- Ceil and Floor ----------------------
    public static class ceilFloorPair {
        int ceil = (int) 1e9;
        int floor = -(int) 1e9;
    }

    public static void ceilNFloor(ArrayList<Edge>[] graph, int src, boolean[] vis, int data, int wsf,
            ceilFloorPair pair) {
        if (wsf < data)
            pair.floor = Math.max(pair.floor, wsf);
        if (wsf > data)
            pair.ceil = Math.min(pair.ceil, wsf);

        vis[src] = true;
        for (Edge e : graph[src])
            if (!vis[e.nbr])
                ceilNFloor(graph, e.nbr, vis, data, wsf + e.weight, pair);

        vis[src] = false;
    }

    public static void ceilFloor(ArrayList<Edge>[] graph, int data) {
        ceilFloorPair pair = new ceilFloorPair();
        ceilNFloor(graph, 0, new boolean[graph.length], data, 0, pair);
    }

    // ------------------- Get Connected Components Of A Graph -----------------
    // TC : O(V + E)
    // O(V)
    public static void gcc(ArrayList<Edge>[] graph, boolean[] vis, ArrayList<ArrayList<Integer>> comps) {
        for (int i = 0; i < graph.length; i++) {
            if (!vis[i]) {
                ArrayList<Integer> comp = new ArrayList<>();
                dfs(graph, i, vis, comp);
                comps.add(comp);
            }
        }
    }

    // O(E)
    public static void dfs(ArrayList<Edge>[] graph, int src, boolean[] vis, ArrayList<Integer> comp) {
        vis[src] = true;
        comp.add(src);
        for (Edge e : graph[src]) {
            if (!vis[e.nbr])
                dfs(graph, e.nbr, vis, comp);
        }
    }

    // -------------- 200. Number of Islands -------------
    public int numIslands(char[][] grid) {
        int islandCount = 0;
        // direction vector to travel in all 4 directions.
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j, dir);
                    islandCount++;
                }
            }
        }
        return islandCount;
    }

    public void dfs(char[][] grid, int sr, int sc, int[][] dir) {
        // Marking visited land as water(i.e. 1 -> 0)
        grid[sr][sc] = '0';
        for (int[] d : dir) {
            int r = sr + d[0];
            int c = sc + d[1];

            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == '1')
                dfs(grid, r, c, dir);
        }
    }

    // ------------------ 695. Max Area of Island ------------------
    public int maxAreaOfIsland(int[][] grid) {
        int maxSize = 0;
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int size = dfs(grid, i, j, dir);
                    maxSize = Math.max(maxSize, size);
                }
            }
        }
        return maxSize;
    }

    public int dfs(int[][] grid, int sr, int sc, int[][] dir) {
        grid[sr][sc] = 0;
        int size = 0;
        for (int[] d : dir) {
            int r = sr + d[0];
            int c = sc + d[1];

            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 1)
                size += dfs(grid, r, c, dir);
        }

        return size + 1;
    }

    public static void main(String[] args) {
        construction();
    }

}
