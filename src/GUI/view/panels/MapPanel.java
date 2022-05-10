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
        mainField.update();
        for ( int i = 0; i < neighbourFields.length ; i++) {
            neighbourFields[i].update();
        }

    }

    @Override
    public void onClick() {

    }
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Point origin = new Point(1000 / 2, 500 / 2);
        drawCircle(g2d, origin, 200, true, true, 0x4d664d, 0);
        drawPolygon(g2d,1000/2, 550/2 ,1,0x00cc00,true);
    }

    public void drawCircle(Graphics2D g, Point origin, int radius,
                           boolean centered, boolean filled, int colorValue, int lineThickness) {
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
    }

    public void drawPolygon(Graphics2D g, int xcenter, int ycenter, int lineThickness, int colorValue, boolean filled) {
        g.setColor(new Color(colorValue));
        g.setStroke(new BasicStroke(lineThickness, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));

        int[] xpoints = new int[6];
        int[] ypoints = new int[6];
        for (int p = 0; p < 6; p++) {
            double fraction = (double) p / 6;
            double angle = fraction * Math.PI * 2 + Math.toRadians((90 + 180) % 360);
            int x = (int) (xcenter + Math.cos(angle) * 120); //1000/2 -> center
            int y = (int) (ycenter + Math.sin(angle) * 120); //120 -> radius
            Point point = new Point(x,y);
            xpoints[p] = point.x;
            ypoints[p] = point.y;
        }
        if (filled)
            g.fillPolygon(xpoints, ypoints, 6);
        else
            g.drawPolygon(xpoints, ypoints, 6);
    }
}
