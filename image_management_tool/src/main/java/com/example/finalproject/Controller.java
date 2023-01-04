package com.example.finalproject;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.*;
import javafx.stage.FileChooser;
import java.awt.image.BufferedImage;
import java.util.List;
import java.io.*;
import javax.imageio.ImageIO;
import java.io.IOException;


public class Controller {
    @FXML
    ImageView imageOne;
    @FXML
    ImageView imageTwo;
    @FXML
    ImageView imageThree;
    double width;
    double height;
    @FXML
    Label labelOne;
    @FXML
    Label labelTwo;
    @FXML
    Label labelThree;
    @FXML
    Label labelFour;
    @FXML
    Label labelFive;
    @FXML
    Label labelSix;
    Image image;
    List<File> selectedFiles;
    FileChooser fileChooser;



    
    public void uploadImage(ActionEvent e) {
        //file upload function
        FileChooser fileChooser = new FileChooser();
        // Set the "extension filters" to restrict the files that can be selected
        // In this case, only image files (png, jpg, jpeg, gif) can be selected
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        selectedFiles = fileChooser.showOpenMultipleDialog(null);

        if (selectedFiles != null && !selectedFiles.isEmpty()) {
            for (int i = 0; i < selectedFiles.size(); i++) {
                // Load the image from the selected file
                image = new Image(selectedFiles.get(i).toURI().toString());
                height = image.getHeight();
                width = image.getWidth();
                if (i == 0) {
                    imageOne.setImage(image);
                    labelOne.setText("Height: " + height);
                    labelTwo.setText("Width: " + width);
                }else if(i==1) {
                    imageTwo.setImage(image);
                    labelFive.setText("Height: " + height);
                    labelSix.setText("Width: " + width);
                }else{
                    imageThree.setImage(image);
                    labelThree.setText("Height: " + height);
                    labelFour.setText("Width: " + width);
                }
            }
        }
    }


    //exit function
    public void exit() {
        Platform.exit();
    }

    @FXML
    CheckBox png;
    @FXML
    CheckBox jpg;
    @FXML
    CheckBox gif;
    @FXML
    CheckBox Brighter;
    @FXML
    CheckBox Darker;
    @FXML
    CheckBox Grey;
    int imageNo;


    //image select function
    public void chooseImageOne(){
        imageNo = 0;
        imageOne.setEffect(new DropShadow());
    }
    public void chooseImageTwo(){
        imageNo = 1;
        imageTwo.setEffect(new DropShadow());
    }
    public void chooseImageThree(){
        imageNo = 2;
        imageThree.setEffect(new DropShadow());
    }


    //convert image function
    ImageConverter imageConverter;
    public void convertImage(ActionEvent e){
        if (!png.isSelected() && !jpg.isSelected() && !gif.isSelected()){
            noImageAlert.showAndWait();
        } else if (png.isSelected()){
            imageConverter = new PngConverter();
        }else if (jpg.isSelected()){
            imageConverter = new JpegConverter();
        }else if (gif.isSelected()){
            imageConverter = new Gifconverter();
        }
        imageConverter.convert(selectedFiles.get(imageNo));
        saveMessage();
    }


    //filter function
    ImageFilter imageFilter;
    Image newImage;
    public void filteredImage(ActionEvent e){
        image = new Image(selectedFiles.get(imageNo).toURI().toString());
        if (Brighter.isSelected()){
            imageFilter = new BrighterFilter();
        }else if(Darker.isSelected()){
            imageFilter = new DarkerFilter();
        }else if(Grey.isSelected()){
            imageFilter = new GreyFilter();
        }
        newImage = imageFilter.filter(image,imageNo,imageOne,imageTwo,imageThree);
    }


    //save filtered image function
    public void saveImage(ActionEvent actionEvent) throws IOException {
        if(!Brighter.isSelected() && !Darker.isSelected() && !Grey.isSelected()){
            noActionAlert.showAndWait();
        } else{

            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(newImage, null);
            fileChooser = new FileChooser();
            fileChooser.setTitle("Select Save Location");
            File location = fileChooser.showSaveDialog(null);
            try {
                ImageIO.write(bufferedImage, "png", location);
                saveMessage();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    Alert noActionAlert = new Alert(Alert.AlertType.WARNING, "Please choose filter before saving!");
    Alert noImageAlert = new Alert(Alert.AlertType.WARNING, "Please choose format before converting!");

    // save message function
    public void saveMessage() {
        Alert saveImageAlert = new Alert(Alert.AlertType.INFORMATION);
        saveImageAlert.setTitle("Image Saved");
        saveImageAlert.setContentText("Image saved successfully！");
        saveImageAlert.showAndWait();
    }

}