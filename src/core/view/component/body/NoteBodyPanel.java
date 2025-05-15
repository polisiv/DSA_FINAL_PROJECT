package core.view.component.body;

import core.model.NoteModel;
import core.view.component.common.TitleTextField;

public class NoteBodyPanel extends javax.swing.JPanel {
        private NoteModel currentNote;
        public NoteBodyPanel(NoteModel note) {

                title = new TitleTextField(40);
                date = new javax.swing.JLabel();
                jScrollPane1 = new javax.swing.JScrollPane();
                content = new javax.swing.JTextArea();

                title.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
                title.setForeground(new java.awt.Color(13, 27, 42));

                date.setForeground(new java.awt.Color(65, 90, 119));

                content.setColumns(20);
                content.setForeground(new java.awt.Color(13, 27, 42));
                content.setRows(5);
                jScrollPane1.setViewportView(content);
                
                setNote(note);

                initComponents();
                setOpaque(false);
        }
        
        public NoteBodyPanel() {
            this(new NoteModel("Untitled", ""));
        }

        public void setNote(NoteModel note) {
                this.currentNote = note;
                title.setText(note.getTitle()); // e.g., JTextField
                content.setText(note.getContent()); // e.g., JTextArea
                date.setText(note.getDate());
        }
        
        public NoteModel getNote() {
            currentNote.setTitle(title.getText());
            currentNote.setContent(content.getText());

            return currentNote;
        }


        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(title)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(date)
                                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                                .addComponent(jScrollPane1,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                388,
                                                                                                Short.MAX_VALUE))
                                                                .addContainerGap()));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(title,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(date)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jScrollPane1,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                110, Short.MAX_VALUE)
                                                                .addContainerGap()));
        }// </editor-fold>//GEN-END:initComponents

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JTextArea content;
        private javax.swing.JLabel date;
        private javax.swing.JScrollPane jScrollPane1;
        private TitleTextField title;
        // End of variables declaration//GEN-END:variables
}
