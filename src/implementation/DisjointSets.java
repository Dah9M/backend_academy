package implementation;

import java.util.HashMap;
import java.util.Map;

public class DisjointSets {
    private final Map<Integer, Integer> parent = new HashMap<>();
    private final Map<Integer, Integer> rank = new HashMap<>();

    public void create_set(int x) {
        parent.put(x, x);
        rank.put(x, 0);
    }

    public int find_set(int x) {
        if (parent.get(x) != x) {
            parent.put(x, find_set(parent.get(x))); // Path compression
        }
        return parent.get(x);
    }

    public void union(int x, int y) {
        int rootX = find_set(x);
        int rootY = find_set(y);
        if (rootX != rootY) {
            if (rank.get(rootX) > rank.get(rootY)) {
                parent.put(rootY, rootX);
            } else if (rank.get(rootX) < rank.get(rootY)) {
                parent.put(rootX, rootY);
            } else {
                parent.put(rootY, rootX);
                rank.put(rootX, rank.get(rootX) + 1);
            }
        }
    }
}
