package core.datalayer;

import java.io.FileWriter;
import java.io.IOException;

public class NoteDatabase {

    //this will only change the content
    public void addNote(String content){
        this.addNote(null,content);
    }

    //this will change both title and content
    public void addNote(String title, String content){
        try {
            FileWriter file = new FileWriter("/resource/database/Note.txt");
            file.write(content);
            file.close();
            System.out.println("""
                    *******************
                    MODIFY SUCCESSFULLY
                    *******************
                    """);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteNote(){

    }


}
