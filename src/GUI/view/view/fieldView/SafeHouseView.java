package GUI.view.view.fieldView;

import GUI.view.view.equipmentView.EquipmentView;
import main.com.teamalfa.blindvirologists.city.fields.SafeHouse;

import java.awt.*;
import java.util.ArrayList;

public class SafeHouseView extends FieldView{
    private SafeHouse safeh;
    private ArrayList<EquipmentView> equipments = new ArrayList<>();

    public SafeHouseView(){
        color = Color.orange;
        setFieldText("safe");
    }

    public void setSafeh(SafeHouse safeh) {
        this.safeh = safeh;
    }

    @Override
    public SafeHouse getField(){
        return safeh;
    }
}
