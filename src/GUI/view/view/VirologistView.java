package GUI.view.view;

import GUI.view.frames.GameFrame;
import main.com.teamalfa.blindvirologists.turn_handler.Game;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;
import main.com.teamalfa.blindvirologists.virologist.Virologist;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class VirologistView extends JButton implements View, ActionListener {
    private final int iconWidth = 896 / 20;
    private final int iconHeight = 1196 / 20;
    private Virologist virologist;
    private boolean isHighlighted;
    private static HashMap<Virologist, String> colorMap = new HashMap<Virologist, String>();
    private static final String colors[] = new String[] {"blue", "green", "grey", "orange", "red", "yellow"};

    public VirologistView(Virologist virologist){
        if (colorMap.get(virologist) == null)
            colorMap.put(virologist, colors[colorMap.size()]);
        setLayout(null);
        this.virologist = virologist;
        setPreferredSize(new Dimension(iconWidth, iconHeight));
        handleIcon();
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        this.addActionListener(this);
    }

    @Override
    public void update() {

    }

    @Override
    public void onClick() {

    }

    private void handleIcon(){
        // ezt itt borzaszto szar igy nezzetek el
        String color = colorMap.get(virologist);
        ImageIcon icon;
        if(TurnHandler.GetOrder().contains(virologist)) {
            String iconPath = "resources/virologist/" + color + "_virologist";
            if (isHighlighted)
                iconPath = iconPath + "_glowing";
            iconPath = iconPath + ".png";
            icon = new ImageIcon(iconPath);
        }
        else {
            icon = new ImageIcon("resources/virologist/bear.png");
        }
        Image img = icon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        JLabel thumb = new JLabel();
        thumb.setIcon(icon);
        thumb.setBounds(0,0,iconWidth,iconHeight);
        removeAll();
        add(thumb);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isHighlighted)
            GameFrame.setHighlightedVirologistView(null);
        else
            GameFrame.setHighlightedVirologistView(this);
    }

    public void setHighlighted(boolean value) {
        isHighlighted = value;
        handleIcon();
    }

    public Virologist getVirologist() {
        return virologist;
    }
}
