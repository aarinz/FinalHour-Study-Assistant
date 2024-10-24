package Codes;

public class StudyMaterial {

    private String fileName;
    private String filePath;
    private String section;

    // Constructor
    public StudyMaterial(String fileName, String filePath, String section) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.section = section;
    }

    // Getters
    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getSection() {
        return section;
    }

    // Setters (if needed)
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
