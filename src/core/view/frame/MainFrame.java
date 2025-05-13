package core.view.frame;

import core.view.component.common.HeaderEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

public class MainFrame extends javax.swing.JFrame {
    public MainFrame() {
        setUndecorated(true);
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        getContentPane().setBackground(new Color(0, 0, 0, 0));

        top.initWindowControlPanel(MainFrame.this, mainPanel);
        top.initDrag(MainFrame.this);
        
        top.searchHeader.addEvent(new HeaderEvent() {
            @Override
            public void buttonSelected(int index){
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
            public void buttonSelected(int index){
                switch (index) {
                    case 1 -> System.out.println("Show new notes");
                    case 7 -> getView("search");
                    case 6 -> System.out.println("Save");
                }
            }
        });
        getView("search");

        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screenSize.width - getWidth();
        int y = 0;
        setLocation(x, y);
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

        mainPanel.setBackground(new java.awt.Color(13, 27, 42));

        top.setPreferredSize(new java.awt.Dimension(400, 70));

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

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    private void getView(String view) {
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
        body.showSearchPanel();
    }
    
    private void showNoteView() {
        top.showNoteHeader();
        body.showNotePanel();
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private core.view.component.body.Body body;
    private core.view.component.common.RoundPanel mainPanel;
    private core.view.component.header.Top top;
    // End of variables declaration//GEN-END:variables
}
