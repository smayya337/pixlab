
/**
 * This class controls the scoreboard at the top of the panel.
 * The scoreboard gives information about the pixel at the current
 * location of the color-picker, as well as the resolution of the
 * image.
 */

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.text.DecimalFormat;

public class ScoreboardPix extends JPanel {
   private JLabel xy, red, green, blue, rgb, res;

   public ScoreboardPix() {
      setLayout(new FlowLayout());
      setBackground(Color.white);
      xy = new JLabel("(r,c)=(?,?)");
      add(xy);
      add(new JLabel("   "));
      red = new JLabel("R=???");
      add(red);
      green = new JLabel("G=???");
      add(green);
      blue = new JLabel("B=???");
      add(blue);
      add(new JLabel("   "));
      rgb = new JLabel("      ");
      rgb.setOpaque(true);
      rgb.setBackground(Color.black);
      add(rgb);
      add(new JLabel("   "));
      res = new JLabel("Resolution:");
      add(res);
   }

   /**
    * This method updates the scoreboard with the current location of the
    * color-picker and the color of the pixel at that location.
    * 
    * @param x      the color-picker's x-coordinate
    * @param y      the color-picker's y-coordinate
    * @param rgbval the color to display
    */

   public void update(int x, int y, int rgbval) {
      Color tmp = new Color(rgbval);
      int redval = tmp.getRed();
      int greenval = tmp.getGreen();
      int blueval = tmp.getBlue();
      xy.setText("(r,c)=(" + y + "," + x + ")");
      red.setText("R=" + redval);
      green.setText("G=" + greenval);
      blue.setText("B=" + blueval);
      rgb.setBackground(tmp);
   }

   /**
    * This method sets the resolution information for the picture.
    * 
    * @param x width of image
    * @param y height of image
    */

   public void setres(int x, int y) {
      DecimalFormat df = new DecimalFormat("0.00");
      double mp = x * y;
      mp /= 1000000;
      res.setText("Resolution: " + x + "x" + y + " px (" + df.format(mp) + " MP)");
   }
}
