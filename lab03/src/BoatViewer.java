import javax.swing.*;

public class BoatViewer {
    public static void main(String[] args) {
        int jFrameWidth = 1000;
        int jFrameHeight = 1000;

        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setSize(jFrameWidth + 50, jFrameHeight + 50);

        jFrame.add(new Boats(jFrameWidth, jFrameHeight));
    }
}
