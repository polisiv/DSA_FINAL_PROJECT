package core.view.component.body;

import core.model.NoteModel;
import java.util.ArrayList;

public class SearchBodyPanel extends javax.swing.JPanel {

    public SearchBodyPanel() {
        initComponents();
        setOpaque(false);
    }

    public void displayNotes(ArrayList<NoteModel> notes) {
        noteList.displayNotes(notes);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        noteList = new core.view.component.common.NoteList<>();

        // for when i have note service
        // List<NoteModel> notes = noteService.getAllNotes();
        // String[] titles = notes.stream()
        // .map(NoteModel::getTitle)
        // .toArray(String[]::new);

        // noteList.setModel(new javax.swing.AbstractListModel<String>() {
        // @Override
        // public int getSize() {
        // return titles.length;
        // }

        // @Override
        // public String getElementAt(int i) {
        // return titles[i];
        // }
        // });

        noteList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] titles = { "Shopping List", "Workout Plan", "Meeting Notes", "Groceries", "Routine" };

            @Override
            public int getSize() {
                return titles.length;
            }

            @Override
            public String getElementAt(int i) {
                return titles[i];
            }
        });

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
    private core.view.component.common.NoteList<String> noteList;
    // End of variables declaration//GEN-END:variables
}
