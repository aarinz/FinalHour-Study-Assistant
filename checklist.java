import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class checklist extends JPanel {
    private DefaultListModel<Task> listModel;
    private JList<Task> taskList;
    private JTextField taskField;

    public checklist(JPanel mainPanel) {
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

        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setCellRenderer(new TaskRenderer());
        taskList.setFont(new Font("Arial", Font.PLAIN, 18));

        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setPreferredSize(new Dimension(350, 300));

        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.PLAIN, 20));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "main");
            }
        });

        add(headerPanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
        add(backButton, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskDescription = taskField.getText();
                if (!taskDescription.isEmpty()) {
                    listModel.addElement(new Task(taskDescription, false));
                    taskField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a task.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        taskList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Task selectedTask = taskList.getSelectedValue();
                if (selectedTask != null) {
                    selectedTask.toggleCompleted();
                    taskList.repaint();
                }
            }
        });
    }

    private static class Task {
        private String description;
        private boolean completed;

        public Task(String description, boolean completed) {
            this.description = description;
            this.completed = completed;
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
    }

    private class TaskRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Task task = (Task) value;
            JCheckBox checkBox = new JCheckBox(task.getDescription(), task.isCompleted());
            checkBox.setOpaque(false);
            checkBox.setForeground(task.isCompleted() ? new Color(192, 192, 192) : Color.WHITE);
            checkBox.setSelected(task.isCompleted());

            if (task.isCompleted()) {
                checkBox.setFont(checkBox.getFont().deriveFont(Font.ITALIC));
            } else {
                checkBox.setFont(checkBox.getFont().deriveFont(Font.PLAIN));
            }

            return checkBox;
        }
    }
}
