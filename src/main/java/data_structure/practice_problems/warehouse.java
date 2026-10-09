package data_structure.practice_problems;

public class warehouse {

    public static int[] warehouseSummary(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            throw new IllegalArgumentException("Grid must not be empty.");
        }

        int totalItems = 0;
        int maxValue = -1;
        int maxRow = 0;
        int maxCol = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                int value = grid[row][col];
                totalItems += value;

                if (value > maxValue) {
                    maxValue = value;
                    maxRow = row;
                    maxCol = col;
                }
            }
        }

        return new int[] {totalItems, maxRow, maxCol};
    }

    public static void main(String[] args) {
        int[][] grid = {
            {4, 9, 2},
            {7, 1, 6},
            {3, 12, 5}
        };

        int[] result = warehouseSummary(grid);

        System.out.println("(" + result[0] + ", ("
                + result[1] + ", " + result[2] + "))");
    }
}