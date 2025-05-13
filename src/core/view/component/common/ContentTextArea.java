package core.view.component.common;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextArea;

public class ContentTextArea extends JTextArea {
    public ContentTextArea() {
        super();
        setBorder(null);

        setForeground(new Color(13, 27, 42));
        setBackground(Color.WHITE);
        setCaretColor(new Color(13, 27, 42));
        setSelectionColor(new Color(76, 204, 255));

        setLineWrap(true);
        setWrapStyleWord(true);
        setRows(5);
        setPreferredSize(new Dimension(388, 100));
    }
}
