package org.panels;

import org.map.Grid;
import org.map.objects.Criminal;
import org.map.objects.Police;
import org.map.objects.Wall;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Krulvis on 20-Jun-17.
 */
public class GridPanel extends JPanel {
    private final int RECTANGLE_SIZE = 30;
    private Grid grid;

    public GridPanel() {
        initGrid(20, 20);
    }

    public GridPanel(Grid grid) {
        setOpaque(false);
        setGrid(grid);
    }

    public void initGrid(int width, int height) {
        this.grid = new Grid(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int x = 0; x < grid.getGridArray().length; x++) {
            for (int y = 0; y < grid.getGridArray()[x].length; y++) {
                int cellX = RECTANGLE_SIZE + (x * RECTANGLE_SIZE);
                int cellY = RECTANGLE_SIZE + (y * RECTANGLE_SIZE);


                g.setColor(Color.lightGray);
                g.fillRect(cellX, cellY, RECTANGLE_SIZE, RECTANGLE_SIZE);
                g.setColor(Color.black);
                g.drawRect(cellX, cellY, RECTANGLE_SIZE, RECTANGLE_SIZE);

                g.setColor(grid.getGridArray()[x][y].getColor());

                if (grid.getGridArray()[x][y].isPursuiter())
                    g.drawImage(Police.getImage(), cellX + 3, cellY + 3, RECTANGLE_SIZE - 6, RECTANGLE_SIZE - 6, null);
                else if (grid.getGridArray()[x][y].isEvader()) {
                    g.drawImage(Criminal.getImage(), cellX + 3, cellY + 3, RECTANGLE_SIZE - 6, RECTANGLE_SIZE - 6, null);
                } else if (grid.getGridArray()[x][y].isWall()) {
                    g.drawImage(Wall.getImage(), cellX, cellY, RECTANGLE_SIZE, RECTANGLE_SIZE, null);
                }

                if (grid.getGridArray()[x][y].isFloor()) {
                    g.setColor(grid.getGridArray()[x][y].getColor());
                    g.fillRect(cellX, cellY, RECTANGLE_SIZE, RECTANGLE_SIZE);
                }

                g.setColor(Color.black);
                g.drawRect(cellX, cellY, RECTANGLE_SIZE, RECTANGLE_SIZE);
            }

        }
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
        repaint();
    }

}