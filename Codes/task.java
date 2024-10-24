package Codes;
import java.time.LocalTime;

public class task {
    private String description;
    private boolean completed;
    private LocalTime reminderTime;

    public task(String description, boolean completed, LocalTime reminderTime) {
        this.description = description;
        this.completed = completed;
        this.reminderTime = reminderTime;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void toggleCompleted() {
        completed = !completed;
    }

    public LocalTime getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(LocalTime reminderTime) {
        this.reminderTime = reminderTime;
    }
}
