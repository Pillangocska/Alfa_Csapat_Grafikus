package GUI.view.view.fieldView;

import GUI.view.view.VirologistView;
import GUI.view.view.equipmentView.*;
import main.com.teamalfa.blindvirologists.city.fields.SafeHouse;
import main.com.teamalfa.blindvirologists.equipments.Bag;
import main.com.teamalfa.blindvirologists.equipments.Cloak;
import main.com.teamalfa.blindvirologists.equipments.Equipment;
import main.com.teamalfa.blindvirologists.equipments.active_equipments.Axe;
import main.com.teamalfa.blindvirologists.equipments.active_equipments.Gloves;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;
import main.com.teamalfa.blindvirologists.virologist.Virologist;

import java.awt.*;
import java.util.ArrayList;

public class SafeHouseView extends FieldView{
    private ArrayList<EquipmentView> equipments = new ArrayList<>();
    private SafeHouse safeHouse;
    public SafeHouseView(){
        color = Color.getHSBColor(0,250,0);
        newImage = Toolkit.getDefaultToolkit().createImage("resources/SafeHouse1.png");
        backGround = newImage.getScaledInstance(200,200,Image.SCALE_DEFAULT);
        this.text = "safe";
        setFieldText("safe");
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawPolygon(g2d,200/2, 200/2 ,1,color.getRGB(),true);
        g.drawImage(backGround,0,0,null);
        this.repaint();
    }

    public void setField(SafeHouse safeh) {
        field = safeh;
    }

    @Override
    public void update() {
        // remove all components from field
        removeAll();

        // and update only if its current field
        if(field == TurnHandler.getActiveVirologist().getField()) {
            for (Virologist virologist : field.getVirologists()) {
                add(new VirologistView(virologist));
            }
            safeHouse = (SafeHouse) TurnHandler.getActiveVirologist().getField();
            for(Equipment equipment : safeHouse.getEquipments()){
                if(equipment.getName().equals("axe"))
                    add(new AxeView((Axe) equipment));
                if(equipment.getName().equals("bag"))
                    add(new BagView((Bag) equipment));
                if(equipment.getName().equals("cloak"))
                    add(new CloakView((Cloak) equipment));
                if(equipment.getName().equals("glove"))
                    add(new GlovesView((Gloves) equipment));
            }
        }
    }
}
