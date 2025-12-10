/*
  ImageApp: 
 */

import java.awt.Color;

public class ImageApp
{

  private static Picture singlePicture;

  public static Picture getPicture(){
    return singlePicture;
  }

  public static Picture rotate(int degree, Pixel[][] pixels, Picture origImage){
    /* this a reference needed before every 90 degree rotation 
    must store new pixels otherwise you will inderectly modify the original picture */
    Picture refPicture = new Picture(pixels.length, pixels[0].length);
    Pixel[][] refPixels = refPicture.getPixels2D();
    for (int i = 0; i< pixels.length; i++){
      for (int j = 0; j< pixels[i].length; j++){
        refPixels[i][j].setColor(pixels[i][j].getColor());
      }
    }
    //this is our rotation matrix for 90 degree rotation
    int[][] rotationalArr = {{0, -1}, {1, 0}};
    Matrix rotationalMatrix = new Matrix(rotationalArr);

    int degreesremaining = degree;
    while (degreesremaining >= 90){
      // change image dimensions to fit when flipped
      int srcH = refPixels.length;     
      int srcW = refPixels[0].length;   
      int destW = srcH;                 
      int destH = srcW;                  
      

      origImage = new Picture(destH, destW);
      pixels = origImage.getPixels2D();

      //iterate over each pixel and apply rotation
      for (int i = 0; i< srcH; i++){
        for (int j = 0; j< srcW; j++){
          int[] tempVector = {j, i};
          Vector vect1 = new Vector(tempVector);
          Vector rotated = Matrix.matrixMultiply(vect1, rotationalMatrix);

          //these are routed x and y that have negative y values that must be shifted down
          int newX = rotated.get(0);
          int newY = rotated.get(1);
          // shift new coords
          int newYTranslated = newY + (destH - 1);
          int newXTranslated = newX-1;
          //if the translated value falls in the bounds set the color of this pixel to the original unmoved pixel
          if (newXTranslated >= 0 && newXTranslated < destW && newYTranslated >= 0 && newYTranslated < destH){
            pixels[newYTranslated][newXTranslated].setColor(refPixels[i][j].getColor());
          }
        }
      }
      //reset reference to be rotated again
      refPicture = new Picture(pixels.length, pixels[0].length);
      refPixels = refPicture.getPixels2D();
      for (int i = 0; i< pixels.length; i++){
        for (int j = 0; j< pixels[i].length; j++){
          refPixels[i][j].setColor(pixels[i][j].getColor());
        }
      }
      degreesremaining -= 90;
    }
    return origImage;
  }

  // this function inserts a small image into a larger one at position (x,y)
  public static void insert(int x, int y, Pixel[][] pixels){
    Picture smallPic = new Picture("lib2/balloon.png");
    Pixel[][] smallPixels = smallPic.getPixels2D();
    Pixel[][] mainPixels = pixels;
    // iterate through each pixel of the small image
    for (int i = 0; i < smallPixels.length; i++){
      for (int j = 0; j < smallPixels[i].length; j++){
        // check bounds
        if (y+i < mainPixels.length && x+j < mainPixels[0].length && !smallPixels[i][j].getColor().equals(Color.WHITE)){
          mainPixels[y+i][x+j].setColor(smallPixels[i][j].getColor());
        }
      }
    }
  }

  public static void recolor(Pixel[][] recoloredPixels){
    for (Pixel[] recoloredPixel1 : recoloredPixels) {
      for (Pixel recoloredPixel : recoloredPixel1) {
        // get RBG values of pixel
        int red = recoloredPixel.getRed();
        int green = recoloredPixel.getGreen();
        int blue = recoloredPixel.getBlue();
        // rearrage the values for the pixel's RGB
        recoloredPixel.setColor(new Color(green, blue, red));
      }
    }
  }

  public static void negative(Pixel[][] negPixels){
    // iterate through each pixel
    for (Pixel[] negPixel1 : negPixels) {
      for (Pixel negPixel : negPixel1) {
        // get RBG values of pixel
        int red = negPixel.getRed();
        int green = negPixel.getGreen();
        int blue = negPixel.getBlue();
        // change the RBG colors to their negative color
        negPixel.setColor(new Color(255 - red, 255 - green, 255 - blue));
      }
    }
  }

  public static void grayscale(Pixel[][] grayscalePixels){
    // iterate through each pixel
    for (Pixel[] grayscalePixel1 : grayscalePixels) {
      for (Pixel grayscalePixel : grayscalePixel1) {
        // get RBG values of pixel
        int red = grayscalePixel.getRed();
        int green = grayscalePixel.getGreen();
        int blue = grayscalePixel.getBlue();
        int avg = (red + green + blue) / 3;
        // change the RBG colors to their negative color
        grayscalePixel.setColor(new Color(avg, avg, avg));
      }
    }
  }

  // used for ui to update the display through button clicks
  public static void updateImageAtRuntime(Picture newPic){
    singlePicture = newPic;
    singlePicture.explore();
  }
  public static void main(String[] args)
  {

    // use any file from the lib folder
    String pictureFile = "lib/beach.jpg";

    // Get an image, get 2d array of pixels, show a color of a pixel, and display the image
    ImageApp.singlePicture = new Picture(pictureFile);
    Pixel[][] origPixels = ImageApp.singlePicture.getPixels2D();
    System.out.println(origPixels[0][0].getColor());
    ImageApp.singlePicture.explore();

   /*  All calls to our image manipulation methods are made inside of PictureExplorer.java 
      Specifically they are made in createControlPanel() at line 429
      these are called when buttons are pressed */



    // for testing  2D algorithms
    int[][] test1 = { { 1, 2, 3, 4 },
        { 5, 6, 7, 8 },
        { 9, 10, 11, 12 },
        { 13, 14, 15, 16 } };
    int[][] test2 = new int[4][4];


  }
}
