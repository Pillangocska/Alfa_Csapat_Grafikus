package GUI.view.panels;

import GUI.view.view.View;
import GUI.view.view.fieldView.FieldView;
import main.com.teamalfa.blindvirologists.city.fields.Field;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel implements View {
    FieldView mainField;
    FieldView[] neighbourFields;
    public MapPanel(){
        this.setBackground(Color.GREEN);
        this.setBorder(BorderFactory.createLineBorder(Color.RED));
        this.setOpaque(false);
        this.setFont(new Font("Viner Hand ITC", Font.PLAIN, 15));
        this.setVisible(true);

        //The map panel's center will be where the current virologist is
        mainField = new FieldView();
        mainField.setField(TurnHandler.getActiveVirologist().getField());
        //It's neighbouring fields are where the neighbours are in the model
        neighbourFields = new FieldView[mainField.getField().getNeighbours().size()];
        for(int i = 0 ; i < mainField.getField().getNeighbours().size() ; i++) {
            neighbourFields[i] = new FieldView();
        }
        for(int i = 0 ; i < neighbourFields.length ; i++){
            neighbourFields[i].setField(mainField.getField().getNeighbours().get(i));
            this.add(neighbourFields[i]);
        }

        this.add(mainField);
        this.repaint();
    }

    @Override
    public void update() {

    }

    @Override
    public void onClick() {

    }
}
