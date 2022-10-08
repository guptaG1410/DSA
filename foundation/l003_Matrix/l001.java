import java.util.*;

public class l001 {
    public static Scanner scn = new Scanner(System.in);

    public static void input(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++)
                arr[i][j] = scn.nextInt();
        }
    }

    public static void display(int[][] arr) {
        for (int[] innerArr : arr) {
            for (int ele : innerArr)
                System.out.print(ele + "\t");
            System.out.println();
        }
    }

    public static int maximum(int[][] arr) {
        int max = -(int) 1e9;
        for (int[] innerArr : arr) {
            for (int ele : innerArr)
                max = Math.max(ele, max);
        }
        return max;
    }

    public static int minimum(int[][] arr) {
        int min = (int) 1e9;
        for (int[] innerArr : arr) {
            for (int ele : innerArr)
                min = Math.min(ele, min);
        }
        return min;
    }

    public static boolean find(int[][] arr, int data) {
        boolean res = false;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                res = arr[i][j] == data;
                if (res)
                    return res;
            }
        }
        return res;
    }

    // The State Of Wakanda - 1
    public static void stateOfWakanda_1(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        for (int j = 0; j < m; j++) {
            if (j % 2 == 0) {
                for (int i = 0; i < n; i++)
                    System.out.println(arr[i][j]);
            } else {
                for (int i = n - 1; i >= 0; i--)
                    System.out.println(arr[i][j]);
            }
        }
    }

    // The State Of Wakanda - 2 or diagonal print (using GAP strategy)
    public static void stateOfWakanda_2(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        for (int gap = 0; gap < m; gap++) {
            for (int i = 0, j = gap; i < n && j < m; i++, j++) {
              System.out.println(arr[i][j]);
            }
       }
    }

    // follow up - full diagonal print 
    public static void fullDiagonal(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        for(int gap = n - 1; gap >= 1; gap--) {
            for(int i = gap, j = 0; i < n && j < m; i++, j++)
                System.out.println(arr[i][j]);
        }

        for(int gap = 0; gap < m; gap++) {
            for(int i = 0, j = gap; i < n && j < m; i++, j++)
                System.out.println(arr[i][j]);
        }
    }

    // Spiral Matrix
    public static void spiralMatrix(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        int rmin = 0, rmax = n - 1, cmin = 0, cmax = m - 1, tnele = n * m;
        while(tnele > 0) {
            for(int r = rmin; r <= rmax && tnele > 0; r++) {
                System.out.println(arr[r][cmin]);
                tnele--;
            }
            cmin++;
            for(int c = cmin; c <= cmax && tnele > 0; c++) {
                System.out.println(arr[rmax][c]);
                tnele--;
            }
            rmax--;
            for(int r = rmax; r >= rmin && tnele > 0; r--) {
                System.out.println(arr[r][cmax]);
                tnele--;
            }
            cmax--;
            for(int c = cmax; c >= cmin && tnele > 0; c--) {
                System.out.println(arr[rmin][c]);
                tnele--;
            }
            rmin++;
        }
    }

    // 59. Spiral Matrix II
    public int[][] generateMatrix(int n) {
        int rmin = 0, rmax = n - 1, cmin = 0, cmax = n - 1, tnele = 1;
        int[][] ans = new int[n][n];
        while(tnele <= n * n) {
            for(int c = cmin; c <= cmax && tnele <= n * n; c++) {
                ans[rmin][c] = tnele++;
            }
            rmin++;
            for(int r = rmin; r <= rmax && tnele <= n * n; r++) {
                ans[r][cmax] = tnele++;
            }
            cmax--;
            for(int c = cmax; c >= cmin && tnele <= n * n; c--) {
                ans[rmax][c] = tnele++;
            }
            rmax--;
            for(int r = rmax; r >= rmin && tnele <= n * n; r--) {
                ans[r][cmin] = tnele++;
            }
            cmin++;
        }
        return ans;           
    }

    // Exit Point in a Matrix
    public static void exitPoint(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        int i = 0, j = 0, dir = 0;
        while(true) {
            dir = (dir + arr[i][j]) % 4;
            if(dir == 0) {
                j++;
                if(j == m) {
                    System.out.println(i + "\n" + (j - 1));
                    break;
                } 
            } else if(dir == 1) {
                i++;
                if(i == n) {
                    System.out.println((i - 1) + "\n" + j);
                    break;
                }
            } else if(dir == 2) {
                j--;
                if(j == -1) {
                    System.out.println(i + "\n" + (j + 1));
                    break;
                }
            } else {
                i--;
                if(i == -1) {
                    System.out.println((i + 1) + "\n" + j);
                    break;
                }
            }
        }
    }

    // Rotate By 90 Degree
    public static void swap(int[][] arr, int i, int si, int j, int ei) {
        int temp = arr[i][si];
        arr[i][si] = arr[j][ei];
        arr[j][ei] = temp;
    }
    
    public static int[][] rotateBy90(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        for(int i = 0; i < n; i++) {
            for(int j = i; j < m; j++)
                swap(arr, i, j, j, i);
        }
        int si = 0, ei = m - 1;
        while(si <= ei) {
            for(int r = 0; r < n; r++) 
                swap(arr, r, si, r, ei);
            si++;
            ei--;
        }
        return arr;
    }

    // Saddle Point 
    public static int maxInCol(int[][] arr, int c) {
        int maxEle = -(int)1e9;
        int r = -1;
        for(int i = 0; i < arr.length; i++) {
            if(maxEle < arr[i][c]) {
                maxEle = arr[i][c];
                r = i;
            }
        }
        return r;
    }
    
    public static void saddlePoint(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        boolean flag = false;
        for(int i = 0; i < n; i++) {
            int minVal = (int)1e9;
            int c = -1;
            for(int j = 0; j < m; j++) {
                if(minVal > arr[i][j]) {
                    minVal = arr[i][j];
                    c = j;
                }
            }
            
            int r = maxInCol(arr, c);
            
            if(r == i) {
                System.out.println(arr[r][c]);
                flag = true;
            }
        }
        if(!flag) 
            System.out.println("Invalid input");
    }

    // Search In A Sorted 2d Array
    public static void searchIn2D(int[][] arr, int data) {
        int i = 0, j = arr[0].length - 1;
        while(i < arr.length && j >= 0) {
            if(data == arr[i][j]){
                System.out.println(i);
                System.out.println(j);
                return;
            } else if(data < arr[i][j])     j--;
            else if(data > arr[i][j])   i++;
        }
        System.out.println("Not Found");
    }

    public static void main(String[] args) {
        int[][] arr = new int[scn.nextInt()][scn.nextInt()];
        input(arr);
        display(arr);
        System.out.println(maximum(arr));
        System.out.println(minimum(arr));
        System.out.println(find(arr, 5));
    }
}
