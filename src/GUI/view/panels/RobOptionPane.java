package GUI.view.panels;

import GUI.view.view.agentView.AgentView;
import GUI.view.view.equipmentView.*;
import main.com.teamalfa.blindvirologists.equipments.Bag;
import main.com.teamalfa.blindvirologists.equipments.Cloak;
import main.com.teamalfa.blindvirologists.equipments.Equipment;
import main.com.teamalfa.blindvirologists.equipments.active_equipments.Axe;
import main.com.teamalfa.blindvirologists.equipments.active_equipments.Gloves;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;
import main.com.teamalfa.blindvirologists.virologist.Virologist;
import main.com.teamalfa.blindvirologists.virologist.backpack.Backpack;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RobOptionPane extends JOptionPane {


    public RobOptionPane(Backpack backpack) {
        if (backpack != null) {
            for (var eq : backpack.getEquipmentPocket().getEquipmentHolder()) {
                EquipmentView newView = null;
                if (eq instanceof Axe) {
                    newView = new AxeView((Axe) eq);
                } else if (eq instanceof Bag) {
                    newView = new BagView((Bag) eq);
                } else if (eq instanceof Cloak) {
                    newView = new CloakView((Cloak) eq);
                } else if (eq instanceof Gloves) {
                    newView = new GlovesView((Gloves) eq);
                }
                System.out.println("most itt vagyok");
                newView.addActionListener(e -> TurnHandler.getActiveVirologist().robEquipment(eq));
                add(newView);
            }
        }
    }
}
