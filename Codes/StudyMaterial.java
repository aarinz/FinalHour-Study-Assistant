package Codes;

import java.sql.Timestamp;


public class StudyMaterial {
    private String name;
    private String filePath;
    private String section;
    private Timestamp savedAt;

    public StudyMaterial(String name, String filePath, String section, Timestamp savedAt) {
        this.name = name;
        this.filePath = filePath;
        this.section = section;
        this.savedAt = savedAt;
    }

    public String getName() {
        return name;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getSection() {
        return section;
    }

    public Timestamp getSavedAt() {
        return savedAt;
    }
}