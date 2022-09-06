package com.amazon.alexa.featureservice.database.roomdb;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.amazon.alexa.featureservice.dao.FeatureDao;
import com.amazon.alexa.featureservice.database.dao.FeatureFlagDao;
import com.amazon.alexa.featureservice.database.entity.Feature;
import com.amazon.alexa.featureservice.util.Converters;
@TypeConverters({Converters.class})
@Database(entities = {Feature.class}, version = 1)
/* loaded from: classes7.dex */
public abstract class FeatureAppDatabase extends RoomDatabase {
    private static final String APP_DATABASE_FILE_NAME = "com.amazon.alexa.featureservice.FeatureServiceV2.db";
    private static volatile FeatureAppDatabase instance;

    public static FeatureAppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (FeatureAppDatabase.class) {
                if (instance == null) {
                    instance = (FeatureAppDatabase) Room.databaseBuilder(context.getApplicationContext(), FeatureAppDatabase.class, APP_DATABASE_FILE_NAME).build();
                }
            }
        }
        return instance;
    }

    public abstract FeatureFlagDao featureDao();

    public abstract FeatureDao userDao();
}
