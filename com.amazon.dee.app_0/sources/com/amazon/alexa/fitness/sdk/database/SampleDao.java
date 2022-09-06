package com.amazon.alexa.fitness.sdk.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
@Dao
/* loaded from: classes8.dex */
public interface SampleDao {
    @Query("DELETE FROM EchoBudSamplesTable where sessionId=:sessionId")
    void deleteAllEchoBudSamples(String str);

    @Query("DELETE FROM LocationSamplesTable where sessionId=:sessionId")
    void deleteAllLocationSamples(String str);

    @Query("DELETE FROM MeasurementSamplesTable where sessionId=:sessionId")
    void deleteAllMeasurementSamples(String str);

    @Query("SELECT * FROM EchoBudSamplesTable where sessionId = :sessionId")
    List<EchoBudSampleEntity> getAllEchoBudSamples(String str);

    @Query("SELECT * FROM LocationSamplesTable where sessionId = :sessionId")
    List<LocationSampleEntity> getAllLocationSamples(String str);

    @Query("SELECT * FROM MeasurementSamplesTable where sessionId = :sessionId")
    List<MeasurementSampleEntity> getAllMeasurementSamples(String str);

    @Query("SELECT * FROM MeasurementSamplesTable  where sessionId = :sessionId AND type = :sampleType")
    List<MeasurementSampleEntity> getAllMeasurementSamples(String str, String str2);

    @Insert
    void insertEchobudSample(List<EchoBudSampleEntity> list);

    @Insert
    void insertLocationSample(List<LocationSampleEntity> list);

    @Insert
    void insertMeasurementSample(List<MeasurementSampleEntity> list);
}
