package core.view.component.common;

import core.view.uiconfig.Config;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

public class WindowControlButton extends JButton {
    
    public WindowControlButton() {
        setContentAreaFilled(false);
        setBorder(Config.WINDOW_CONTROL_BUTTON_BORDER);
        setCursor(Config.WINDOW_CONTROL_BUTTON_CURSOR);
    }

    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        int size = Math.min(width, height);
        int x = (width - size) / 2;
        int y = (height - size) / 2;
        g2.setColor(getBackground());
        g2.fillOval(x, y, size, size);
        super.paint(grphcs);
    }
}
