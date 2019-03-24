package com.example.ratemyplate2;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlateTest {

    @Test
    public void setGetName() {
        Image image = new Image("hello");
        Plate plate = new Plate("Steak", "Medium rare", image);
        plate.setName("Fish");
        Assert.assertEquals("Fish", plate.getName());
    }

    @Test
    public void setGetCaption() {
        Image image = new Image("hello");
        Plate plate = new Plate("Steak", "Medium rare", image);
        plate.setCaption("Welldone");
        Assert.assertEquals("Welldone", plate.getCaption());
    }

    @Test
    public void setGetImage() {
        Image image = new Image("hello");
        Image image1 = new Image("bye");
        Plate plate = new Plate("Steak", "Medium rare", image);
        plate.setImage(image1);
        Assert.assertEquals("bye", plate.getImage().getFileName());
    }


}