package com.amazon.alexa.featureservice.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.amazon.alexa.featureservice.database.entity.Feature;
import java.util.List;
@Dao
/* loaded from: classes7.dex */
public interface FeatureDao {
    @Delete
    void delete(Feature feature);

    @Query("DELETE FROM Feature")
    void deleteAll();

    @Query("SELECT * FROM FEATURE WHERE featureName = :feature")
    Feature get(String str);

    @Query("SELECT * FROM Feature")
    List<Feature> getAll();

    @Insert(onConflict = 1)
    void insert(Feature feature);
}
