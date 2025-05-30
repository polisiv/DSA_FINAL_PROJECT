package core.view.component.body;

public class SearchBodyPanel extends javax.swing.JPanel {

    public SearchBodyPanel() {
        initComponents();
        setOpaque(false);
    }

    @SuppressWarnings("unchecked")
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        // noteList = new core.view.component.common.NoteList<>();
        noteList = new core.view.component.common.NoteList();

        jScrollPane2.setViewportView(noteList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    // public core.view.component.common.NoteList<String> noteList;
    public core.view.component.common.NoteList noteList;

    // End of variables declaration//GEN-END:variables

    void applyTheme() {
        noteList.applyTheme();
    }
}
