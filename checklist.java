import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class checklist {
    private JPanel panel;
    private ArrayList<JCheckBox> tasks;
    private ArrayList<String> completedTasks;
    private JPanel mainPanel; 

    public checklist(JPanel mainPanel) {
        this.mainPanel = mainPanel; 
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.black);

        tasks = new ArrayList<>();
        completedTasks = new ArrayList<>();

        setupUI();
    }

    private void setupUI() {
        JLabel titleLabel = new JLabel("CHECKLIST");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 35));

        JButton addButton = new JButton("+");
        styleButton(addButton);

        JTextField taskField = new JTextField(20);
        taskField.setMaximumSize(new Dimension(300, 30));
        taskField.setVisible(false); // to hide the taskField

        addButton.addActionListener(e -> taskField.setVisible(true)); 
        // to add task on enter
        taskField.addActionListener(e -> {
            String newTask = taskField.getText();
            if (!newTask.isEmpty()) {
                addTask(newTask);
                taskField.setText("");
                taskField.setVisible(false); // hide the field after adding
            }
        });

        
        JButton backButton = new JButton("Back");
        styleButton(backButton);
        backButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainPanel.getLayout();
            cl.show(mainPanel, "main"); 
        });
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(taskField);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(addButton);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(backButton);
    }

    private void addTask(String taskName) {
        JCheckBox taskCheckBox = new JCheckBox(taskName);
        taskCheckBox.setBackground(Color.black);
        taskCheckBox.setForeground(Color.white);
        taskCheckBox.setFont(new Font("Arial", Font.PLAIN, 20));

        taskCheckBox.addActionListener(e -> {
            if (taskCheckBox.isSelected()) {
                taskCheckBox.setFont(new Font("Arial", Font.PLAIN, 20));
                taskCheckBox.setForeground(Color.gray);
                taskCheckBox.setText("<html><strike>" + taskName + "</strike></html>");
                moveCompletedTask(taskCheckBox);
            } else {
                taskCheckBox.setFont(new Font("Arial", Font.PLAIN, 20));
                taskCheckBox.setForeground(Color.white);
                taskCheckBox.setText(taskName);
                moveUncompletedTask(taskCheckBox);
            }
        });

        tasks.add(taskCheckBox); 
        panel.add(taskCheckBox);
        panel.revalidate();
        panel.repaint();
    }

    private void moveCompletedTask(JCheckBox task) {
        panel.remove(task);
        panel.add(task, panel.getComponentCount() - 2); 
        panel.revalidate();
        panel.repaint();
    }

    private void moveUncompletedTask(JCheckBox task) {
        panel.remove(task);
        panel.add(task, 2);
        panel.revalidate();
        panel.repaint();
    }

    private void styleButton(JButton button) {
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
    }

    public JPanel getPanel() {
        return panel;
    }
}
