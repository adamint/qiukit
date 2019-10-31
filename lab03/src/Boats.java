import javax.swing.*;
import java.awt.*;

public class Boats extends JComponent {
    private int width;
    private int height;

    public Boats(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        new Boat(Boat.asInteger(width * .1), Boat.asInteger(height * .1), Boat.asInteger(width * .2), Boat.asInteger(height * .2))
                .draw(graphics);

        new Boat(Boat.asInteger(width * .5), Boat.asInteger(height * .2), Boat.asInteger(width * .07), Boat.asInteger(height * .07))
                .draw(graphics);

        new Boat(Boat.asInteger(width * .3), Boat.asInteger(height * .5), Boat.asInteger(width * .4), Boat.asInteger(height * .35))
                .draw(graphics);

        new Boat(Boat.asInteger(width * .5), 0, Boat.asInteger(width * .5), Boat.asInteger(height * .5))
                .draw(graphics);

        new Boat(Boat.asInteger(width * .1), Boat.asInteger(height * .4), Boat.asInteger(width * .15), Boat.asInteger(height * .15))
                .draw(graphics);
    }
}
