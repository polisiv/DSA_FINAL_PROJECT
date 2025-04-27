package core.view.component.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class ListItem extends javax.swing.JPanel {

    private final MigLayout mig;
    private final JPanel panel = new JPanel();
    private final Image binImage = new ImageIcon(getClass().getResource("/core/view/icon/bin.png")).getImage();
    private boolean deleteAble;
    
    public ListItem() {
        this(new Font("Helvetica", Font.PLAIN, 15), "Default Text");
    }
    
    public ListItem(Font font, String text) {
        initComponents();
        mig = new MigLayout("fillx", "0[fill]0", "0[]0");
        setLayout(mig);
        JLabel label = new JLabel(text);
        label.setFont(font); 
        label.setForeground(new Color(13, 27, 42));
        panel.setBackground(new Color(224, 225, 221));
        panel.setLayout(new MigLayout("fill, aligny center", "15[]", "fill")); 
        panel.add(label);
        this.add(panel, "h 40, w 100%");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(224, 225, 221));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setItemColor(Color selectedColor) {
        panel.setBackground(selectedColor);
    }

    public void movingX(int x) {
        int clampedX = Math.min(x, 0); 
        mig.setComponentConstraints(panel, "x " + clampedX + ", h 40, w 100%");

        if (x < 0) {
            setBackground(new Color(119, 141, 169));
        }
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs.create();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2.setColor(new Color(224, 225, 221));
        g2.fillRoundRect(0, 0, 50, getHeight(), 10, 10); 

        g2.drawImage(binImage, getWidth() - 35, 12, 20, 20, this);

        g2.dispose();
    }


    public boolean isDeleteAble() {
        return deleteAble;
    }
    
    public void setDeleteAble(boolean deleteAble) {
        this.deleteAble = deleteAble;
    }
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
