package implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalGenerator implements Generator {
    private final DisjointSets disjointSets = new DisjointSets();

    @Override
    public void generate(Maze maze) {
        Cell[][] grid = maze.getGrid();
        List<Cell> walls = new ArrayList<>();

        // Initialize sets for each cell and add walls
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                Cell cell = grid[row][col];
                if (cell.getType() == Cell.Type.PASSAGE) continue;
                disjointSets.create_set(row * grid[0].length + col);
                walls.add(cell);
            }
        }

        Collections.shuffle(walls);

        for (Cell wall : walls) {
            List<Cell> neighbors = wall.getAllNeighbours(grid);
            for (Cell neighbor : neighbors) {
                if (disjointSets.find_set(wall.getRow() * grid[0].length + wall.getCol()) !=
                    disjointSets.find_set(neighbor.getRow() * grid[0].length + neighbor.getCol())) {
                    wall.removeWallBetween(neighbor, grid);  // Передаем grid как аргумент
                    disjointSets.union(wall.getRow() * grid[0].length + wall.getCol(),
                        neighbor.getRow() * grid[0].length + neighbor.getCol());
                }
            }
        }
    }
}
