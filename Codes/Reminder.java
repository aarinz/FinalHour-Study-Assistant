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

    // Instance variable to hold the countdown timer
    private Timer countdownTimer;

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
        JLabel timeLabel = new JLabel("Reminder Time (MM:SS): ");
        timeLabel.setForeground(Color.WHITE);
        add(timeLabel, gbc);

        timeField = new JTextField("MM:SS", 5);
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
                    String[] timeParts = timeText.split(":");
                    int minutes = Integer.parseInt(timeParts[0]);
                    int seconds = Integer.parseInt(timeParts[1]);
                    int totalSeconds = minutes * 60 + seconds;

                    task newTask = new task(taskDescription, false, LocalTime.now()); 
                    listModel.addElement(newTask); 

                    startCountdown(totalSeconds, taskDescription);
                    taskField.setText("");
                    timeField.setText("MM:SS");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid time format. Please use MM:SS.", "Error", JOptionPane.ERROR_MESSAGE);
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

    private void startCountdown(int totalSeconds, String taskDescription) {
        if (countdownTimer != null) {
            countdownTimer.cancel(); // Cancel any existing timer
        }

        countdownTimer = new Timer();
        countdownTimer.scheduleAtFixedRate(new TimerTask() {
            int remainingTime = totalSeconds;

            @Override
            public void run() {
                if (remainingTime <= 0) {
                    countdownTimer.cancel(); // Stop the timer
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(null, "Reminder: " + taskDescription, "Reminder Alert", JOptionPane.INFORMATION_MESSAGE);
                    });
                } else {
                    remainingTime--;
                    int minutes = remainingTime / 60;
                    int seconds = remainingTime % 60;
                    System.out.println(String.format("Time remaining: %02d:%02d", minutes, seconds)); // Optional: show remaining time in console
                }
            }
        }, 0, 1000); // Update every second
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
}
