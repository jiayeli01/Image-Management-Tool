package com.example.finalproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * 002922952_Yunjia Sun
 * front-end: CSS
 * back-end: logic of [filter & filter save & alert] functions
 * 002923509_Jiaye Li
 * front-end: Layout
 * back-end: logic of [upload files & covert format & covert save] functions
 */

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Image Management Tool 🖼");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}