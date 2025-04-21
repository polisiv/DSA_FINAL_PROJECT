package core.modelservice;

public class Trie {
    private class TrieNode{
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    private TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        word = word.toLowerCase();
        for(char c : word.toCharArray()){
            int index = c - 'a';
            if(node.children[index] == null){
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode node = root;
        word = word.toLowerCase();
        for(char c : word.toCharArray()){
            int index = c - 'a';
            if(node.children[index] == null){
                return false;
            }
            node = node.children[index];
        }
        return node.isEnd;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for(char c : prefix.toCharArray()){
            int index = c - 'a';
            if(node.children[index] == null){
                return false;
            }
            node = node.children[index];
        }
        return true;
    }
}
