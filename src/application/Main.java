package application;

import core.controller.NoteController;
import core.datalayer.NoteDatabase;
import core.modelservice.NoteService;
import core.view.frame.MainFrame;

import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            NoteService noteService = new NoteService(new NoteDatabase()); 
            NoteController controller = new NoteController(mainFrame, noteService);
            controller.init();
            mainFrame.setVisible(true);
        });
    }
}
