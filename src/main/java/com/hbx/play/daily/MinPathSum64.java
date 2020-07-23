package com.hbx.play.daily;

import java.util.Map;

public class MinPathSum64 {

    public static void main(String[] args) {
        int[][] arr = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1},
                {1, 2, 5},
        };

        int[][] arrRes = {
                {1, 4, 5},
                {2, 7, 6},
                {6, 8, 7},
                {7, 9, 12},
        };

        int[][] arrRes22 = {
                {1, 4, 5, 4},
                {2, 7, 6, 5},
                {6, 8, 7, 66},
        };
        System.out.println(minPathSum(arr));
        System.out.println(minPathSum2(arr));
        System.out.println(minPathSum3(arr));
    }

    public static int minPathSum(int[][] grid) {
//        if (grid.length == 0 || grid[0].length == 0) {
//            return 0;
//        }
        int row = grid.length, col = grid[0].length;

        int res[][] = new int[row][col];
        res[0][0] = grid[0][0];

        for (int i = 1; i < row; i++) {
            res[i][0] = res[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < col; j++) {
            res[0][j] += res[0][j - 1] + grid[0][j];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                res[i][j] = Math.min(res[i - 1][j] + grid[i][j], res[i][j - 1] + grid[i][j]);
            }
        }
        return res[row - 1][col - 1];
    }

    public static int minPathSum2(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int min = Math.min(grid.length, grid[0].length);
        int max = Math.max(grid.length, grid[0].length);
        boolean rowIsSmall = grid.length < grid[0].length ? true : false;

        int res[] = new int[min];
        res[0] = grid[0][0];

        for (int i = 1; i < min; i++) {
            res[i] = res[i - 1] + (rowIsSmall ? grid[i][0] : grid[0][i]);
        }
        for (int i = 1; i < max; i++) {
            res[0] += (rowIsSmall ? grid[0][i] : grid[i][0]);
            for (int j = 1; j < min; j++) {
                int temp = rowIsSmall ? grid[j][i] : grid[i][j];
                res[j] = Math.min(res[j - 1] + temp, res[j] + temp);
            }
        }
        return res[min - 1];
    }

    public static int minPathSum3(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length, col = grid[0].length;
        int res[] = new int[col];
        res[0] = grid[0][0];

        for (int i = 1; i < col; i++) {
            res[i] = res[i - 1] + grid[0][i];
        }

        for (int i = 1; i < row; i++) {
            res[0] += grid[i][0];
            for (int j = 1; j < col; j++) {
                res[j] = Math.min(res[j - 1] + grid[i][j], res[j] + +grid[i][j]);
            }
        }
        return res[col - 1];
    }
}
