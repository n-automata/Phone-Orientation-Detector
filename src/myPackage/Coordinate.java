package myPackage;

import java.io.FileWriter;
import java.io.IOException;

public class Coordinate {

    private double x;
    private double y;
    private double z;
    private int label;

    // Constructors
    public Coordinate() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.label = 0;
    }

    public Coordinate(double x, double y, double z, int label) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.label = label;
    }

    // Setter methods
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    // Getter methods
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public int getLabel() {
        return label;
    }

    // Other methods
    public static double distanceBetween(Coordinate point1, Coordinate point2) {
        // distance formula: sqrt((x2-x1)^2 + (y2-y1)^2 + (z2-z1)^2)
        double distance1 = Math.pow(point2.getX() - point1.getX(), 2);
        double distance2 = Math.pow(point2.getY() - point1.getY(), 2);
        double distance3 = Math.pow(point2.getZ() - point1.getZ(), 2);
        return Math.sqrt(distance1 + distance2 + distance3);
    }

    // Overriding toString method for printing Coordinate object
    @Override
    public String toString() {
        return x + "," + y + "," + z + "," + label;
    }

    // Method to write Coordinate object to file
    public void writeToFile(FileWriter writer) throws IOException {
        writer.write(toString());
    }
}

