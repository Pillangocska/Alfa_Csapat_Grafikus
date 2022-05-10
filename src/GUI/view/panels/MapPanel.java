package GUI.view.panels;

import GUI.view.frames.GameFrame;
import GUI.view.view.View;
import GUI.view.view.fieldView.FieldView;
import main.com.teamalfa.blindvirologists.city.fields.Field;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MapPanel extends JPanel implements View {
    FieldView mainField;
    ArrayList<FieldView> neighbourFields = new ArrayList<>();

    public MapPanel(GameFrame gf){
        this.setBackground(Color.GREEN);
        this.setBorder(BorderFactory.createLineBorder(Color.RED));
        this.setOpaque(true);
        this.setFont(new Font("Viner Hand ITC", Font.PLAIN, 15));
        this.setVisible(true);
        this.add(mainField);
        this.repaint();
    }

    @Override
    public void update() {
        Field currfield = TurnHandler.getActiveVirologist().getField();
        mainField = findFieldViewByField(currfield);
        mainField.update();
        neighbourFields.clear();
        for (int i = 0 ; i < currfield.getNeighbours().size(); i++) {
            neighbourFields.add(findFieldViewByField(currfield.getNeighbours().get(i)));
            neighbourFields.get(i).update();
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
