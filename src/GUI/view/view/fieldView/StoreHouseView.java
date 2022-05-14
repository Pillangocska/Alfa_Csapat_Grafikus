package GUI.view.view.fieldView;

import GUI.view.view.ElementView;
import GUI.view.view.VirologistView;
import GUI.view.view.equipmentView.AxeView;
import GUI.view.view.equipmentView.BagView;
import GUI.view.view.equipmentView.CloakView;
import GUI.view.view.equipmentView.GlovesView;
import main.com.teamalfa.blindvirologists.city.fields.SafeHouse;
import main.com.teamalfa.blindvirologists.city.fields.StoreHouse;
import main.com.teamalfa.blindvirologists.equipments.Bag;
import main.com.teamalfa.blindvirologists.equipments.Cloak;
import main.com.teamalfa.blindvirologists.equipments.Equipment;
import main.com.teamalfa.blindvirologists.equipments.active_equipments.Axe;
import main.com.teamalfa.blindvirologists.equipments.active_equipments.Gloves;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;
import main.com.teamalfa.blindvirologists.virologist.Virologist;

import java.awt.*;

public class StoreHouseView extends FieldView {
    private ElementView elementView;
    private StoreHouse storeHouse;

    public StoreHouseView(){
        color = Color.getHSBColor(0,250,0);
        newImage = Toolkit.getDefaultToolkit().createImage("resources/StoreHouse1.png");
        backGround = newImage.getScaledInstance(200,200,Image.SCALE_DEFAULT);
        this.text = "store";
        setFieldText("store");
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawPolygon(g2d,200/2, 200/2 ,1,color.getRGB(),true);
        g.drawImage(backGround,0,0,null);
        this.repaint();
    }

    public void setField(StoreHouse storeh) {
        field = storeh;
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
            storeHouse = (StoreHouse) TurnHandler.getActiveVirologist().getField();
            elementView = new ElementView(storeHouse.getElements());
        }
    }
}
