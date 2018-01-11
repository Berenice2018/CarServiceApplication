package com.gregoriopalama.carservice.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by gregorio on 29/12/2017.
 */

@Database(entities = {Car.class}, version = 1)
public abstract class CarServiceDatabase extends RoomDatabase {
    public static final String DB_NAME = "CarService.db";
    public static CarServiceDatabase instance;

    public abstract CarDao carDao();

    public static synchronized CarServiceDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    static CarServiceDatabase create(Context context) {
        Builder<CarServiceDatabase> builder =
                Room.databaseBuilder(context.getApplicationContext(),
                    CarServiceDatabase.class,
                    DB_NAME);
        return builder.build();
    }
}
