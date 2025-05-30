package application;

import core.controller.NoteController;
import core.datalayer.NoteDatabase;
import core.model.AbbreviationModel;
import core.modelservice.NoteService;
import core.view.frame.MainFrame;
import javax.swing.*;
import javax.swing.undo.UndoManager;

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
            AbbreviationModel abbreviationModel = new AbbreviationModel();
            UndoManager undoManager = new UndoManager();
            NoteController controller = new NoteController(mainFrame, noteService, abbreviationModel, undoManager);
            controller.init();
            mainFrame.setVisible(true);
        });
    }
}
