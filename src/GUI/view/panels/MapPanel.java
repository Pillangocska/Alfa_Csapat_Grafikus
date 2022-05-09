package GUI.view.panels;

import GUI.view.view.View;
import GUI.view.view.fieldView.FieldView;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel implements View {
    FieldView mainField;
    FieldView[] neighbourFields;
    public MapPanel(){
        this.setBackground(Color.GREEN);
        this.setBorder(BorderFactory.createLineBorder(Color.RED));
        this.setOpaque(true);
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
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Point origin = new Point(200 / 2, 200 / 2);
        drawCircle(g2d, origin, 380, true, true, 0x4488FF, 0);
    }

    public void drawCircle(Graphics2D g, Point origin, int radius,
                           boolean centered, boolean filled, int colorValue, int lineThickness) {
        // Store before changing
        Stroke tmpS = g.getStroke();
        Color tmpC = g.getColor();

        g.setColor(new Color(colorValue));
        g.setStroke(new BasicStroke(lineThickness, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND));

        int diameter = radius * 2;
        int x2 = centered ? origin.x - radius : origin.x;
        int y2 = centered ? origin.y - radius : origin.y;

        if (filled)
            g.fillOval(x2, y2, diameter, diameter);
        else
            g.drawOval(x2, y2, diameter, diameter);

        // Set values to previous when done
        g.setColor(tmpC);
        g.setStroke(tmpS);
    }
}
