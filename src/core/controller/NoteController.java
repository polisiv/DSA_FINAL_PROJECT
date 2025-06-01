package core.controller;

import core.model.AbbreviationModel;
import core.model.NoteModel;
import core.modelservice.NoteService;
import core.modelservice.Sorter;
import core.view.frame.MainFrame;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.undo.UndoManager;

public class NoteController {
    private final MainFrame mainFrame;
    private final NoteService noteService;

    private Map<String, NoteModel> notesMap;
    private List<NoteModel> displayedNotes;

    private final AbbreviationModel abbreviationModel;
    private final UndoManager undoManager;
    private boolean isSmartAbbreviationEnabled;

    public NoteController(MainFrame mainFrame, NoteService noteService, AbbreviationModel abbreviationModel, UndoManager undoManager) {
        this.mainFrame = mainFrame;
        this.noteService = noteService;
        this.abbreviationModel = abbreviationModel;
        this.undoManager = undoManager;
        this.isSmartAbbreviationEnabled = true;
    }

    public void init() {
        notesMap = noteService.getAllNotes();
        displayedNotes = new ArrayList<>(notesMap.values());
        mainFrame.initWithNotes(displayedNotes);
        bindUIEvents();
        addToggleKeybinding();
        mainFrame.getBody().note.content.getDocument().addUndoableEditListener(undoManager);
    }

    private void bindUIEvents() {
        mainFrame.setOnSave(() -> {
            NoteModel note = mainFrame.getCurrentNote();
            noteService.saveNote(note);
            // updateNoteLists();
            // mainFrame.setNotes(displayedNotes);
        });


        mainFrame.setOnNewNote(() -> {
            NoteModel newNote = new NoteModel("N/A", "Untitled Note");
            notesMap.put(newNote.getNoteId(), newNote);
            displayedNotes.add(0, newNote);
            noteService.saveNote(newNote);
            // updateNoteLists();
            mainFrame.setNotes(displayedNotes);
            mainFrame.showNoteDetail(newNote);
            addAutoCompleteKeybinding();
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
            noteService.deleteNote(note);
            displayedNotes.remove(note);
            notesMap.remove(note.getNoteId());
            //updateNoteLists();
            mainFrame.setNotes(displayedNotes);
        });

        mainFrame.setOnNoteSelected(index -> {
            NoteModel selectedNote = displayedNotes.get(index);
            mainFrame.showNoteDetail(selectedNote);
            addAutoCompleteKeybinding();
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

    // private void updateNoteLists() {
    //     notesMap = noteService.getAllNotes();
    //     displayedNotes = new ArrayList<>(notesMap.values());
    // }

    private void addAutoCompleteKeybinding(){
        addSpaceKeybinding();
        addEnterKeybinding();
        addUndoKeybinding();
    }

    private void addToggleKeybinding() {
        boolean isMac = System.getProperty("os.name").toLowerCase().contains("mac");

        KeyStroke keyStroke = isMac
        ? KeyStroke.getKeyStroke("meta shift H")  
        : KeyStroke.getKeyStroke("control H"); 

        InputMap inputMap = mainFrame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = mainFrame.getRootPane().getActionMap();

        inputMap.put(keyStroke, "toggleSuggestions");
        actionMap.put("toggleSuggestions", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isSmartAbbreviationEnabled = !isSmartAbbreviationEnabled;
                System.out.println("Smart Suggestions: " + (isSmartAbbreviationEnabled ? "ON" : "OFF"));
            }
        });
    }

    private void addSpaceKeybinding() {
        JTextArea textArea = mainFrame.getBody().note.content;
        KeyStroke spaceKey = KeyStroke.getKeyStroke("SPACE");

        InputMap inputMap = textArea.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap actionMap = textArea.getActionMap();

        inputMap.put(spaceKey, "customSpace");
        actionMap.put("customSpace", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Space key pressed");
                if (isSmartAbbreviationEnabled) {
                    triggerAutocomplete();
                } else {
                    insertSpace();
                }
            }
        });
    }
    private void addEnterKeybinding() {
        JTextArea textArea = mainFrame.getBody().note.content;
        KeyStroke enterKey = KeyStroke.getKeyStroke("ENTER");

        InputMap inputMap = textArea.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap actionMap = textArea.getActionMap();

        inputMap.put(enterKey, "customEnter");
        actionMap.put("customEnter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Enter key pressed");
                if (isSmartAbbreviationEnabled) {
                    triggerAutocomplete(); // expand if needed
                }
                insertNewline(); // always insert newline after
            }
        });
    }

private void insertNewline() {
    try {
        JTextArea textArea = mainFrame.getBody().note.content;
        textArea.getDocument().insertString(textArea.getCaretPosition(), "\n", null);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}


    private void triggerAutocomplete() {
        JTextArea textArea = mainFrame.getBody().note.content;
        int caretPos = textArea.getCaretPosition();
        int currentLine;
        try {
            currentLine = textArea.getLineOfOffset(caretPos);
            int lineStartOffset = textArea.getLineStartOffset(currentLine);
            int lineEndOffset = caretPos;

            String lineTextBeforeCaret = textArea.getText(lineStartOffset, lineEndOffset - lineStartOffset);

            int wordStartInLine = lineTextBeforeCaret.lastIndexOf(' ');
            wordStartInLine = (wordStartInLine == -1) ? 0 : wordStartInLine + 1;

            String currentWord = lineTextBeforeCaret.substring(wordStartInLine).trim();

            // System.out.println("Current word (safe): " + currentWord);

            if (abbreviationModel.isAbbreviation(currentWord)) {
                String expansion = abbreviationModel.getExpansion(currentWord);

                // Replace current word in current line only
                textArea.getDocument().remove(lineStartOffset + wordStartInLine, lineEndOffset - (lineStartOffset + wordStartInLine));
                textArea.getDocument().insertString(lineStartOffset + wordStartInLine, expansion, null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void insertSpace() {
        try {
            JTextArea textArea = mainFrame.getBody().note.content;
            textArea.getDocument().insertString(textArea.getCaretPosition(), " ", null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addUndoKeybinding() {
        JTextArea textArea = mainFrame.getBody().note.content;
        KeyStroke undoKey = KeyStroke.getKeyStroke("control Z");

        InputMap inputMap = textArea.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap actionMap = textArea.getActionMap();

        inputMap.put(undoKey, "Undo");
        actionMap.put("Undo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (undoManager.canUndo()) {
                    undoManager.undo();
                }
            }
        });
    }
}
