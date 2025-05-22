package core.modelservice;

import core.datalayer.NoteDatabase;
import core.model.NoteModel;

import java.util.*;

class Trie {
    private class TrieNode {
        private TrieNode[] children; // 32-125
        private boolean isEnd;
        private List<NoteModel> notes;

        public TrieNode() {
            children = new TrieNode[94];
            isEnd = false;
            notes = null;

        }

        public TrieNode[] getChildren() {
            return children;
        }

        public void setChildren(TrieNode[] children) {
            this.children = children;
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd(boolean end) {
            isEnd = end;
        }

        public void addNote(NoteModel note) {
            if (notes == null){
                notes = new ArrayList<>();
            }
            notes.add(note);
        }


    }

    private final TrieNode root;
    public Trie(){
        root = new TrieNode();
    }

    public void insert(String word, NoteModel note) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (c < ' ' || c > '~') { // Validate character range
                throw new IllegalArgumentException("Character out of supported range: " + c);
            }
            int index = c - ' ';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEnd = true;
        node.addNote(note);
    }

    public List<NoteModel> searchNotes(String query) {  // Changed from searchNote to searchNotes
        Set<NoteModel> results = new HashSet<>();
        if(query == null || query.isBlank()) {
            return new ArrayList<>(results);
        }

        for(String term : query.trim().split("\\s+")){
            if(!term.isEmpty()){
                searchByPrefix(root, term, 0, results);
            }
        }
        return new ArrayList<>(results);
    }

    private void searchByPrefix(TrieNode node, String prefix, int index, Set<NoteModel> results) {
        if(node == null) {
            return;
        }
        if(index == prefix.length()){
            collectAllNotes(node, results);
            return;
        }

        char c = prefix.charAt(index);
        if(c < ' ' || c > '~') { // Validate character range
            throw new IllegalArgumentException("Character out of supported range: " + c);
        }
        int charIndex = c - ' ';
        if(node.children[charIndex] != null) {
            searchByPrefix(node.children[charIndex], prefix, index + 1, results);
        }
    }

    private void collectAllNotes(TrieNode node, Set<NoteModel> results) {
        if (node == null) return;

        // Add all notes from this node
        if (node.isEnd && node.notes != null) {
            results.addAll(node.notes);
        }

        // Recursively check all children
        for (TrieNode child : node.children) {
            if (child != null) {
                collectAllNotes(child, results);
            }
        }
    }
}

