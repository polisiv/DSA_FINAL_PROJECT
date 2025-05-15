package application;

import com.formdev.flatlaf.FlatLightLaf;
import core.controller.NoteController;
import core.datalayer.NoteDatabase;
import core.modelservice.NoteService;
import core.view.frame.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("Unsupported laf exception.");
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
