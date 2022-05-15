package GUI.view.view.equipmentView;

import GUI.view.view.View;
import main.com.teamalfa.blindvirologists.equipments.Equipment;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
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
