package core.model;
import java.time.LocalDateTime;

public class NoteModel {
    private String content;
    private String title;
    private String date;    // Date of creation
    private String noteId;
    private boolean isSaved = false;
    private boolean isDeleted = false;

    public NoteModel(String content, String title) {
        this.content = content;
        this.title = title;
        date = java.time.LocalDate.now().toString(); //using instant time (year-month-day)
        noteId = LocalDateTime.now().toString(); //the note id in this format "2024-01-04T11:59:03.286975"
        isSaved = true;
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

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
