package GUI.view.view.fieldView;

import GUI.view.view.ElementView;
import main.com.teamalfa.blindvirologists.city.fields.StoreHouse;

import java.awt.*;

public class StoreHouseView extends FieldView {
    private ElementView elementv;
    private StoreHouse storeHouse;

    public StoreHouseView(){
        color = Color.CYAN;
        newImage = Toolkit.getDefaultToolkit().createImage("resources/bag.png");
        backGround = newImage.getScaledInstance(195,195,Image.SCALE_DEFAULT);
        this.text = "store";
        setFieldText("store");
    }

    public void setField(StoreHouse storeh) {
        field = storeh;
    }
}
