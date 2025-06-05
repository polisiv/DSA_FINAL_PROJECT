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
            case "Alphabetically Descending":
                result = alphabeticalSort(displayedNotes);
                Collections.reverse(result);
                break;
            case "Date Newest":
                result = dateSort(displayedNotes);
                break;
            case "Date Oldest":
                result = dateSort(displayedNotes);
                Collections.reverse(result);
                break;
            default:
                result = displayedNotes;
        }
        return result;
    }
private static final int MIN_RUN = 32;

private static List<NoteModel> alphabeticalSort(List<NoteModel> displayedNotes) {
    List<NoteModel> result = new ArrayList<>(displayedNotes);
    int n = result.size();

    for (int i = 0; i < n; i += MIN_RUN) {
        insertionSort(result, i, Math.min((i + MIN_RUN - 1), (n - 1)));
    }

    for (int size = MIN_RUN; size < n; size = 2 * size) {
        for (int left = 0; left < n; left += 2 * size) {
            int mid = left + size - 1;
            int right = Math.min((left + 2 * size - 1), (n - 1));

            if (mid < right) {
                merge(result, left, mid, right);
            }
        }
    }

    return result;
}

private static void insertionSort(List<NoteModel> list, int left, int right) {
    for (int i = left + 1; i <= right; i++) {
        NoteModel temp = list.get(i);
        int j = i - 1;
        while (j >= left && list.get(j).getTitle().compareToIgnoreCase(temp.getTitle()) > 0) {
            list.set(j + 1, list.get(j));
            j--;
        }
        list.set(j + 1, temp);
    }
}

private static void merge(List<NoteModel> list, int left, int mid, int right) {
    List<NoteModel> leftPart = new ArrayList<>(list.subList(left, mid + 1));
    List<NoteModel> rightPart = new ArrayList<>(list.subList(mid + 1, right + 1));

    int i = 0, j = 0, k = left;
    while (i < leftPart.size() && j < rightPart.size()) {
        if (leftPart.get(i).getTitle().compareToIgnoreCase(rightPart.get(j).getTitle()) <= 0) {
            list.set(k++, leftPart.get(i++));
        } else {
            list.set(k++, rightPart.get(j++));
        }
    }

    while (i < leftPart.size()) list.set(k++, leftPart.get(i++));
    while (j < rightPart.size()) list.set(k++, rightPart.get(j++));
   }



private static List<NoteModel> dateSort(List<NoteModel> displayedNotes) {
    List<NoteModel> result = new ArrayList<>(displayedNotes);
    int n = result.size();

    // Chạy insertion sort trên từng đoạn nhỏ (MIN_RUN)
    for (int i = 0; i < n; i += MIN_RUN) {
        insertionSortByDate(result, i, Math.min(i + MIN_RUN - 1, n - 1));
    }

    // Merge các đoạn đã sort
    for (int size = MIN_RUN; size < n; size = 2 * size) {
        for (int left = 0; left < n; left += 2 * size) {
            int mid = left + size - 1;
            int right = Math.min(left + 2 * size - 1, n - 1);

            if (mid < right) {
                mergeByDate(result, left, mid, right);
            }
        }
    }
    return result;
}
private static void insertionSortByDate(List<NoteModel> list, int left, int right) {
    for (int i = left + 1; i <= right; i++) {
        NoteModel temp = list.get(i);
        int j = i - 1;
        while (j >= left && list.get(j).getCreatedDateTime().compareTo(temp.getCreatedDateTime()) > 0) {
            list.set(j + 1, list.get(j));
            j--;
        }
        list.set(j + 1, temp);
    }
}

private static void mergeByDate(List<NoteModel> list, int left, int mid, int right) {
    List<NoteModel> leftPart = new ArrayList<>(list.subList(left, mid + 1));
    List<NoteModel> rightPart = new ArrayList<>(list.subList(mid + 1, right + 1));

    int i = 0, j = 0, k = left;
    while (i < leftPart.size() && j < rightPart.size()) {
        if (leftPart.get(i).getCreatedDateTime().compareTo(rightPart.get(j).getCreatedDateTime()) <= 0) {
            list.set(k++, leftPart.get(i++));
        } else {
            list.set(k++, rightPart.get(j++));
        }
    }
    while (i < leftPart.size()) list.set(k++, leftPart.get(i++));
    while (j < rightPart.size()) list.set(k++, rightPart.get(j++));
}

    


}
