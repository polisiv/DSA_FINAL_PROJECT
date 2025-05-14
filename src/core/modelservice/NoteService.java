package core.modelservice;

import core.datalayer.NoteDatabase;
import core.model.NoteModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NoteService {
    private List<Trie> notes = null;
    private NoteDatabase db;
    private NoteModel note;
    public NoteService(){
        db = new NoteDatabase();
        note = new NoteModel();
    }
    public void  getAllNotes(){
        db.readNote();
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
