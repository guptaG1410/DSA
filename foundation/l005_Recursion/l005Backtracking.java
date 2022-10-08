import java.util.ArrayList;
import java.util.Scanner;

public class l005Backtracking {
    // ALGO -{ Mark
    // for all unvisited Nbr's
    // ---->call dfs for Nbr's
    // unMark}

    // 0 -> empty cell, 1 -> blocked cell

    // Flood Fill
    public static int floodFill(int sr, int sc, int[][] board, String ans, int[][] dir, String[] dirS) {
        if (sr == board.length - 1 && sc == board[0].length - 1) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;

        board[sr][sc] = 1;

        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < board.length && c < board[0].length) {
                if (board[r][c] == 0)
                    count += floodFill(r, c, board, ans + dirS[d], dir, dirS);
            }
        }

        board[sr][sc] = 0;
        return count;
    }

    // Flood Fill With Jumps
    public static int floodFill_jumps(int sr, int sc, int[][] board, String ans, int[][] dir, String[] dirS) {
        int n = board.length, m = board[0].length;
        if (sr == n - 1 && sc == m - 1) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        board[sr][sc] = 1;
        // 1. If we replace direction loop with radius loop then it will traverse the
        // maze(2D array) in the circular motion i.e. radius = constant and direction =
        // varies.
        // 2. And when we do reverse of upper case as shown in the code then it will
        // traverse the maze in the direction to direction motion i.e. radius = varies
        // and direction = constant.
        // Preferred 2 over 1 cauz it safe us from unnecessary checks.
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= Math.max(n, m); rad++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];

                if (r >= 0 && c >= 0 && r < n && c < m) {
                    if (board[r][c] == 0)
                        count += floodFill_jumps(r, c, board, ans + dirS[d] + rad, dir, dirS);
                } else
                    break; // Here, if the dir goes out of the boundaries we'll simply break from that.
            }
        }
        board[sr][sc] = 0;
        return count;
    }

    // In this function given below we only find 1 possible path from the source to
    // destination, then we return to the root node.
    public static boolean floodFill_2(int sr, int sc, int[][] board, String ans, int[][] dir, String[] dirS) {
        int n = board.length, m = board[0].length;
        if (sr == n - 1 && sc == m - 1) {
            System.out.println(ans);
            return true;
        }

        board[sr][sc] = 1;

        boolean res = false;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < board.length && c < board[0].length) {
                if (board[r][c] == 0)
                    res = res || floodFill_2(r, c, board, ans + dirS[d], dir, dirS); // Here, whenever res hits the base
                                                                                     // case it becomes TRUE after that
                                                                                     // they don't traverse to the other
                                                                                     // possible paths but directly come
                                                                                     // to the root node or we can say
                                                                                     // the parent node.
            }
        }

        board[sr][sc] = 0;
        return res;
    }

    // Longest Path in a maze
    public static int floodFill_longest(int[][] maze, int sr, int sc, int[][] dir) {
        if (sr == maze.length - 1 && sc == maze[0].length - 1)
            return 0;

        maze[sr][sc] = 1;
        int longPath = -1;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < maze.length && c < maze[0].length && maze[r][c] == 0) {
                int recAns = floodFill_longest(maze, r, c, dir);
                if (recAns != -1 && recAns + 1 > longPath)
                    longPath = recAns + 1;
            }
        }
        maze[sr][sc] = 0;
        return longPath;
    }

    // Shortest Path in a maze
    public static int floodFill_short(int[][] maze, int sr, int sc, int[][] dir) {
        if (sr == maze.length - 1 && sc == maze[0].length - 1)
            return 0;

        maze[sr][sc] = 1;
        int shortPath = (int) 1e9;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < maze.length && c < maze[0].length && maze[r][c] == 0) {
                int recAns = floodFill_short(maze, r, c, dir);
                if (recAns != (int) 1e9 && recAns + 1 < shortPath)
                    shortPath = recAns + 1;
            }
        }
        maze[sr][sc] = 0;
        return shortPath;
    }

    // Rat in a Maze Problem - I
    // https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1#
    public static ArrayList<String> findPath(int[][] m, int n) {
        ArrayList<String> ans = new ArrayList<>();
        if (n == 0 || m[n - 1][n - 1] == 0 || m[0][0] == 0)
            return ans;
        int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        String[] dirS = { "U", "D", "L", "R" };
        getPath(m, 0, 0, "", ans, dir, dirS);
        return ans;
    }

    public static void getPath(int[][] maze, int sr, int sc, String ans, ArrayList<String> res, int[][] dir,
            String[] dirS) {
        if (sr == maze.length - 1 && sc == maze[0].length - 1) {
            res.add(ans);
            return;
        }
        maze[sr][sc] = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < maze.length && c < maze[0].length) {
                if (maze[r][c] == 1)
                    getPath(maze, r, c, ans + dirS[d], res, dir, dirS);
            }
        }
        maze[sr][sc] = 1;
    }

    // Special Matrix
    // https://practice.geeksforgeeks.org/problems/special-matrix4201/1/#
    // This code would lead to TLE, so it's better to approach it with DP.
    public int FindWays(int n, int m, int[][] blocked_cells) {
        // Code here
        int[][] maze = new int[n][m];

        for (int[] cell : blocked_cells) {
            int i = cell[0] - 1;
            int j = cell[1] - 1;

            maze[i][j] = -1;
        }
        int[][] dir = { { 0, 1 }, { 1, 0 } };
        if (maze[n - 1][m - 1] == -1 || maze[0][0] == -1)
            return 0;

        return getPath(maze, 0, 0, dir);
    }

    public static int getPath(int[][] maze, int sr, int sc, int[][] dir) {
        if (sr == maze.length - 1 && sc == maze[0].length - 1)
            return 1;

        int count = 0;
        maze[sr][sc] = -1;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < maze.length && c < maze[0].length) {
                if (maze[r][c] == 0)
                    count += getPath(maze, r, c, dir);
            }
        }
        maze[sr][sc] = 0;
        return count;
    }

    // Knight Tour - Only one possible path.
    // https://www.geeksforgeeks.org/the-knights-tour-problem-backtracking-1/
    public static boolean knightTour(int[][] board, int sr, int sc, int move, int[] dirX, int[] dirY) {
        board[sr][sc] = move;
        if (move == 63)
            return true;

        boolean res = false;
        for (int d = 0; d < 8; d++) {
            int r = sr + dirX[d];
            int c = sc + dirY[d];

            if (r >= 0 && c >= 0 && r < board.length && c < board[0].length && board[r][c] == -1) {
                res = knightTour(board, r, c, move + 1, dirX, dirY);
                if (res)
                    return true;
            }
        }
        board[sr][sc] = -1;
        return res;
    }

    public static void knightTour() {
        int[][] board = new int[8][8];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++)
                board[i][j] = -1;
        }

        int[] xMove = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int[] yMove = { 1, 2, 2, 1, -1, -2, -2, -1 };

        // int[][] dir = { { 2, 1 }, { 1, 2 }, { -1, 2 }, { -2, 1 }, { -2, -1 }, { -1,
        // -2 }, { 1, -2 }, { 2, -1 } };

        System.out.println(knightTour(board, 0, 0, 0, xMove, yMove));

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++)
                System.out.print(board[i][j] + "\t");
            System.out.println();
        }
    }

    // Knights Tour - Here, we've to print all possible paths.
    public static void printKnightsTour(int[][] chess, int n, int sr, int sc, int upcomingMove, int[][] dir) {
        if (upcomingMove == n * n) {
            chess[sr][sc] = upcomingMove;
            displayBoard(chess);
            chess[sr][sc] = 0;
            return;
        }

        chess[sr][sc] = upcomingMove;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < chess.length && c < chess[0].length && chess[r][c] == 0) {
                printKnightsTour(chess, n, r, c, upcomingMove + 1, dir);
            }
        }
        chess[sr][sc] = 0;
    }

    public static void displayBoard(int[][] chess) {
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[0].length; j++) {
                System.out.print(chess[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    public static void printKnightsTour() {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int r = scn.nextInt();
        int c = scn.nextInt();

        int[][] chess = new int[n][n];
        int[][] dir = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 } };
        printKnightsTour(chess, n, r, c, 1, dir);
    }

    public static void main(String[] args) {
        int[][] dir3 = { {0, 1}, {1, 1}, {1, 0} };
        String[] dir3S = { "H", "D", "V"};

        int[][] dir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
        String[] dirS = { "t", "l", "d", "r" };

        int[][] board = { { 0, 0, 0 }, { 0, 0, 1 }, { 0, 0, 0 } };

        int[][] dir8 = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, -1 }, { 1, -1 }, { 1, 1 }, { -1, 1 } };
        String[] dir8S = { "u", "r", "d", "l", "n", "w", "s", "e" };

        System.out.println(floodFill_jumps(0, 0, board, "", dir, dirS));
        // knightTour();
        // printKnightsTour();
    }
}
