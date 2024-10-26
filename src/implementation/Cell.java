package implementation;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    public enum Type { WALL, PASSAGE }

    private final int row;
    private final int col;
    private Type type;
    private boolean visited;
    private int distance = -1;
    private Cell parent;

    public Cell(int row, int col, Type type) {
        this.row = row;
        this.col = col;
        this.type = type;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }
    public boolean isVisited() { return visited; }
    public void setVisited(boolean visited) { this.visited = visited; }
    public int getDistance() { return distance; }
    public void setDistance(int distance) { this.distance = distance; }
    public Cell getParent() { return parent; }
    public void setParent(Cell parent) { this.parent = parent; }

    public List<Cell> getAllNeighbours(Cell[][] grid) {
        List<Cell> neighbors = new ArrayList<>();
        int height = grid.length;
        int width = grid[0].length;

        if (row > 1 && grid[row - 2][col].getType() == Type.WALL) neighbors.add(grid[row - 2][col]);
        if (row < height - 2 && grid[row + 2][col].getType() == Type.WALL) neighbors.add(grid[row + 2][col]);
        if (col > 1 && grid[row][col - 2].getType() == Type.WALL) neighbors.add(grid[row][col - 2]);
        if (col < width - 2 && grid[row][col + 2].getType() == Type.WALL) neighbors.add(grid[row][col + 2]);

        return neighbors;
    }

    public void removeWallBetween(Cell neighbor, Cell[][] grid) {
        int midRow = (row + neighbor.getRow()) / 2;
        int midCol = (col + neighbor.getCol()) / 2;
        grid[midRow][midCol].setType(Type.PASSAGE);
    }
}
