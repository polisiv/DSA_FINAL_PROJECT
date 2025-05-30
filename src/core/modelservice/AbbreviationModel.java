package core.modelservice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbbreviationModel {
    private final Map<String, String> abbreviationsMap = new HashMap<>();

    private final String filePath = "src/resource/database/Abbreviations.csv";

    public AbbreviationModel() {
        loadFromFile();
    }

    private void loadFromFile() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    abbreviationsMap.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading abbreviations: " + e.getMessage());
        }
    }

    public String getExpansion(String abbreviation) {
        return abbreviationsMap.get(abbreviation);
    }

    public boolean isAbbreviation(String abbreviation) {
        return abbreviationsMap.containsKey(abbreviation);
    }
    
}
