package core.modelservice;

import java.util.*;

public class BKTree {
    private static class Node {
        String word;
        Map<Integer, Node> children = new HashMap<>();

        Node(String word) {
            this.word = word;
        }
    }

    private final Node root;

    public BKTree(List<String> words) {
        if (words == null || words.isEmpty()) {
            root = null;
        } else {
            Iterator<String> it = words.iterator();
            root = new Node(it.next());
            while (it.hasNext()) {
                insert(it.next());
            }
        }
    }

    public void insert(String word) {
        Node current = root;
        while (true) {
            int dist = levenshtein(current.word, word);
            if (dist == 0) {
                return; // already exists
            }
            Node child = current.children.get(dist);
            if (child != null) {
                current = child;
            } else {
                current.children.put(dist, new Node(word));
                return;
            }
        }
    }

    public List<String> search(String word, int maxDistance) {
        List<String> result = new ArrayList<>();
        searchRecursive(root, word, maxDistance, result);
        return result;
    }

    private void searchRecursive(Node node, String word, int maxDistance, List<String> result) {
        if (node == null) return;

        int dist = levenshtein(node.word, word);
        if (dist <= maxDistance) {
            result.add(node.word);
        }

        int start = dist - maxDistance;
        int end = dist + maxDistance;

        for (int d = Math.max(start, 1); d <= end; d++) {
            Node child = node.children.get(d);
            if (child != null) {
                searchRecursive(child, word, maxDistance, result);
            }
        }
    }

    // Standard Levenshtein distance
    private int levenshtein(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                        dp[i - 1][j - 1], // substitution
                        Math.min(
                            dp[i - 1][j],   // deletion
                            dp[i][j - 1]    // insertion
                        )
                    );
                }
            }
        }

        return dp[a.length()][b.length()];
    }
}
