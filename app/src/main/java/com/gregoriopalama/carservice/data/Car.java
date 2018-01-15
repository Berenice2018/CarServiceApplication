package com.gregoriopalama.carservice.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


/**
 * Created by gregorio on 29/12/2017.
 */

@Entity(tableName = Car.TABLE_NAME)
public class Car {
    public static final String TABLE_NAME = "Car";

    public static final String COLUMN_PLATE = "plate";

    @PrimaryKey
    @NonNull
    private String plate;

    private String name;

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
