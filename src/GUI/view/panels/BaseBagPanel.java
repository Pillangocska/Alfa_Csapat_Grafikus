package GUI.view.panels;

import GUI.view.view.View;

import javax.swing.*;
import java.awt.*;

public class BaseBagPanel extends JPanel implements View {
    // the number of slots avalible in the panel
    private int viewCount;

    public BaseBagPanel(int numberOfViews) {
        viewCount = numberOfViews;
        setOpaque(false);
    }

    public BaseBagPanel() {
        viewCount = 0;
        setOpaque(false);
    }

    @Override
    public void update() {    }

    @Override
    public void onClick() {    }

    protected void setViewCount(int size) {
        viewCount = size;
    }

    protected int getViewCount() {
        return viewCount;
    }
}
