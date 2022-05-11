package GUI.view.view.fieldView;

import GUI.view.view.equipmentView.EquipmentView;
import main.com.teamalfa.blindvirologists.city.fields.SafeHouse;

import java.awt.*;
import java.util.ArrayList;

public class SafeHouseView extends FieldView{
    private ArrayList<EquipmentView> equipments = new ArrayList<>();

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
}
