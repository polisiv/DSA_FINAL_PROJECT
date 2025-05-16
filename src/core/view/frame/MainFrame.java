package core.view.frame;

import core.model.NoteModel;
import core.modelservice.NoteFilterType;
import core.view.component.common.FilterPopupMenu;
import core.view.component.common.HeaderEvent;
import core.view.uiconfig.Config;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MainFrame extends javax.swing.JFrame {
    private Runnable onNewNote;
    private Runnable onSave;
    private java.util.function.Consumer<Integer> onDeleteNote;
    private java.util.function.Consumer<String> onSearch;
    private java.util.function.Consumer<Integer> onNoteSelected;
    private java.util.function.Consumer<NoteFilterType> onFilterSelected;

    public void setOnSave(Runnable callback) {
        this.onSave = callback;
    }

    public void setOnNewNote(Runnable callback) {
        this.onNewNote = callback;
    }

    public void setOnSearch(Consumer<String> callback) {
        this.onSearch = callback;
    }

    public void setOnDeleteNote(Consumer<NoteModel> callback) {
        body.search.noteList.setOnDeleteNote(callback);
    }

    public void setOnNoteSelected(Consumer<Integer> callback) {
        this.onNoteSelected = callback;
    }
    
    public void setOnFilterSelected(Consumer<NoteFilterType> callback) {
        this.onFilterSelected = callback;
    }


    private List<NoteModel> displayedNotes;

    public MainFrame() {
        Config.setBlueTheme();
        setUndecorated(true);
        initComponents();
        setBackground(Config.TRANSPARENT_BLACK);
        getContentPane().setBackground(Config.TRANSPARENT_BLACK);


        top.initWindowControlPanel(MainFrame.this, mainPanel);
        top.initDrag(MainFrame.this);

        top.searchHeader.addEvent(new HeaderEvent() {
            @Override
            public void buttonSelected(int index) {
                switch (index) {
                    case 1 -> {
                        if (onNewNote != null)
                            onNewNote.run();
                    }
                    case 2 -> System.out.println("Search");
                    case 3 -> {
                        FilterPopupMenu popup = new FilterPopupMenu(filterType -> {
                            if (onFilterSelected != null) {
                                onFilterSelected.accept(filterType);
                            }
                        });
                        popup.show(top.searchHeader.filterButton, 0, top.searchHeader.filterButton.getHeight());
                    }
                    case 4 -> {}
                }
            }
        });
        top.searchHeader.setOnBlueThemeSelected(() -> {
            Config.setBlueTheme();
            this.applyTheme();
        });
        top.searchHeader.setOnGreenThemeSelected(() -> {
            Config.setGreenTheme();
            this.applyTheme();
        });
        
        setOnFilterSelected(filterType -> {
            switch (filterType) {
                case ALPHABETICAL_ASCENDING -> displayedNotes.sort(Comparator.comparing(NoteModel::getTitle));
                case ALPHABETICAL_DESCENDING -> displayedNotes.sort(Comparator.comparing(NoteModel::getTitle).reversed());
                case NEWEST_FIRST -> displayedNotes.sort(Comparator.comparing(NoteModel::getDate).reversed());
                case OLDEST_FIRST -> displayedNotes.sort(Comparator.comparing(NoteModel::getDate));
            }
            setNotes(displayedNotes); // Refresh view
        });


        top.searchHeader.textField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                emit();
            }

            public void removeUpdate(DocumentEvent e) {
                emit();
            }

            public void changedUpdate(DocumentEvent e) {
                emit();
            }

            private void emit() {
                if (onSearch != null) {
                    onSearch.accept(top.searchHeader.textField.getText());
                }
            }
        });

        top.noteHeader.addEvent(new HeaderEvent() {
            @Override
            public void buttonSelected(int index) {
                switch (index) {
                    case 1 -> {
                        if (onNewNote != null)
                            onNewNote.run();
                    }
                    case 7 -> showSearchView();
                    case 6 -> {
                        if (onSave != null)
                            onSave.run();
                    }
                }
            }
        });

        body.search.noteList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int index = body.search.noteList.locationToIndex(e.getPoint());

                if (index >= 0 && onNoteSelected != null) {
                    onNoteSelected.accept(index);
                }
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
        setNotes(notes); // Initially show all
        showSearchView(); // show view
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
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
                                .addGap(0, 0, Short.MAX_VALUE)));
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(top, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(body, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private core.view.component.body.Body body;
    private core.view.component.common.RoundPanel mainPanel;
    private core.view.component.header.Top top;
    // End of variables declaration//GEN-END:variables
}