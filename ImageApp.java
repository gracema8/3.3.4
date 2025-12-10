/*
  ImageApp: 
 */

import java.awt.Color;

public class ImageApp
{

  public static Pixel[][] rotate(int degree, Pixel[][] pixels, Pixel[][] originalPixels){
    Pixel[][] newPixels = pixels;
    int[][] rotationalArr = {{0, -1}, {1, 0}};
    Matrix rotationalMatrix = new Matrix(rotationalArr);
    int degreesremaining = degree;
    while (degreesremaining >= 90){
      for (int i = 0; i< pixels.length; i++){
        for (int j = 0; j< pixels[i].length; j++){
          int[] tempVector = {j, i};
          Vector vect1 = new Vector(tempVector);
          Vector rotated = Matrix.matrixMultiply(vect1, rotationalMatrix);
          // width: 639
          //height: 479
          int newX = rotated.get(0);
          int newY = rotated.get(1)+145;
          if (!(newX<0 || newY<0)){newPixels[newY][newX].setColor(originalPixels[i][j].getColor());}
        }
      }
      degreesremaining -= 90;
    }
    return newPixels;
  }

  public static void main(String[] args)
  {

    // use any file from the lib folder
    String pictureFile = "lib2/dog.png";

    // Get an image, get 2d array of pixels, show a color of a pixel, and display the image
    Picture origImg = new Picture(pictureFile);
    Pixel[][] origPixels = origImg.getPixels2D();
    System.out.println(origPixels[0][0].getColor());
    origImg.explore();


    // Image #1 Using the original image and pixels, recolor an image by changing the RGB color of each Pixel
    Picture recoloredImg = new Picture(pictureFile);
    Pixel[][] recoloredPixels = recoloredImg.getPixels2D();
   
      // iterate through each pixel
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
    // display image
    System.out.println(recoloredPixels[0][0].getColor());
    recoloredImg.explore();

   
    // Image #2 Using the original image and pixels, create a photographic negative of the image
    Picture negImg = new Picture(pictureFile);
    Pixel[][] negPixels = negImg.getPixels2D();

      // iterate through each pixel
      for (var negPixel1 : negPixels) {
          for (Pixel negPixel : negPixel1) {
              // get RBG values of pixel
              int red = negPixel.getRed();
              int green = negPixel.getGreen();
              int blue = negPixel.getBlue();
              // change the RBG colors to their negative color
              negPixel.setColor(new Color(255 - red, 255 - green, 255 - blue));
          }
      }
    // display image
    System.out.println(negPixels[0][0].getColor());
    negImg.explore();


    // Image #3 Using the original image and pixels, create a grayscale version of the image
    Picture grayscaleImg = new Picture(pictureFile);
    Pixel[][] grayscalePixels = grayscaleImg.getPixels2D();


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
    // display image
    System.out.println(grayscalePixels[0][0].getColor());
    grayscaleImg.explore();


    // Image #4 Using the original image and pixels, rotate it 180 degrees
    Picture upsidedownImage = new Picture(pictureFile);
    Pixel[][] upsideDownPixels = upsidedownImage.getPixels2D();

    upsideDownPixels = rotate(180,upsideDownPixels, origPixels);
    upsidedownImage.explore();
    /* to be implemented */

    // Image #5 Using the original image and pixels, rotate image 90
    Picture rotateImg = new Picture(pictureFile);
    Pixel[][] rotatePixels = rotateImg.getPixels2D();

    /* to be implemented */
    rotatePixels = rotate(90, rotatePixels, origPixels);
    rotateImg.explore();
    
      // Image #6 Using the original image and pixels, rotate image -90
    Picture rotateImg2 = new Picture(pictureFile);
    Pixel[][] rotatePixels2 = rotateImg2.getPixels2D();

    /* to be implemented */


    // Final Image: Add a small image to a larger one

    /* to be implemented */




    // for testing  2D algorithms
    int[][] test1 = { { 1, 2, 3, 4 },
        { 5, 6, 7, 8 },
        { 9, 10, 11, 12 },
        { 13, 14, 15, 16 } };
    int[][] test2 = new int[4][4];


  }
}
