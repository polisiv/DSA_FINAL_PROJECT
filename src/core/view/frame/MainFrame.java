package core.view.frame;

import core.model.NoteModel;
import core.modelservice.NoteFilterType;
import core.view.component.body.Body;
import core.view.component.common.FilterPopupMenu;
import core.view.uiconfig.Config;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MainFrame extends javax.swing.JFrame {

    private Runnable onNewNote;
    private Runnable onSave;
    private Consumer<String> onSearch;
    private Consumer<Integer> onNoteSelected;
    private Consumer<NoteFilterType> onFilterSelected;

    private List<NoteModel> displayedNotes;

    public void setOnSave(Runnable callback) {
        this.onSave = callback;
    }

    public void setOnNewNote(Runnable callback) {
        this.onNewNote = callback;
    }

    public void setOnSearch(Consumer<String> callback) {
        this.onSearch = callback;
    }

    public void setOnNoteSelected(Consumer<Integer> callback) {
        this.onNoteSelected = callback;
    }

    public void setOnFilterSelected(Consumer<NoteFilterType> callback) {
        this.onFilterSelected = callback;
    }

    public void setOnDeleteNote(Consumer<NoteModel> callback) {
        body.search.noteList.setOnDeleteNote(callback);
    }

    public MainFrame() {
        Config.setBlueTheme();
        setUndecorated(true);
        initComponents();
        setBackground(Config.TRANSPARENT_BLACK);
        getContentPane().setBackground(Config.TRANSPARENT_BLACK);

        // Window controls & drag
        top.initWindowControlPanel(MainFrame.this, mainPanel);
        top.initDrag(MainFrame.this);

        top.searchHeader.addEvent(this::handleSearchHeaderEvent);
        top.noteHeader.addEvent(this::handleNoteHeaderEvent);

        top.searchHeader.setOnBlueThemeSelected(() -> {
            Config.setBlueTheme();
            this.applyTheme();
        });
        top.searchHeader.setOnGreenThemeSelected(() -> {
            Config.setGreenTheme();
            this.applyTheme();
        });

        top.searchHeader.textField.getDocument().addDocumentListener(createSearchListener());

        body.search.noteList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                handleNoteListClick(e);
            }
        });
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screenSize.width - getWidth();
        int y = 0;
        setLocation(x, y);
    }

    public void initWithNotes(List<NoteModel> notes) {
        this.displayedNotes = notes;
        setNotes(notes);
        showSearchView();
    }

    private void showSearchView() {
        top.showSearchHeader();
        setNotes(displayedNotes);
        body.showSearchPanel();
    }

    public void showNoteDetail(NoteModel note) {
        top.showNoteHeader();
        body.showNotePanel(note);
    }

    public void setNotes(List<NoteModel> notes) {
        this.displayedNotes = notes;
        body.search.noteList.displayNotes(notes);
    }

    public NoteModel getCurrentNote() {
        return body.note.getNote();
    }

    public void applyTheme() {
        mainPanel.setBackground(Config.DARKEST);
        top.applyTheme();
        body.applyTheme();
        revalidate();
        repaint();
    }

    public Body getBody() {
        return body;
    }

    private void handleSearchHeaderEvent(int index) {
        switch (index) {
            case 1 -> {
                if (onNewNote != null) onNewNote.run();
            }
            case 2 -> System.out.println("Search"); // Placeholder
            case 3 -> {
                FilterPopupMenu popup = new FilterPopupMenu(filterType -> {
                    if (onFilterSelected != null) {
                        onFilterSelected.accept(filterType);
                    }
                });
                popup.show(top.searchHeader.filterButton, 0, top.searchHeader.filterButton.getHeight());
            }
            case 4 -> { // for theme selection, but i put it in the search header anyway lol
            }
        }
    }

    private void handleNoteHeaderEvent(int index) {
        switch (index) {
            case 1 -> {
                if (onNewNote != null) onNewNote.run();
            }
            case 6 -> {
                if (onSave != null) onSave.run();
            }
            case 7 -> showSearchView();
        }
    }

    private DocumentListener createSearchListener() {
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { emitSearch(); }
            @Override
            public void removeUpdate(DocumentEvent e) { emitSearch(); }
            @Override
            public void changedUpdate(DocumentEvent e) { emitSearch(); }
        };
    }

    private void emitSearch() {
        if (onSearch != null) {
            onSearch.accept(top.searchHeader.textField.getText());
        }
    }

    private void handleNoteListClick(java.awt.event.MouseEvent e) {
        int index = body.search.noteList.locationToIndex(e.getPoint());
        if (index >= 0 && onNoteSelected != null) {
            onNoteSelected.accept(index);
        }
    }

    // === Auto-generated layout ===
    @SuppressWarnings("unchecked")
    private void initComponents() {
        mainPanel = new core.view.component.common.RoundPanel();
        top = new core.view.component.header.Top();
        body = new core.view.component.body.Body();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        mainPanel.setBackground(Config.DARKEST);
        top.setPreferredSize(Config.TOP_PREFERRED_SIZE);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(top, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(body, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(top, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(body, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private core.view.component.body.Body body;
    private core.view.component.common.RoundPanel mainPanel;
    private core.view.component.header.Top top;
}
