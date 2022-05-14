package GUI.view.view.fieldView;

import GUI.view.view.ElementView;
import GUI.view.view.VirologistView;
import main.com.teamalfa.blindvirologists.city.fields.StoreHouse;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;
import main.com.teamalfa.blindvirologists.virologist.Virologist;

import java.awt.*;

public class StoreHouseView extends FieldView {
    private ElementView elementView;
    private StoreHouse storeHouse;

    public StoreHouseView(){
        color = Color.getHSBColor(0,250,140);
        newImage = Toolkit.getDefaultToolkit().createImage("resources/StoreHouse1.png");
        backGround = newImage.getScaledInstance(200,200,Image.SCALE_DEFAULT);
        this.text = "store";
        setFieldText("store");
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawPolygon(g2d,200/2, 200/2 ,1,color.getRGB(),true);
        if(TurnHandler.getActiveVirologist().getDiscoveredFields().contains(field))
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
        if(field.equals(TurnHandler.getActiveVirologist().getField())) {
            if(TurnHandler.getActiveVirologist().getDiscoveredFields().contains(field)) {
                for (Virologist virologist : field.getVirologists()) {
                    add(new VirologistView(virologist));
                }
                storeHouse = (StoreHouse) TurnHandler.getActiveVirologist().getField();
                elementView = new ElementView(storeHouse.getElements());
                add(elementView);
            }
            else {
                add(new VirologistView(TurnHandler.getActiveVirologist()));
            }
        }
    }
}
