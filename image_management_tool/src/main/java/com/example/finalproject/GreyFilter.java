package com.example.finalproject;

import javafx.scene.image.*;
import javafx.scene.paint.Color;

public class GreyFilter implements ImageFilter{

    @Override
    public Image filter(Image image, int imageNo, ImageView imageOne, ImageView imageTwo, ImageView imageThree) {

        PixelReader pixelReader = image.getPixelReader();
        // Create WritableImage
        WritableImage wImage = new WritableImage(
                (int) image.getWidth(),
                (int) image.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = pixelReader.getColor(x, y);
                color = color.grayscale();
                pixelWriter.setColor(x, y, color);
            }
        }
        //choose image to add filter
        if (imageNo == 0) {
            imageOne.setImage(wImage);
        } else if (imageNo == 1) {
            imageTwo.setImage(wImage);
        } else if (imageNo == 2) {
            imageThree.setImage(wImage);
        }
        return wImage;
    }
}
