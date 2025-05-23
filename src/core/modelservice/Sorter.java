package core.modelservice;

import core.model.NoteModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sorter {

    public static List<NoteModel> sort(List<NoteModel> displayedNotes, String sortType){
        List<NoteModel> result = new ArrayList<>();
        switch (sortType) {
            case "Alphabetically Ascending":
                result = alphabeticalSort(displayedNotes);
                break;
//            case "Alphabetically Descending":
//                result = alphabeticalSort(displayedNotes).reverse();
//                break;
            case "Date Newest":
                result = dateSort(displayedNotes);
                break;
//            case "Date Oldest":
//                result = dateSort(displayedNotes).reverse();
//                break;
            default:
                result = displayedNotes;
        }
        return result;
    }

    private static List<NoteModel> alphabeticalSort(List<NoteModel> displayedNotes){
        List<NoteModel> result = new ArrayList<>();
        return result;
    }


    private static List<NoteModel> dateSort(List<NoteModel> displayedNotes){
        List<NoteModel> result = new ArrayList<>();
        return result;
    }

}
