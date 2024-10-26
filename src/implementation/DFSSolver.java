package implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFSSolver implements Solver {
    @Override
    public List<Cell> solve(Maze maze) {
        Cell start = maze.getStartCell();
        Cell end = maze.getEndCell();
        Stack<Cell> stack = new Stack<>();
        List<Cell> path = new ArrayList<>();
        stack.push(start);
        start.setVisited(true);

        while (!stack.isEmpty()) {
            Cell current = stack.pop();
            if (current == end) {
                while (current != null) {
                    path.add(0, current);
                    current = current.getParent();
                }
                return path;
            }

            for (Cell neighbor : current.getAllNeighbours(maze.getGrid())) {
                if (neighbor.getType() == Cell.Type.PASSAGE && !neighbor.isVisited()) {
                    neighbor.setVisited(true);
                    neighbor.setParent(current);
                    stack.push(neighbor);
                }
            }
        }
        return path;
    }
}
