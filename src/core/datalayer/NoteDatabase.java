package core.datalayer;

import core.model.NoteModel;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NoteDatabase {
    private final String PATH = "src/resource/database/Note.txt";
    private final Path file = Path.of(PATH);
    private static HashMap<String, List<String>> noteQuerry = new HashMap<>();

    public NoteDatabase() {

    }

    public void saveNote(String id, String title, String content) {
        String note = """
                ID: %s
                =============START=============
                Title: %s
                Content: %s
                =============END===============
                """.formatted(id,
                title,
                content);

        if (Files.isWritable(file)) {
            try {
                Files.writeString(file, note, StandardOpenOption.APPEND);
                System.out.println("""
                        *******************
                        ADD SUCCESSFULLY
                        *******************
                        """);
            } catch (IOException e) {
                System.out.println("""
                        *********************
                        ADD UNSUCCESSFULLY
                        *********************
                        """);
            }
        }
    }

    public void deleteNote(String id) {
        try {
            List<String> lines = Files.readAllLines(file);
            List<String> updatedLines = new ArrayList<>();
            boolean isTargetNote = false;
            boolean noteFound = false;

            for (String line : lines) {
                if (line.startsWith("ID: ")) {
                    String lineId = line.substring(4).trim();
                    if (lineId.equals(id)) {
                        isTargetNote = true;
                        noteFound = true;
                        continue;
                    } else {
                        isTargetNote = false;
                        updatedLines.add(line);
                    }
                } else if (isTargetNote) {
                    if (line.trim().equals("END")) {
                        isTargetNote = false;
                    }
                    continue;
                } else {
                    updatedLines.add(line);
                }
            }

            if (!noteFound) {
                System.out.println("""
                        ******************
                        NOTE NOT FOUND
                        ******************
                        """);
                return;
            }

            if (Files.isWritable(file)) {
                Files.write(file, updatedLines);
                noteQuerry.remove("ID: " + id);
                System.out.println("""
                        ******************
                        DELETE SUCCESSFULLY
                        ******************
                        """);
            } else {
                System.out.println("""
                        ********************
                        FILE NOT WRITABLE
                        ********************
                        """);
            }
        } catch (IOException e) {
            System.err.println("error: " + e.getMessage());
            System.out.println("""
                    ********************
                    DELETE UNSUCCESSFULLY
                    ********************
                    """);
        }
    }

    public HashMap<String, List<String>> loadNote() {
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
            String title = null;
            StringBuilder contentBuilder = null;
            boolean readingContent = false;

            for (String line : lines) {
                if (line.startsWith("ID: ")) {
                    currentId = line.substring(4).trim();
                    title = null;
                    contentBuilder = new StringBuilder();
                    readingContent = false;
                } else if (line.startsWith("Title: ")) {
                    title = line.substring(7).trim();
                } else if (line.startsWith("Content: ")) {
                    readingContent = true;
                    contentBuilder.append(line.substring(9).trim()).append("\n");
                } else if (line.startsWith("=============END===============")) {
                    readingContent = false;
                    if (currentId != null && title != null && contentBuilder != null) {
                        String content = contentBuilder.toString().strip();
                        notes.put(currentId, new NoteModel(title, content));
                    }
                    // reset
                    currentId = null;
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
