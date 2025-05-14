package core.view.component.common;

import core.model.NoteModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

public class NoteList<E extends Object> extends JList<E> {

    public Color getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(Color selectedColor) {
        this.selectedColor = selectedColor;
    }

    private final DefaultListModel model;
    private Color selectedColor;
    private int movingIndex = -1;
    private final int deleteAble = -50;
    private final int optionAble = 100;
    private int x;
    private int mx;

    public NoteList() {
        model = new DefaultListModel();
        super.setModel(model);
        selectedColor = new Color(204, 205, 201);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                x = me.getX();
                int index = locationToIndex(me.getPoint());
                if (index != movingIndex) {
                    movingIndex = index;
                    mx = 0;
                } else {
                    if (mx != 0) {
                        x -= optionAble;
                    }
                }
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (mx <= deleteAble) {
                    // remove item
                    model.removeElementAt(movingIndex);
                }
                if (mx >= optionAble) {

                } else {
                    movingIndex = -1;
                    mx = 0;
                }
                setSelectedIndex(movingIndex);
                repaint();
            }

        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                if (movingIndex != -1) {
                    mx = me.getX() - x;
                    repaint();
                }
            }
        });
    }

    @Override
    public ListCellRenderer getCellRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> jList, Object o, int index, boolean selected,
                    boolean focus) {
                ListItem item = new ListItem(NoteList.this.getFont(), o + "");
                if (index == movingIndex) {
                    item.setItemColor(selectedColor);
                    if (mx <= deleteAble) {
                        mx = deleteAble;
                    }
                    if (mx >= optionAble) {
                        mx = optionAble;
                    }
                    item.movingX(mx);
                    item.setDeleteAble(mx <= deleteAble);
                }
                return item;
            }
        };
    }

    @Override
    public void setModel(ListModel<E> lm) {
        model.clear(); 
        for (int i = 0; i < lm.getSize(); i++) {
            model.addElement(lm.getElementAt(i));
        }
    }


    public void addItem(Object obj) {
        model.addElement(obj);
    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        if (model.isEmpty()) {
            Graphics2D g2 = (Graphics2D) grphcs.create(); // Create a new graphics context
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.GRAY); // Set text color

            String message = "There is currently no note";
            Font font = getFont(); // Use the panel's current font
            g2.setFont(font);

            // Get font metrics to measure the text
            FontMetrics metrics = g2.getFontMetrics(font);
            int textWidth = metrics.stringWidth(message);
            int textHeight = metrics.getHeight();

            // Calculate x (horizontal center) and y (vertical center with ascent)
            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() - textHeight) / 2 + metrics.getAscent();

            // Draw the message
            g2.drawString(message, x, y);
            g2.dispose(); // Dispose of the graphics context
        }
    }

    public void displayNotes(ArrayList<NoteModel> notes) {
        // Clear current items
        setModel(new javax.swing.DefaultListModel<>());
        // Add new notes
        for (NoteModel note : notes) {
            addItem(note.getTitle());
        }
    }
}
