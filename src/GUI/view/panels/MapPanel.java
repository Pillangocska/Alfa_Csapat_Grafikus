package GUI.view.panels;

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
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBackground(Color.BLACK);
        this.setOpaque(true);
        this.setFont(new Font("Viner Hand ITC", Font.PLAIN, 15));
        bindFields();
        update();
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
        int offSet = 10;
        int dimension = FieldView.getHexaDimension();
        int horizontalOffset = dimension/2;
        int verticalOffset = dimension - dimension / 4 + offSet;
        Point center = new Point(1000/2-dimension/2, 550/2-dimension/2);

        mainField.setBounds(center.x, center.y, dimension,dimension);

        // northeast
        neighbourFields.get(0).setBounds(center.x + horizontalOffset, center.y - verticalOffset, dimension, dimension);
        // east
        if(neighbourFields.size() >= 2) {
            neighbourFields.get(1).setBounds(center.x + dimension, center.y, dimension, dimension);
        }
        // southeast
        if(neighbourFields.size() >= 3) {
            neighbourFields.get(2).setBounds(center.x + horizontalOffset, center.y + verticalOffset, dimension, dimension);
        }
        // southwest
        if(neighbourFields.size() >= 4) {
            neighbourFields.get(3).setBounds(center.x - horizontalOffset, center.y + verticalOffset, dimension, dimension);
        }
        // west
        if(neighbourFields.size() >= 5) {
            neighbourFields.get(4).setBounds(center.x - dimension, center.y, dimension, dimension);
        }
        //northwest
        if(neighbourFields.size() >= 6) {
            neighbourFields.get(5).setBounds(center.x - horizontalOffset, center.y - verticalOffset, dimension, dimension);
        }
    }

    @Override
    public void update() {
        removeAll();
        // current virologist's field
        Field current = TurnHandler.getActiveVirologist().getField();

        //The map panel's center will be where the current virologist is
        mainField = findFieldViewByField(current);

        //It's neighbouring fields are where the neighbours are in the model
        neighbourFields = new ArrayList<>();
        for(Field field : current.getNeighbours()) {
            neighbourFields.add(findFieldViewByField(field));
        }

        // update the main field and add it panel
        mainField.update();
        this.add(mainField);
        addNeighbours();

        // position the neighbouring fields and update all of them
        positionFields();
        for(FieldView fv : fieldViews)
            fv.update();
        this.setVisible(true);
        revalidate();
        this.repaint();
    }

    @Override
    public void onClick() {

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
            labView.setField(laboratory);
            fieldViews.add(labView);
        }

        //bind safe houses
        for(SafeHouse safeHouse : City.getAllSafeHouses()){
            SafeHouseView safeHouseView = new SafeHouseView();
            safeHouseView.setField(safeHouse);
            fieldViews.add(safeHouseView);
        }

        // bind store houses
        for(StoreHouse storeHouse : City.getAllStoreHouses()){
            StoreHouseView storeHouseView = new StoreHouseView();
            storeHouseView.setField(storeHouse);
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
