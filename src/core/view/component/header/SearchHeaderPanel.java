package core.view.component.header;

import core.view.component.common.SearchTextField;
import core.view.component.common.HeaderButton;
import core.view.component.common.HeaderEvent;
import core.view.component.common.ThemePopupMenu;
import core.view.uiconfig.Config;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

public class SearchHeaderPanel extends javax.swing.JPanel {

    private List<HeaderEvent> events;
    public SearchTextField textField = new SearchTextField(Config.SEARCH_TEXT_FIELD_LENGTH);
    
    private Runnable onBlueThemeSelected;
    private Runnable onGreenThemeSelected;

    public void setOnBlueThemeSelected(Runnable callback) {
        this.onBlueThemeSelected = callback;
    }

    public void setOnGreenThemeSelected(Runnable callback) {
        this.onGreenThemeSelected = callback;
    }


    public SearchHeaderPanel() {
        initComponents();
        events = new ArrayList<>();
        setLayout(new MigLayout("fill, inset 0, alignx center, aligny top", "[]6[]", "[]"));
        addSpace(Config.HEADER_BLANK_SPACE);
        addItem(Config.ADD_NOTE_ICON, Config.ADD_NOTE_EVENT_INDEX);
        addItem(Config.SEARCH_ICON, Config.SEARCH_EVENT_INDEX);
        textField.setVisible(true);
        add(textField, "w 130!, h 25!, gapleft 0");
        addItem(Config.FILTER_ICON, Config.FILTER_EVENT_INDEX);
        
        HeaderButton settingsButton = addItem(Config.SETTING_ICON, Config.SETTING_EVENT_INDEX);

        ThemePopupMenu themePopup = new ThemePopupMenu(() -> {
            if (onBlueThemeSelected != null) onBlueThemeSelected.run();
        }, () -> {
            if (onGreenThemeSelected != null) onGreenThemeSelected.run();
        });

        settingsButton.addActionListener(e -> {
            themePopup.show(settingsButton, 0, settingsButton.getHeight());
        });


        addSpace(Config.HEADER_BLANK_SPACE);
        
        
        repaint();
        revalidate();
    }

    private HeaderButton addItem(String icon, int index) {
        HeaderButton item = new HeaderButton();
        item.setImage(new ImageIcon(getClass().getResource("/core/view/icon/" + icon + ".png")).getImage(),
                Config.WHITE);
        item.addActionListener((ActionEvent ae) -> {
            runEvent(index);
        });
        add(item, "w 50!, h 50!, center");
        return item;
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

    void applyTheme() {
        textField.applyTheme();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}