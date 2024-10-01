import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;


public class main {
    public static void main(String[] args) {
            
        JFrame frame = new JFrame();
        JLabel label = new JLabel();
        Border border = BorderFactory.createLineBorder(Color.WHITE, 3);

        label.setText("FinalHour Study Assistant");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("FiraCode Nerd Font Mono SemBd", Font.PLAIN, 35));
        
        label.setBorder(BorderFactory.createCompoundBorder(border, 
        BorderFactory.createEmptyBorder(20, 0, 0, 0))); // (top, left, bottom, right)

        frame.setTitle("FinalHour Study Assistant");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit on close
        frame.setSize(620, 620); // Set dimensions
        frame.setVisible(true);
        frame.add(label);

        ImageIcon image = new ImageIcon("FH.png"); // Logo
        frame.setIconImage(image.getImage());

        frame.getContentPane().setBackground(Color.black); // Background color
    }
}
