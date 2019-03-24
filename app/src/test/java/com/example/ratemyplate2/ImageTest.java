package com.example.ratemyplate2;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImageTest {

    @Test
    public void getFileName() {
        Image image = new Image("hello");
        Assert.assertEquals("hello", image.getFileName());
    }

    @Test
    public void setFileName() {
        Image image = new Image("hello");
        image.setFileName(
                "bye"
        );
        Assert.assertEquals("bye", image.getFileName());
    }
}