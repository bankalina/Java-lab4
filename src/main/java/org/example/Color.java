package org.example;
import jakarta.persistence.*;

@Embeddable
public class Color {
    @Column(nullable = true)
    private int red;
    @Column(nullable = true)
    private int green;
    @Column(nullable = true)
    private int blue;
    @Column(nullable = true)
    private double alpha;

    public Color() {}

    public Color(int r, int g, int b, double a) {
        if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255 || a < 0 || a > 1) {
            throw new IllegalArgumentException("Tylko wartosci z zakresu od 0 do 255.");
        }
        this.red = r;
        this.green = g;
        this.blue = b;
        this.alpha = a;
    }

    public Color(int r, int g, int b) {
        this(r, g, b, 0);
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public double getAlpha() {
        return alpha;
    }
}
