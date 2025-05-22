package core.modelservice;

import core.datalayer.NoteDatabase;
import core.model.NoteModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoteService {
    private List<Trie> notes = null;
    private NoteDatabase db;
    public NoteService(NoteDatabase db){
        this.db = db;
    }
    public Map<String, NoteModel> getAllNotes(){
        return db.getAllNotes();
    }


    public void loadNote(){
        HashMap<String,List<String>> query = db.loadNote();
    }

    public void saveNote(NoteModel newNote){ 
        // avoids having multiple notes if saved multiple times
        db.deleteNote(newNote.getNoteId()); 
        db.saveNote(newNote.getNoteId(), newNote.getTitle(), newNote.getContent()); 
    }

    public void deleteNote(NoteModel model){
        db.deleteNote(model.getNoteId());
    }


    public List<NoteModel> searchNote(){
        return null;
    }



}
