package GUI.view.menus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EquipmentPopupMenu extends JPopupMenu {
    private JMenuItem toss;
    private JMenuItem equip;

    public EquipmentPopupMenu() {
        setOpaque(false);
        setBorderPainted(false);

        toss = new NiceMenuItem(new AbstractAction("Toss") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ittvok");
            }
        });

        equip = new NiceMenuItem(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("most meg itt");
            }
        });

        add(toss);

        add(equip);

    }
}
