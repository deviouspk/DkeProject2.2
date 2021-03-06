package org.map;

import org.algorithms.pathfinding.Grid2d;
import org.map.objects.Floor;

import java.awt.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by Tony on 17/06/2017.
 */
public class Grid {


    private int width;
    private int height;
    private GridObject[][] grid;

    public Grid() {
        this.width = 20;
        this.height = 20;
        this.initGrid();
    }

    public Grid(int width, int length) {
        this.width = width;
        this.height = length;
        this.initGrid();
    }

    public Grid(int squareSize) {
        this.width = squareSize;
        this.height = squareSize;
        this.initGrid();
    }

    /**
     * Loads a Grid -> GridObject[][] from a Properties variable
     * GridObject#getObjectFromString(String option) also checks for the Entity if GridObject is Controllable
     */
    public static Grid loadGrid(Properties p) {
        if (p == null) {
            return null;
        }
        int width = Integer.parseInt(p.getProperty("grid_width", "20"));
        int height = Integer.parseInt(p.getProperty("grid_height", "20"));
        Grid grid = new Grid(width, height);
        for (Object key : p.keySet()) {
            String k = key.toString();
            if (k.contains("[") && k.contains("]")) {
                int x = Integer.parseInt(k.substring(k.indexOf("[") + 1, k.indexOf("]")));
                int y = Integer.parseInt(k.substring(k.lastIndexOf("[") + 1, k.lastIndexOf("]")));
                GridObject obj = GridObject.getObjectFromString(p.getProperty(k, "FLOOR"));
                grid.getGridArray()[x][y] = obj;
            }
        }
        return grid;
    }

