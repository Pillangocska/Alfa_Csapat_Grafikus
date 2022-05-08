package GUI.view.panels;


import GUI.view.view.View;

import javax.swing.*;
import java.awt.*;

public class WhatHappenedPanel extends JPanel implements View {
    private static TextArea textArea;

    public WhatHappenedPanel(){
        textArea = new TextArea();
        textArea.setForeground(Color.RED);
        textArea.setBackground(Color.BLACK);
        textArea.setFont(new Font("Viner Hand ITC",Font.PLAIN,10));
        textArea.append("Game started...");
        textArea.setEditable(false);
        textArea.setBounds(0,0,1000,1000);

        this.setBackground(Color.BLUE);
        this.setLayout(null);
        this.setVisible(true);
        this.add(textArea);
        this.repaint();
    }

    public static void logOnPanel(String message){
        textArea.append("\n"+message);
    }
    @Override
    public void update() {/*doesn't do anything yet*/}

    @Override
    public void onClick() {/*doesn't do anything*/}
}
