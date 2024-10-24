package Codes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Checklist extends JPanel {
    private DefaultListModel<Task> listModel;
    private JPanel taskPanel;
    private JTextField taskField;

    public Checklist(JPanel mainPanel) {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.BLACK);
        JLabel titleLabel = new JLabel("CHECKLIST");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("FiraCode Nerd Font Mono SemBd", Font.PLAIN, 35));
        headerPanel.add(titleLabel);

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.BLACK);
        inputPanel.setLayout(new FlowLayout());

        taskField = new JTextField(20);
        taskField.setPreferredSize(new Dimension(250, 30));
        inputPanel.add(taskField);

        JButton addButton = new JButton("+");
        addButton.setBackground(Color.BLACK);
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Arial", Font.PLAIN, 20));
        inputPanel.add(addButton);

        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.PLAIN, 20));
        backButton.setPreferredSize(new Dimension(100, 30));
        
        backButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainPanel.getLayout();
            cl.show(mainPanel, "main");
        });

        add(headerPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(new BorderLayout());

        buttonPanel.add(inputPanel, BorderLayout.CENTER);
        buttonPanel.add(backButton, BorderLayout.SOUTH);

        add(buttonPanel, BorderLayout.CENTER);

        listModel = new DefaultListModel<>();
        taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        taskPanel.setBackground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setPreferredSize(new Dimension(350, 300));

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.BLACK);
        centerPanel.add(scrollPane);

        add(centerPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            String taskDescription = taskField.getText();
            if (!taskDescription.isEmpty()) {
                addTask(new Task(taskDescription));
                taskField.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a task.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void addTask(Task task) {
        listModel.addElement(task);

        JPanel taskItem = new JPanel(new FlowLayout(FlowLayout.LEFT));
        taskItem.setBackground(Color.BLACK);

        JLabel taskLabel = new JLabel(task.getDescription());
        taskLabel.setForeground(Color.WHITE);
        taskLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton removeButton = new JButton("Remove");
        removeButton.setBackground(Color.RED);
        removeButton.setForeground(Color.WHITE);
        removeButton.setFont(new Font("Arial", Font.PLAIN, 12));
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.removeElement(task);
                taskPanel.remove(taskItem);
                taskPanel.revalidate();
                taskPanel.repaint();
            }
        });

        taskItem.add(taskLabel);
        taskItem.add(removeButton);

        taskPanel.add(taskItem);
        taskPanel.revalidate();
        taskPanel.repaint();
    }

    private static class Task {
        private String description;

        public Task(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
