package core.modelservice;

import java.io.*;
import java.util.*;

public class DictionaryLoader {

    public static List<String> loadDictionary(String filename) {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    words.add(line.toLowerCase());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }
}
