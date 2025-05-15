package core.view.component.common;

import core.view.uiconfig.Config;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JTextField;

public class TitleTextField extends JTextField{
    
    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }

    private String labelText = "";
    
    public TitleTextField(int column) {
        super(column);
        setBorder(Config.TEXT_FIELD_BORDER);
        
        setForeground(Config.DARKEST_BLUE);
        setBackground(Config.WHITE);
        
        setSelectionColor(Config.DARKER_BLUE);
    }

    
    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        // the underline color
        g2.setColor(Config.LIGHTER_BLUE);
        // a 1px line at the bottom of the text field
        int y = getHeight() - 1;
        g2.drawLine(0, y, 400-12, y);
        g2.dispose();
    }
    
}
