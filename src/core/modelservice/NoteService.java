package core.modelservice;

import core.model.NoteModel;

import java.util.ArrayList;
import java.util.List;

public class NoteService {
    private List<Trie> notes = null;
    List<NoteModel> list = null;
    public List<NoteModel>  getAllNotes(){
        System.out.println("This will get all notes");
        return null;
    }


    public void loadNote(){
        System.out.println("See each note individually");
    }

    public void saveNote(NoteModel newNote){
//        if()

    }

    public void deleteNote(){

    }


    public List<NoteModel> searchNote(){
        return null;
    }



}
