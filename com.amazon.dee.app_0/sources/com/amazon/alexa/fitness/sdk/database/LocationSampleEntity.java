package com.amazon.alexa.fitness.sdk.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.amazon.alexa.fitness.util.ConstantsKt;
@Entity(tableName = ConstantsKt.LOCATION_SAMPLES_TABLE_NAME)
/* loaded from: classes8.dex */
public class LocationSampleEntity {
    @NonNull
    public Double altitude;
    @NonNull
    public Long collectedAt;
    @NonNull
    public Float heading;
    @NonNull
    public Float horizontalAccuracy;
    @PrimaryKey(autoGenerate = true)
    public Long id;
    @NonNull
    public Double latitude;
    @NonNull
    public Double longitude;
    @NonNull
    public Long receivedAt;
    @NonNull
    public String sessionId;
    @NonNull
    public String sourceId;
    @NonNull
    public Float speed;
    @NonNull
    public Float verticalAccuracy;

    public LocationSampleEntity(@NonNull String str, @NonNull String str2, @NonNull Long l, @NonNull Long l2, @NonNull Double d, @NonNull Double d2, @NonNull Double d3, @NonNull Float f, @NonNull Float f2, @NonNull Float f3, @NonNull Float f4) {
        this.sessionId = str;
        this.sourceId = str2;
        this.receivedAt = l2;
        this.latitude = d;
        this.longitude = d2;
        this.altitude = d3;
        this.horizontalAccuracy = f;
        this.verticalAccuracy = f2;
        this.heading = f3;
        this.speed = f4;
        this.collectedAt = l;
    }
}
