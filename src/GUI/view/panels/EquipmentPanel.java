package GUI.view.panels;

import GUI.view.view.InventorySlot;
import GUI.view.view.agentView.*;
import GUI.view.view.equipmentView.*;
import main.com.teamalfa.blindvirologists.agents.Agent;
import main.com.teamalfa.blindvirologists.agents.Vaccine;
import main.com.teamalfa.blindvirologists.agents.virus.AmnesiaVirus;
import main.com.teamalfa.blindvirologists.agents.virus.BearVirus;
import main.com.teamalfa.blindvirologists.agents.virus.DanceVirus;
import main.com.teamalfa.blindvirologists.agents.virus.ParalyzeVirus;
import main.com.teamalfa.blindvirologists.equipments.Bag;
import main.com.teamalfa.blindvirologists.equipments.Cloak;
import main.com.teamalfa.blindvirologists.equipments.Equipment;
import main.com.teamalfa.blindvirologists.equipments.active_equipments.Axe;
import main.com.teamalfa.blindvirologists.equipments.active_equipments.Gloves;
import main.com.teamalfa.blindvirologists.virologist.backpack.pockets.EquipmentPocket;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EquipmentPanel extends BaseBagPanel {
    private ArrayList<EquipmentView> views;
    private ArrayList<InventorySlot> slots;
    private EquipmentPocket equipmentPocket;

    /**
     * constructs a new equipment panel base on the parameter
     * @param equipmentPocket
     */
    public EquipmentPanel(EquipmentPocket equipmentPocket) {
        // initializing
        setLayout(new GridBagLayout());
        this.equipmentPocket = equipmentPocket;
        slots = new ArrayList<>();

        // creating inventory slots
        for (int i = 0; i < equipmentPocket.getMaxSize(); i++) {
            slots.add(new InventorySlot(null));
        }

        // the update method will create views and bind them to the slots
        update();

        // creating the layout of the panel
        GridBagConstraints constraints = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.PAGE_START, GridBagConstraints.NONE, new Insets(0, 0, 2, 0), 0, 0);

        // creating the title of the panel
        JLabel title = new JLabel("Equipments"); // todo this correctly called "Pieces of equipment" but that is long and making it multiline is difficult
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Viner Hand ITC", Font.PLAIN, 12));
        title.setForeground(Color.RED);

        add(title, constraints);

        // adding slots
        constraints.anchor = GridBagConstraints.CENTER;
        for(var s: slots) {
            constraints.gridy++;
            add(s, constraints);
        }
    }

    @Override
    /**
     * updates the view of the equipment panel: constructs views from pices of equipment found in the equipment pocket and binds them to the inventory slots.
     */
    public void update() {
        views = new ArrayList<>();
        ArrayList<Equipment> piecesOfEquipment = equipmentPocket.getEquipmentHolder();

        // creates a view for each agent found in the agent pocket
        for (var eq : piecesOfEquipment) {
            if (eq instanceof Gloves) {
                views.add(new GlovesView((Gloves) eq));
                continue;
            }
            if (eq instanceof Cloak) {
                views.add(new CloakView((Cloak) eq));
                continue;
            }
            if (eq instanceof Axe) {
                views.add(new AxeView((Axe) eq));
                continue;
            }
            if (eq instanceof Bag) {
                views.add(new BagView((Bag) eq));
                continue;
            }
        }

        // binds the views to slots
        int i = 0;
        for (i = i; i < views.size(); i++) {
            slots.get(i).setView(views.get(i));
        }

        // fill the rest with empty slots
        for(i = i; i < slots.size(); i++)
            slots.get(i).setView(null);
    }

    public void setEquipmentPocket(EquipmentPocket equipmentPocket) {
        this.equipmentPocket = equipmentPocket;
    }
}
