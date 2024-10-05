import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class reminder extends JPanel {
    private JTextField taskField;
    private JTextField timeField; 
    private DefaultListModel<task> listModel;

    public reminder(JPanel mainPanel, DefaultListModel<task> taskListModel) {
        this.listModel = taskListModel; 

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.BLACK);

        JLabel titleLabel = new JLabel("Set Reminder");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("FiraCode Nerd Font Mono SemBd", Font.PLAIN, 35));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);

        taskField = new JTextField(20);
        taskField.setMaximumSize(new Dimension(400, 30));
        timeField = new JTextField("HH:mm", 5);
        timeField.setMaximumSize(new Dimension(100, 30));

        JButton setReminderButton = new JButton("Set Reminder");
        styleButton(setReminderButton);

        JButton backButton = new JButton("Back");
        styleButton(backButton);

        setReminderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskDescription = taskField.getText();
                String timeText = timeField.getText();
                
                // Validate time input
                try {
                    LocalTime reminderTime = LocalTime.parse(timeText, DateTimeFormatter.ofPattern("HH:mm"));
                    task newTask = new task(taskDescription, false, reminderTime); 
                    listModel.addElement(newTask); // Add task with reminder

                    // Schedule reminder
                    scheduleReminder(newTask);
                    taskField.setText("");
                    timeField.setText("HH:mm");
                    JOptionPane.showMessageDialog(null, "Reminder set for " + reminderTime);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid time format. Please use HH:mm.");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "main"); // Show main panel
            }
        });

        add(Box.createRigidArea(new Dimension(0, 50)));
        add(titleLabel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(new JLabel("Task: "));
        add(taskField);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(new JLabel("Reminder Time (HH:mm): "));
        add(timeField);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(setReminderButton);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(backButton);
    }

    private void styleButton(JButton button) {
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void scheduleReminder(task task) {
        Timer timer = new Timer();
        LocalTime reminderTime = task.getReminderTime();
        LocalTime now = LocalTime.now();
        long delay = java.time.Duration.between(now, reminderTime).toMillis();

        if (delay < 0) {
            delay += 24 * 60 * 60 * 1000; // Adjust for next day 
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(null, "Reminder: " + task.getDescription(), "Reminder", JOptionPane.INFORMATION_MESSAGE);
            }
        }, delay);
    }
}
