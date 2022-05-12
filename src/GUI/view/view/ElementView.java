package GUI.view.view;

import main.com.teamalfa.blindvirologists.virologist.backpack.ElementBank;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ElementView extends JButton implements View, ActionListener {
    protected ElementBank eb;

    public ElementView(ElementBank elementBank) {
        eb = elementBank;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void update() {

    }

    @Override
    public void onClick() {

    }
}
