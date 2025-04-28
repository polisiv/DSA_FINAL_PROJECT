package core.view.component.header;

import core.view.component.common.HeaderButton;
import core.view.component.common.HeaderEvent;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

public class SearchHeaderPanel extends javax.swing.JPanel {

    private List<HeaderEvent> events;

    public SearchHeaderPanel() {
        initComponents();
        events = new ArrayList<>();
        setLayout(new MigLayout("fill, inset 0, alignx center, aligny top", "[]6[]", "[]"));
        addSpace(20);
        addItem("1", 1);
        addItem("2", 2);
        JTextField textField = new JTextField(40);
        textField.setVisible(false);
        add(textField, "w 130!, h 25!, gapleft 0");
        addItem("3", 3);
        addItem("4", 4);
        addSpace(20);
        repaint();
        revalidate();
    }

    private void addItem(String icon, int index) {
        HeaderButton item = new HeaderButton();
        item.setImage(new ImageIcon(getClass().getResource("/core/view/icon/" + icon + ".png")).getImage(),
                Color.WHITE);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                runEvent(index);
            }
        });
        add(item, "w 50!, h 50!, center");
    }

    private void addSpace(int size) {
        add(new JLabel(), "h " + size + "!");
    }

    public void addEvent(HeaderEvent event) {
        events.add(event);
    }

    private void runEvent(int index) {
        for (HeaderEvent event : events) {
            event.buttonSelected(index);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 52, Short.MAX_VALUE));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}