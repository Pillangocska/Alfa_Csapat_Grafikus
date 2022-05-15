package main.GUI.view.view.geneticCodeView;

import main.logic.com.teamalfa.blindvirologists.agents.genetic_code.AmnesiaCode;
import main.logic.com.teamalfa.blindvirologists.turn_handler.TurnHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AmnesiaCodeView extends GeneticCodeView {
    private final AmnesiaCode code;
    private final int iconWidth = 896 / 20;
    private final int iconHeight = 1196 / 20;

    public AmnesiaCodeView(AmnesiaCode code) {
        super(code);
        setLayout(null);
        this.code = code;
        setPreferredSize(new Dimension(iconWidth, iconHeight));
        handleIcon();
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        this.addActionListener(this);
    }

    @Override
    protected void handleIcon() {
        ImageIcon icon = new ImageIcon("resources/amnesiavirus.gif");
        JLabel thumb = new JLabel();
        thumb.setIcon(icon);
        thumb.setBounds(0,0, iconWidth, iconHeight);
        add(thumb);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TurnHandler.getActiveVirologist().learn(code);
    }
}
