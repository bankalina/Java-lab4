package org.example;
import jakarta.persistence.*;

@MappedSuperclass
public abstract class Shape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Embedded
    private Color color;

    public Shape() {}

    public Shape(Color c) {
        this.color = c;
    }

    public int getId() {
        return id;
    }

    public abstract double getArea();
    public abstract double getPerimeter();

    public String getColorDescription() {
        return "RGBA(" + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + ", " + color.getAlpha() + ")";
    }

    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
