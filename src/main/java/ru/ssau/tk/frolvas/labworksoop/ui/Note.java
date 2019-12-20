package ru.ssau.tk.frolvas.labworksoop.ui;

import javafx.scene.control.TextField;

public class Note {
    private double x;
    private double y;

    public Note(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
