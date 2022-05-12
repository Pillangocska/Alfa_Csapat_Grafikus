package GUI.view.panels;

import GUI.view.view.equipmentView.*;
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
    private EquipmentPocket equipmentPocket;

    public EquipmentPanel(EquipmentPocket equipmentPocket) {
        super(equipmentPocket.getMaxSize());
        this.equipmentPocket = equipmentPocket;
        setLayout(new GridBagLayout());

        // creating the layout of the panel. creating constraints that will later be manipulated at each element
        GridBagConstraints constraints = new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                GridBagConstraints.PAGE_START, GridBagConstraints.NONE, new Insets(3, 0, 3, 0), 0, 0);

        // creating the title of the panel
        JLabel title = new JLabel();
        title.setText("Equipments"); // the correct name would be "Pieces of Equipment"
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Viner Hand ITC", Font.PLAIN, 12));
        title.setForeground(Color.RED);

        add(title, constraints);
        update();
    }

    @Override
    /**
     *
     */
    public void update() {
        // set the number of slots this panel is going to have
        setViewCount(equipmentPocket.getMaxSize());
        // create the slots and fill them up according to the agent pocket
        views = new ArrayList<EquipmentView>();
        ArrayList<Equipment> equipments = equipmentPocket.getEquipmentHolder();
        int i = 0;
        for (i = i; i < equipments.size(); i++) {
            Equipment eq = equipments.get(i);
            if (eq instanceof Gloves) views.add(new GlovesView((Gloves) eq));
            if (eq instanceof Cloak) views.add(new CloakView((Cloak) eq));
            if (eq instanceof Axe) views.add(new AxeView((Axe) eq));
            if (eq instanceof Bag) views.add(new BagView((Bag) eq));
        }

        // fill the rest with empty slots
        for (i = i; i < getViewCount(); i++) {
            views.add(new EmptyEquipmentInventorySlotView());
        }
    }
}
