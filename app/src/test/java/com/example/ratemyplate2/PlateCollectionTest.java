package com.example.ratemyplate2;

import android.app.Application;
import android.content.Context;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlateCollectionTest {

    @Test
    public void addPlate() {
        PlateCollection plates = new PlateCollection();
        Image image = new Image("default");
        Plate plate = new Plate("Default", "default", image);
        plates.addPlate(plate);
        Assert.assertEquals(1, plates.getPlates().size());
    }

    @Test
    public void removePlateByPosition() {
        PlateCollection plates = new PlateCollection();
        Image image = new Image("default");
        Plate plate = new Plate("Default", "default", image);
        plates.addPlate(plate);
        plates.removePlateByPosition(0);
        Assert.assertEquals(0, plates.getPlates().size());
    }

    @Test
    public void setPlates() {
        PlateCollection plates = new PlateCollection();
        PlateCollection plates2 = new PlateCollection();
        Image image = new Image("default");
        Plate plate = new Plate("Default", "default", image);
        plates.addPlate(plate);
        plates2.setPlates(plates.getPlates());
        Assert.assertEquals(1, plates.getPlates().size());
    }

}