package GUI.view.view.fieldView;

import GUI.view.view.ElementView;
import main.com.teamalfa.blindvirologists.city.fields.StoreHouse;

import java.awt.*;

public class StoreHouseView extends FieldView {
    private ElementView elementv;
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
}
