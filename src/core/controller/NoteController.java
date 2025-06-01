package core.controller;

import core.model.NoteModel;
import core.modelservice.AbbreviationModel;
import core.modelservice.BKTree;
import core.modelservice.DictionaryLoader;
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

    private BKTree normalWordsBkTree;

    public NoteController(MainFrame mainFrame, NoteService noteService, AbbreviationModel abbreviationModel, UndoManager undoManager) {
        this.mainFrame = mainFrame;
        this.noteService = noteService;
        this.abbreviationModel = abbreviationModel;
        this.undoManager = undoManager;
        this.isSmartAbbreviationEnabled = true;
    }

    public void init() {
        List<String> dictionaryWords = DictionaryLoader.loadDictionary("src/resource/database/dictionary.txt");
        normalWordsBkTree = new BKTree(dictionaryWords);

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
            } else {
                displayedNotes = noteService.searchNotes(query);
            }
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
            mainFrame.setNotes(displayedNotes);
        });
    }

    // private void updateNoteLists() {
    //     notesMap = noteService.getAllNotes();
    //     displayedNotes = new ArrayList<>(notesMap.values());
    // }

    private void addAutoCompleteKeybinding() {
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
        addKeyBinding(textArea, KeyStroke.getKeyStroke("SPACE"), "customSpace", () -> {
            System.out.println("Space key pressed");
            if (isSmartAbbreviationEnabled) {
                triggerAutocomplete();
            } else {
                insertSpace();
            }
        });
    }

    private void addEnterKeybinding() {
        JTextArea textArea = mainFrame.getBody().note.content;
        addKeyBinding(textArea, KeyStroke.getKeyStroke("ENTER"), "customEnter", () -> {
            System.out.println("Enter key pressed");
            if (isSmartAbbreviationEnabled) {
                triggerAutocomplete();
            }
            insertNewline();
        });
    }

    private void addUndoKeybinding() {
        JTextArea textArea = mainFrame.getBody().note.content;
        addKeyBinding(textArea, KeyStroke.getKeyStroke("control Z"), "Undo", () -> {
            if (undoManager.canUndo()) {
                undoManager.undo();
            }
        });
    }

    private void addKeyBinding(JComponent component, KeyStroke keyStroke, String actionName, Runnable actionLogic) {
        InputMap inputMap = component.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap actionMap = component.getActionMap();

        inputMap.put(keyStroke, actionName);
        actionMap.put(actionName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionLogic.run();
            }
        });
    }

    private void triggerAutocomplete() {
        try {
            JTextArea textArea = mainFrame.getBody().note.content;
            int caretPos = textArea.getCaretPosition();
            int currentLine = textArea.getLineOfOffset(caretPos);
            int lineStartOffset = textArea.getLineStartOffset(currentLine);
            int lineEndOffset = caretPos;

            String lineTextBeforeCaret = textArea.getText(lineStartOffset, lineEndOffset - lineStartOffset);
            int wordStartInLine = lineTextBeforeCaret.lastIndexOf(' ');
            wordStartInLine = (wordStartInLine == -1) ? 0 : wordStartInLine + 1;

            String currentWord = lineTextBeforeCaret.substring(wordStartInLine).trim();

            if (abbreviationModel.isAbbreviation(currentWord)) {
                String expansion = abbreviationModel.getExpansion(currentWord);
                replaceCurrentWord(textArea, currentLine, wordStartInLine, expansion);
                return;
            }

            if (normalWordsBkTree != null && !currentWord.isEmpty()) {
                List<String> corrections = normalWordsBkTree.search(currentWord, 1);
                boolean alreadyCorrect = corrections.stream().anyMatch(w -> w.equalsIgnoreCase(currentWord));

                if (!alreadyCorrect && !corrections.isEmpty()) {
                    String correction = corrections.get(0);
                    replaceCurrentWord(textArea, currentLine, wordStartInLine, correction);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void replaceCurrentWord(JTextArea textArea, int currentLine, int wordStartInLine, String replacement) {
        try {
            int lineStartOffset = textArea.getLineStartOffset(currentLine);
            int caretPos = textArea.getCaretPosition();
            int lineEndOffset = caretPos;

            textArea.getDocument().remove(lineStartOffset + wordStartInLine, lineEndOffset - (lineStartOffset + wordStartInLine));
            textArea.getDocument().insertString(lineStartOffset + wordStartInLine, replacement, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertTextAtCaret(String text) {
        try {
            JTextArea textArea = mainFrame.getBody().note.content;
            textArea.getDocument().insertString(textArea.getCaretPosition(), text, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void insertSpace() {
        insertTextAtCaret(" ");
    }

    private void insertNewline() {
        insertTextAtCaret("\n");
    }
}