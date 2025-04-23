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
}
