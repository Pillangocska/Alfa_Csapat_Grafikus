package main.com.teamalfa.blindvirologists.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpFrame extends JFrame implements ActionListener {
    JButton backButton;
    HelpFrame(){
        this.setTitle("Help");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(300,700);
        ImageIcon imageIcon = new ImageIcon("logo.png");
        this.setIconImage(imageIcon.getImage());
        this.getContentPane().setBackground(new Color(0,0,0));

        //back button to go back to the menu
        backButton = new JButton();
        backButton.addActionListener(this);
        backButton.setText("Back");
        backButton.setFont(new Font("Bauhaus 93",Font.PLAIN,30));
        backButton.setForeground(Color.RED);
        backButton.setBackground(Color.BLACK);
        backButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        backButton.setFocusPainted(false);
        backButton.setBounds(0,0,100,50);

        this.setLayout(null);
        this.setVisible(true);
        this.add(backButton);
        this.repaint();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            this.dispose();
        }
    }
}
