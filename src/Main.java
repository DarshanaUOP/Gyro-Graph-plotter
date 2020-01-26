import javax.swing.*;
import java.awt.*;

/**
 * Created by acer on 08-Dec-17.
 */
public class Main {
    public static void main(String[] args) {
        GraphDraw g=new GraphDraw();
        g.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        g.setSize(1400,920);
        g.setVisible(true);
        g.setEnabled(true);
        g.setTitle("Gyro sensor");
        g.setLayout(new GridBagLayout());

    }
}

