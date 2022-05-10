package GUI.view.panels;

import GUI.view.frames.GameFrame;
import GUI.view.view.View;
import GUI.view.view.fieldView.FieldView;
import GUI.view.view.fieldView.LaboratoryView;
import GUI.view.view.fieldView.SafeHouseView;
import GUI.view.view.fieldView.StoreHouseView;
import main.com.teamalfa.blindvirologists.city.City;
import main.com.teamalfa.blindvirologists.city.fields.Field;
import main.com.teamalfa.blindvirologists.city.fields.Laboratory;
import main.com.teamalfa.blindvirologists.city.fields.SafeHouse;
import main.com.teamalfa.blindvirologists.city.fields.StoreHouse;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MapPanel extends JPanel implements View {
    private final ArrayList<FieldView> fieldViews = new ArrayList<>();
    FieldView mainField;
    ArrayList<FieldView> neighbourFields;
    public MapPanel(){
        setLayout(null);
        this.setBorder(BorderFactory.createLineBorder(Color.RED));
        this.setOpaque(true);
        this.setFont(new Font("Viner Hand ITC", Font.PLAIN, 15));
        bindFields();

        // current virologist's field
        Field current = TurnHandler.getActiveVirologist().getField();

        //The map panel's center will be where the current virologist is
        mainField = findFieldViewByField(current);

        //It's neighbouring fields are where the neighbours are in the model
        neighbourFields = new ArrayList<>();
        for(Field field : current.getNeighbours()) {
            neighbourFields.add(findFieldViewByField(field));
        }
        this.add(mainField);
        addNeighbours();

        positionFields();
        this.setVisible(true);
        this.repaint();
    }

    private void addNeighbours(){
        int max = 1;
        for(FieldView neighbour : neighbourFields) {
            if(max > 6)
                break;
            this.add(neighbour);
            max++;
        }
    }

    private void positionFields(){
        Point center = new Point(1000/2 - 40, 500/2 - 40);
        int offSet = 10;
        int dimension = 100;
        int horizontalOffset = dimension/2;
        int verticalOffset = dimension - dimension / 4 + offSet;

        mainField.setBounds(center.x, center.y, dimension,dimension);

        // northeast
        neighbourFields.get(0).setBounds(center.x + horizontalOffset, center.y - verticalOffset, 100, 100);
        // east
        neighbourFields.get(1).setBounds(center.x + dimension, center.y, 100, 100);
        // southeast
        neighbourFields.get(2).setBounds(center.x + horizontalOffset, center.y + verticalOffset, 100, 100);
        // southwest
        neighbourFields.get(3).setBounds(center.x - horizontalOffset, center.y + verticalOffset, 100, 100);
        // west
        neighbourFields.get(4).setBounds(center.x - dimension, center.y, 100, 100);
        //northwest
        neighbourFields.get(5).setBounds(center.x - horizontalOffset, center.y - verticalOffset, 100, 100);

    }

    @Override
    public void update() {

    }

    @Override
    public void onClick() {

    }
//    @Override
//    public void paintComponent(Graphics g) {
//        Graphics2D g2d = (Graphics2D) g;
//        Point origin = new Point(1000 / 2, 500 / 2);
//        drawCircle(g2d, origin, 200, true, true, 0x4d664d, 0);
//        //drawPolygon(g2d,1000/2, 550/2 ,1,0x00cc00,true);
//    }

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

    private void bindFields(){
        // bind fields
        for(Field field : City.getAllFields()){
            FieldView fieldView = new FieldView();
            fieldView.setField(field);
            fieldViews.add(fieldView);
        }

        // bind labs
        for(Laboratory laboratory : City.getAllLaboratories()){
            LaboratoryView labView = new LaboratoryView();
            labView.setLab(laboratory);
            fieldViews.add(labView);
        }

        //bind safe houses
        for(SafeHouse safeHouse : City.getAllSafeHouses()){
            SafeHouseView safeHouseView = new SafeHouseView();
            safeHouseView.setSafeh(safeHouse);
            fieldViews.add(safeHouseView);
        }

        // bind store houses
        for(StoreHouse storeHouse : City.getAllStoreHouses()){
            StoreHouseView storeHouseView = new StoreHouseView();
            storeHouseView.setStoreh(storeHouse);
            fieldViews.add(storeHouseView);
        }
    }

    private FieldView findFieldViewByField(Field field) {
        // find fieldView by its field object
        for(FieldView fieldView : fieldViews)
            if(fieldView.getField() == field)
                return fieldView;
        return null;
    }
}
