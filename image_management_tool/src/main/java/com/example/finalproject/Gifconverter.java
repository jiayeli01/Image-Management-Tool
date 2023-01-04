package com.example.finalproject;
import java.io.*;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
public class Gifconverter implements ImageConverter{
    @Override
    public void convert(File file) {
            try {
                // Create a file chooser to select a location to save the converted image
                BufferedImage bufferedImage = ImageIO.read(file);
                // show the file chooser and wait for the user to select the output location
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Select Output Location");
                fileChooser.setInitialFileName(file.getName().split("\\.")[0] + ".gif");
                File location = fileChooser.showSaveDialog(null);
                ImageIO.write(bufferedImage, "gif", location);
            } catch (IOException ex) {
                // handle the IOException
                System.err.println("An error occurred while trying to convert the image: " + ex.getMessage());
                ex.printStackTrace();
            }
    }
}
