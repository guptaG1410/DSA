public class l003_NQueen {

    public static boolean isSafeToPlace(boolean[][] box, int sr, int sc) {
        int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };
        // In permutation, we've to use all 8 directions.
        // int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
        // In combination, we only need these 4 directions of a queen instead of 8
        // because there is
        // no queen in the lower half to kill the current queen.
        int n = box.length, m = box[0].length;
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= n; rad++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];

                if (r >= 0 && c >= 0 && r < n && c < m) {
                    if (box[r][c])
                        return false;
                } else
                    break;
            }
        }
        return true;
    }

    // Combination of placing N-Queens in a box.
    public static int nQueen_01(boolean[][] box, int bno, int tnq, String asf) {
        if (tnq == 0) {
            System.out.println(asf);
            return 1;
        }
        int n = box.length, m = box[0].length;
        int count = 0;
        for (int i = bno; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (isSafeToPlace(box, r, c)) {
                box[r][c] = true;
                count += nQueen_01(box, i + 1, tnq - 1, asf + "(" + r + ", " + c + ") ");
                box[r][c] = false;
            }
        }
        return count;
    }

    // Permutation of placing N-Queens in a box.
    public static int nQueen_02(boolean[][] box, int tnq, String asf) {
        if(tnq == 0) {
            System.out.println(asf);
            return 1;
        }
        int n = box.length, m = box[0].length;
        int count = 0;
        for(int i = 0; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if(isSafeToPlace(box, r, c) && !box[r][c]) {
                box[r][c] = true;
                count += nQueen_02(box, tnq - 1, asf + "(" + r + ", " + c + ") ");
                box[r][c] = false;
            }
        }
        return count;
    }

    // Branch and Bound Method to solve N-Queen.
    static boolean[] row, col, diag, adiag;
    
    // Combination of placing n Queen in a box : [Optimized]
    public static int nQueen_03(int n, int m, int bno, int tnq, String asf) {
        if(tnq == 0) {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        for(int i = bno; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if(!row[r] && !col[c] && !diag[r + c] && !adiag[r - c + m - 1]) {
                row[r] = col[c] = diag[r + c] = adiag[r - c + m - 1] = true;
                count += nQueen_03(n, m, i + 1, tnq - 1, asf + "(" + r + ", " + c + ") ");
                row[r] = col[c] = diag[r + c] = adiag[r - c + m - 1] = false;
            }
        }
        return count;
    }

    // If we want only first traced path in N-Queen.
    public static boolean nQueen_03_1(int n, int m, int bno, int tnq, String asf) {
        if(tnq == 0) {
            System.out.println(asf);
            return true;
        }
        boolean res = false;
        for(int i = bno; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if(!row[r] && !col[c] && !diag[r + c] && !adiag[r - c + m - 1]) {
                row[r] = col[c] = diag[r + c] = adiag[r - c + m - 1] = true;
                res = res || nQueen_03_1(n, m, i + 1, tnq - 1, asf + "(" + r + ", " + c + ") ");
                if(res)
                    return true;
                row[r] = col[c] = diag[r + c] = adiag[r - c + m - 1] = false;
            }
        }
        return res;
    }

    // Permutation of placing n Queen in a box : [Optimized]
    public static int nQueen_04(int n, int m, int tnq, String asf) {
        if(tnq == 0) {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        for(int i = 0; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if(!row[r] && !col[c] && !diag[r + c] && !adiag[r - c + m - 1]) {
                row[r] = col[c] = diag[r + c] = adiag[r - c + m - 1] = true;
                count += nQueen_04(n, m, tnq - 1, asf + "(" + r + ", " + c + ") ");
                row[r] = col[c] = diag[r + c] = adiag[r - c + m - 1] = false;
            }
        }
        return count;
    }

    // Combination-optimized : [In this code, we place only one Queen in one row(floor) so that we don't need to do useless checks for the entire row(floor) ].
    public static int nQueen_05(int n, int m, int floor, int tnq, String asf) {
        if(tnq == 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        for(int room = 0; room < m; room++) {
            //Here, we traverse along the column-wise cauz we have to fix a row by placing one Queen on each row(floor).
            int r = floor, c = room;
            if(!col[c] && !diag[r + c] && !adiag[r - c + m - 1]) {
                col[c] = diag[r + c] = adiag[r - c + m - 1] = true;
                count += nQueen_05(n, m, floor + 1, tnq - 1, asf + "(" + r + ", " + c + ") ");
                col[c] = diag[r + c] = adiag[r - c + m - 1] = false;
            }
        }
        return count;
    }

    // Permutation-optimized : [In this code, we place only one Queen in one row(floor) so that we don't need to do useless checks for the entire row(floor)].
    // In this optimized code, subsequence method would take place so that we can place each queen per row.
    public static int nQueen_06(int n, int m, int floor, int tnq, String asf) {
        if(tnq == 0 || floor >= n) {
            if(tnq == 0) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count = 0;
        for(int room = 0; room < m; room++) {
            // Here, we have to do check for row cauz to get all possible arrangements we continuously passed floor = 0 while placing the queen.
            int r = floor, c = room;
            if(!row[r] && !col[c] && !diag[r + c] && !adiag[r - c + m - 1]) {
                row[r] = col[c] = diag[r + c] = adiag[r - c + m - 1] = true;
                count += nQueen_06(n, m, 0, tnq - 1, asf + "(" + r + ", " + c + ") ");
                row[r] = col[c] = diag[r + c] = adiag[r - c + m - 1] = false;
            }
        }
        count += nQueen_06(n, m, floor + 1, tnq, asf);
        return count;
    }

    public static void main(String[] args) {
        int n = 4, tnq = 4;
        boolean[][] box = new boolean[n][n];
        row = new boolean[n];
        col = new boolean[n];
        diag = new boolean[n + n - 1];
        adiag = new boolean[n + n - 1];
        // System.out.println(nQueen_02(box, tnq, ""));
        System.out.println(nQueen_04(n, n, tnq, ""));
        System.out.println(nQueen_06(n, n, 0, tnq, ""));
    }
}
