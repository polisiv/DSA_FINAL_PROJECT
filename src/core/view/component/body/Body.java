package core.view.component.body;

import core.model.NoteModel;
import javax.swing.JPanel;

public class Body extends JPanel {
    public SearchBodyPanel search;
    public NoteBodyPanel note;

    public Body() {
        search = new SearchBodyPanel();
        note = new NoteBodyPanel();
        initComponents();
        setOpaque(false);
        scrollPane.setViewportBorder(null);
       
    }

    public void showSearchPanel() {
        scrollPane.setViewportView(search);
    }
    public void showNotePanel(NoteModel model) {
        note = new NoteBodyPanel(model); 
        scrollPane.setViewportView(note);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();

        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables

    public void applyTheme() {
        search.applyTheme();
        note.applyTheme();
    }
}
