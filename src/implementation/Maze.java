package implementation;

public class Maze {
    private final Cell[][] grid;
    private final int width;
    private final int height;

    public Maze(int height, int width) {
        this.width = width;
        this.height = height;
        grid = new Cell[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = new Cell(row, col, Cell.Type.WALL);
            }
        }
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public Cell getStartCell() {
        return grid[0][0];
    }

    public Cell getEndCell() {
        return grid[height - 1][width - 1];
    }
}
