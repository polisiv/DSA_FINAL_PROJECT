package core.view.component.header;

import javax.swing.*;
import core.view.component.common.RoundPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Top extends JPanel {

    public SearchHeaderPanel searchHeader = new SearchHeaderPanel();
    public NoteHeaderPanel noteHeader = new NoteHeaderPanel();
    private int mouseX, mouseY;

    public Top() {
        initComponents();
        setOpaque(false);
    }

    public void initWindowControlPanel(JFrame frame, RoundPanel panel) {
        windowControlPanel.initEvent(frame, panel);
    }

    public void initDrag(JFrame frame) {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                frame.setLocation(x - mouseX, y - mouseY);
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int x = screenSize.width - getWidth();
                int y = 0;
                frame.setLocation(x, y);
            }
        });
    }

    public void showSearchHeader() {
        headerContainer.removeAll();
        headerContainer.add(searchHeader, BorderLayout.CENTER);
        headerContainer.revalidate();
        headerContainer.repaint();
    }

    public void showNoteHeader() {
        headerContainer.removeAll();
        headerContainer.add(noteHeader, BorderLayout.CENTER);
        headerContainer.revalidate();
        headerContainer.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        windowControlPanel = new core.view.component.common.WindowControlPanel();
        headerContainer = new javax.swing.JPanel();

        headerContainer.setOpaque(false);
        headerContainer.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(windowControlPanel,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(32, 358, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(headerContainer, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addContainerGap()))));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(windowControlPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(headerContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 34,
                                        Short.MAX_VALUE)
                                .addContainerGap()));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel headerContainer;
    private core.view.component.common.WindowControlPanel windowControlPanel;
    // End of variables declaration//GEN-END:variables
}