    public static Properties storeGrid(Grid grid) {
        Properties p = new Properties();
        GridObject[][] objects = grid.getGridArray();
        p.setProperty("grid_width", String.valueOf(grid.getWidth()));
        p.setProperty("grid_height", String.valueOf(grid.getHeight()));
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                p.setProperty("[" + x + "][" + y + "]", objects[x][y].toString());
            }
        }
        return p;
    }

    private void initGrid() {
        this.grid = new GridObject[this.width][this.height];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Floor();
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public GridObject[][] getGridArray() {
        return grid;
    }

    public int[][] getRawGrid() {
        int[][] rawGrid = new int[this.getWidth()][this.getHeight()];
        for (int x = 0; x < this.getWidth(); x++) {
            for (int y = 0; y < this.getHeight(); y++) {
                rawGrid[x][y] = this.grid[x][y].getId();
            }
        }
        return rawGrid;
    }

    public ArrayList<Point> getCriminalLocations() {
        GridObject[][] gridArray = getGridArray();
        ArrayList<Point> locations = new ArrayList<>();
        for (int x = 0; x < gridArray.length; x++) {
            for (int y = 0; y < gridArray[x].length; y++) {
                if (gridArray[x][y].isCriminal())
                    locations.add(new Point(x, y));
            }
        }
        return locations;
    }

    public ArrayList<Point> getPoliceLocations() {
        GridObject[][] gridArray = getGridArray();
        ArrayList<Point> locations = new ArrayList<>();
        for (int x = 0; x < gridArray.length; x++) {
            for (int y = 0; y < gridArray[x].length; y++) {
                if (gridArray[x][y].isPolice())
                    locations.add(new Point(x, y));
            }
        }
        return locations;
    }

    private double[][] convertToIntArrayToLookForCriminals() {
        GridObject[][] gridArray = getGridArray();
        double[][] intArray = new double[gridArray.length][gridArray[0].length];
        for (int x = 0; x < gridArray.length; x++) {
            for (int y = 0; y < gridArray[x].length; y++) {
                if (gridArray[x][y].isWall())
                    intArray[x][y] = -1;
                else if (gridArray[x][y].isCriminal())
                    intArray[x][y] = 0;
                else if (gridArray[x][y].isPolice())
                    intArray[x][y] = 2;
                else
                    intArray[x][y] = 1;
            }
        }
        return swapArrayValues(intArray);
    }

    public double[][] swapArrayValues(double[][] array) {
        double[][] newArray = new double[array[0].length][array.length];
        for (int x = 0; x < array.length; x++) {
            for (int y = 0; y < array[x].length; y++) {
                newArray[y][x] = array[x][y];
            }
        }
        return newArray;
    }

    private double[][] convertToIntArrayToLookForPolice() {
        GridObject[][] gridArray = getGridArray();
        double[][] intArray = new double[gridArray.length][gridArray[0].length];
        for (int x = 0; x < gridArray.length; x++) {
            for (int y = 0; y < gridArray[x].length; y++) {
                if (gridArray[x][y].isWall())
                    intArray[x][y] = -1;
                else if (gridArray[x][y].isCriminal())
                    intArray[x][y] = 2;
                else if (gridArray[x][y].isPolice())
                    intArray[x][y] = 0;
                else
                    intArray[x][y] = 1;
            }
        }
        return swapArrayValues(intArray);
    }

    public java.util.List<Grid2d.MapNode> findBestPathtoCriminal(Point start, Point end) {
        double[][] map = convertToIntArrayToLookForCriminals();
        Grid2d map2d = new Grid2d(map, false);
        return map2d.findPath(start.x, start.y, end.x, end.y);
    }

    public java.util.List<Grid2d.MapNode> findBestPathtoPolice(Point start, Point end) {
        double[][] map = convertToIntArrayToLookForPolice();
        Grid2d map2d = new Grid2d(map, false);
        return map2d.findPath(start.x, start.y, end.x, end.y);
    }

    private double[][] convertToWeightedIntArrayToLookForPolice() {
        double[][] gridArray = convertToIntArrayToLookForPolice();
        double[][] doubleArray = new double[gridArray.length][gridArray[0].length];
        for (int x = 0; x < gridArray.length; x++) {
            for (int y = 0; y < gridArray[x].length; y++) {
                if (gridArray[x][y] == 2) {
                    doubleArray = populateNeighbouringValues(gridArray, x, y, 2, 4);
                } else if (gridArray[x][y] == 0) {
                    doubleArray = populateNeighbouringValues(gridArray, x, y, 0, 10);
                }
            }
        }
        return doubleArray;
    }

    public java.util.List<Grid2d.MapNode> findBestWeightedPathAwayFromPolice(Point start) {
        double[][] map = convertToWeightedIntArrayToLookForPolice();
        Grid2d map2d = new Grid2d(map, false);
        Point bestLocation = FindBestPossibleCriminalLocation();
        return map2d.findPath(start.x, start.y, bestLocation.x, bestLocation.y);
    }

    public Point FindBestPossibleCriminalLocation() {
        int bestLocationX = 0;
        int bestLocationY = 0;
        double LowestweightScore = 0;

        double[][] gridArray = convertToWeightedIntArrayToLookForPolice();
        for (int x = 0; x < gridArray.length; x++) {
            for (int y = 0; y < gridArray[x].length; y++) {
                if (gridArray[x][y] != -1) {
                    if (gridArray[x][y] <= LowestweightScore) {
                        bestLocationX = x;
                        bestLocationY = y;
                        LowestweightScore = gridArray[x][y];
                    }

                }
            }
        }
        return new Point(bestLocationX, bestLocationY);
    }

    private double[][] populateNeighbouringValues(double[][] grid, int x, int y, double targetValue, double newValue) {
        double[][] gridArray = grid;

        if (gridArray[x][y] == targetValue) {
            gridArray = setGridValue(grid, x, y, newValue, 1);

            gridArray = setGridValue(grid, x + 1, y, newValue, 0.8);
            gridArray = setGridValue(grid, x, y + 1, newValue, 0.8);
            gridArray = setGridValue(grid, x - 1, y, newValue, 0.8);
            gridArray = setGridValue(grid, x, y - 1, newValue, 0.8);

            gridArray = setGridValue(grid, x + 2, y, newValue, 0.6);
            gridArray = setGridValue(grid, x, y + 2, newValue, 0.6);
            gridArray = setGridValue(grid, x - 2, y, newValue, 0.6);
            gridArray = setGridValue(grid, x, y - 2, newValue, 0.6);

            gridArray = setGridValue(grid, x + 1, y + 1, newValue, 0.6);
            gridArray = setGridValue(grid, x - 1, y - 1, newValue, 0.6);
            gridArray = setGridValue(grid, x - 1, y + 1, newValue, 0.6);
            gridArray = setGridValue(grid, x + 1, y - 1, newValue, 0.6);


        }
        return gridArray;
    }

    private double[][] setGridValue(double[][] grid, int x, int y, double newValue, double modifier) {
        if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] != -1)
            grid[x][y] = newValue * modifier;

        return grid;

    }

}
