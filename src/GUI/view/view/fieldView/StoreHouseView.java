package GUI.view.view.fieldView;

import GUI.view.view.ElementView;
import main.com.teamalfa.blindvirologists.city.fields.StoreHouse;

import java.awt.*;

public class StoreHouseView extends FieldView {
    private ElementView elementv;

    public StoreHouseView(){
        color = Color.CYAN;
        this.text = "store";
        setFieldText("store");
    }

    public void setField(StoreHouse storeh) {
        field = storeh;
    }
}
