package com.amazon.alexa.fitness.sdk.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.amazon.alexa.fitness.util.ConstantsKt;
@Entity(tableName = ConstantsKt.MEASUREMENT_SAMPLES_TABLE_NAME)
/* loaded from: classes8.dex */
public class MeasurementSampleEntity {
    @NonNull
    public String aggregation;
    @NonNull
    public String algorithmId;
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public Long id;
    @NonNull
    public Long receivedAt;
    @NonNull
    public String sessionId;
    @NonNull
    public String type;
    @NonNull
    public String units;
    @NonNull
    public String value;

    public MeasurementSampleEntity(@NonNull String str, @NonNull String str2, @NonNull Long l, @NonNull String str3, @NonNull String str4, @NonNull String str5, @NonNull String str6) {
        this.sessionId = str;
        this.algorithmId = str2;
        this.receivedAt = l;
        this.type = str4;
        this.value = str3;
        this.units = str5;
        this.aggregation = str6;
    }
}
