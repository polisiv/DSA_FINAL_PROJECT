package core.model;

public class NoteModel {
    private String content;
    private String title;
    private final String date;    // Date of creation

    public NoteModel(String content, String title) {
        this.content = content;
        this.title = title;
        date = java.time.LocalDate.now().toString(); //using instant time (year-month-day)
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

}
