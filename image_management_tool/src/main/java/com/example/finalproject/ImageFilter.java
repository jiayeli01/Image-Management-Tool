package com.example.finalproject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;

public interface ImageFilter {

    Image filter(Image image, int imageNo, ImageView imageOne,ImageView imageTwo,ImageView imageThree);
}
