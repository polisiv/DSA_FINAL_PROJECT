package core.modelservice;

import core.datalayer.NoteDatabase;
import core.model.NoteModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoteService {
    private final Trie searchIndex;
    private NoteDatabase db;
    public NoteService(NoteDatabase db){
        this.db = db;
        this.searchIndex = new Trie();
    }
    public Map<String, NoteModel> getAllNotes(){
        Map<String, NoteModel> notes = db.getAllNotes();
        // Index all existing notes
        for (NoteModel note : notes.values()) {
            indexNote(note);
        }
        return notes;
    }


    public void loadNote(){
        HashMap<String,List<String>> query = db.loadNote();
    }

    public void saveNote(NoteModel newNote){ 
        // avoids having multiple notes if saved multiple times
        db.deleteNote(newNote.getNoteId()); 
        db.saveNote(newNote.getNoteId(), newNote.getTitle(), newNote.getContent());
        indexNote(newNote);
    }

    public void deleteNote(NoteModel model){
        db.deleteNote(model.getNoteId());
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
}
