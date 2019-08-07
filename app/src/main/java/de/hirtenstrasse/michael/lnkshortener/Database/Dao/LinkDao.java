package de.hirtenstrasse.michael.lnkshortener.Database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import de.hirtenstrasse.michael.lnkshortener.Models.Link;

@Dao
public interface LinkDao {

    @Insert
    void insert(Link link);

    @Query("DELETE FROM link_table")
    void deleteAll();

    @Query("SELECT * from link_table ORDER BY created DESC")
    LiveData<List<Link>> getAllLinks();
}