package core.controller;

import core.model.NoteModel;
import core.view.NoteView;
import core.datalayer.NoteDatabase;

public class NoteController {
    private NoteModel model;
    private NoteView view;
    private NoteDatabase database;

    public NoteController(NoteModel model, NoteView view, NoteDatabase database) {
        this.database = database;
        this.model = model;
        this.view = view;

        // this.view.setAddNoteListener(e -> addNote());
        // this.view.setDeleteNoteListener(e -> deleteNote());
        // this.view.setUpdateNoteListener(e -> updateNote());
        // this.view.setDisplayNotesListener(e -> displayNotes());
    }

    // private void addNote(){
    // String note = view.getNote();
    // model.addNote(note);
    // view.displayNotes(model.getNotes());
    // }
    // private void deleteNote(){
    // String note = view.getNote();
    // model.deleteNote(note);
    // view.displayNotes(model.getNotes());
    // }
    // private void updateNote(){
    // String oldNote = view.getOldNote();
    // String newNote = view.getNewNote();
    // model.updateNote(oldNote, newNote);
    // view.displayNotes(model.getNotes());
    // }
    // private void displayNotes(){
    // view.displayNotes(model.getNotes());
    // }
    // create getter and setter methods for model variables -> control model object

    // control view object
}
