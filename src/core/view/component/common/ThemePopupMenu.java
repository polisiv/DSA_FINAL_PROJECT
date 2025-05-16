package core.view.component.common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ThemePopupMenu extends JPopupMenu {

    public ThemePopupMenu(Runnable onBlue, Runnable onGreen) {
        setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1));
        setBackground(new Color(250, 250, 250));
        
        add(createColorItem(new Color(13, 27, 42), onBlue));   // Blue
        add(createColorItem(new Color(33, 104, 105), onGreen)); // Green
    }

    private JPanel createColorItem(Color color, Runnable onClick) {
        JButton button = new JButton();
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (getModel().isRollover()) {
                    g2.setColor(new Color(210, 210, 210));
                    g2.fillRoundRect(5, 2, getWidth() - 10, getHeight() - 4, 10, 10);
                }

                g2.setColor(color);
                g2.fillRoundRect(10, 5, getWidth() - 20, getHeight() - 10, 10, 10);
            }

            ButtonModel getModel() {
                return button.getModel(); 
            }
        };
        
         // invisible button for model state
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setVisible(false); 
        
        panel.setLayout(null);
        panel.add(button);
        panel.setPreferredSize(new Dimension(50, 30));
        panel.setOpaque(false);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.getModel().setRollover(true);
                panel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.getModel().setRollover(false);
                panel.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                onClick.run();
                setVisible(false);
            }
        });

        return panel;
    }
}

