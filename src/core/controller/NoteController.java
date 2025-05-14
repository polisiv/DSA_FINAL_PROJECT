package core.controller;

import core.model.NoteModel;
//import core.view.NoteView;
import core.modelservice.NoteService;
import core.view.frame.MainFrame;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NoteController {
    private final MainFrame mainFrame;
    private final NoteService noteService;

    private Map<String, NoteModel> notesMap;
    private List<NoteModel> displayedNotes;

    public NoteController(MainFrame mainFrame, NoteService noteService) {
        this.mainFrame = mainFrame;
        this.noteService = noteService;
    }

    public void init() {
        notesMap = noteService.getAllNotes();
        List<NoteModel> displayedNotes = new ArrayList<>(notesMap.values());
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