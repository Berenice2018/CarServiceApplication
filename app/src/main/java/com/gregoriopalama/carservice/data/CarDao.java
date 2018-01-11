package com.gregoriopalama.carservice.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by gregorio on 29/12/2017.
 */

@Dao
public interface CarDao {
    @Insert(onConflict = OnConflictStrategy.FAIL)
    public void insert(Car... cars);

    @Update
    public void update(Car car);

    @Delete
    public void delete(Car car);

    @Query("SELECT * FROM " + Car.TABLE_NAME)
    public LiveData<List<Car>> selectAll();
}
