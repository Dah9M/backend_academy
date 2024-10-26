package implementation;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DFSGenerator implements Generator {
    @Override
    public void generate(Maze maze) {
        Cell[][] grid = maze.getGrid();
        Stack<Cell> stack = new Stack<>();
        Cell start = grid[1][1];
        start.setVisited(true);
        start.setType(Cell.Type.PASSAGE);
        stack.push(start);

        while (!stack.isEmpty()) {
            Cell current = stack.peek();
            List<Cell> neighbors = current.getAllNeighbours(grid);
            Collections.shuffle(neighbors);

            boolean carved = false;
            for (Cell neighbor : neighbors) {
                if (!neighbor.isVisited()) {
                    neighbor.setVisited(true);
                    current.removeWallBetween(neighbor, grid);  // Передаем grid как аргумент
                    neighbor.setType(Cell.Type.PASSAGE);
                    stack.push(neighbor);
                    carved = true;
                    break;
                }
            }

            if (!carved) {
                stack.pop();
            }
        }
    }
}
