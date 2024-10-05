import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class checklist extends JPanel {
    private DefaultListModel<String> listModel; // Model to hold the tasks
    private JList<String> taskList; // List to display tasks
    private JTextField taskField; // Input field for new tasks

    public checklist(JPanel mainPanel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.BLACK);
        
        JLabel titleLabel = new JLabel("CHECKLIST");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("FiraCode Nerd Font Mono SemBd", Font.PLAIN, 35));
        titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        taskField = new JTextField();
        taskField.setPreferredSize(new Dimension(200, 30));
        
        JButton addButton = new JButton("+");
        addButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        addButton.setBackground(Color.BLACK);
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Arial", Font.PLAIN, 20));
        
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setForeground(Color.WHITE);
        taskList.setBackground(Color.DARK_GRAY);
        
        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setPreferredSize(new Dimension(300, 200));

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.PLAIN, 20));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "main"); // Show main panel
            }
        });

        add(titleLabel);
        add(taskField);
        add(addButton);
        add(scrollPane);
        add(backButton);
        
        // Action listener for adding tasks
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = taskField.getText();
                if (!task.isEmpty()) {
                    listModel.addElement(task); // Add task to the model
                    taskField.setText(""); // Clear input field
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a task.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Adding an action listener for checking off tasks
        taskList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedTask = taskList.getSelectedValue();
                if (selectedTask != null) {
                    // Confirm before removing the task
                    int result = JOptionPane.showConfirmDialog(this, "Mark this task as completed?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        listModel.removeElement(selectedTask); // Remove the completed task from the list
                    }
                }
            }
        });
    }
}
