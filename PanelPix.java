
/**
 * This class controls the panel that houses the display, buttons,
 * and scoreboard.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelPix extends JPanel {
   private DisplayPix display;
   private ScoreboardPix scoreboard;

   public PanelPix() {
      setLayout(new BorderLayout());

      scoreboard = new ScoreboardPix();
      add(scoreboard, BorderLayout.NORTH);

      JPanel east = new JPanel();
      east.setLayout(new GridLayout(15, 2));
      JButton zerored = new JButton("Zero Red");
      zerored.addActionListener(new Listener_zeroRed());
      east.add(zerored);
      JButton zerogreen = new JButton("Zero Green");
      zerogreen.addActionListener(new Listener_zeroGreen());
      east.add(zerogreen);
      JButton zeroblue = new JButton("Zero Blue");
      zeroblue.addActionListener(new Listener_zeroBlue());
      east.add(zeroblue);
      JButton negate = new JButton("Negate");
      negate.addActionListener(new Listener_negate());
      east.add(negate);
      JButton gray = new JButton("Grayscale");
      gray.addActionListener(new Listener_grayscale());
      east.add(gray);
      JButton sepia = new JButton("Sepia Tone");
      sepia.addActionListener(new Listener_sepia());
      east.add(sepia);
      JButton blur = new JButton("Blur");
      blur.addActionListener(new Listener_blur());
      east.add(blur);
      JButton posterize = new JButton("Posterize");
      posterize.addActionListener(new Listener_posterize());
      east.add(posterize);
      JButton splash1 = new JButton("Red Splash");
      splash1.addActionListener(new Listener_splash_red());
      east.add(splash1);
      JButton splash2 = new JButton("Green Splash");
      splash2.addActionListener(new Listener_splash_green());
      east.add(splash2);
      JButton splash3 = new JButton("Blue Splash");
      splash3.addActionListener(new Listener_splash_blue());
      east.add(splash3);
      JButton mirrorLR = new JButton("Mirror Left-Right");
      mirrorLR.addActionListener(new Listener_mirrorLR());
      east.add(mirrorLR);
      JButton mirrorRL = new JButton("Mirror Right-Left");
      mirrorRL.addActionListener(new Listener_mirrorRL());
      east.add(mirrorRL);
      JButton mirrorUD = new JButton("Mirror Up-Down");
      mirrorUD.addActionListener(new Listener_mirrorUD());
      east.add(mirrorUD);
      JButton mirrorDU = new JButton("Mirror Down-Up");
      mirrorDU.addActionListener(new Listener_mirrorDU());
      east.add(mirrorDU);
      JButton flipLR = new JButton("Flip Left-Right");
      flipLR.addActionListener(new Listener_flipLR());
      east.add(flipLR);
      JButton flipUD = new JButton("Flip Up-Down");
      flipUD.addActionListener(new Listener_flipUD());
      east.add(flipUD);
      JButton pixelate = new JButton("Pixelate");
      pixelate.addActionListener(new Listener_pixelate());
      east.add(pixelate);
      JButton sunsetize = new JButton("Sunsetize");
      sunsetize.addActionListener(new Listener_sunsetize());
      east.add(sunsetize);
      JButton redeye = new JButton("Remove Red-Eye");
      redeye.addActionListener(new Listener_redeye());
      east.add(redeye);
      JButton detect = new JButton("Edge Detector");
      detect.addActionListener(new Listener_edge());
      east.add(detect);
      JButton encoder = new JButton("Encode");
      encoder.addActionListener(new Listener_encode());
      east.add(encoder);
      JButton decoder = new JButton("Decode");
      decoder.addActionListener(new Listener_decode());
      east.add(decoder);
      JButton chromakey = new JButton("Chromakey");
      chromakey.addActionListener(new Listener_chroma());
      east.add(chromakey);
      JButton gbr = new JButton("GBR");
      gbr.addActionListener(new Listener_gbr());
      east.add(gbr);
      JButton brg = new JButton("BRG");
      brg.addActionListener(new Listener_brg());
      east.add(brg);
      JButton adjustRed = new JButton("Adjust Red Values");
      adjustRed.addActionListener(new Listener_adjustRed());
      east.add(adjustRed);
      JButton adjustGreen = new JButton("Adjust Green Values");
      adjustGreen.addActionListener(new Listener_adjustGreen());
      east.add(adjustGreen);
      JButton adjustBlue = new JButton("Adjust Blue Values");
      adjustBlue.addActionListener(new Listener_adjustBlue());
      east.add(adjustBlue);
      JButton adjustAll = new JButton("Adjust Color Values");
      adjustAll.addActionListener(new Listener_adjustAll());
      east.add(adjustAll);
      add(east, BorderLayout.EAST);

      display = new DisplayPix();
      display.addMouseListener(new Mouse());

      display.addKeyListener(new Key());
      display.setFocusable(true);
      add(display, BorderLayout.CENTER);

      JPanel south = new JPanel();
      south.setLayout(new FlowLayout());
      JButton restore = new JButton("Restore Original Image");
      restore.addActionListener(new Listener_restore());
      south.add(restore);
      JButton openimg = new JButton("Open an Image File");
      openimg.addActionListener(new Listener_openimg());
      south.add(openimg);
      JButton undo = new JButton("Undo");
      undo.addActionListener(new Listener_undo());
      south.add(undo);
      JButton save = new JButton("Save");
      save.addActionListener(new Listener_save());
      south.add(save);
      add(south, BorderLayout.SOUTH);

      scoreboard.setres(display.i.getIconWidth(), display.i.getIconHeight());
   }

   /**
    * Listener for the "Zero Red" button.
    */

   private class Listener_zeroRed implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.zeroRed();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Zero Green" button.
    */

   private class Listener_zeroGreen implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.zeroGreen();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Zero Blue" button.
    */

   private class Listener_zeroBlue implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.zeroBlue();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Negate" button.
    */

   private class Listener_negate implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.negate();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Grayscale" button.
    */

   private class Listener_grayscale implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.gray();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Sepia" button.
    */

   private class Listener_sepia implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.sepia();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Blur" button.
    */

   private class Listener_blur implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.blur();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Posterize" button.
    */

   private class Listener_posterize implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.posterize();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Red Splash" button.
    */

   private class Listener_splash_red implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.splash_red();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Green Splash" button.
    */

   private class Listener_splash_green implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.splash_green();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Blue Splash" button.
    */

   private class Listener_splash_blue implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.splash_blue();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Mirror Left-Right" button.
    */

   private class Listener_mirrorLR implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.mirrorLR();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Mirror Right-Left" button.
    */

   private class Listener_mirrorRL implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.mirrorRL();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Mirror Up-Down" button.
    */

   private class Listener_mirrorUD implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.mirrorUD();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Mirror Down-Up" button.
    */

   private class Listener_mirrorDU implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.mirrorDU();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Flip Left-Right" button.
    */

   private class Listener_flipLR implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.flipLR();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Flip Up-Down" button.
    */

   private class Listener_flipUD implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.flipUD();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Pixelate" button.
    */

   private class Listener_pixelate implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.pixelate();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Sunsetize" button.
    */

   private class Listener_sunsetize implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.sunsetize();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Remove Red-Eye" button.
    */

   private class Listener_redeye implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.redeye();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Edge Detector" button.
    */

   private class Listener_edge implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.edge();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Encode" button.
    */

   private class Listener_encode implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.encode();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Decode" button.
    */

   private class Listener_decode implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.decode();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Chromakey" button.
    */

   private class Listener_chroma implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.chroma();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "GBR" button.
    */

   private class Listener_gbr implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.gbr();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "BRG" button.
    */

   private class Listener_brg implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.brg();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Adjust Red Values" button.
    */

   private class Listener_adjustRed implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.adjustRed();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Adjust Green Values" button.
    */

   private class Listener_adjustGreen implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.adjustGreen();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Adjust Blue Values" button.
    */

   private class Listener_adjustBlue implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.adjustBlue();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Adjust Color Values" button.
    */

   private class Listener_adjustAll implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.adjustAll();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Restore Original Image" button.
    */

   private class Listener_restore implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.resetImage();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Open an Image File" button.
    */

   private class Listener_openimg implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         if (display.openImage()) {
            update(display.getXval(), display.getYval());
         }
      }
   }

   /**
    * Listener for the "Undo" button.
    */

   private class Listener_undo implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.undo();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * Listener for the "Save" button.
    */

   private class Listener_save implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         display.save();
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * This private class handles mouse events.
    */

   private class Mouse extends MouseAdapter {
      public void mouseClicked(MouseEvent e) {
         update(e.getX(), e.getY());
      }
   }

   /**
    * This private class handles key events.
    */

   private class Key extends KeyAdapter {
      public void keyPressed(KeyEvent e) {
         switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
               display.up();
               break;
            case KeyEvent.VK_DOWN:
               display.down();
               break;
            case KeyEvent.VK_LEFT:
               display.left();
               break;
            case KeyEvent.VK_RIGHT:
               display.right();
               break;
         }
         update(display.getXval(), display.getYval());
      }
   }

   /**
    * This method updates the display and scoreboard with information regarding the
    * color-picker. It also updates the resolution information on the scoreboard.
    * 
    * @param x the x-coordinate of the color-picker
    * @param y the y-coordinate of the color-picker
    */

   private void update(int x, int y) {
      int rgb = display.getRGB(x, y);
      display.update(x, y);
      scoreboard.update(display.getCol(), display.getRow(), rgb);
      scoreboard.setres(display.i.getIconWidth(), display.i.getIconHeight());
      display.repaint();
      display.requestFocus();
   }
}
