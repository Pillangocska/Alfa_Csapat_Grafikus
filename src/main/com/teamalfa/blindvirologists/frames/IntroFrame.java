package main.com.teamalfa.blindvirologists.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class IntroFrame extends JFrame implements ActionListener {
    JButton startButton;
    JComboBox difficultyBox;
    JComboBox playerNumberBox;

    IntroFrame(){
        this.setTitle("Game Parameters");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(277,240);
        ImageIcon imageIcon = new ImageIcon("logo.png");
        this.setIconImage(imageIcon.getImage());
        this.getContentPane().setBackground(new Color(0,0,0));

        //play button to start the game
        startButton = new JButton();
        startButton.addActionListener(this);
        startButton.setText("Start Game");
        startButton.setFont(new Font("Bauhaus 93",Font.PLAIN,30));
        startButton.setForeground(Color.RED);
        startButton.setBackground(Color.BLACK);
        startButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        startButton.setFocusPainted(false);
        startButton.setBounds(35,0,200,50);

        JLabel difLabel = new JLabel("Select Difficulty:");
        difLabel.setForeground(Color.RED);
        difLabel.setFont(new Font("Bauhaus 93",Font.PLAIN,20));
        difLabel.setBackground(Color.BLACK);
        difLabel.setOpaque(true);
        difLabel.setBounds(0,100,150,40);

        JLabel playerLabel = new JLabel("Select Players:");
        playerLabel.setForeground(Color.RED);
        playerLabel.setFont(new Font("Bauhaus 93",Font.PLAIN,20));
        playerLabel.setBackground(Color.BLACK);
        playerLabel.setOpaque(true);
        playerLabel.setBounds(0,150,150,40);

        //Combo box to select the difficulty
        String[] difficulties = {"Easy", "Normal", "Hard"};
        difficultyBox = new JComboBox(difficulties);
        difficultyBox.setBounds(160,100,100,30);
        difficultyBox.setFont(new Font("Bauhaus 93",Font.PLAIN,15));
        difficultyBox.setForeground(Color.RED);
        difficultyBox.setBackground(Color.BLACK);
        difficultyBox.setSelectedIndex(1);
        difficultyBox.addActionListener(this);

        //Combo box to select the number of players
        Integer[] numberOfPlayers = {1,2,3,4,5,6};
        playerNumberBox = new JComboBox(numberOfPlayers);
        playerNumberBox.setBounds(160,150,100,30);
        playerNumberBox.setFont(new Font("Bauhaus 93",Font.PLAIN,15));
        playerNumberBox.setForeground(Color.RED);
        playerNumberBox.setBackground(Color.BLACK);
        playerNumberBox.setSelectedIndex(3);
        playerNumberBox.addActionListener(this);

        this.setLayout(null);
        this.setVisible(true);
        this.add(difLabel);
        this.add(playerLabel);
        this.add(startButton);
        this.add(difficultyBox);
        this.add(playerNumberBox);
        this.repaint();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton){
            String dif = Objects.requireNonNull(difficultyBox.getSelectedItem()).toString();
            //System.out.println(dif);
            int players = (int) playerNumberBox.getSelectedItem();
            //System.out.println(players);
            GameFrame gameFrame = new GameFrame(dif,players);
            this.dispose();
        }
    }
}
