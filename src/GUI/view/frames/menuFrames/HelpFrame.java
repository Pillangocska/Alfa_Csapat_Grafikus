package GUI.view.frames.menuFrames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * On this frame the player can learn how to play
 * the game and learn the rules
 */
public class HelpFrame extends JFrame implements ActionListener {
    /**
     * Closes this window and goes back
     * to the main menu
     */
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

        //Textarea for how to play the game
        TextArea textArea = new TextArea();
        textArea.setForeground(Color.RED);
        textArea.setBackground(Color.CYAN);
        textArea.setFont(new Font("Viner Hand ITC",Font.PLAIN,10));
        textArea.setBounds(0,50,285,610);

        this.setLayout(null);
        this.setVisible(true);
        this.add(backButton);
        this.add(textArea);
        this.repaint();
    }

    /**
     * Upon clicking the "back" button the player
     * goes back to the main menu
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            this.dispose();
        }
    }
}
