package com.amazon.alexa.fitness.sdk.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.amazon.alexa.fitness.util.ConstantsKt;
@Entity(tableName = ConstantsKt.ECHOBUD_SAMPLES_TABLE_NAME)
/* loaded from: classes8.dex */
public class EchoBudSampleEntity {
    @NonNull
    public String activity;
    @NonNull
    public Integer cadence;
    @NonNull
    public Long collectedAt;
    @PrimaryKey(autoGenerate = true)
    public Long id;
    @NonNull
    public Long receivedAt;
    @NonNull
    public String sensorId;
    @NonNull
    public String sessionId;
    @NonNull
    public Integer steps;

    public EchoBudSampleEntity(@NonNull String str, @NonNull String str2, @NonNull Long l, @NonNull Long l2, @NonNull String str3, @NonNull Integer num, @NonNull Integer num2) {
        this.sessionId = str;
        this.sensorId = str2;
        this.receivedAt = l2;
        this.activity = str3;
        this.steps = num;
        this.cadence = num2;
        this.collectedAt = l;
    }
}
