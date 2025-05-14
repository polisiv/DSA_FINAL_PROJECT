package core.view.frame;

import core.model.NoteModel;
import core.view.component.common.HeaderEvent;
import core.view.uiconfig.Config;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.AbstractListModel;

public class MainFrame extends javax.swing.JFrame {
    private List<NoteModel> displayedNotes;

    public MainFrame() {
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
                    case 1 -> getView("note");
                    case 2 -> System.out.println("Search");
                    case 3 -> System.out.println("Filter");
                    case 4 -> System.out.println("Theme changer");
                }
            }
        });
        top.noteHeader.addEvent(new HeaderEvent() {
            @Override
            public void buttonSelected(int index) {
                switch (index) {
                    case 1 -> System.out.println("Show new notes");
                    case 7 -> getView("search");
                    case 6 -> System.out.println("Save");
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
        getView("search"); // show view
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new core.view.component.common.RoundPanel();
        top = new core.view.component.header.Top();
        body = new core.view.component.body.Body();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        mainPanel.setBackground(Config.DARKEST_BLUE);

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

    public void getView(String view) {
        switch (view) {
            case "search":
                showSearchView();
                break;
            case "note":
                showNoteView();
                break;
            default:
                System.out.println("Invalid view: " + view);
                break;
        }
    }

    private void showSearchView() {
        top.showSearchHeader();
        setNotes(displayedNotes);
        body.showSearchPanel();
    }

    private void showNoteView() {
        top.showNoteHeader();
        body.showNotePanel();
    }

    public void setNotes(List<NoteModel> notes) {
        this.displayedNotes = notes;

        body.search.noteList.setModel(new AbstractListModel<>() {
            @Override
            public int getSize() {
                return displayedNotes.size();
            }

            @Override
            public String getElementAt(int i) {
                return displayedNotes.get(i).getTitle();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private core.view.component.body.Body body;
    private core.view.component.common.RoundPanel mainPanel;
    private core.view.component.header.Top top;
    // End of variables declaration//GEN-END:variables
}