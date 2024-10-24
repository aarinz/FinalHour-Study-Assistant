package Codes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class studymaterials extends JPanel {

    private JPanel importantSectionPanel, formulaSectionPanel, leftoverSectionPanel;
    private ArrayList<File> importantFiles, formulaFiles, leftoverFiles;
    private Map<File, JLabel> fileThumbnails;

    public studymaterials(JPanel mainPanel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.black);
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));

        importantSectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        formulaSectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftoverSectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        importantFiles = new ArrayList<>();
        formulaFiles = new ArrayList<>();
        leftoverFiles = new ArrayList<>();
        fileThumbnails = new HashMap<>();

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

        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

        styleButton(uploadImportantButton);
        styleButton(uploadFormulaButton);
        styleButton(uploadLeftoverButton);
        styleButton(clearImportantButton);
        styleButton(clearFormulaButton);
        styleButton(clearLeftoverButton);
        styleButton(saveButton);
        styleButton(loadButton);
        styleButton(backButton);

        add(Box.createRigidArea(new Dimension(0, 20)));
        add(titleLabel);
        add(Box.createRigidArea(new Dimension(0, 50)));
        addSection("Important Portion", uploadImportantButton, clearImportantButton, importantSectionPanel);
        add(Box.createRigidArea(new Dimension(0, 30)));
        addSection("Formulas", uploadFormulaButton, clearFormulaButton, formulaSectionPanel);
        add(Box.createRigidArea(new Dimension(0, 30)));
        addSection("Leftover Portion", uploadLeftoverButton, clearLeftoverButton, leftoverSectionPanel);
        add(Box.createRigidArea(new Dimension(0, 50)));

        JPanel saveLoadPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        saveLoadPanel.add(saveButton);
        saveLoadPanel.add(loadButton);
        saveLoadPanel.setBackground(Color.black);
        add(saveLoadPanel);

        add(backButton);
        
        backButton.addActionListener(e -> ((CardLayout) mainPanel.getLayout()).show(mainPanel, "main"));

        uploadImportantButton.addActionListener(e -> uploadFile(importantFiles, importantSectionPanel));
        uploadFormulaButton.addActionListener(e -> uploadFile(formulaFiles, formulaSectionPanel));
        uploadLeftoverButton.addActionListener(e -> uploadFile(leftoverFiles, leftoverSectionPanel));

        clearImportantButton.addActionListener(e -> clearFiles(importantFiles, importantSectionPanel));
        clearFormulaButton.addActionListener(e -> clearFiles(formulaFiles, formulaSectionPanel));
        clearLeftoverButton.addActionListener(e -> clearFiles(leftoverFiles, leftoverSectionPanel));
    }

    private void styleButton(JButton button) {
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setAlignmentX(JButton.CENTER_ALIGNMENT);
    }

    private void addSection(String sectionTitle, JButton uploadButton, JButton clearButton, JPanel sectionPanel) {
        JLabel sectionLabel = new JLabel(sectionTitle, JLabel.LEFT);
        sectionLabel.setForeground(Color.WHITE);
        sectionLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        sectionLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        sectionPanel.setPreferredSize(new Dimension(800, 100));
        sectionPanel.setBackground(Color.black);

        add(sectionLabel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(sectionPanel);
        add(uploadButton);
        add(clearButton);
    }

    private void uploadFile(ArrayList<File> fileList, JPanel sectionPanel) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File[] files = fileChooser.getSelectedFiles();
            for (File file : files) {
                fileList.add(file);
                displayFile(file, sectionPanel);
            }
        }
    }

    private void displayFile(File file, JPanel sectionPanel) {
        String fileName = file.getName().toLowerCase();
        
        if (fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
            ImageIcon thumbnailIcon = new ImageIcon(new ImageIcon(file.getPath()).getImage()
                    .getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            JLabel imageLabel = new JLabel(thumbnailIcon);
            sectionPanel.add(imageLabel);
            sectionPanel.revalidate();
            sectionPanel.repaint();
            
            fileThumbnails.put(file, imageLabel);

            imageLabel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    showResizableImage(file);
                }
            });
        } else if (fileName.endsWith(".pdf") || fileName.endsWith(".ppt") || fileName.endsWith(".pptx")) {
            JLabel fileLabel = new JLabel(file.getName());
            fileLabel.setForeground(Color.WHITE);
            sectionPanel.add(fileLabel);
            sectionPanel.revalidate();
            sectionPanel.repaint();

            fileThumbnails.put(file, fileLabel);

            fileLabel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    try {
                        Desktop.getDesktop().open(file);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
    }

    private void showResizableImage(File file) {
        ImageIcon fullSizeIcon = new ImageIcon(file.getPath());
        JLabel fullImageLabel = new JLabel(fullSizeIcon);
        JScrollPane scrollPane = new JScrollPane(fullImageLabel);
        scrollPane.setPreferredSize(new Dimension(1300, 1000));
        scrollPane.setViewportView(fullImageLabel);
        JOptionPane.showMessageDialog(null, scrollPane, "Full View", JOptionPane.PLAIN_MESSAGE);
    }

    private void clearFiles(ArrayList<File> fileList, JPanel sectionPanel) {
        fileList.clear();
        sectionPanel.removeAll();
        sectionPanel.revalidate();
        sectionPanel.repaint();
    }
}
