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
        displayedNotes = new ArrayList<>(notesMap.values());

        mainFrame.initWithNotes(displayedNotes);

        bindUIEvents();
    }

    private void bindUIEvents() {
        mainFrame.setOnSave(() -> {
            NoteModel note = mainFrame.getCurrentNote();
            noteService.saveNote(note);
        });


        mainFrame.setOnNewNote(() -> {
            NoteModel newNote = new NoteModel("N/A", "Untitled Note");
            notesMap.put(newNote.getTitle(), newNote);
            displayedNotes.add(0, newNote);
            noteService.saveNote(newNote);
            mainFrame.setNotes(displayedNotes);
            mainFrame.showNoteDetail(newNote);
        });

        mainFrame.setOnSearch(query -> {
            // search logic
            if (query == null || query.isBlank()) {
                mainFrame.setNotes(displayedNotes);
                return;
            }

            List<NoteModel> filtered = displayedNotes.stream()
                .filter(note -> note.getTitle().toLowerCase().contains(query.toLowerCase())
                             || note.getContent().toLowerCase().contains(query.toLowerCase()))
                .toList();

            mainFrame.setNotes(filtered);
        });
       
        mainFrame.setOnDeleteNote(note -> {
            displayedNotes.remove(note);
            notesMap.remove(note.getTitle());
            noteService.deleteNote(note);
            mainFrame.setNotes(displayedNotes);
        });

        mainFrame.setOnNoteSelected(index -> {
            NoteModel selectedNote = displayedNotes.get(index);
            mainFrame.showNoteDetail(selectedNote);
        });
    }
}
