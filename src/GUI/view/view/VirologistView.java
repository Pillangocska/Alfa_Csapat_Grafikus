package GUI.view.view;

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

public class VirologistView extends JPanel implements View {
    private final int iconWidth = 896 / 20;
    private final int iconHeight = 1196 / 20;
    private Virologist virologist;
    public VirologistView(Virologist virologist){
        setLayout(null);
        this.virologist = virologist;
        setPreferredSize(new Dimension(iconWidth, iconHeight));
        handleIcon();
        setOpaque(false);
    }

    @Override
    public void update() {

    }

    @Override
    public void onClick() {

    }

    private void handleIcon(){
        // ezt itt borzaszto szar igy nezzetek el
        ImageIcon icon;
        if(TurnHandler.GetOrder().contains(virologist)) {
            icon = new ImageIcon("resources/virologist/virologist.png");
        }
        else {
            icon = new ImageIcon("resources/virologist/bear.png");
        }
        Image img = icon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        JLabel thumb = new JLabel();
        thumb.setIcon(icon);
        thumb.setBounds(0,0,iconWidth,iconHeight);
        add(thumb);
    }
}
