import java.util.ArrayList;
import java.util.Arrays;

public class dfs {
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
        // Here, each index of an array of name graph is able to hold ArrayArrayList but
        // actually not holding any ArrayArrayList.
        ArrayList<Edge>[] graph = new ArrayList[N];

        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>(); // Now here, array is actually holding an empty arrayArrayList.

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 4, 6, 8);
        addEdge(graph, 5, 6, 3);

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

    // 463. Island Perimeter--------------------
    public int islandPerimeter(int[][] grid) {
        int totPerimeter = 0;
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    totPerimeter += 4;
                    int count = countNbr(grid, i, j, dir);
                    totPerimeter -= count;
                }
            }
        }
        return totPerimeter;
    }

    public int countNbr(int[][] grid, int sr, int sc, int[][] dir) {
        int count = 0;
        for (int[] d : dir) {
            int r = sr + d[0];
            int c = sc + d[1];

            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 1)
                count++;
        }
        return count;
    }

    // 130. Surrounded Regions ----------------
    public void solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i == 0 || j == 0 || i == board.length - 1 || j == board[0].length - 1) {
                    if (board[i][j] == 'O')
                        dfs(board, i, j);
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'B') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void dfs(char[][] board, int sr, int sc) {
        board[sr][sc] = 'B';
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        for (int[] d : dir) {
            int r = sr + d[0];
            int c = sc + d[1];

            if (r >= 0 && c >= 0 && r < board.length && c < board[0].length && board[r][c] == 'O')
                dfs(board, r, c);
        }
    }

    // 1020. Number of Enclaves -------------------
    public int numEnclaves(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i == 0 || j == 0 || i == board.length - 1 || j == board[0].length - 1) {
                    if (board[i][j] == 1)
                        dfs1(board, i, j);
                }
            }
        }

        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1)
                    count++;
            }
        }

        return count;
    }

    public void dfs1(int[][] board, int sr, int sc) {
        board[sr][sc] = 2;
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        for (int[] d : dir) {
            int r = sr + d[0];
            int c = sc + d[1];

            if (r >= 0 && c >= 0 && r < board.length && c < board[0].length && board[r][c] == 1)
                dfs1(board, r, c);
        }
    }

    // 1254. Number of Closed Islands-----------------
    public int closedIsland(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 || j == 0 || i == grid.length - 1 || j == grid[0].length - 1) {
                    if (grid[i][j] == 0)
                        dfs2(grid, i, j);
                }
            }
        }

        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    count++;
                    dfs2(grid, i, j);
                }
            }
        }
        return count;

    }

    public void dfs2(int[][] board, int sr, int sc) {
        board[sr][sc] = 1;
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        for (int[] d : dir) {
            int r = sr + d[0];
            int c = sc + d[1];

            if (r >= 0 && c >= 0 && r < board.length && c < board[0].length && board[r][c] == 0)
                dfs2(board, r, c);
        }
    }

    // 1905. Count Sub Islands-----------------------
    public int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length, n = grid1[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1) {
                    boolean dfs = isSubIsland(grid1, grid2, i, j, m, n);
                    if (dfs)
                        count++;
                }
            }
        }
        return count;
    }

    public boolean isSubIsland(int[][] grid1, int[][] grid2, int sr, int sc, int m, int n) {
        boolean ans = (grid1[sr][sc] == grid2[sr][sc]);
        grid2[sr][sc] = 0;
        for (int[] d : dir) {
            int r = sr + d[0];
            int c = sc + d[1];

            if (r >= 0 && c >= 0 && r < m && c < n && grid2[r][c] == 1)
                ans = isSubIsland(grid1, grid2, r, c, m, n) && ans;
        }

        return ans;
    }


    // Journey to the Moon -----------------------
    // https://www.hackerrank.com/challenges/journey-to-the-moon/problem
    public static long journeyToMoon(int n, ArrayList<ArrayList<Integer>> astronaut) {
        // Write your code here
            ArrayList<ArrayList<Integer>> ArrayList = new ArrayList<>(n);
            for(int i = 0; i < n; i++) 
                ArrayList.add(new ArrayList<>());
                
            for(ArrayList<Integer> e : astronaut) {
                ArrayList.get(e.get(0)).add(e.get(1));
                ArrayList.get(e.get(1)).add(e.get(0));
            }
            
            long totalSize = 0, ans = 0;
            boolean[] vis = new boolean[n];
            for(int i = 0; i < n; i++) {
                if(!vis[i]) {
                    int size = dfs(ArrayList, vis, i);
                    ans = ans + totalSize * size;
                    totalSize += size;
                }
            }
            return ans;
        }
        
        public static int dfs(ArrayList<ArrayList<Integer>> graph, boolean[] vis, int src) {
            vis[src] = true;
            int size = 0;
            for(int nbr : graph.get(src)) {
                if(!vis[nbr]) 
                    size += dfs(graph, vis, nbr);
            }
            return size + 1;
        }
}
