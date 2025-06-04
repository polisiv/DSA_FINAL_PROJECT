package core.modelservice;

import core.datalayer.NoteDatabase;
import core.model.NoteModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class NoteService {
    private final Trie searchIndex;
    private NoteDatabase db;
    public NoteService(NoteDatabase db){
        this.db = db;
        this.searchIndex = new Trie();
        indexNotes();
    }
    public Map<String, NoteModel> getAllNotes(){
        return db.getAllNotes();
    }

    public void indexNotes(){
        for(NoteModel note : getAllNotes().values()){
            indexNote(note);
        }
    }

    public void saveNote(NoteModel newNote){ 
        // avoids having multiple notes if saved multiple times
        String cleanedContent = reformat(newNote.getContent());
        newNote.setContent(cleanedContent);
        db.deleteNote(newNote.getNoteId()); 
        db.saveNote(newNote.getNoteId(), newNote.getTitle(), newNote.getContent());
        indexNote(newNote);
    }

    public void deleteNote(NoteModel model){
        if(model == null){return;}
        removeNoteFromTrie(model);
        db.deleteNote(model.getNoteId());

    }

    private void removeNoteFromTrie(NoteModel note) {
        if(note == null){return;}

        // Remove title words
        for(String word: note.getTitle().split("\\s+")){
            if(!word.isEmpty()){
                searchIndex.delete(word,note);
            }
        }

        // Remove content words
        for(String word: note.getContent().split("\\s+")){
            if(!word.isEmpty()){
                searchIndex.delete(word,note);
            }
        }
    }

    public void indexNote(NoteModel note) {
        // Index title words
        for (String word : note.getTitle().split("\\s+")) {
            if (!word.isEmpty()) {
                searchIndex.insert(word, note);
            }
        }

        // Index content words
        for (String word : note.getContent().split("\\s+")) {
            if (!word.isEmpty()) {
                searchIndex.insert(word, note);
            }
        }
    }

    public List<NoteModel> searchNotes(String query) {
        return query == null || query.trim().isEmpty()
                ? new ArrayList<>(getAllNotes().values())
                : searchIndex.searchNotes(query);
    }

    public String reformat(String content) {
        if (content == null || content.isBlank()) {
        return "";
        }

        Queue<Character> queue = new LinkedList<>();
        boolean inWord = false;

        for (char c : content.toCharArray()) {
            if (c == ' ') {
                if (inWord) {
                    queue.add(' ');
                    inWord = false;
                }
            } else {
                queue.add(c);
                inWord = true;
            }
        }

    // Loại bỏ khoảng trắng cuối nếu có
        while (!queue.isEmpty() && queue.peek() == ' ') {
            queue.poll(); // remove leading space
        }

    // Build result string
        StringBuilder sb = new StringBuilder();
        boolean firstChar = true;

        while (!queue.isEmpty()) {
            char c = queue.poll();

            if (firstChar && Character.isLetter(c)) {
                if (Character.isLowerCase(c)) {
                    c = (char)(c - ('a' - 'A')); // thủ công viết hoa
                }
                firstChar = false;
            }

            sb.append(c);
        }

    // Xoá khoảng trắng cuối
        int len = sb.length();
        if (len > 0 && sb.charAt(len - 1) == ' ') {
            sb.deleteCharAt(len - 1);
        }

        return sb.toString();
    }
}
