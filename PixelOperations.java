
/**
 * This class contains methods for manipulating pixels in images.
 */

import java.awt.Color;
import java.awt.image.BufferedImage;

public class PixelOperations {

   /**
    * This method returns a two-dimensional matrix containing the RGB values of
    * every pixel in the image.
    * 
    * @param img the image to get color for
    * @return matrix with all color values
    */

   public Color[][] getArray(BufferedImage img) {
      Color[][] arr;
      int numcols = img.getWidth();
      int numrows = img.getHeight();
      arr = new Color[numrows][numcols];
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            int rgb = img.getRGB(k, j);
            arr[j][k] = new Color(rgb);
         }
      }
      return arr;
   }

   /**
    * This method updates the image with new color values.
    * 
    * @param img image to modify
    * @param arr matrix of updated color values
    */

   public void setImage(BufferedImage img, Color[][] arr) {
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            Color tmp = arr[j][k];
            int rgb = tmp.getRGB();
            img.setRGB(k, j, rgb);
         }
      }
   }

   /**
    * This method sets the red value of every pixel in the image to zero.
    * 
    * @param arr matrix of colors to modify
    */

   public void zeroRed(Color[][] arr) {
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            Color tmp = arr[j][k];
            arr[j][k] = new Color(0, tmp.getGreen(), tmp.getBlue());
         }
      }
   }

   /**
    * This method sets the green value of every pixel in the image to zero.
    * 
    * @param arr matrix of colors to modify
    */

   public void zeroGreen(Color[][] arr) {
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            Color tmp = arr[j][k];
            arr[j][k] = new Color(tmp.getRed(), 0, tmp.getBlue());
         }
      }
   }

   /**
    * This method sets the blue value of every pixel in the image to zero.
    * 
    * @param arr matrix of colors to modify
    */

   public void zeroBlue(Color[][] arr) {
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            Color tmp = arr[j][k];
            arr[j][k] = new Color(tmp.getRed(), tmp.getGreen(), 0);
         }
      }
   }

   /**
    * This method sets every pixel's RGB values to 255 - (current value).
    * 
    * @param arr matrix of colors to modify
    */

   public void negate(Color[][] arr) {
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            Color tmp = arr[j][k];
            arr[j][k] = new Color(255 - tmp.getRed(), 255 - tmp.getGreen(), 255 - tmp.getBlue());
         }
      }
   }

   /**
    * This method takes the average of the RGB values for a pixel, then sets that
    * value as the red, green, and blue values.
    * 
    * @param arr matrix of colors to modify
    */

   public void gray(Color[][] arr) {
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            Color tmp = arr[j][k];
            int avg = (int) ((tmp.getRed() + tmp.getGreen() + tmp.getBlue()) / 3);
            arr[j][k] = new Color(avg, avg, avg);
         }
      }
   }

   /**
    * This method uses Microsoft's recommended formula to add a sepia tint.
    * 
    * @param arr matrix of colors to modify
    */

   public void sepia(Color[][] arr) {
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            Color tmp = arr[j][k];
            int newRed = (int) (0.393 * tmp.getRed() + 0.769 * tmp.getGreen() + 0.189 * tmp.getBlue());
            int newGreen = (int) (0.349 * tmp.getRed() + 0.686 * tmp.getGreen() + 0.168 * tmp.getBlue());
            int newBlue = (int) (0.272 * tmp.getRed() + 0.534 * tmp.getGreen() + 0.131 * tmp.getBlue());
            if (newRed > 255)
               newRed = 255;
            if (newGreen > 255)
               newGreen = 255;
            if (newBlue > 255)
               newBlue = 255;
            arr[j][k] = new Color(newRed, newGreen, newBlue);
         }
      }
   }

   /**
    * This method produces a blur effect by averaging the RGB values of surrounding
    * pixels.
    * 
    * @param arr matrix of colors to modify
    */

   public void blur(Color[][] arr) {
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            int newRed = 0;
            int newGreen = 0;
            int newBlue = 0;
            int counter = 0;
            if (j != 0) {
               newRed += arr[j - 1][k].getRed();
               newGreen += arr[j - 1][k].getGreen();
               newBlue += arr[j - 1][k].getBlue();
               counter++;
            }
            if (j != arr.length - 1) {
               newRed += arr[j + 1][k].getRed();
               newGreen += arr[j + 1][k].getGreen();
               newBlue += arr[j + 1][k].getBlue();
               counter++;
            }
            if (k != 0) {
               newRed += arr[j][k - 1].getRed();
               newGreen += arr[j][k - 1].getGreen();
               newBlue += arr[j][k - 1].getBlue();
               counter++;
            }
            if (k != arr[0].length - 1) {
               newRed += arr[j][k + 1].getRed();
               newGreen += arr[j][k + 1].getGreen();
               newBlue += arr[j][k + 1].getBlue();
               counter++;
            }
            newRed /= counter;
            newGreen /= counter;
            newBlue /= counter;
            arr[j][k] = new Color(newRed, newGreen, newBlue);
         }
      }
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
    * 
    * @param arr matrix of colors to modify
    */

   public void posterize(Color[][] arr) {
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            Color tmp = arr[j][k];
            int newRed;
            int newGreen;
            int newBlue;
            if (tmp.getRed() >= 0 && tmp.getRed() <= 63) {
               newRed = 32;
            }
            if (tmp.getRed() >= 64 && tmp.getRed() <= 127) {
               newRed = 96;
            }
            if (tmp.getRed() >= 128 && tmp.getRed() <= 191) {
               newRed = 160;
            } else {
               newRed = 224;
            }
            if (tmp.getGreen() >= 0 && tmp.getGreen() <= 63) {
               newGreen = 32;
            }
            if (tmp.getGreen() >= 64 && tmp.getGreen() <= 127) {
               newGreen = 96;
            }
            if (tmp.getGreen() >= 128 && tmp.getGreen() <= 191) {
               newGreen = 160;
            } else {
               newGreen = 224;
            }
            if (tmp.getBlue() >= 0 && tmp.getBlue() <= 63) {
               newBlue = 32;
            }
            if (tmp.getBlue() >= 64 && tmp.getBlue() <= 127) {
               newBlue = 96;
            }
            if (tmp.getBlue() >= 128 && tmp.getBlue() <= 191) {
               newBlue = 160;
            } else {
               newBlue = 224;
            }
            arr[j][k] = new Color(newRed, newGreen, newBlue);
         }
      }
   }

   /**
    * This method grays out all pixels except those that are predominantly red,
    * i.e. their red value is greater than or equal to the sum of their green and
    * blue values.
    * 
    * @param arr matrix of colors to modify
    */

   public void splash_red(Color[][] arr) {
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            Color tmp = arr[j][k];
            int avg = (int) ((tmp.getRed() + tmp.getGreen() + tmp.getBlue()) / 3);
            if (tmp.getRed() <= (tmp.getGreen() + tmp.getBlue())) {
               arr[j][k] = new Color(avg, avg, avg);
            }
         }
      }
   }

   /**
    * This method grays out all pixels except those that are predominantly green,
    * i.e. their green value is greater than or equal to the sum of their red and
    * blue values.
    * 
    * @param arr matrix of colors to modify
    */

   public void splash_green(Color[][] arr) {
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            Color tmp = arr[j][k];
            int avg = (int) ((tmp.getRed() + tmp.getGreen() + tmp.getBlue()) / 3);
            if (tmp.getGreen() <= (tmp.getRed() + tmp.getBlue())) {
               arr[j][k] = new Color(avg, avg, avg);
            }
         }
      }
   }

   /**
    * This method grays out all pixels except those that are predominantly blue,
    * i.e. their blue value is greater than or equal to the sum of their red and
    * green values.
    * 
    * @param arr matrix of colors to modify
    */

   public void splash_blue(Color[][] arr) {
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            Color tmp = arr[j][k];
            int avg = (int) ((tmp.getRed() + tmp.getGreen() + tmp.getBlue()) / 3);
            if (tmp.getBlue() <= (tmp.getGreen() + tmp.getRed())) {
               arr[j][k] = new Color(avg, avg, avg);
            }
         }
      }
   }

   /**
    * This method makes the right half of the image a mirror of the left.
    * 
    * @param arr matrix of colors to modify
    */

   public void mirrorLR(Color[][] arr) {
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            arr[j][arr[0].length - k - 1] = arr[j][k];
         }
      }
   }

   /**
    * This method makes the left half of the image a mirror of the right.
    * 
    * @param arr matrix of colors to modify
    */

   public void mirrorRL(Color[][] arr) {
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            arr[j][k] = arr[j][arr[0].length - k - 1];
         }
      }
   }

   /**
    * This method makes the bottom half of the image a mirror of the top.
    * 
    * @param arr matrix of colors to modify
    */

   public void mirrorUD(Color[][] arr) {
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            arr[arr.length - j - 1][k] = arr[j][k];
         }
      }
   }

   /**
    * This method makes the top half of the image a mirror of the bottom.
    * 
    * @param arr matrix of colors to modify
    */

   public void mirrorDU(Color[][] arr) {
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            arr[j][k] = arr[arr.length - j - 1][k];
         }
      }
   }

   /**
    * This method flips the left and right halves of the image.
    * 
    * @param arr matrix of colors to modify
    */

   public void flipLR(Color[][] arr) {
      Color[][] arr2 = new Color[arr.length][arr[0].length];
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            arr2[j][arr[0].length - k - 1] = arr[j][k];
         }
      }
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            arr[j][k] = arr2[j][k];
         }
      }
   }

   /**
    * This method flips the top and bottom halves of the image.
    * 
    * @param arr matrix of colors to modify
    */

   public void flipUD(Color[][] arr) {
      Color[][] arr2 = new Color[arr.length][arr[0].length];
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            arr2[arr.length - j - 1][k] = arr[j][k];
         }
      }
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            arr[j][k] = arr2[j][k];
         }
      }
   }

   /**
    * This method pixelates the image by creating 10x10 blocks that are all the
    * same color.
    * 
    * @param arr matrix of colors to modify
    */

   public void pixelate(Color[][] arr) {
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            arr[j][k] = arr[j - (j % 10)][k - (k % 10)];
         }
      }
   }

   /**
    * This method adds a red "sunset"-like tint by decreasing the green and blue
    * values of every pixel.
    * 
    * @param arr matrix of colors to modify
    */

   public void sunsetize(Color[][] arr) {
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            arr[j][k] = new Color(arr[j][k].getRed(), (int) (arr[j][k].getGreen() * 0.8),
                  (int) (arr[j][k].getBlue() * 0.8));
         }
      }
   }

   /**
    * This method detects red-eye coloration and removes it.
    * 
    * @param arr matrix of colors to modify
    */

   public void redeye(Color[][] arr) {
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            if (k >= 600 && k <= 1050 && j >= 400 && j <= 500
                  && arr[j][k].getRed() > (arr[j][k].getGreen() + arr[j][k].getBlue())) { // hardcoded lol
               arr[j][k] = new Color(0, arr[j][k].getGreen(), arr[j][k].getBlue());
            }
         }
      }
   }

   /**
    * This method attempts to find edges based on the distances between RGB values
    * in 3-dimensional space.
    * 
    * @param arr matrix of colors to modify
    */

   public void edge(Color[][] arr) {
      int threshold = 20;
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            if (j != 0) {
               if (colorDistance(arr[j][k].getRed(), arr[j][k].getGreen(), arr[j][k].getBlue(), arr[j - 1][k].getRed(),
                     arr[j - 1][k].getGreen(), arr[j - 1][k].getBlue()) > threshold) {
                  arr[j][k] = Color.BLACK;
               } else
                  arr[j][k] = Color.WHITE;
            } else if (j != arr.length - 1) {
               if (colorDistance(arr[j][k].getRed(), arr[j][k].getGreen(), arr[j][k].getBlue(), arr[j + 1][k].getRed(),
                     arr[j + 1][k].getGreen(), arr[j + 1][k].getBlue()) > threshold) {
                  arr[j][k] = Color.BLACK;
               } else
                  arr[j][k] = Color.WHITE;
            } else if (k != 0) {
               if (colorDistance(arr[j][k].getRed(), arr[j][k].getGreen(), arr[j][k].getBlue(), arr[j][k - 1].getRed(),
                     arr[j][k - 1].getGreen(), arr[j][k - 1].getBlue()) > threshold) {
                  arr[j][k] = Color.BLACK;
               } else
                  arr[j][k] = Color.WHITE;
            } else if (k != arr[0].length - 1) {
               if (colorDistance(arr[j][k].getRed(), arr[j][k].getGreen(), arr[j][k].getBlue(), arr[j][k + 1].getRed(),
                     arr[j][k + 1].getGreen(), arr[j][k + 1].getBlue()) > threshold) {
                  arr[j][k] = Color.BLACK;
               } else
                  arr[j][k] = Color.WHITE;
            } else
               arr[j][k] = Color.WHITE;
         }
      }
   }

   /**
    * This method calculates the "distance" between RGB values in 3-dimensional
    * space.
    * 
    * @param r1 red value of the first color
    * @param g1 green value of the first color
    * @param b1 blue value of the first color
    * @param r2 red value of the second color
    * @param g2 green value of the second color
    * @param b2 blue value of the second color
    * @return distance between colors
    */

   private double colorDistance(int r1, int g1, int b1, int r2, int g2, int b2) {
      return Math.sqrt(Math.pow((r1 - r2), 2) + Math.pow((g1 - g2), 2) + Math.pow((b1 - b2), 2));
   }

   /**
    * This method encodes a message of the user's choosing within the RGB values of
    * the image.
    * 
    * @param arr  original image
    * @param arr2 message to be encoded
    */

   public void encode(Color[][] arr, Color[][] arr2) {
      int threshold = 20;
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            int rand = (int) (Math.random() * 3);
            if (rand == 0) {
               if (arr[j][k].getRed() % 2 == 1 && arr2[j][k].getRed() > threshold && arr2[j][k].getGreen() > threshold
                     && arr2[j][k].getBlue() > threshold) {
                  arr[j][k] = new Color(arr[j][k].getRed() - 1, arr[j][k].getGreen(), arr[j][k].getBlue());
               }
               if (arr[j][k].getRed() % 2 == 0 && arr2[j][k].getRed() <= threshold && arr2[j][k].getGreen() <= threshold
                     && arr2[j][k].getBlue() <= threshold) {
                  arr[j][k] = new Color(arr[j][k].getRed() + 1, arr[j][k].getGreen(), arr[j][k].getBlue());
               }
               if (arr[j][k].getGreen() % 2 == 1) {
                  arr[j][k] = new Color(arr[j][k].getRed(), arr[j][k].getGreen() - 1, arr[j][k].getBlue());
               }
               if (arr[j][k].getBlue() % 2 == 1) {
                  arr[j][k] = new Color(arr[j][k].getRed(), arr[j][k].getGreen(), arr[j][k].getBlue() - 1);
               }
            } else if (rand == 1) {
               if (arr[j][k].getGreen() % 2 == 1 && arr2[j][k].getRed() > threshold && arr2[j][k].getGreen() > threshold
                     && arr2[j][k].getBlue() > threshold) {
                  arr[j][k] = new Color(arr[j][k].getRed(), arr[j][k].getGreen() - 1, arr[j][k].getBlue());
               }
               if (arr[j][k].getGreen() % 2 == 0 && arr2[j][k].getRed() <= threshold
                     && arr2[j][k].getGreen() <= threshold && arr2[j][k].getBlue() <= threshold) {
                  arr[j][k] = new Color(arr[j][k].getRed(), arr[j][k].getGreen() + 1, arr[j][k].getBlue());
               }
               if (arr[j][k].getRed() % 2 == 1) {
                  arr[j][k] = new Color(arr[j][k].getRed() - 1, arr[j][k].getGreen(), arr[j][k].getBlue());
               }
               if (arr[j][k].getBlue() % 2 == 1) {
                  arr[j][k] = new Color(arr[j][k].getRed(), arr[j][k].getGreen(), arr[j][k].getBlue() - 1);
               }
            } else {
               if (arr[j][k].getBlue() % 2 == 1 && arr2[j][k].getRed() > threshold && arr2[j][k].getGreen() > threshold
                     && arr2[j][k].getBlue() > threshold) {
                  arr[j][k] = new Color(arr[j][k].getRed(), arr[j][k].getGreen(), arr[j][k].getBlue() - 1);
               }
               if (arr[j][k].getBlue() % 2 == 0 && arr2[j][k].getRed() <= threshold
                     && arr2[j][k].getGreen() <= threshold && arr2[j][k].getBlue() <= threshold) {
                  arr[j][k] = new Color(arr[j][k].getRed(), arr[j][k].getGreen(), arr[j][k].getBlue());
               }
               if (arr[j][k].getGreen() % 2 == 1) {
                  arr[j][k] = new Color(arr[j][k].getRed(), arr[j][k].getGreen() - 1, arr[j][k].getBlue());
               }
               if (arr[j][k].getRed() % 2 == 1) {
                  arr[j][k] = new Color(arr[j][k].getRed() - 1, arr[j][k].getGreen(), arr[j][k].getBlue());
               }
            }
         }
      }
   }

   /**
    * This method reveals an encoded message.
    * 
    * @param arr matrix of colors to modify
    */

   public void decode(Color[][] arr) {
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            if (arr[j][k].getRed() % 2 == 1 || arr[j][k].getGreen() % 2 == 1 || arr[j][k].getBlue() % 2 == 1) {
               arr[j][k] = Color.BLACK;
            } else
               arr[j][k] = Color.WHITE;
         }
      }
   }

   /**
    * This method replaces a region of similar color in one image with part of
    * another image.
    * 
    * @param arr  image to be modified
    * @param arr2 image to be substituted in
    */

   public void chroma(Color[][] arr, Color[][] arr2) {
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            if (arr[j][k].getBlue() > (0.75 * (arr[j][k].getRed() + arr[j][k].getGreen()))) {
               arr[j][k] = arr2[j][k];
            }
         }
      }
   }

   /**
    * This method rotates the color 120 degrees clockwise on the color wheel.
    * 
    * @param arr matrix of colors to modify
    */

   public void gbr(Color[][] arr) {
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            arr[j][k] = new Color(arr[j][k].getBlue(), arr[j][k].getRed(), arr[j][k].getGreen());
         }
      }
   }

   /**
    * This method rotates the color 240 degrees clockwise on the color wheel.
    * 
    * @param arr matrix of colors to modify
    */

   public void brg(Color[][] arr) {
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            arr[j][k] = new Color(arr[j][k].getGreen(), arr[j][k].getBlue(), arr[j][k].getRed());
         }
      }
   }

   /**
    * This method adjusts all red values by a specified percentage.
    * 
    * @param arr matrix of colors to modify
    * @param x   percentage to adjust by
    */

   public void adjustRed(Color[][] arr, int x) {
      double percent = (double) (x) / 100;
      percent += 1;
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            if (arr[j][k].getRed() * percent > 255) {
               arr[j][k] = new Color(255, arr[j][k].getGreen(), arr[j][k].getBlue());
            } else if (arr[j][k].getRed() * percent < 0) {
               arr[j][k] = new Color(0, arr[j][k].getGreen(), arr[j][k].getBlue());
            } else
               arr[j][k] = new Color((int) (arr[j][k].getRed() * percent), arr[j][k].getGreen(), arr[j][k].getBlue());
         }
      }
   }

   /**
    * This method adjusts all green values by a specified percentage.
    * 
    * @param arr matrix of colors to modify
    * @param x   percentage to adjust by
    */

   public void adjustGreen(Color[][] arr, int x) {
      double percent = (double) (x) / 100;
      percent += 1;
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            if (arr[j][k].getGreen() * percent > 255) {
               arr[j][k] = new Color(arr[j][k].getRed(), 255, arr[j][k].getBlue());
            } else if (arr[j][k].getGreen() * percent < 0) {
               arr[j][k] = new Color(arr[j][k].getRed(), 0, arr[j][k].getBlue());
            } else
               arr[j][k] = new Color(arr[j][k].getRed(), (int) (arr[j][k].getGreen() * percent), arr[j][k].getBlue());
         }
      }
   }

   /**
    * This method adjusts all blue values by a specified percentage.
    * 
    * @param arr matrix of colors to modify
    * @param x   percentage to adjust by
    */

   public void adjustBlue(Color[][] arr, int x) {
      double percent = (double) (x) / 100;
      percent += 1;
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            if (arr[j][k].getBlue() * percent > 255) {
               arr[j][k] = new Color(arr[j][k].getRed(), arr[j][k].getGreen(), 255);
            } else if (arr[j][k].getBlue() * percent < 0) {
               arr[j][k] = new Color(arr[j][k].getRed(), arr[j][k].getGreen(), 0);
            } else
               arr[j][k] = new Color(arr[j][k].getRed(), arr[j][k].getGreen(), (int) (arr[j][k].getBlue() * percent));
         }
      }
   }

   /**
    * This method adjusts all color values by a specified percentage.
    * 
    * @param arr matrix of colors to modify
    * @param x   percentage to adjust by
    */

   public void adjustAll(Color[][] arr, int x) {
      double percent = (double) (x) / 100;
      percent += 1;
      for (int j = 0; j < arr.length; j++) {
         for (int k = 0; k < arr[0].length; k++) {
            if (arr[j][k].getRed() * percent > 255) {
               arr[j][k] = new Color(255, arr[j][k].getGreen(), arr[j][k].getBlue());
            } else if (arr[j][k].getRed() * percent < 0) {
               arr[j][k] = new Color(0, arr[j][k].getGreen(), arr[j][k].getBlue());
            } else
               arr[j][k] = new Color((int) (arr[j][k].getRed() * percent), arr[j][k].getGreen(), arr[j][k].getBlue());
            if (arr[j][k].getGreen() * percent > 255) {
               arr[j][k] = new Color(arr[j][k].getRed(), 255, arr[j][k].getBlue());
            } else if (arr[j][k].getGreen() * percent < 0) {
               arr[j][k] = new Color(arr[j][k].getRed(), 0, arr[j][k].getBlue());
            } else
               arr[j][k] = new Color(arr[j][k].getRed(), (int) (arr[j][k].getGreen() * percent), arr[j][k].getBlue());
            if (arr[j][k].getBlue() * percent > 255) {
               arr[j][k] = new Color(arr[j][k].getRed(), arr[j][k].getGreen(), 255);
            } else if (arr[j][k].getBlue() * percent < 0) {
               arr[j][k] = new Color(arr[j][k].getRed(), arr[j][k].getGreen(), 0);
            } else
               arr[j][k] = new Color(arr[j][k].getRed(), arr[j][k].getGreen(), (int) (arr[j][k].getBlue() * percent));
         }
      }
   }
}
