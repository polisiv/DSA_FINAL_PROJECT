package core.controller;

import core.model.NoteModel;
import core.modelservice.NoteService;
import core.modelservice.Sorter;
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
            updateNoteLists();
            mainFrame.setNotes(displayedNotes);
        });


        mainFrame.setOnNewNote(() -> {
            NoteModel newNote = new NoteModel("N/A", "Untitled Note");
//            notesMap.put(newNote.getTitle(), newNote);
//            displayedNotes.add(0, newNote);
            noteService.saveNote(newNote);
            updateNoteLists();
            mainFrame.setNotes(displayedNotes);
            mainFrame.showNoteDetail(newNote);
        });

        mainFrame.setOnSearch(query -> {
            if (query == null || query.isBlank()) {
                displayedNotes = new ArrayList<>(notesMap.values());
                //updateNoteLists();
                mainFrame.setNotes(displayedNotes);
                return;
            }
            displayedNotes = noteService.searchNotes(query);
            mainFrame.setNotes(displayedNotes);
        });
       
        mainFrame.setOnDeleteNote(note -> {
//            displayedNotes.remove(note);
//            notesMap.remove(note.getTitle());
            noteService.deleteNote(note);
            updateNoteLists();
            mainFrame.setNotes(displayedNotes);
        });

        mainFrame.setOnNoteSelected(index -> {
            NoteModel selectedNote = displayedNotes.get(index);
            mainFrame.showNoteDetail(selectedNote);
        });

        mainFrame.setOnFilterSelected(filterType -> {
            switch (filterType) {
                case ALPHABETICAL_ASCENDING -> displayedNotes = Sorter.sort(displayedNotes, "Alphabetically Ascending");
                case ALPHABETICAL_DESCENDING -> displayedNotes = Sorter.sort(displayedNotes, "Alphabetically Descending");
                case NEWEST_FIRST -> displayedNotes = Sorter.sort(displayedNotes, "Date Newest");
                case OLDEST_FIRST -> displayedNotes = Sorter.sort(displayedNotes, "Date Oldest");
            }
            mainFrame.setNotes(displayedNotes); // Refresh view
        });

    }

    private void updateNoteLists() {
        notesMap = noteService.getAllNotes();
        displayedNotes = new ArrayList<>(notesMap.values());
    }
}
