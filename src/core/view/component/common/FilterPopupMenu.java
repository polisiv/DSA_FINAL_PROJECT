package core.view.component.common;

import core.modelservice.NoteFilterType;
import core.view.uiconfig.Config;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class FilterPopupMenu extends JPopupMenu {

    public FilterPopupMenu(Consumer<NoteFilterType> onFilterSelected) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Config.WHITE);

        add(wrap(createFilterItem("A → Z", NoteFilterType.ALPHABETICAL_ASCENDING, onFilterSelected)));
        add(wrap(createFilterItem("Z → A", NoteFilterType.ALPHABETICAL_DESCENDING, onFilterSelected)));
        add(wrap(createFilterItem("Newest First", NoteFilterType.NEWEST_FIRST, onFilterSelected)));
        add(wrap(createFilterItem("Oldest First", NoteFilterType.OLDEST_FIRST, onFilterSelected)));
    }

    private JPanel createFilterItem(String label, NoteFilterType filterType, Consumer<NoteFilterType> onSelect) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        lbl.setForeground(Config.DARKEST);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(150, 30));
        panel.add(lbl);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onSelect.accept(filterType);
                setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(Config.DARKER_GRAY);
                panel.setOpaque(true);
                panel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setOpaque(false);
                panel.repaint();
            }
        });

        return panel;
    }

    private JPanel wrap(JPanel inner) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
        wrapper.add(inner, BorderLayout.CENTER);
        return wrapper;
    }
}
