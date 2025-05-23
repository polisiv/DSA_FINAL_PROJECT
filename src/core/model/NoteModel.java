package core.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NoteModel {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy    hh:mm a");
    private String content;
    private String title;
    private String date;    // Date of creation
    private String noteId;

    public NoteModel(String title, String content) {
        this.title = title;
        this.content = content;
        date = LocalDateTime.now().format(formatter);
        //using instant time (year-month-day)
        noteId = LocalDateTime.now().toString(); //the note id in this format "2024-01-04T11:59:03.286975"
    }

    public NoteModel(){

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public String getNoteId(){
        return noteId;
    }
    public LocalDateTime getCreatedDateTime() {
        return LocalDateTime.parse(noteId);
    }

}
