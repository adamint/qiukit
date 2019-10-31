import javax.swing.*;

public class BoatViewer {
    public static void main(String[] args) {
        int width = 500;
        int height = 500;

        JFrame jframe = new JFrame();
        jframe.setVisible(true);
        jframe.setSize(width + 50, height + 20);

        Boat boat = new Boat(width, height);
        jframe.add(boat);
    }
}