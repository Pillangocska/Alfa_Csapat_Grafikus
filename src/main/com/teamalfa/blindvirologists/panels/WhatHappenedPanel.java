package main.com.teamalfa.blindvirologists.panels;

import main.com.teamalfa.blindvirologists.frames.View;

import javax.swing.*;
import java.awt.*;

public class WhatHappenedPanel extends JPanel implements View {
    private String log;
    private TextArea textArea;

    public WhatHappenedPanel(){
        textArea = new TextArea();
        this.setLayout(null);
        this.setVisible(true);
        this.add(textArea);
        this.repaint();
    }

    public String getLog(){ return log; }

    public void logOnPanel(String message){
        this.log += message;
    }
    @Override
    public void update() {

    }

    @Override
    public void onClick() {/*doesn't do anything*/}
}
