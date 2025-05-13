package core.view.component.common;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TitleTextField extends JTextField{
    
    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    private String labelText = "";
    private Color lineColor = new Color(119, 141, 169);
    
    public TitleTextField(int column) {
        super(column);
        setBorder(new EmptyBorder(1, 5, 1, 5));
        
        setForeground(new Color(13, 27, 42));
        setBackground(Color.WHITE);
        
        setSelectionColor(new Color(76, 204, 255));
    }

    
    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        // Set the underline color
    g2.setColor(lineColor);

    // Draw a 1px line at the bottom of the text field
    int y = getHeight() - 1;
    g2.drawLine(0, y, 400-12, y);
        g2.dispose();
    }
    
}
