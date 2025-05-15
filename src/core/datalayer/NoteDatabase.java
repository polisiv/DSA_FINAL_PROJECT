package core.datalayer;

import core.model.NoteModel;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NoteDatabase {
    private final String PATH = "src/resource/database/Note.txt";
    static int quantity = 0;
    private final Path file = Path.of(PATH);
    private static HashMap<String, List<String>> noteQuerry = new HashMap<>();

    public NoteDatabase(){

    }
    public void addNote(String id, String title, String content, boolean isDeleted){
        String note = """
                        ID: %s
                        Delete: %s
                        =============START=============
                        Title: %s
                        Content: %s
                        ==============END==============
                        """.formatted(id, isDeleted ? "true" : "false",title != null && !title.isEmpty() ? title : "N/A" ,content);
        if(Files.isWritable(file)){
            try {
                Files.writeString(file, note, StandardOpenOption.APPEND
                );
                System.out.println("""
                   *******************
                   MODIFY SUCCESSFULLY
                   *******************
                   """);
                quantity++;
            } catch (IOException e) {
                System.out.println("""
                   *********************
                   MODIFY UNSUCCESSFULLY
                   *********************
                   """);
            }
        }
    }

//    public static void main(String[] args) {
//        NoteDatabase dp = new NoteDatabase();
////        dp.addNote("70v3.2","", """
////               2""",false);
//        dp.readNote();
////        HashMap<String,List<String>> test = dp.loadNote();
////        test.forEach((s,v) -> System.out.println("KEY: " + s + " VALUE: " + v));
//    }


    public void deleteNote(String id) {
        try {
            List<String> lines = Files.readAllLines(file);
            List<String> updatedLines = new ArrayList<>();
            boolean isTargetNote = false;
    
            for (String line : lines) {
                if (line.startsWith("ID: ")) {
                    if (line.equals("ID: " + id)) {
                        isTargetNote = true;
                    } else {
                        updatedLines.add(line);
                    }
                } else if (!isTargetNote) {
                    updatedLines.add(line); 
                } else if (line.contains("END")) {
                    isTargetNote = false;
                }
            }
    
            Files.write(file, updatedLines);
    
            System.out.println("""
                    ******************
                    DELETE SUCCESSFULLY
                    ******************
                    """);
    
        } catch (IOException e) {
            System.out.println("""
                    ********************
                    DELETE UNSUCCESSFULLY
                    ********************
                    """);
        }
    }

    //clean up the db by creating new db to store remaining notes
    public void cleanUp(){

    }

    public HashMap<String,List<String>> loadNote(){
        try {
            List<String> lines = Files.readAllLines(file);
            List<String> temp = new ArrayList<>();
            String currentId = null;
            for (String line : lines) {
                if (line.startsWith("ID: ")) {
                    // Save the previous note if it exists
                    if (currentId != null && !temp.isEmpty()) {
                        noteQuerry.put(currentId, new ArrayList<>(temp));
                    }
                    // Start a new note
                    currentId = line;
                    temp.clear();
                }
                temp.add(line);

                if (line.contains("END")) {
                    // Save the current note
                    if (currentId != null) {
                        noteQuerry.put(currentId, new ArrayList<>(temp));
                    }
                    temp.clear();
                    currentId = null;
                }
            }

            return noteQuerry;
        } catch (IOException e) {
            System.out.println("""
                ****************
                NO CONTENT FOUND
                ****************
                """
            );
            return new HashMap<>();
        }
    }

    public Map<String, NoteModel> getAllNotes() {
    Map<String, NoteModel> notes = new LinkedHashMap<>();

    try {
        List<String> lines = Files.readAllLines(file);
        String currentId = null;
        boolean isDeleted = false;
        String title = null;
        StringBuilder contentBuilder = null;
        boolean readingContent = false;

        for (String line : lines) {
            if (line.startsWith("ID: ")) {
                currentId = line.substring(4).trim();
                isDeleted = false;
                title = null;
                contentBuilder = new StringBuilder();
                readingContent = false;
            } else if (line.startsWith("Delete: ")) {
                isDeleted = line.equals("Delete: true");
            } else if (line.startsWith("Title: ")) {
                title = line.substring(7).trim();
            } else if (line.startsWith("Content: ")) {
                readingContent = true;
                contentBuilder.append(line.substring(9).trim()).append("\n");
            } else if (line.startsWith("==============END==============")) {
                readingContent = false;
                if (!isDeleted && currentId != null && title != null && contentBuilder != null) {
                    String content = contentBuilder.toString().strip();
                    notes.put(currentId, new NoteModel(title, content));
                }
                // reset
                currentId = null;
                isDeleted = false;
                title = null;
                contentBuilder = null;
            } else if (readingContent && contentBuilder != null) {
                contentBuilder.append(line).append("\n");
            }
        }

    } catch (IOException e) {
        System.out.println("""
                *****************
                NO CONTENT FOUND
                *****************
                """);
    }

    return notes;
}

}
