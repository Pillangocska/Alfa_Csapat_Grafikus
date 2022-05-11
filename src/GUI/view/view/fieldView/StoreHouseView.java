package GUI.view.view.fieldView;

import GUI.view.view.ElementView;
import main.com.teamalfa.blindvirologists.city.fields.StoreHouse;

import java.awt.*;

public class StoreHouseView extends FieldView {
    private StoreHouse storeh;
    private ElementView elementv;

    public StoreHouseView(){
        color = Color.CYAN;
        this.text = "store";
        setFieldText("store");
    }

    public void setStoreh(StoreHouse storeh) {
        this.storeh = storeh;
    }

    @Override
    public StoreHouse getField(){
        return storeh;
    }
}
