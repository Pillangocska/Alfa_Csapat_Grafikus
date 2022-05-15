package GUI.view.menus;

import javax.swing.*;
import java.awt.*;

public class NiceMenuItem extends JMenuItem {
    public NiceMenuItem(Action a) {
        super(a);
        setForeground(Color.RED);
        setBackground(Color.BLACK);
        setBorderPainted(false);
        setBorder(BorderFactory.createEmptyBorder(0,0 ,0 , 0));
    }
}
