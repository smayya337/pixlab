
/**
 * The driver class for Shreyas Mayya's PixLab.
 * Only the name of the window has been changed from the original PixLab.
 */

import javax.swing.JFrame;

public class DriverPix {
   public static void main(String[] args) {
      JFrame f = new JFrame("Shreyas Mayya's PixLab");
      f.setSize(900, 600);
      f.setLocation(100, 50);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setContentPane(new PanelPix());
      f.setVisible(true);
   }
}
