package implementation;

import java.util.*;

public class DijkstraSolver implements Solver {
    @Override
    public List<Cell> solve(Maze maze) {
        Cell start = maze.getStartCell();
        Cell end = maze.getEndCell();
        PriorityQueue<Cell> queue = new PriorityQueue<>(Comparator.comparingInt(Cell::getDistance));
        start.setDistance(0);
        queue.add(start);

        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            if (current == end) {
                List<Cell> path = new ArrayList<>();
                while (current != null) {
                    path.add(0, current);
                    current = current.getParent();
                }
                return path;
            }

            for (Cell neighbor : current.getAllNeighbours(maze.getGrid())) {
                int newDist = current.getDistance() + 1;
                if (neighbor.getType() == Cell.Type.PASSAGE && (neighbor.getDistance() == -1 || newDist < neighbor.getDistance())) {
                    neighbor.setDistance(newDist);
                    neighbor.setParent(current);
                    queue.add(neighbor);
                }
            }
        }
        return Collections.emptyList();
    }
}
