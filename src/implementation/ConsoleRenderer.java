package implementation;

import java.util.List;

public class ConsoleRenderer implements Renderer {
    @Override
    public String render(Maze maze) {
        StringBuilder sb = new StringBuilder();
        Cell[][] grid = maze.getGrid();
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                sb.append(cell.getType() == Cell.Type.WALL ? "#" : ".");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public String render(Maze maze, List<Cell> path) {
        StringBuilder sb = new StringBuilder();
        Cell[][] grid = maze.getGrid();
        for (Cell cell : path) {
            grid[cell.getRow()][cell.getCol()].setType(Cell.Type.PASSAGE);
        }
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                sb.append(cell.getType() == Cell.Type.WALL ? "#" : "*");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
