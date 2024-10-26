package implementation;

import java.util.List;
import java.util.Scanner;

public class MazeApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите ширину лабиринта: ");
        int width = scanner.nextInt();
        System.out.print("Введите высоту лабиринта: ");
        int height = scanner.nextInt();

        Maze maze = new Maze(height, width);
        Generator generator = selectGenerator(scanner);
        generator.generate(maze);

        Solver solver = selectSolver(scanner);
        List<Cell> path = solver.solve(maze);

        Renderer renderer = new ConsoleRenderer();
        System.out.println("Сгенерированный лабиринт:");
        System.out.println(renderer.render(maze));

        if (path.isEmpty()) {
            System.out.println("Путь не найден.");
        } else {
            System.out.println("Найденный путь:");
            System.out.println(renderer.render(maze, path));
        }
    }

    private static Generator selectGenerator(Scanner scanner) {
        System.out.println("1. Краскал\n2. DFS");
        int choice = scanner.nextInt();
        return choice == 1 ? new KruskalGenerator() : new DFSGenerator();
    }

    private static Solver selectSolver(Scanner scanner) {
        System.out.println("1. DFS\n2. Дейкстра");
        int choice = scanner.nextInt();
        return choice == 1 ? new DFSSolver() : new DijkstraSolver();
    }
}
