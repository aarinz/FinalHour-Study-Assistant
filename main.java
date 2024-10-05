import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.CardLayout; 
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  

public class main {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        JPanel mainPanel = new JPanel(new CardLayout());

        JLabel label = new JLabel("FinalHour Study Assistant");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("FiraCode Nerd Font Mono SemBd", Font.PLAIN, 35));
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.black);

        JButton materialButton = new JButton("Study Materials");
        JButton checklistButton = new JButton("Manage Checklist");
        JButton pomodoroButton = new JButton("Pomodoro Timer");
        JButton reminderButton = new JButton("Set Reminders");
        Font buttonFont = new Font("Arial", Font.PLAIN, 20);
        Color bgColor = Color.BLACK;
        Color textColor = Color.WHITE;

        styleButton(materialButton, buttonFont, bgColor, textColor);
        styleButton(checklistButton, buttonFont, bgColor, textColor);
        styleButton(pomodoroButton, buttonFont, bgColor, textColor);
        styleButton(reminderButton, buttonFont, bgColor, textColor);

        //gap
        buttonPanel.add(materialButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(checklistButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(pomodoroButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(reminderButton);

        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setBackground(Color.black);
        mainContent.add(Box.createRigidArea(new Dimension(0, 20)));
        mainContent.add(label);
        mainContent.add(Box.createRigidArea(new Dimension(0, 50)));
        mainContent.add(buttonPanel);

        studymaterials studyMaterialsPanel = new studymaterials(mainPanel); 
        mainPanel.add(mainContent, "main"); 
        mainPanel.add(studyMaterialsPanel, "studyMaterials"); 


        materialButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "studyMaterials"); 
            }
        });

        checklistButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "checklist"); // Show checklist panel
            }
        });

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setTitle("FinalHour Study Assistant");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 720); //size
        
        frame.setContentPane(mainPanel); 
        ImageIcon image = new ImageIcon("FH.png");
        frame.setIconImage(image.getImage());
        frame.getContentPane().setBackground(Color.black);
        frame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));

        frame.setVisible(true);
    }

    private static void styleButton(JButton button, Font font, Color bgColor, Color textColor) {
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFont(font);
        button.setAlignmentX(JButton.CENTER_ALIGNMENT);
    }
}
