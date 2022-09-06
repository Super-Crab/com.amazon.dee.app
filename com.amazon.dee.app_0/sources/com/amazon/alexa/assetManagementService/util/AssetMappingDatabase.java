package com.amazon.alexa.assetManagementService.util;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.amazon.alexa.assetManagementService.dao.AssetMappingDao;
import com.amazon.alexa.assetManagementService.entity.AssetMapping;
import com.amazon.alexa.assetManagementService.model.constants.LocalDBQuery;
@Database(entities = {AssetMapping.class}, version = 2)
/* loaded from: classes6.dex */
public abstract class AssetMappingDatabase extends RoomDatabase {
    private static volatile AssetMappingDatabase instance;

    public static AssetMappingDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AssetMappingDatabase.class) {
                instance = (AssetMappingDatabase) Room.databaseBuilder(context.getApplicationContext(), AssetMappingDatabase.class, LocalDBQuery.ASSET_MAPPING_DB).fallbackToDestructiveMigration().build();
            }
        }
        return instance;
    }

    public abstract AssetMappingDao assetMappingDao();
}
