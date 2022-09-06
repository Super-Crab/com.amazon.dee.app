package com.amazon.alexa.fitness.sdk.database;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
@Database(entities = {EchoBudSampleEntity.class, MeasurementSampleEntity.class, LocationSampleEntity.class}, version = 2)
/* loaded from: classes8.dex */
public abstract class FitnessDatabase extends RoomDatabase {
    public static final Migration MIGRATION_1_2 = new Migration(1, 2) { // from class: com.amazon.alexa.fitness.sdk.database.FitnessDatabase.1
        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `LocationSamplesTable` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `sessionId` TEXT NOT NULL, `sensorId` TEXT NOT NULL, `collectedAt` INTEGER NOT NULL, `receivedAt` INTEGER NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `altitude` REAL NOT NULL, `horizontalAccuracy` REAL NOT NULL, `verticalAccuracy` REAL NOT NULL, `bearing` REAL NOT NULL, `speed` REAL NOT NULL)");
        }
    };

    public abstract SampleDao sampleDao();
}
