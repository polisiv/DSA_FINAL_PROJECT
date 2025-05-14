package core.controller;

import core.model.NoteModel;
//import core.view.NoteView;
import core.datalayer.NoteDatabase;
import core.modelservice.NoteService;
import core.view.frame.MainFrame;
import java.util.ArrayList;
import java.util.List;

public class NoteController {
    private final MainFrame mainFrame;
    private final NoteService noteService;

    private List<NoteModel> allNotes;
    private List<NoteModel> displayedNotes;

    public NoteController(MainFrame mainFrame, NoteService noteService) {
        this.mainFrame = mainFrame;
        this.noteService = noteService;
    }

    public void init() {
        // allNotes = noteService.getAllNotes();
        // displayedNotes = new ArrayList<>(allNotes);
        List<NoteModel> displayedNotes = List.of(
                new NoteModel("Buy milk and eggs", "Shopping List"),
                new NoteModel("Leg day workout at 6PM", "Workout Plan"),
                new NoteModel("Team sync: finalize roadmap", "Meeting Notes"),
                new NoteModel("Fruits: apples, bananas, oranges", "Groceries"),
                new NoteModel("Morning routine: read, run, journal", "Routine"));
        mainFrame.initWithNotes(displayedNotes);
    }

    // public void search(String query) {
    // if (query == null || query.isBlank()) {
    // displayedNotes = allNotes;
    // } else {
    // displayedNotes = allNotes.stream()
    // .filter(note -> note.getTitle().toLowerCase().contains(query.toLowerCase())
    // || note.getContent().toLowerCase().contains(query.toLowerCase()))
    // .toList();
    // }
    // mainFrame.setNotes(displayedNotes);
    // }
    // }
}