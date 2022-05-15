package main.GUI.view.view.equipmentView;

import main.GUI.view.view.View;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class EquipmentView extends JButton implements View, ActionListener {
    @Override
    public void update() {

    }

    @Override
    public void onClick() {

    }

    protected abstract void handleIcon();
}
