package core.view.component.common;

import core.model.NoteModel;
import core.view.uiconfig.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

public class NoteList extends JList<NoteModel> {

    private final DefaultListModel<NoteModel> model;
    private Color selectedColor;
    private int movingIndex = -1;
    private int x;
    private int mx;
    
    private java.util.function.Consumer<NoteModel> onDeleteNote;

    public void setOnDeleteNote(java.util.function.Consumer<NoteModel> onDeleteNote) {
        this.onDeleteNote = onDeleteNote;
    }

    public NoteList() {
        model = new DefaultListModel<>();
        super.setModel(model);
        selectedColor = Config.DARKER_GRAY;

        setupMouseInteraction();
    }

    private void setupMouseInteraction() {
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
                        x -= Config.NOTE_LIST_OPTIONABLE;
                    }
                }

                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (movingIndex >= 0 && movingIndex < model.size()) {
                    NoteModel note = model.getElementAt(movingIndex);

                    if (mx <= Config.NOTE_LIST_DELETABLE) {
                        note.setDeleted(true);    
                        model.removeElementAt(movingIndex);
                        if (onDeleteNote != null) {
                            onDeleteNote.accept(note);
                        }
                    }

                    if (mx >= Config.NOTE_LIST_OPTIONABLE) {
                        // Placeholder for future feature (e.g., options menu)
                    } else {
                        movingIndex = -1;
                        mx = 0;
                    }

                    setSelectedIndex(movingIndex);
                    repaint();
                }
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
    public ListCellRenderer<? super NoteModel> getCellRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> jList, Object value, int index,
                                                          boolean selected, boolean focus) {
                NoteModel note = (NoteModel) value;
                ListItem item = new ListItem(NoteList.this.getFont(), note.getTitle());

                if (index == movingIndex) {
                    item.setItemColor(selectedColor);

                    if (mx <= Config.NOTE_LIST_DELETABLE) {
                        mx = Config.NOTE_LIST_DELETABLE;
                    } else if (mx >= Config.NOTE_LIST_OPTIONABLE) {
                        mx = Config.NOTE_LIST_OPTIONABLE;
                    }

                    item.movingX(mx);
                    item.setDeleteAble(mx <= Config.NOTE_LIST_DELETABLE);
                }

                return item;
            }
        };
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (model.isEmpty()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Config.DARKEST_BLUE);

            String message = Config.EMPTY_LIST_MESSAGE;
            Font font = getFont();
            g2.setFont(font);

            FontMetrics metrics = g2.getFontMetrics(font);
            int textWidth = metrics.stringWidth(message);
            int textHeight = metrics.getHeight();

            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() - textHeight) / 2 + metrics.getAscent();

            g2.drawString(message, x, y);
            g2.dispose();
        }
    }

    @Override
    public void setModel(ListModel<NoteModel> newModel) {
        model.clear();
        for (int i = 0; i < newModel.getSize(); i++) {
            model.addElement(newModel.getElementAt(i));
        }
    }

    public void displayNotes(List<NoteModel> notes) {
        model.clear();
        for (NoteModel note : notes) {
            model.addElement(note);
        }
    }

    public void addItem(NoteModel note) {
        model.addElement(note);
    }

    public Color getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(Color selectedColor) {
        this.selectedColor = selectedColor;
    }
}