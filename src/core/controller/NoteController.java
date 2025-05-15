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
        // load notes
        notesMap = noteService.getAllNotes();
        displayedNotes = new ArrayList<>(notesMap.values());

        // inject notes into view
        mainFrame.initWithNotes(displayedNotes);

        // bind UI callbacks 
        bindUIEvents();
    }

    private void bindUIEvents() {
        mainFrame.setOnSave(() -> {
            //noteService.saveAllNotes(notesMap); // saving logic
        });

        mainFrame.setOnNewNote(() -> {
            // new note logic
            NoteModel newNote = new NoteModel("", "Untitled Note");
            notesMap.put(newNote.getTitle(), newNote);
            displayedNotes.add(0, newNote);
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

//        mainFrame.setOnDeleteNote(index -> {
//            if (index >= 0 && index < displayedNotes.size()) {
//                NoteModel note = displayedNotes.remove(index);
//                note.setDeleted(true);
//                notesMap.remove(note.getTitle());
//                mainFrame.setNotes(displayedNotes);
//            }
//        });

        mainFrame.setOnNoteSelected(index -> {
            NoteModel selectedNote = displayedNotes.get(index);
            mainFrame.showNoteDetail(selectedNote);
        });
    }
}
