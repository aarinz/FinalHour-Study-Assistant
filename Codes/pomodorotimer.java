package Codes;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class pomodorotimer extends JPanel {
    private JLabel timerLabel;
    private JButton startButton, stopButton, resetButton, backButton;
    private Timer timer;
    private int timeLeft; 
    private boolean isRunning;

    public pomodorotimer(JPanel mainPanel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.BLACK);
        
        timerLabel = new JLabel("25:00");
        timerLabel.setFont(new Font("FiraCode Nerd Font Mono SemBd", Font.PLAIN, 60));
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setAlignmentX(CENTER_ALIGNMENT);
        
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");
        backButton = new JButton("Back");

        styleButton(startButton);
        styleButton(stopButton);
        styleButton(resetButton);
        styleButton(backButton);

        timeLeft = 1500; 
        isRunning = false;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeLeft > 0) {
                    timeLeft--;
                    updateTimerLabel();
                } else {
                    timer.stop();
                    isRunning = false;
                    JOptionPane.showMessageDialog(null, "Time's up!");
                }
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isRunning) {
                    timer.start();
                    isRunning = true;
                }
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRunning) {
                    timer.stop();
                    isRunning = false;
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                timeLeft = 1500; 
                isRunning = false;
                updateTimerLabel();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "main"); 
            }
        });

        add(Box.createRigidArea(new Dimension(0, 50)));
        add(timerLabel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(startButton);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(stopButton);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(resetButton);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(backButton);
        add(Box.createRigidArea(new Dimension(0, 50)));
    }

    private void updateTimerLabel() {
        int minutes = timeLeft / 60;
        int seconds = timeLeft % 60;
        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    private void styleButton(JButton button) {
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setAlignmentX(CENTER_ALIGNMENT);
    }
}
