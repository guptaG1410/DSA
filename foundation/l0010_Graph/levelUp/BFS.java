import java.util.LinkedList;

public class BFS {
    // 1020. Number of Enclaves-------------
    public int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public int numEnclaves(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 || j == 0 || i == grid.length - 1 || j == grid[0].length - 1) {
                    if (grid[i][j] == 1)
                        bfs(grid, i, j);
                }
            }
        }

        int area = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1)
                    area++;
            }
        }

        return area;
    }

    public void bfs(int[][] grid, int sr, int sc) {
        LinkedList<int[]> que = new LinkedList<>();
        que.addLast(new int[] { sr, sc });
        grid[sr][sc] = 0;

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int[] land = que.removeFirst();
                for (int[] d : dir) {
                    int r = land[0] + d[0];
                    int c = land[1] + d[1];

                    if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 1) {
                        grid[r][c] = 0;
                        que.addLast(new int[] { r, c });
                    }
                }
            }
        }

    }

    // 994. Rotting Oranges ---------------------------------
    public int orangesRotting(int[][] grid) {
        LinkedList<int[]> que = new LinkedList<>();
        int freshOranges = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2)
                    que.addLast(new int[] { i, j });
                else if (grid[i][j] == 1)
                    freshOranges++;
            }
        }

        if (freshOranges == 0)
            return 0;

        int time = 0;
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int[] rottenOrg = que.removeFirst();
                for (int[] d : dir) {
                    int r = rottenOrg[0] + d[0];
                    int c = rottenOrg[1] + d[1];

                    if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 1) {
                        grid[r][c] = 2;
                        que.addLast(new int[] { r, c });
                        freshOranges--;
                    }
                }
            }
            time++;
        }

        return freshOranges == 0 ? time - 1 : -1;
    }

    // 542. 01 Matrix --------------
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        LinkedList<int[]> que = new LinkedList<>();
        boolean[][] vis = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    que.addLast(new int[] { i, j });
                    vis[i][j] = true;
                }
            }
        }

        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int[] rmvPair = que.removeFirst();
                for (int[] d : dir) {
                    int r = rmvPair[0] + d[0];
                    int c = rmvPair[1] + d[1];

                    if (r >= 0 && c >= 0 && r < m && c < n && !vis[r][c]) {
                        vis[r][c] = true;
                        mat[r][c] = mat[rmvPair[0]][rmvPair[1]] + 1;
                        que.addLast(new int[] { r, c });
                    }
                }
            }
        }

        return mat;
    }

    // 210. Course Schedule II
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        for (int[] p : prerequisites)
            indegree[p[0]]++;

        LinkedList<Integer> que = new LinkedList<>();

        for (int i = 0; i < numCourses; i++)
            if (indegree[i] == 0)
                que.addLast(i);

        int[] topo = new int[numCourses];
        int idx = 0;
        while (que.size() != 0) {
            int ele = que.removeFirst();
            topo[idx++] = ele;
            for (int[] p : prerequisites) {
                if (p[1] == ele) {
                    indegree[p[0]]--;
                    if (indegree[p[0]] == 0)
                        que.addLast(p[0]);
                }
            }
        }
        return idx == numCourses ? topo : new int[0];
    }

}
