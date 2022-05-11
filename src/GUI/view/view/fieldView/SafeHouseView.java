package GUI.view.view.fieldView;

import GUI.view.view.equipmentView.EquipmentView;
import main.com.teamalfa.blindvirologists.city.fields.SafeHouse;

import java.awt.*;
import java.util.ArrayList;

public class SafeHouseView extends FieldView{
    private ArrayList<EquipmentView> equipments = new ArrayList<>();

    public SafeHouseView(){
        color = Color.orange;
        this.text = "safe";
        setFieldText("safe");
    }

    public void setField(SafeHouse safeh) {
        field = safeh;
    }
}
