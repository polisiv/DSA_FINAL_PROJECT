package core.modelservice;

import core.datalayer.NoteDatabase;
import core.model.NoteModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoteService {
    private List<Trie> notes = null;
    private NoteDatabase db;
    private NoteModel note;
    public NoteService(NoteDatabase db){
        this.db = db;
        note = new NoteModel();
    }
    public Map<String, NoteModel> getAllNotes(){
        return db.getAllNotes();
    }


    public void loadNote(){
        HashMap<String,List<String>> query = db.loadNote();
    }

    public void saveNote(NoteModel newNote){
        db.addNote(newNote.getNoteId(),newNote.getTitle(),newNote.getContent(), newNote.isDeleted());
    }

    public void deleteNote(){

    }


    public List<NoteModel> searchNote(){
        return null;
    }



}
