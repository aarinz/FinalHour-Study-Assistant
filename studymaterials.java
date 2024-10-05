import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;


public class studymaterials extends JPanel {

    public studymaterials(JPanel mainPanel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.black);
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));

        JLabel titleLabel = new JLabel("Study Materials", JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("FiraCode Nerd Font Mono SemBd", Font.PLAIN, 35));
        titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JButton uploadImportantButton = new JButton("Upload File");
        JButton uploadFormulaButton = new JButton("Upload File");
        JButton uploadLeftoverButton = new JButton("Upload File");

        JButton clearImportantButton = new JButton("Clear Files");
        JButton clearFormulaButton = new JButton("Clear Files");
        JButton clearLeftoverButton = new JButton("Clear Files");

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

        styleButton(uploadImportantButton);
        styleButton(uploadFormulaButton);
        styleButton(uploadLeftoverButton);
        styleButton(clearImportantButton);
        styleButton(clearFormulaButton);
        styleButton(clearLeftoverButton);
        styleButton(backButton);

        add(Box.createRigidArea(new Dimension(0, 20)));
        add(titleLabel);
        add(Box.createRigidArea(new Dimension(0, 50)));
        addSection("Important Portion", uploadImportantButton, clearImportantButton);
        add(Box.createRigidArea(new Dimension(0, 30)));
        addSection("Formulas", uploadFormulaButton, clearFormulaButton);
        add(Box.createRigidArea(new Dimension(0, 30)));
        addSection("Leftover Portion", uploadLeftoverButton, clearLeftoverButton);
        add(Box.createRigidArea(new Dimension(0, 50)));
        add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) mainPanel.getLayout()).show(mainPanel, "main");
            }
        });
    }

    
    private void styleButton(JButton button) {
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setAlignmentX(JButton.CENTER_ALIGNMENT);
    }
    private void addSection(String sectionTitle, JButton uploadButton, JButton clearButton) {
        JLabel sectionLabel = new JLabel(sectionTitle, JLabel.LEFT);
        sectionLabel.setForeground(Color.WHITE);
        sectionLabel.setFont(new Font("Arial", Font.PLAIN, 25));

        sectionLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        uploadButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        clearButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

        add(sectionLabel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(uploadButton);
        add(clearButton);
    }
}
