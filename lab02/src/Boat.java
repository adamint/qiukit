import javax.swing.*;
import java.awt.*;

public class Boat extends JComponent {
    private int width;
    private int height;
    private final int xOffset = 40;
    private final int yOffset = 50;

    public Boat(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        final Color brown = new Color(150, 75, 0);
        graphics.setColor(brown);

        // left side of boat
        graphics.fillRect(xOffset, asInteger(height * 0.65) - yOffset, asInteger(width * 0.1), asInteger(height * 0.2)); // left rect, boat body

        Polygon leftTriangle = new Polygon(); // left triangle, boat body
        leftTriangle.addPoint(xOffset, asInteger(height * 0.85) - yOffset);
        leftTriangle.addPoint(xOffset + asInteger(width * 0.1), asInteger(height * 0.85) - yOffset);
        leftTriangle.addPoint(xOffset + asInteger(width * 0.1), asInteger(height * 0.95) - yOffset);
        graphics.fillPolygon(leftTriangle);

        // main boat body
        graphics.fillRect(xOffset + asInteger(width * 0.1), asInteger(height * 0.65) - yOffset, asInteger(width * 0.6), asInteger(height * 0.3));

        // right side of boat
        graphics.fillRect(xOffset + asInteger(width * 0.7), asInteger(height * 0.65) - yOffset, asInteger(width * 0.1), asInteger(height * 0.2)); // left rect, boat body

        Polygon rightTriangle = new Polygon(); // right triangle, boat body
        rightTriangle.addPoint(xOffset + asInteger(width * 0.7), asInteger(height * 0.85) - yOffset);
        rightTriangle.addPoint(xOffset + asInteger(width * 0.8), asInteger(height * 0.85) - yOffset);
        rightTriangle.addPoint(xOffset + asInteger(width * 0.7), asInteger(height * 0.95) - yOffset);
        graphics.fillPolygon(rightTriangle);


        // boat sail support

        graphics.fillRect(xOffset + asInteger(width * 0.35), asInteger(height * 0.22) - yOffset, asInteger(width * 0.03), asInteger(height * 0.43));

        // boat sail
        final Color gray = Color.GRAY;
        graphics.setColor(gray);

        Polygon sail = new Polygon();
        sail.addPoint(xOffset + asInteger(width * 0.38), asInteger(height * 0.25) - yOffset);
        sail.addPoint(xOffset + asInteger(width * 0.38), asInteger(height * 0.6) - yOffset);
        sail.addPoint(xOffset + asInteger(width * 0.38) + asInteger(width * 0.3), asInteger(height * 0.6) - yOffset);

        graphics.fillPolygon(sail);
    }

    private int asInteger(double d) {
        return (int) d;
    }
}
