package com.example.finalproject;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
public class PngConverter implements ImageConverter{
    @Override
    public void convert(File file) {
        try {
            // Create a file chooser to select a location to save the converted image
            BufferedImage bufferedImage = ImageIO.read(file);
            // show the file chooser and wait for the user to select the output location
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Output Location");
            fileChooser.setInitialFileName(file.getName().split("\\.")[0] + ".png");
            File location = fileChooser.showSaveDialog(null);
            ImageIO.write(bufferedImage, "png", location);
        } catch (IOException ex) {
            // handle the IOException
            System.err.println("An error occurred while trying to convert the image: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
