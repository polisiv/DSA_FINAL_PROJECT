package application;

import core.controller.NoteController;
import core.datalayer.NoteDatabase;
import core.modelservice.NoteService;
import core.view.frame.MainFrame;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException |
                 InstantiationException | IllegalAccessException e) {
            System.out.println("Failed to apply LookAndFeel: " + e.getMessage());
        }
        
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            NoteService noteService = new NoteService(new NoteDatabase()); 
            NoteController controller = new NoteController(mainFrame, noteService);
            controller.init();
            mainFrame.setVisible(true);
        });
    }
}
