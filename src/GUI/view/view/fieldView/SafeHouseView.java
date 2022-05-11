package GUI.view.view.fieldView;

import GUI.view.view.equipmentView.EquipmentView;
import main.com.teamalfa.blindvirologists.city.fields.SafeHouse;

import java.awt.*;
import java.util.ArrayList;

public class SafeHouseView extends FieldView{
    private ArrayList<EquipmentView> equipments = new ArrayList<>();

    public SafeHouseView(){
        color = Color.orange;
        newImage = Toolkit.getDefaultToolkit().createImage("resources/cloak.png");
        backGround = newImage.getScaledInstance(195,195,Image.SCALE_DEFAULT);
        this.text = "safe";
        setFieldText("safe");
    }

    public void setField(SafeHouse safeh) {
        field = safeh;
    }
}
