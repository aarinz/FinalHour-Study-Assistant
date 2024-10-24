package Codes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class Reminder extends JPanel {
    private JTextField taskField;
    private JTextField timeField; 
    private DefaultListModel<task> listModel;

    public Reminder(JPanel mainPanel, DefaultListModel<task> taskListModel) {
        this.listModel = taskListModel; 
        initializeUI(mainPanel);
    }

    private void initializeUI(JPanel mainPanel) {
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        JLabel titleLabel = new JLabel("Set Reminder");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("FiraCode Nerd Font Mono SemBd", Font.PLAIN, 35));
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridy++;
        JLabel taskLabel = new JLabel("Task: ");
        taskLabel.setForeground(Color.WHITE);
        add(taskLabel, gbc);

        taskField = new JTextField(20);
        styleTextField(taskField);
        gbc.gridx = 1;
        add(taskField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel timeLabel = new JLabel("Reminder Time (HH:mm): ");
        timeLabel.setForeground(Color.WHITE);
        add(timeLabel, gbc);

        timeField = new JTextField("HH:mm", 5);
        styleTextField(timeField);
        gbc.gridx = 1;
        add(timeField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JButton setReminderButton = new JButton("Set Reminder");
        styleButton(setReminderButton);
        add(setReminderButton, gbc);

        gbc.gridx = 1;
        JButton backButton = new JButton("Back");
        styleButton(backButton);
        add(backButton, gbc);

        setReminderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskDescription = taskField.getText().trim();
                String timeText = timeField.getText().trim();
                
                try {
                    LocalTime reminderTime = LocalTime.parse(timeText, DateTimeFormatter.ofPattern("HH:mm"));
                    task newTask = new task(taskDescription, false, reminderTime); 
                    listModel.addElement(newTask); 
                    scheduleReminder(newTask);
                    taskField.setText("");
                    timeField.setText("HH:mm");
                    JOptionPane.showMessageDialog(null, "Reminder set for " + reminderTime);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid time format. Please use HH:mm.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "main");
            }
        });
    }

    private void styleButton(JButton button) {
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); 
        button.setBorderPainted(true);
        button.setFocusPainted(false); 
        button.setContentAreaFilled(false); 
        button.setOpaque(true); 
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.WHITE, 2), 
            BorderFactory.createEmptyBorder(10, 20, 10, 20) 
        ));
    }

    private void styleTextField(JTextField textField) {
        textField.setMaximumSize(new Dimension(400, 30));
        textField.setBackground(Color.WHITE); // Set background to white
        textField.setForeground(Color.BLACK); // Set text color to black
        textField.setCaretColor(Color.BLACK); // Change caret color to black
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
    }

    private void scheduleReminder(task task) {
        Timer timer = new Timer();
        LocalTime reminderTime = task.getReminderTime();
        LocalTime now = LocalTime.now();
        long delay = java.time.Duration.between(now, reminderTime).toMillis();

        if (delay < 0) {
            delay += 24 * 60 * 60 * 1000; 
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(null, "Reminder: " + task.getDescription(), "Reminder", JOptionPane.INFORMATION_MESSAGE);
            }
        }, delay);
    }
}
