package core.view.component.body;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Body extends JPanel {
    SearchBodyPanel search = new SearchBodyPanel();
    NoteBodyPanel note = new NoteBodyPanel();
    public Body() {
        initComponents();
        setOpaque(false);
        
        scrollPane.setViewportBorder(null);
        showSearchPanel();
    }
    
    public void showSearchPanel() {
    scrollPane.setViewportView(search);
}

public void showNotePanel() {
    scrollPane.setViewportView(note);
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();

        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
