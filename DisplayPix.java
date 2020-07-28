
/**
 * This class controls the image display.
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.*;
import java.util.*;
import javax.imageio.ImageIO;

public class DisplayPix extends JPanel {
   private PixelOperations pix = new PixelOperations();
   public ImageIcon i = new ImageIcon("images/640x480.jpg");
   private ImageIcon message, moon;
   private BufferedImage img = new BufferedImage(i.getIconWidth(), i.getIconHeight(), BufferedImage.TYPE_INT_RGB);
   private Graphics buf = img.getGraphics();
   private boolean clicked = false;
   private int x, y;
   private Stack<Color[][]> images = new Stack<Color[][]>();

   public DisplayPix() {
      int w = img.getWidth();
      int h = img.getHeight();

      buf.drawImage(i.getImage(), 0, 0, w, h, null);
      images.push(pix.getArray(img));
   }

   /**
    * This method is a simple getter for the x-coordinate of the color picker. Note
    * the name: it's getXval, not getX!
    * 
    * @return color-picker x-coordinate
    */

   public int getXval() {
      return x;
   }

   /**
    * This method is a simple getter for the y-coordinate of the color picker. Note
    * the name: it's getYval, not getY!
    * 
    * @return color-picker y-coordinate
    */

   public int getYval() {
      return y;
   }

   /**
    * This method returns the row of the color-picker. The row is calculated with
    * respect to the buffer's and image's sizes.
    * 
    * @return row of color-picker
    */

   public int getRow() {
      return y * img.getHeight() / getHeight();
   }

   /**
    * This method returns the column of the color-picker. The column is calculated
    * with respect to the buffer's and image's sizes.
    * 
    * @return column of color-picker
    */

   public int getCol() {
      return x * img.getWidth() / getWidth();
   }

   /**
    * This method returns the color of a specific pixel in the image.
    * 
    * @return pixel color
    * @param x the x-coordinate of the location
    * @param y the y-coordinate of the location
    */

   public int getRGB(int x, int y) {
      int xpos = x * img.getWidth() / getWidth();
      int ypos = y * img.getHeight() / getHeight();

      return img.getRGB(xpos, ypos);
   }

   /**
    * This method gets the coordinates of the color-picker, then assigns those
    * values to the x and y variables. The method also sets the value of the
    * "clicked" variable to true.
    * 
    * @param xval x-coordinate of color-picker
    * @param yval y-coordinate of color-picker
    */

   public void update(int xval, int yval) {
      clicked = true;

      x = xval;
      y = yval;
   }

   /**
    * This method sets the red value of every pixel in the image to zero.
    */

   public void zeroRed() {
      images.push(pix.getArray(img));
      Color[][] tmp = pix.getArray(img);
      pix.zeroRed(tmp);
      pix.setImage(img, tmp);
   }

   /**
    * This method sets the green value of every pixel in the image to zero.
    */

   public void zeroGreen() {
      images.push(pix.getArray(img));
      Color[][] tmp = pix.getArray(img);
      pix.zeroGreen(tmp);
      pix.setImage(img, tmp);
   }

   /**
    * This method sets the blue value of every pixel in the image to zero.
    */

   public void zeroBlue() {
      images.push(pix.getArray(img));
      Color[][] tmp = pix.getArray(img);
      pix.zeroBlue(tmp);
      pix.setImage(img, tmp);
   }

   /**
    * This method sets every pixel's RGB values to 255 - (current value).
    */

   public void negate() {
      images.push(pix.getArray(img));
      Color[][] tmp = pix.getArray(img);
      pix.negate(tmp);
      pix.setImage(img, tmp);
   }

   /**
    * This method takes the average of the RGB values for a pixel, then sets that
    * value as the red, green, and blue values.
    */

   public void gray() {
      images.push(pix.getArray(img));
      Color[][] tmp = pix.getArray(img);
      pix.gray(tmp);
      pix.setImage(img, tmp);
   }

   /**
    * This method uses Microsoft's recommended formula to add a sepia tint.
    */

   public void sepia() {
      images.push(pix.getArray(img));
      Color[][] tmp = pix.getArray(img);
      pix.sepia(tmp);
      pix.setImage(img, tmp);
   }

   /**
    * This method produces a blur effect by averaging the RGB values of surrounding
    * pixels.
    */

   public void blur() {
      images.push(pix.getArray(img));
      Color[][] tmp = pix.getArray(img);
      pix.blur(tmp);
      pix.setImage(img, tmp);
   }

   /**
    * This method sets the RGB values of a pixel based on which range the current
    * values fall into:
    * <ul>
    * <li>0-63, inclusive: 32
    * <li>64-127, inclusive: 96
    * <li>128-191, inclusive: 160
    * <li>191-255, inclusive: 224
    * </ul>
    */

   public void posterize() {
      images.push(pix.getArray(img));
      Color[][] tmp = pix.getArray(img);
      pix.posterize(tmp);
      pix.setImage(img, tmp);
   }

   /**
    * This method grays out all pixels except those that are predominantly red,
    * i.e. their red value is greater than or equal to the sum of their green and
    * blue values.
    */

   public void splash_red() {
      images.push(pix.getArray(img));
      Color[][] tmp = pix.getArray(img);
      pix.splash_red(tmp);
      pix.setImage(img, tmp);
   }

   /**
    * This method grays out all pixels except those that are predominantly green,
    * i.e. their green value is greater than or equal to the sum of their red and
    * blue values.
    */

   public void splash_green() {
      images.push(pix.getArray(img));
      Color[][] tmp = pix.getArray(img);
      pix.splash_green(tmp);
      pix.setImage(img, tmp);
   }

   /**
    * This method grays out all pixels except those that are predominantly blue,
    * i.e. their blue value is greater than or equal to the sum of their red and
    * green values.
    */

   public void splash_blue() {
      images.push(pix.getArray(img));
      Color[][] tmp = pix.getArray(img);
      pix.splash_blue(tmp);
      pix.setImage(img, tmp);
   }

   /**
    * This method makes the right half of the image a mirror of the left.
    */

   public void mirrorLR() {
      images.push(pix.getArray(img));
      Color[][] tmp = pix.getArray(img);
      pix.mirrorLR(tmp);
      pix.setImage(img, tmp);
   }

   /**
    * This method makes the left half of the image a mirror of the right.
    */

   public void mirrorRL() {
      images.push(pix.getArray(img));
      Color[][] tmp = pix.getArray(img);
      pix.mirrorRL(tmp);
      pix.setImage(img, tmp);
   }

   /**
    * This method makes the bottom half of the image a mirror of the top.
    */

   public void mirrorUD() {
      images.push(pix.getArray(img));
      Color[][] tmp = pix.getArray(img);
      pix.mirrorUD(tmp);
      pix.setImage(img, tmp);
   }

   /**
    * This method makes the top half of the image a mirror of the bottom.
    */

   public void mirrorDU() {
      images.push(pix.getArray(img));
      Color[][] tmp = pix.getArray(img);
      pix.mirrorDU(tmp);
      pix.setImage(img, tmp);
   }

   /**
    * This method flips the left and right halves of the image.
    */

   public void flipLR() {
      images.push(pix.getArray(img));
      Color[][] tmp = pix.getArray(img);
      pix.flipLR(tmp);
      pix.setImage(img, tmp);
   }

   /**
    * This method flips the top and bottom halves of the image.
    */

   public void flipUD() {
      images.push(pix.getArray(img));
      Color[][] tmp = pix.getArray(img);
      pix.flipUD(tmp);
      pix.setImage(img, tmp);
   }

   /**
    * This method pixelates the image by creating 10x10 blocks that are all the
    * same color.
    */

   public void pixelate() {
      images.push(pix.getArray(img));
      Color[][] tmp = pix.getArray(img);
      pix.pixelate(tmp);
      pix.setImage(img, tmp);
   }

   /**
    * This method adds a red "sunset"-like tint by decreasing the green and blue
    * values of every pixel.
    */

   public void sunsetize() {
      images.push(pix.getArray(img));
      Color[][] tmp = pix.getArray(img);
      pix.sunsetize(tmp);
      pix.setImage(img, tmp);
   }

   /**
    * This method detects red-eye coloration and removes it.
    */

   public void redeye() {
      images.push(pix.getArray(img));
      Color[][] tmp = pix.getArray(img);
      pix.redeye(tmp);
      pix.setImage(img, tmp);
   }

   /**
    * This method attempts to find edges based on the distances between RGB values
    * in 3-dimensional space.
    */

   public void edge() {
      images.push(pix.getArray(img));
      Color[][] tmp = pix.getArray(img);
      pix.edge(tmp);
      pix.setImage(img, tmp);
   }

   /**
    * This method encodes a message of the user's choosing within the RGB values of
    * the image.
    */

   public void encode() {
      images.push(pix.getArray(img));
      Color[][] tmp = pix.getArray(img);
      if (getMessage()) {
         buf.drawImage(message.getImage(), 0, 0, img.getWidth(), img.getHeight(), null);
         Color[][] tmp2 = pix.getArray(img);
         pix.encode(tmp, tmp2);
      }
      pix.setImage(img, tmp);
   }

   /**
    * This method reveals an encoded message.
    */

   public void decode() {
      images.push(pix.getArray(img));
      Color[][] tmp = pix.getArray(img);
      pix.decode(tmp);
      pix.setImage(img, tmp);
   }

   /**
    * This method replaces a region of similar color in one image with part of
    * another image.
    */

   public void chroma() {
      images.push(pix.getArray(img));
      Color[][] tmp = pix.getArray(img);
      if (getChroma()) {
         buf.drawImage(moon.getImage(), 0, 0, img.getWidth(), img.getHeight(), null);
         Color[][] tmp2 = pix.getArray(img);
         pix.chroma(tmp, tmp2);
      }
      pix.setImage(img, tmp);
   }

   /**
    * This method rotates the color 120 degrees clockwise on the color wheel.
    */

   public void gbr() {
      images.push(pix.getArray(img));
      Color[][] tmp = pix.getArray(img);
      pix.gbr(tmp);
      pix.setImage(img, tmp);
   }

   /**
    * This method rotates the color 240 degrees clockwise on the color wheel.
    */

   public void brg() {
      images.push(pix.getArray(img));
      Color[][] tmp = pix.getArray(img);
      pix.brg(tmp);
      pix.setImage(img, tmp);
   }

   /**
    * This method adjusts all red values by a specified percentage.
    */

   public void adjustRed() {
      int x;
      try {
         x = Integer.parseInt(JOptionPane.showInputDialog(
               "What percentage do you want to change the red values by? (Don't include the % sign.)"));
         images.push(pix.getArray(img));
      } catch (Exception e) {
         x = 0;
      }
      Color[][] tmp = pix.getArray(img);
      pix.adjustRed(tmp, x);
      pix.setImage(img, tmp);
   }

   /**
    * This method adjusts all green values by a specified percentage.
    */

   public void adjustGreen() {
      int x;
      try {
         x = Integer.parseInt(JOptionPane.showInputDialog(
               "What percentage do you want to change the green values by? (Don't include the % sign.)"));
         images.push(pix.getArray(img));
      } catch (Exception e) {
         x = 0;
      }
      Color[][] tmp = pix.getArray(img);
      pix.adjustGreen(tmp, x);
      pix.setImage(img, tmp);
   }

   /**
    * This method adjusts all blue values by a specified percentage.
    */

   public void adjustBlue() {
      int x;
      try {
         x = Integer.parseInt(JOptionPane.showInputDialog(
               "What percentage do you want to change the blue values by? (Don't include the % sign.)"));
         images.push(pix.getArray(img));
      } catch (Exception e) {
         x = 0;
      }
      Color[][] tmp = pix.getArray(img);
      pix.adjustBlue(tmp, x);
      pix.setImage(img, tmp);
   }

   /**
    * This method adjusts all RGB values by a specified percentage.
    */

   public void adjustAll() {
      int x;
      try {
         x = Integer.parseInt(JOptionPane.showInputDialog(
               "What percentage do you want to change the color values by? (Don't include the % sign.)"));
         images.push(pix.getArray(img));
      } catch (Exception e) {
         x = 0;
      }
      Color[][] tmp = pix.getArray(img);
      pix.adjustAll(tmp, x);
      pix.setImage(img, tmp);
   }

   /**
    * This method removes all alterations from an image.
    */

   public void resetImage() {
      int w = img.getWidth();
      int h = img.getHeight();
      buf.drawImage(i.getImage(), 0, 0, w, h, null);
   }

   /**
    * This method undoes the most recent change by popping an item off the stack of
    * images.
    */

   public void undo() {
      if (!images.empty()) {
         Color[][] tmp = images.pop();
         pix.setImage(img, tmp);
      }
   }

   /**
    * This method saves an image to a file.
    */

   public void save() {
      try {
         JFileChooser fc = new JFileChooser("images");
         fc.showOpenDialog(null);
         File f = fc.getSelectedFile();
         ImageIO.write(img, f.getName().substring(f.getName().lastIndexOf(".") + 1), f);
      } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "Sorry, your image could not be saved.", "Error",
               JOptionPane.INFORMATION_MESSAGE);
      }
   }

   /**
    * This method attempts to open an image containing the message to be encoded.
    * 
    * @return whether opening the message worked or not
    */

   private boolean getMessage() {
      JFileChooser fc = new JFileChooser("images");
      fc.showOpenDialog(null);
      File f = fc.getSelectedFile();
      try {
         message = new ImageIcon(f.getAbsolutePath());
      } catch (Exception e) {
         return false;
      }
      return true;
   }

   /**
    * This method attempts to open an image to be used as a background in the
    * chroma function.
    * 
    * @return whether opening the image worked or not
    */

   private boolean getChroma() {
      JFileChooser fc = new JFileChooser("images");
      fc.showOpenDialog(null);
      File f = fc.getSelectedFile();
      try {
         moon = new ImageIcon(f.getAbsolutePath());
      } catch (Exception e) {
         return false;
      }
      return true;
   }

   /**
    * This method attempts to open an image to be worked on.
    * 
    * @return whether opening the image worked or not
    */

   public boolean openImage() {
      int w = img.getWidth();
      int h = img.getHeight();
      JFileChooser fc = new JFileChooser("images");
      fc.showOpenDialog(null);
      File f = fc.getSelectedFile();
      try {
         i = new ImageIcon(f.getAbsolutePath());
      } catch (Exception e) {
         return false;
      }
      buf.drawImage(i.getImage(), 0, 0, w, h, null);
      return true;
   }

   /**
    * This method moves the color-picker up one pixel.
    */

   public void up() {
      y = Math.max(0, y - 1);
   }

   /**
    * This method moves the color-picker down one pixel.
    */

   public void down() {
      y = Math.min(getHeight() - 1, y + 1);
   }

   /**
    * This method moves the color-picker one pixel to the left.
    */

   public void left() {
      x = Math.max(0, x - 1);
   }

   /**
    * This method moves the color-picker one pixel to the right.
    */

   public void right() {
      x = Math.min(getWidth() - 1, x + 1);
   }

   /**
    * This method draws the color-picker on the screen.
    * 
    * @param g the Graphics component to draw the color-picker on
    */

   public void paintComponent(Graphics g) {
      g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
      if (clicked) {
         g.setColor(Color.black);
         g.drawLine(x - 5, y - 1, x + 5, y - 1);
         g.drawLine(x - 5, y + 1, x + 5, y + 1);
         g.drawLine(x - 1, y - 5, x - 1, y + 5);
         g.drawLine(x + 1, y - 5, x + 1, y + 5);
         g.setColor(Color.yellow);
         g.drawLine(x - 5, y, x - 1, y);
         g.drawLine(x + 1, y, x + 5, y);
         g.drawLine(x, y - 5, x, y - 1);
         g.drawLine(x, y + 1, x, y + 5);
      }

   }
}